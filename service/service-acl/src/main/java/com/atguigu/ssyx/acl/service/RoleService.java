package com.atguigu.ssyx.acl.service;

import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * FileName: RoleService
 * Package: com.atguigu.ssyx.acl.service
 * Description: TODO
 *
 * @Author: lknowing
 * @Create: 2023/07/08-16:34
 * @Version: 1.0
 */
public interface RoleService extends IService<Role> {
    IPage<Role> searchRole(Page<Role> pageParam, RoleQueryVo roleQueryVo);

    Role getRoleById(Integer id);

    Result saveRole(Role role);

    Result updateRole(Role role);

    Result remoevRoleById(Integer roleId);

    Result batchRemove(List<Long> ids);
}
