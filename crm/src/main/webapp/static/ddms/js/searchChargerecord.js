$(function(){   
	
	//alert("searchcharg");
	$.ajax({
		url:"chargerecord/select",
		type:"post",
		dataType:"json",
		success:function(data){
			$("#chargerecord_table tr:gt(0)").remove();//删除之前的数据
			$.each(data,function(){
				var cid = this.id;//
				var user_telephone = this.userTelephone;//账户
				var charge_date = this.chargeDate;//充值日期
				var end_date = this.endDate;//结束日期
				var charge_type = this.chargeType;//1搜索  2 监控
				var charge_money = this.chargeMoney;//充值金额
				 if(charge_type=="1"){//对charge_type 判断，按指定要求修改
					charge_type = "搜索";
				}
				else{
					charge_type = "监控";
				} 
				//拼接信息到table上
				$("#chargerecord_table").append("<tr><td>"+cid+"</td><td>"+user_telephone+"</td><td>"+charge_date+"</td><td>"+end_date+"</td><td>"+charge_type+"</td><td>"+charge_money+"</td><td><a href='#'>删除</a>&nbsp&nbsp<a href='#'>编辑</a></td></tr>");
			})
		}//success
	})//ajax
}) 