/*$(function()
{
	//alert("search");
	var pathName=window.document.location.pathname;
	var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	$.ajax({
		url:"user/select",
		type:"post",
		dataType:"json",
		success:function(data)
		{
			$("#user_table tr:gt(0)").remove();//移除之前的数据
			$.each(data,function()
			{
				var user_id = this.user_id;//获取用户id
				var user_telephone = this.user_telephone;//获取用户账号
				var user_type = this.user_type;//获取用户类型，1.普通会员，2.vip会员
				 if(user_type=="1")
				{
					user_type = "普通会员";
				}
				else
				{
					user_type = "vip会员";
				} 
				var user_money = this.user_money;//用户余额
				$("#user_table").append("<tr><td>"+user_id+"</td><td>"+user_telephone+"</td><td>"+user_type+"</td><td>"+user_money+"</td><td><a href='#'>删除</a>&nbsp&nbsp<a href='#'>编辑</a></td></tr>");
			})
		}//success
	})//ajax
})*/
