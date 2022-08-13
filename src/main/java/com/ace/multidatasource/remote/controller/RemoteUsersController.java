package com.ace.multidatasource.remote.controller;


import com.ace.multidatasource.remote.entity.RemoteUsers;
import com.ace.multidatasource.remote.service.RemoteUsersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (RemoteUsers)表控制层
 *
 * @author Garlam Au
 * @since 2022-04-05 01:27:31
 */
@RestController
@RequestMapping("remoteUsers")
public class RemoteUsersController {
    /**
     * 服务对象
     */
    @Resource
    private RemoteUsersService remoteUsersService;

    /**
     * 分页查询
     *
     * @param remoteUsers 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<RemoteUsers>> queryByPage(RemoteUsers remoteUsers, PageRequest pageRequest) {
        return ResponseEntity.ok(this.remoteUsersService.queryByPage(remoteUsers, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<RemoteUsers> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.remoteUsersService.queryById(id));
    }


    /**
     * @return all users
     */
    @GetMapping("all")
    public ResponseEntity<List<RemoteUsers>> queryById() {
        return ResponseEntity.ok(this.remoteUsersService.queryAll());
    }

    /**
     * 新增数据
     *
     * @param remoteUsers 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<RemoteUsers> add(RemoteUsers remoteUsers) {
        return ResponseEntity.ok(this.remoteUsersService.insert(remoteUsers));
    }

    /**
     * 编辑数据
     *
     * @param remoteUsers 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<RemoteUsers> edit(RemoteUsers remoteUsers) {
        return ResponseEntity.ok(this.remoteUsersService.update(remoteUsers));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.remoteUsersService.deleteById(id));
    }

}

