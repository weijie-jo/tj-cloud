package com.ruoyi.system.api;

import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.factory.RemoteUserFallbackFactory;
import com.ruoyi.system.api.model.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户服务
 * 
 * @author ruoyi
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/user/info/{username}")
    public R<LoginUser> getUserInfo(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 注册用户信息
     *
     * @param sysUser 用户信息
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/user/register")
    public R<Boolean> registerUserInfo(@RequestBody SysUser sysUser, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 根据角色集合获取用户列表
     *
     * @return LoginUser
     */
    @GetMapping("/user/getUserByRoles")
    public List<SysUser> getUserByRoles(@RequestParam("roleIdArr") List<Long> roleIdArr);

    /**
     * 根据角色获取用户列表
     *
     * @return LoginUser
     */
    @GetMapping("/user/getUserByRole/{roleId}")
    public List<SysUser> getUserByRole(@PathVariable("roleId") Long roleId);

    /**
     * 根据角色集合获取用户列表
     *
     * @return LoginUser
     */
    @GetMapping("/user/getAllUser")
    public List<SysUser> getAllUser();
}
