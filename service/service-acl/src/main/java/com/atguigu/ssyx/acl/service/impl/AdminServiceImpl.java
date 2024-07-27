package com.atguigu.ssyx.acl.service.impl;

import com.atguigu.ssyx.acl.mapper.AdminMapper;
import com.atguigu.ssyx.acl.service.AdminRoleService;
import com.atguigu.ssyx.acl.service.AdminService;
import com.atguigu.ssyx.acl.service.RoleService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.common.util.MD5;
import com.atguigu.ssyx.model.acl.Admin;
import com.atguigu.ssyx.model.acl.AdminRole;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FileName: AdminServiceImpl
 * Package: com.atguigu.ssyx.acl.service.impl
 * Description: TODO
 *
 * @Author: lknowing
 * @Create: 2023/07/08-17:37
 * @Version: 1.0
 */
@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private RoleService roleService;

    @Override
    public IPage<Admin> searchAdmin(Page<Admin> pageParm, AdminQueryVo adminQueryVo) {
        return baseMapper.selectPage(pageParm, new LambdaQueryWrapper<Admin>()
                .like(StringUtils.hasText(adminQueryVo.getUsername()), Admin::getUsername, adminQueryVo.getUsername())
                .like(StringUtils.hasText(adminQueryVo.getName()), Admin::getName, adminQueryVo.getName()));
    }

    @Override
    public Result saveAdmin(Admin admin) {
        String password = admin.getPassword();
        log.info(admin.getUsername() + "的密码是：" + password);
        admin.setPassword(MD5.encrypt(password));
        boolean save = save(admin);
        if (save) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @Override
    public Result updateAdmin(Admin admin) {
        boolean updateById = updateById(admin);
        if (updateById) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @Override
    public Result batchRemove(List<Long> ids) {
        boolean removeByIds = removeByIds(ids);
        if (removeByIds) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @Override
    public Result remoevAdminById(Integer adminId) {
        boolean removeById = removeById(adminId);
        if (removeById) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @Override
    public Admin getAdminById(Integer id) {
        return getById(id);
    }

    @Override
    public List<Role> getRoleByAdmin(Integer adminId) {
        List<Long> roleIdList = adminRoleService.list(new LambdaQueryWrapper<AdminRole>()
                        .eq(AdminRole::getAdminId, adminId))
                .stream().map(AdminRole::getRoleId)
                .collect(Collectors.toList());
        return roleService.listByIds(roleIdList);
    }

    @Override
    public Result assignRoles(Long adminId, Long[] roleIds) {
        adminRoleService.remove(new LambdaQueryWrapper<AdminRole>()
                .eq(AdminRole::getAdminId, adminId));
        List<AdminRole> adminRoles = new ArrayList<>();
        for (Long roleId : roleIds) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(roleId);
            adminRoles.add(adminRole);
        }
        boolean saveBatch = adminRoleService.saveBatch(adminRoles);
        if (saveBatch) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }


}
