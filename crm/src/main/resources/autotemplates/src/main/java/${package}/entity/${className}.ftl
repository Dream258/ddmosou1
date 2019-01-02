package ${package}.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * ${info}${autoInfo.beanHeader}
 */
@TableName("${classNameLower}")
public class ${className} extends Model<${className}> {

    @Override
    protected Serializable pkVal(){ return this.id; }

    private static final long serialVersionUID = 1L;

    <#list tableColumns as column>
    /**  ${(column.label)!} */
    <#if column.type == 'java.util.Date'>
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    </#if>
    private ${column.type} ${column.name};
    </#list>

    public ${className}(){
    }

    // -------------------- GET AND SET --------------------
    <#list tableColumns as column>

    public ${column.type} get${column.nameUpper}(){
        return ${column.name};
    }

    public void set${column.nameUpper}(${column.type} ${column.name}){
        this.${column.name} = ${column.name};
    }
    </#list>
}
