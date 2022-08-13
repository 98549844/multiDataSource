package com.ace.multidatasource.remote.service.impl;


import com.ace.multidatasource.remote.entity.RemoteUsers;
import com.ace.multidatasource.remote.dao.RemoteUsersDao;
import com.ace.multidatasource.remote.service.RemoteUsersService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (RemoteUsers)表服务实现类
 *
 * @author Garlam Au
 * @since 2022-04-05 01:27:31
 */
@Service("remoteUsersService")
public class RemoteUsersServiceImpl implements RemoteUsersService {
    @Resource
    private RemoteUsersDao remoteUsersDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    @Override
    public RemoteUsers queryById(Long userid) {
        return this.remoteUsersDao.queryById(userid);
    }

    @Override
    public List<RemoteUsers> queryAll() {
        return this.remoteUsersDao.queryAll();
    }

    /**
     * 分页查询
     *
     * @param remoteUsers 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<RemoteUsers> queryByPage(RemoteUsers remoteUsers, PageRequest pageRequest) {
        long total = this.remoteUsersDao.count(remoteUsers);
        return new PageImpl<>(this.remoteUsersDao.queryAllByLimit(remoteUsers, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param remoteUsers 实例对象
     * @return 实例对象
     */
    @Override
    public RemoteUsers insert(RemoteUsers remoteUsers) {
        this.remoteUsersDao.insert(remoteUsers);
        return remoteUsers;
    }

    /**
     * 修改数据
     *
     * @param remoteUsers 实例对象
     * @return 实例对象
     */
    @Override
    public RemoteUsers update(RemoteUsers remoteUsers) {
        this.remoteUsersDao.update(remoteUsers);
        return this.queryById(remoteUsers.getUserid());
    }

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long userid) {
        return this.remoteUsersDao.deleteById(userid) > 0;
    }
}
