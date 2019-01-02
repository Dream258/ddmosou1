package ${package}.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xxx.common.framework.base.BaseDAO;
import ${package}.entity.${className};
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * ${info}${autoInfo.daoHeader}
 */
@Component
public interface ${className}DAO extends BaseMapper<${className}> {

    <#if autoTable.isShow==1 || autoTable.isAllShow==1>
    //${autoInfo.controllerIsShow}
    @Update("update ${autoTable.tableName} set ${autoTable.tableName}.is_show = ${r"#{"}showType${r"}"} where id in (${r"#{"}ids${r"}"})")
    public void isShow(@Param("ids") String ids,@Param("showType") Integer showType);
    </#if>
    <#if autoTable.isStatus==1 || autoTable.isAllStatus==1>
    //${autoInfo.controllerIsStatus}
    @Update("update ${autoTable.tableName} set ${autoTable.tableName}.status = ${r"#{"}status${r"}"} where id in (${r"#{"}ids${r"}"}})")
    public void accredit(@Param("ids") String ids,@Param("status") Integer status);
    </#if>
}
@Component
interface Auto${className}DAO extends BaseDAO<${className}>{

}