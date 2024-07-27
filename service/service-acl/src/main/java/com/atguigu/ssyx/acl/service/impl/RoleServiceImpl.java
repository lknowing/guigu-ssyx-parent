package com.atguigu.ssyx.acl.service.impl;

import com.atguigu.ssyx.acl.mapper.RoleMapper;
import com.atguigu.ssyx.acl.service.RoleService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * FileName: RoleServiceImpl
 * Package: com.atguigu.ssyx.acl.service.impl
 * Description: TODO
 *
 * @Author: lknowing
 * @Create: 2023/07/08-16:34
 * @Version: 1.0
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public IPage<Role> searchRole(Page<Role> pageParam, RoleQueryVo roleQueryVo) {
        return baseMapper.selectPage(pageParam, new LambdaQueryWrapper<Role>()
                .like(StringUtils.hasText(roleQueryVo.getRoleName()), Role::getRoleName, roleQueryVo.getRoleName()));
    }

    @Override
    public Role getRoleById(Integer id) {
        return getById(id);
    }

    @Override
    public Result saveRole(Role role) {
        boolean save = save(role);
        if (save){
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @Override
    public Result updateRole(Role role) {
        boolean updateById = updateById(role);
        if (updateById){
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @Override
    public Result remoevRoleById(Integer roleId) {
        boolean removeById = removeById(roleId);
        if (removeById){
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @Override
    public Result batchRemove(List<Long> ids) {
        boolean removeByIds = removeByIds(ids);
        if (removeByIds){
            return Result.ok(null);
        }
        return Result.fail(null);
    }
}
