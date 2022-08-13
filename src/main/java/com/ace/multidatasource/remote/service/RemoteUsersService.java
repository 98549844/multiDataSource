package com.ace.multidatasource.remote.service;


import com.ace.multidatasource.remote.entity.RemoteUsers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (RemoteUsers)表服务接口
 *
 * @author Garlam Au
 * @since 2022-04-05 01:27:31
 */
public interface RemoteUsersService {

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    RemoteUsers queryById(Long userid);

    /**
     * 分页查询
     *
     * @param remoteUsers 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<RemoteUsers> queryByPage(RemoteUsers remoteUsers, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param remoteUsers 实例对象
     * @return 实例对象
     */
    RemoteUsers insert(RemoteUsers remoteUsers);

    /**
     * 修改数据
     *
     * @param remoteUsers 实例对象
     * @return 实例对象
     */
    RemoteUsers update(RemoteUsers remoteUsers);

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    boolean deleteById(Long userid);

}
