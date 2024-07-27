package com.atguigu.ssyx.acl.service;

import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.acl.Admin;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * FileName: AdminService
 * Package: com.atguigu.ssyx.acl.service
 * Description: TODO
 *
 * @Author: lknowing
 * @Create: 2023/07/08-17:37
 * @Version: 1.0
 */
public interface AdminService extends IService<Admin> {
    IPage<Admin> searchAdmin(Page<Admin> pageParm, AdminQueryVo adminQueryVo);

    Result saveAdmin(Admin admin);

    Result updateAdmin(Admin admin);

    Result batchRemove(List<Long> ids);

    Result remoevAdminById(Integer adminId);

    Admin getAdminById(Integer id);

    List<Role> getRoleByAdmin(Integer adminId);

    Result assignRoles(Long adminId, Long[] roleIds);
}
