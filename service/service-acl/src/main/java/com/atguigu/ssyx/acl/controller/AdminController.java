package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.acl.service.AdminService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.acl.Admin;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * FileName: AdminController
 * Package: com.atguigu.ssyx.acl.controller
 * Description: TODO
 *
 * @Author: lknowing
 * @Create: 2023/07/08-17:32
 * @Version: 1.0
 */
@Api(tags = "用户接口")
@RestController
@CrossOrigin
@RequestMapping("/admin/acl/user")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation("删除多个用户")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids) {
        return adminService.batchRemove(ids);
    }
    @ApiOperation("根据id删除用户")
    @DeleteMapping("remove/{adminId}")
    public Result remoevAdminById(@PathVariable Integer adminId) {
        return adminService.remoevAdminById(adminId);
    }

    @PutMapping("update")
    @ApiOperation("更新一个后台用户")
    public Result updateAdmin(@RequestBody Admin admin){
        return adminService.updateAdmin(admin);
    }

    @PostMapping("save")
    @ApiOperation("保存一个新的后台用户")
    public Result saveAdmin(@RequestBody Admin admin){
        return adminService.saveAdmin(admin);
    }

    @GetMapping("{page}/{limit}")
    @ApiOperation("获取后台用户分页列表(带搜索)")
    public Result searchAdmin(@PathVariable Integer page,
                             @PathVariable Integer limit,
                             AdminQueryVo adminQueryVo){
        Page<Admin> pageParm = new Page<>(page,limit);
       IPage<Admin> iPage = adminService.searchAdmin(pageParm,adminQueryVo);
        return Result.ok(iPage);
    }

    @GetMapping("get/{id}")
    @ApiOperation("根据ID获取某个后台用户")
    public Result getAdminById(@PathVariable Integer id){
        Admin admin = adminService.getAdminById(id);
        return Result.ok(admin);
    }

    @GetMapping("toAssign/{adminId}")
    @ApiOperation("获取某个用户的所有角色")
    public Result getRoleByAdmin(@PathVariable Integer adminId){
        List<Role> roleList = adminService.getRoleByAdmin(adminId);
        return Result.ok(roleList);
    }

    @PostMapping("doAssign")
    @ApiOperation("给某个用户分配角色")
    public Result assignRoles(@RequestParam Long adminId,@RequestParam Long[] roleIds){
        return adminService.assignRoles(adminId,roleIds);
    }
}
