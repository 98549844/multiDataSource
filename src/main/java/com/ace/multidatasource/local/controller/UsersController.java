package com.ace.multidatasource.local.controller;


import com.ace.multidatasource.local.entity.ResultObject;
import com.ace.multidatasource.local.entity.Users;
import com.ace.multidatasource.local.service.UsersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Users)表控制层
 *
 * @author Garlam Au
 * @since 2022-04-05 00:51:45
 */
@RestController
@RequestMapping("users")
public class UsersController {
    /**
     * 服务对象
     */
    @Resource
    private UsersService usersService;

    /**
     * 分页查询
     *
     * @param users       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Users>> queryByPage(Users users, PageRequest pageRequest) {
        return ResponseEntity.ok(this.usersService.queryByPage(users, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Users> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.usersService.queryById(id));
    }


    @GetMapping("all")
    public ResponseEntity<List<Users>> queryAll() {
        return ResponseEntity.ok(this.usersService.queryAll());
    }

    @GetMapping("{id}/{remoteId}")
    public ResultObject queryById(@PathVariable("id") Long id, @PathVariable("remoteId") Long remoteId) {
        return this.usersService.updateMultiDataSourceByUserId(id, remoteId);
    }


    /**
     * 新增数据
     *
     * @param users 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Users> add(Users users) {
        return ResponseEntity.ok(this.usersService.insert(users));
    }

    /**
     * 编辑数据
     *
     * @param users 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Users> edit(Users users) {
        return ResponseEntity.ok(this.usersService.update(users));
    }


    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.usersService.deleteById(id));
    }

}

