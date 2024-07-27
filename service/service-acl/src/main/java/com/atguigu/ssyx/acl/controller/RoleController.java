package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.acl.service.RoleService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * FileName: RoleController
 * Package: com.atguigu.ssyx.acl.controller
 * Description: TODO
 *
 * @Author: lknowing
 * @Create: 2023/07/08-16:28
 * @Version: 1.0
 */
@RestController
@RequestMapping("/admin/acl/role")
@CrossOrigin
@Api(tags = "角色接口")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @ApiOperation("删除多个角色")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids) {
        return roleService.batchRemove(ids);
    }
    @ApiOperation("根据id删除角色")
    @DeleteMapping("remove/{roleId}")
    public Result remoevRoleById(@PathVariable Integer roleId) {
        return roleService.remoevRoleById(roleId);
    }

    @ApiOperation("修改角色")
    @PutMapping("update")
    public Result updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }

    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result saveRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @ApiOperation("根据id获取某个角色")
    @GetMapping("get/{id}")
    public Result getRoleById(@PathVariable Integer id) {
        Role roleById = roleService.getRoleById(id);
        return Result.ok(roleById);
    }

    @ApiOperation("获取角色分页列表(带搜索)")
    @GetMapping("{page}/{limit}")
    public Result searchRole(@PathVariable Integer page,
                             @PathVariable Integer limit,
                             RoleQueryVo roleQueryVo) {
        Page<Role> pageParam = new Page<>(page, limit);
        IPage<Role> iPage = roleService.searchRole(pageParam, roleQueryVo);
        return Result.ok(iPage);
    }
}
