package com.ace.multidatasource.remote.dao;


import com.ace.multidatasource.remote.entity.RemoteUsers;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (RemoteUsers)表数据库访问层
 *
 * @author Garlam Au
 * @since 2022-04-05 01:27:31
 */
public interface RemoteUsersDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    RemoteUsers queryById(Long userid);

    List<RemoteUsers> queryAll();

    /**
     * 查询指定行数据
     *
     * @param remoteUsers 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<RemoteUsers> queryAllByLimit(RemoteUsers remoteUsers, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param remoteUsers 查询条件
     * @return 总行数
     */
    long count(RemoteUsers remoteUsers);

    /**
     * 新增数据
     *
     * @param remoteUsers 实例对象
     * @return 影响行数
     */
    int insert(RemoteUsers remoteUsers);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<RemoteUsers> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<RemoteUsers> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<RemoteUsers> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<RemoteUsers> entities);

    /**
     * 修改数据
     *
     * @param remoteUsers 实例对象
     * @return 影响行数
     */
    int update(RemoteUsers remoteUsers);

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 影响行数
     */
    int deleteById(Long userid);

}

