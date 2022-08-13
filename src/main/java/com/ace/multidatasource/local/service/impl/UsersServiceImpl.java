package com.ace.multidatasource.local.service.impl;


import com.ace.multidatasource.local.entity.Constant;
import com.ace.multidatasource.local.entity.ResultObject;
import com.ace.multidatasource.local.entity.Users;
import com.ace.multidatasource.local.dao.UsersDao;
import com.ace.multidatasource.local.service.UsersService;
import com.ace.multidatasource.remote.dao.RemoteUsersDao;
import com.ace.multidatasource.remote.entity.RemoteUsers;
import com.ace.multidatasource.util.RandomUtil;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Users)表服务实现类
 *
 * @author Garlam Au
 * @since 2022-04-05 00:51:45
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {
    @Resource
    private UsersDao usersDao;
    @Resource
    private RemoteUsersDao remoteUsersDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    @Override
    public Users queryById(Long userid) {
        return this.usersDao.queryById(userid);
    }

    /**
     * 分页查询
     *
     * @param users       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Users> queryByPage(Users users, PageRequest pageRequest) {
        long total = this.usersDao.count(users);
        return new PageImpl<>(this.usersDao.queryAllByLimit(users, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    @Override
    public Users insert(Users users) {
        this.usersDao.insert(users);
        return users;
    }

    /**
     * 修改数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    @Override
    public Users update(Users users) {
        this.usersDao.update(users);
        return this.queryById(users.getUserId());
    }

    @Override
    @Transactional
    public ResultObject updateMultiDataSourceByUserId(Long userid, Long remoteUserIs) {
        int i = RandomUtil.getRangeInt(1, 5);
        Users users = this.usersDao.queryById(userid);
        users.setVersion(users.getVersion() + i);
        usersDao.update(users);
        RemoteUsers remoteUsers = this.remoteUsersDao.queryById(remoteUserIs);
        remoteUsers.setVersion(remoteUsers.getVersion() + i + 1);
        remoteUsersDao.update(remoteUsers);

        List<String> result = new ArrayList<>();
        result.add(users.getUsername());
        result.add(remoteUsers.getUsername());
        result.add(users.getVersion().toString());
        result.add(remoteUsers.getVersion().toString());

        return new ResultObject(Constant.ZONE, "更新成功, 随机增加version", result);

    }


    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long userid) {
        return this.usersDao.deleteById(userid) > 0;
    }
}
