package com.xxx.model.base.controller;

import com.xxx.common.constant.CacheConstant;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.NotLogin;
import com.xxx.common.util.EhcacheUtil;
import com.xxx.common.util.MD5Utils;
import com.xxx.model.sys.entity.SysUser;
import com.xxx.model.sys.service.SysMenuService;
import com.xxx.model.sys.service.SysUserService;
import com.xxx.model.sys.utils.UserUtil;
import com.xxx.model.sys.vo.resp.SysMenuResp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 登录Controller
 */
@NotLogin
@Controller
public class LoginController extends BaseController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysMenuService sysMenuService;

    //get请求跳转login页面
    @GetMapping("")
    public String index(){
        return "redirect:/login";
    }

    @GetMapping("login")
    public String login(){
        return UserUtil.isLogin() ? "redirect:/common/index" : "common/login";
    }

    @PostMapping("login")
    public String login(String userName, String password, Model model, HttpSession session){
        SysUser sysUser = new SysUser();
        sysUser.setUsername(userName);
        sysUser.setPassword(MD5Utils.getMD5String(password));
        sysUser = sysUserService.selectOne(userName,MD5Utils.getMD5String(password));
        if(sysUser!=null){
            if(sysUser.getStatus() == 2){
                model.addAttribute("msg", "status");
                return "common/login";
            }
            sysUserService.userLogin(sysUser.getId());
            session.setAttribute("loginUser", sysUser);
            //缓存登录用户数据
            this.loginCache(sysUser);
            return "redirect:/common/index";
        }else{
            model.addAttribute("userName", userName);
            model.addAttribute("msg", "error");
            return "common/login";
        }
    }

    //注销
    @NotLogin(false)
    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginUser");
        return "redirect:/login";
    }

    //用户登录后缓存菜单与权限数据
    private void loginCache(SysUser sysUser){
        List<SysMenuResp> sysMenuResps = sysMenuService.getTreeByUserId(sysUser.getId());
        List<String> permissions = sysMenuService.selectPermissions(sysUser.getId());
        EhcacheUtil.put(CacheConstant.MENU_CACHE.LOGIN_MENU+sysUser.getId(),sysMenuResps);
        EhcacheUtil.put(CacheConstant.MENU_CACHE.LOGIN_PERMISSION+sysUser.getId(),permissions);
    }
}
