package com.ace.multidatasource.local.service;

import com.ace.multidatasource.local.entity.ResultObject;
import com.ace.multidatasource.local.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Users)表服务接口
 *
 * @author Garlam Au
 * @since 2022-04-05 00:51:45
 */
public interface UsersService {

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    Users queryById(Long userid);


    ResultObject updateMultiDataSourceByUserId(Long userid, Long remoteUserIs);

    /**
     * 分页查询
     *
     * @param users       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Users> queryByPage(Users users, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    Users insert(Users users);

    /**
     * 修改数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    Users update(Users users);

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    boolean deleteById(Long userid);

}
