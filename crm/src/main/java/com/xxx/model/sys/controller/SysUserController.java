package com.xxx.model.sys.controller;

import com.alibaba.fastjson.JSON;
import com.xxx.common.bean.Pager;
import com.xxx.common.bean.ResultData;
import com.xxx.common.constant.CodeEnum;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.common.util.ImportExcelUtil;
import com.xxx.common.util.MD5Utils;
import com.xxx.common.util.OutputExcelUtil;
import com.xxx.common.util.StringUtil;
import com.xxx.model.sys.entity.SysRole;
import com.xxx.model.sys.entity.SysUser;
import com.xxx.model.sys.service.SysRoleService;
import com.xxx.model.sys.service.SysUserService;
import com.xxx.model.sys.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/4 0004.
 */
@Controller
@RequestMapping("sysUser")
public class SysUserController extends BaseController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleService sysRoleService;

    //列表
    @Permission("sys:sysUser:view")
    @ResponseBody
    @GetMapping("list")
    public Map<String, Object> list(int department){
        Map<String, Object> map = new HashMap<>();
        map.put("list",sysUserService.findListByDepartmentAll(department));
        return map;
    }

    //分页列表
    @Permission("sys:sysUser:view")
    @GetMapping("page")
    public String page(SysUser sysUser, Pager<SysUser> pager, Model model){
        pager.setOrderBy("update_time desc");
        Pager<SysUser> page = sysUserService.selectPage(sysUser, pager);
        model.addAttribute("pager",page);
        model.addAttribute("departId",sysUser.getDepartmentId());
        return "sys/sysUser/sysUserPage";
    }

    //跳转用户添加编辑页面
    @Permission("sys:sysUser:view")
    @GetMapping("saveFrom")
    public String saveFrom(SysUser sysUser,Model model){
        if(sysUser.getId() != null){
            sysUser = sysUserService.selectById(sysUser.getId());
        }
        List<SysRole> roles = sysRoleService.selectAll(new SysRole());
        model.addAttribute("sysUser",sysUser);
        model.addAttribute("roles",roles);
        return "sys/sysUser/sysUserSave";
    }

    //添加编辑操作
    @Permission("sys:sysUser:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(SysUser sysUser, String roleIds){
        try {
            SysUser user = UserUtil.getUser();
            if(user == null){
                return error(CodeEnum.SESSION_TIME_OUT);
            }
            if(StringUtils.isNotBlank(sysUser.getPassword())){
                sysUser.setPassword(MD5Utils.MD5(sysUser.getPassword()));
            }
            sysUserService.save(sysUser, StringUtil.toLongArray(roleIds),user.getId());
            return success();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return error();
        }
    }

    /** 上下架 */
    @Permission("sys:sysUser:edit")
    @GetMapping("accredit")
    public String accredit(Long id,Integer status){
        if(id != null && status != null){
            sysUserService.accredit(id,status==1?2:1);
        }
        return "redirect:/sysUser/page";
    }

    //根据用户id获取角色ID集合
    @ResponseBody
    @PostMapping("getRids")
    public List<Long> getRids(Long userId){
        return sysRoleService.selectByUser(userId);
    }

    //验证用户名
    @ResponseBody
    @GetMapping("validationUname")
    public boolean validationUname(String username,Long userId){
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        SysUser user = sysUserService.selectOne(sysUser);
        if(user == null || ( userId != null && userId.equals(user.getId()))){
            return true;
        }
        return false;
    }

    //验证密码
    @ResponseBody
    @GetMapping("validationPwd")
    public ResultData<Object> validationPwd(String pwd){
        SysUser sysUser = UserUtil.getUser();
        sysUser = sysUserService.selectById(sysUser.getId());
        if(sysUser != null && sysUser.getPassword().equals(MD5Utils.getMD5String(pwd))){
            return success(true);
        }
        return success(false);
    }

    //修改密码
    @ResponseBody
    @PostMapping("updatePwd")
    public ResultData<Object> updatePwd(String newPassword){
        SysUser user = new SysUser();
        user.setId(UserUtil.getUser().getId());
        user.setPassword(MD5Utils.getMD5String(newPassword));
        sysUserService.updateNotNull(user);
        return success();
    }

    //excel导出
    @ResponseBody
    @GetMapping("outputExl")
    public void outputExl(){
        List<SysUser> users = sysUserService.selectAll(new SysUser());
        new OutputExcelUtil("系统用户.xlsx","系统用户",users,SysUser.class);
    }

    //excel导入
    @ResponseBody
    @PostMapping("importExl")
    public ResultData<Object> importExl(MultipartFile file){
        try {
            List<SysUser> list = new ImportExcelUtil(file, 2, SysUser.class).getList();
            if(list == null || list.size() < 1){
                return error(CodeEnum.IMPORT_EXCEL_ERROR);
            }
            return success(JSON.toJSONString(list));
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return error();
        }

    }

    /** 获取上级菜单弹窗跳转页面 */
    @GetMapping("getTreeFrom")
    public String getTreeFrom(){
        return "sys/sysUser/sysDepartmentTree";
    }
}
