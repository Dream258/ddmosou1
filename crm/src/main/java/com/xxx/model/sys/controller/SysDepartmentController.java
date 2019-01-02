package com.xxx.model.sys.controller;

import com.xxx.common.bean.ResultData;
import com.xxx.common.framework.base.BaseController;
import com.xxx.common.interfaces.Permission;
import com.xxx.model.sys.entity.SysDepartment;
import com.xxx.model.sys.service.SysDepartmentService;
import com.xxx.model.sys.vo.resp.SysDepartmentResp;
import com.xxx.model.sys.vo.resp.TreeResp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 部门管理视图层
 */
@Controller
@RequestMapping("sysDepartment")
public class SysDepartmentController extends BaseController {

    @Resource
    private SysDepartmentService sysDepartmentService;

    @ModelAttribute
    public SysDepartment get(@RequestParam(required=false) Long id) {
        if (id != null){
            return sysDepartmentService.selectById(id);
        }else{
            return new SysDepartment();
        }
    }

    /** 部门管理列表 */
    @Permission("sys:sysDepartment:view")
    @ResponseBody
    @GetMapping("list")
    public Map<String, Object> list(){
        Map<String, Object> map = new HashMap<>();
        map.put("list",sysDepartmentService.findList());
        return map;
    }

    /** 部门管理列表 */
    @Permission("sys:sysDepartment:view")
    @RequestMapping("treeList")
    public String treeList(Model model){
        List<SysDepartmentResp> treeList = sysDepartmentService.getAllTreeList();
        model.addAttribute("treeList",treeList);
        return "sys/sysDepartment/sysDepartmentPage";
    }

    /** 跳转菜单保存跳转页面 */
    @Permission("sys:sysDepartment:view")
    @GetMapping("saveFrom")
    public String saveFrom(SysDepartment sysDepartment,Model model){
        if(sysDepartment.getFid() != null ){
            if(sysDepartment.getFid().equals(0L)){
                model.addAttribute("fname","主菜单");
            }else{
                SysDepartment fDepartment = sysDepartmentService.selectById(sysDepartment.getFid());
                model.addAttribute("fname",fDepartment!=null?fDepartment.getName():"");
            }
        }
        model.addAttribute("sysDepartment",sysDepartment);
        return "sys/sysDepartment/sysDepartmentSave";
    }

    /** 保存菜单 */
    @Permission("sys:sysDepartment:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(SysDepartment sysDepartment){
        try {
            if(sysDepartment.getId() == null){
                //新增
                sysDepartment.setStatus(1);
                sysDepartmentService.insert(sysDepartment);
            }else{
                //修改
                sysDepartmentService.updateParams(sysDepartment);
            }
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }

    /** 状态 */
    @Permission("sys:sysDepartment:edit")
    @GetMapping("accredit")
    public String accredit(Long id,Integer status){
        if(id != null && status != null){
            sysDepartmentService.accredit(id,status==1?2:1);
        }
        return "redirect:/sysDepartment/treeList";
    }

    /** 获取上级菜单弹窗跳转页面 */
    @GetMapping("getTreeFrom")
    public String getTreeFrom(){
        return "sys/sysDepartment/sysDepartmentTree";
    }

    /** 获取上级菜单弹窗接口 */

    @ResponseBody
    @PostMapping("getTree")
    public ResultData<Object> getTree(){
        try {
            SysDepartment sysDepartment = new SysDepartment();
            sysDepartment.setStatus(1);
            List<SysDepartment> sysDepartments = sysDepartmentService.selectAll(sysDepartment);
            List<SysDepartmentResp> sysDepartmentResps = sysDepartmentService.install(sysDepartments.stream().filter(menu -> menu.getLevel() != 3).collect(Collectors.toList()));
            List<TreeResp> treeResps = new ArrayList<TreeResp>();
            treeResps.add(TreeResp.getTreeDepartmentResp(sysDepartmentResps));
            return success(treeResps);
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }
}