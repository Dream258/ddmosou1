package com.xxx.model.base.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseService;
import com.xxx.model.base.dao.DdMemberDAO;
import com.xxx.model.base.entity.DdMember;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 会员管理业务逻辑类
 */
@Service
public class DdMemberService extends BaseService<DdMember> {

    @Resource
    private DdMemberDAO ddMemberDAO;

    /**
     * 保存
     */
    @Transactional
    public void save(DdMember ddMember) {
        if (ddMember.getId() == null) {
            /**
             * 生成以ddms+开头的推广码
             */
            String code = "ddms+" + UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
            ddMember.setUserCode(code);
            this.insert(ddMember);
        } else {
            this.updateParams(ddMember);
        }
    }

    /**
     * 会员登录
     */
    @Transactional
    public DdMember sysrLogin(String username, String password){
        DdMember ddMember = ddMemberDAO.sysLogin(username,password);
        return ddMember;
    }

    /**
     * 使用手机号修改会员类别
     */
    public int updateUserType(String userTelephone,Integer userType) {
        return ddMemberDAO.updateUserType(userTelephone, userType);
    }

    /**
     * 通过用户ID查询用户类型
     *
     * @param id
     * @return
     */
    @Transactional
    public Integer selectType(Integer id) {
        DdMember member = this.selectById(id);
        return member.getUserType();
    }

    //查询下级
    public Page<DdMember> selectSubordinate(Page<DdMember> page, Map map) {
        return page.setRecords(ddMemberDAO.selectSubordinate(page,map));
    }

    //查询已邀请人数
    public Integer selectPeopleNum(Integer user_id) {
        return ddMemberDAO.selectPeopleNum(user_id);
    }

    //查询已邀请人充值的人数
    public Integer selectPeopleNumOrders(List<DdMember> spnc) {
        return ddMemberDAO.selectPeopleNumOrders(spnc);
    }

    //使用手机号修改密码
    public int updateUserPassword(String userPassword, String userTelephone) {
        return ddMemberDAO.updateUserPassword(userPassword, userTelephone);
    }

    // 判断手机号是否注册
    public DdMember sysLoginTelephone(String user_telephone) {
        return ddMemberDAO.sysLoginTelephone(user_telephone);
    }

    //根据邀请码查询ID
    public Integer selectIDByCode(String user_code) {
        return ddMemberDAO.selectIDByCode(user_code);
    }

    //注册
    public int addRegister(String user_telephone, String user_password, Integer user_id) {
        return ddMemberDAO.addRegister(user_telephone, user_password,user_id);
    }

    // 修改邀请码
    public Integer updateCode(String user_telephone, String user_password,String user_code) {
        return ddMemberDAO.updateCode(user_telephone, user_password, user_code);
    }

}