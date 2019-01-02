package com.xxx.model.sys.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.xxx.common.interfaces.ImportExcel;
import com.xxx.common.interfaces.OutputExcel;

import java.io.Serializable;

/**
 * 系统用户实体类
 */
@TableName("sys_user")
public class SysUser extends Model<SysUser> implements Serializable {

	@Override
	protected Serializable pkVal(){ return this.id; }

	private static final long serialVersionUID = 1L;

	/**  ID编号 */
	private Long id;
	/**  用户名 */
	@ImportExcel()
	@OutputExcel(title = "用户名")
	private String username;
	/**  密码 */
	private String password;
	/**  姓名 */
	@ImportExcel(1)
	@OutputExcel(title = "姓名",sort = 1)
	private String name;
	/**  手机号码 */
	@ImportExcel(2)
	@OutputExcel(title = "手机号码",sort = 2)
	private String phone;
	/**  头像 */
	private String image;
	/**  是否禁用 1是 2否 */
	private Integer status;
	/**  级别 */
	private Integer level;
	/**  登录时间 */
	private java.util.Date loginTime;
	/**  登录错误时间 */
	private java.util.Date errorDate;
	/**  一段时间内登录出错次数 */
	private Integer errorTime;
	/**  创建时间 */
	private java.util.Date createTime;
	/**  修改时间 */
	private java.util.Date updateTime;
	/**  创建人 */
	private Long createBy;
	/**  修改人 */
	private Long updateBy;
	/**  部门ID */
	private Long departmentId;

	/**  无参构造函数 */
	public SysUser(){}

	// -------------------- GET AND SET --------------------

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}

	public String getPassword(){ return password; }
	
	public void setPassword(String password){
		this.password = password;
	}

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public String getPhone(){
		return phone;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getImage() { return image; }

	public void setImage(String image) { this.image = image; }

	public Integer getStatus(){
		return status;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getLevel() { return level; }

	public void setLevel(Integer level) { this.level = level; }

	public java.util.Date getLoginTime(){
		return loginTime;
	}
	
	public void setLoginTime(java.util.Date loginTime){
		this.loginTime = loginTime;
	}

	public java.util.Date getErrorDate(){
		return errorDate;
	}
	
	public void setErrorDate(java.util.Date errorDate){
		this.errorDate = errorDate;
	}

	public Integer getErrorTime(){
		return errorTime;
	}
	
	public void setErrorTime(Integer errorTime){
		this.errorTime = errorTime;
	}

	public java.util.Date getCreateTime(){
		return createTime;
	}
	
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	public Long getCreateBy(){
		return createBy;
	}
	
	public void setCreateBy(Long createBy){
		this.createBy = createBy;
	}

	public Long getUpdateBy(){
		return updateBy;
	}
	
	public void setUpdateBy(Long updateBy){
		this.updateBy = updateBy;
	}

	public Long getDepartmentId() { return departmentId; }

	public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }

}
