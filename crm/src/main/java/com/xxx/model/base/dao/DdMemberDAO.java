package com.xxx.model.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xxx.common.framework.base.BaseDAO;
import com.xxx.model.base.entity.DdMember;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 会员管理持久层
 */
@Component
public interface DdMemberDAO extends BaseMapper<DdMember> {
    //登录
    public DdMember sysLogin(@Param("user_telephone") String user_telephone, @Param("user_password") String user_password);

    // 使用手机号修改会员类别
    public int updateUserType(@Param("user_telephone") String user_telephone, @Param("user_type") Integer user_type);

    // 查询已邀请人数
    public Integer selectPeopleNum(@Param("id") Integer id);

    // 按分页查询下级
    public List<DdMember> selectSubordinate(Page page, Map map);

    // 查询已邀请人充值的人数
    public Integer selectPeopleNumOrders(List<DdMember> spnc);

    //根据手机号修改密码
    public int updateUserPassword(@Param("userPassword") String userPassword, @Param("userTelephone") String userTelephone);

    //判断手机号是否注册
    public DdMember sysLoginTelephone(@Param("user_telephone") String user_telephone);

    // 根据邀请码查询ID
    public Integer selectIDByCode(@Param("user_code") String user_code);

    //注册
    public int addRegister(@Param("user_telephone") String user_telephone, @Param("user_password") String user_password, @Param("user_id") Integer user_id);

    // 修改邀请码
    public Integer updateCode(@Param("user_telephone") String user_telephone,
                              @Param("user_password") String user_password, @Param("user_code") String user_code);
}
@Component
interface AutoDdMemberDAO extends BaseDAO<DdMember>{

}