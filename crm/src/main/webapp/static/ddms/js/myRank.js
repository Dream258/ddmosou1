$(function() {
	 $('#keywordTable .item_wrap').html("");
	 $.get("shopController/fandShop",{},function(data){
		 var goodsWrapx = $('#keywordTable .item_wrap');
		 goodsWrapx.html("");
		 var str = "<table class='table' align='center' style='margin:0; padding:0'>";
		 str += "<tr><th style='text-align:center; width:200px;line-height:40px; height:40px; border:1px solid #ccc; border-bottom:0'>关键词</th>";
		 str += "<th style='text-align:center; width:200px;line-height:40px; height:40px;border:1px solid #ccc; border-bottom:0'>单价</th>";
		 str += "<th style='text-align:center; width:200px;line-height:40px; height:40px;border:1px solid #ccc; border-bottom:0'>团购价</th>";
		 str += "<th style='text-align:center; width:200px;line-height:40px; height:40px;border:1px solid #ccc; border-bottom:0'>排名</th>";
		 str += "<th style='text-align:center; width:200px;line-height:40px; height:40px;border:1px solid #ccc; border-bottom:0'>销量</th>";
		 str += "<th style='text-align:center; width:200px;line-height:40px; height:40px;border:1px solid #ccc; border-bottom:0'>操作</th></tr>";
		 str += "</table>";
		 for (var s = 0; s < data.length; s++) {
			var stu = data[s];
			str += "<table class='table' align='center' style='margin:0; padding:0'>";
			str += "<tr style='height:40px; line-height:40px; border:1px solid #ccc'><td style='text-align:center; width:200px; line-height:40px; height:40px'>"+stu.keyword+"</td>";
			str += "<td style='text-align:center; width:200px;line-height:40px; height:40px;border:1px solid #ccc'>"+stu.unitPrice+"</td>";
			str += "<td style='text-align:center; width:200px;line-height:40px; height:40px;border:1px solid #ccc'>"+stu.groupBuyPrice+"</td>";
			str += "<td style='text-align:center; width:200px;line-height:40px; height:40px;border:1px solid #ccc'>"+stu.rank+"</td>";
			str += "<td style='text-align:center; width:200px;line-height:40px; height:40px;border:1px solid #ccc'>"+stu.salesVolume+"</td>";
			str += "<td style='text-align:center; width:200px;line-height:40px; height:40px;border:1px solid #ccc'><a href='shopController/deleteShop?id="+stu.id+"'>删除</a></td><tr>";
			str += "</table>";
			}
		 goodsWrapx.append(str);
	 });
	/*$(".dd_ranking .clearfix .nav_li").each(function(index,item){
		 if($(item).attr("data-key")=="2"){
			
			$.getJSON("watch/watch_get.action",{"watch_type":"1"},function(data){
				if(data==1){
					 $('#keywordTable .item_wrap').html("<center><font color='red'>没有监控商品</font></center>");	
				}else{
					 var formDataKey = {
				                "keyword":data.keywords,
				                "sku":data.goods_id,
				                "sort":data.sort_type
				            };
					 initDataGoods(formDataKey);
				}
			});
		 }
	});*/
	
	$(".dd_ranking .clearfix .nav_li").each(function(index,item){
		$(item).click(function(){
			 if($(this).attr("data-key")=="2"){
				 
				 //alert("关键字监控");
			 }
          if($(this).attr("data-key")=="1"){
				 
        	  //alert("类目监控");
			 }
			if($(this).attr("data-key")=="3"){
				 
				//alert("活动监控"); 
			}
			
			
		});
	
	});
	
	
	$(".add_keywords_sure").click(function() {
		//$(".add_keywords_sure").removeAttr("data-dismiss");
		
		 $(".add_keywords_sure").attr("data-dismiss","modal");
		if ($("#ranking2")[0].checked == true) {
			 $.get("watch/watch_count.action",{"watch_type":"1"},function(count){
				// alert(count+"-----------"+typeof(count));
				 if(count==1){
					 alert("监控数目已达上限");
					 
				 }else{
					 
					 
			
			 var goodsWrapx = $('#keywordTable .item_wrap');
			 goodsWrapx.html("");
			  var formDataKey = {
		                "keyword":$('#keyword_form').val(),
		                "sku":$('#keyword_sku').val(),
		                "sort":$('.keyword_type .keyword_btn.active').attr('key')
		                
		            };
	    		$(".keyword_result").hide();
	    		
	    		$.get("goods/search.action",formDataKey,function(url){
	       		 var hrsvalues= url.split("*");
	       	         
	       		   $.post(hrsvalues[1],function(data){
	       			//   alert(data.error_response.error_code);
	       			//   $("#search_table tr").remove();
	       			
	       			  if(hrsvalues[0]!='null'&&data.goods_search_response.goods_list.length==1){
	       				  //alert('dddddddaaaaa');
	       				  var paimings=hrsvalues[0].split("-");
	       				  var paimingresults=(parseInt(paimings[0])-1)*100+parseInt(paimings[1]);
	       				  var $trt=$("<table class='table'><tr align='center'><th>关键字</th><th>商品信息</th><th>单价</th><th>团购价</th><th>排名</th><th>销量</th></tr><th>操作</th><tr align='center'><th>"+$('#keyword_form').val()+"</th><th><img src='"+data.goods_search_response.goods_list[0].goods_thumbnail_url+"' width='100px'>"+data.goods_search_response.goods_list[0].goods_name+"</th><th>￥"+data.goods_search_response.goods_list[0].min_normal_price/100+"</th><th>￥"+data.goods_search_response.goods_list[0].min_group_price/100+"</th><th><font color='red'>第 "+paimingresults+" 名</font></th><th>"+data.goods_search_response.goods_list[0].sold_quantity+"件</th><th><a class='btn active' href='javascript:cancleWatchGood("+data.goods_search_response.goods_list[0].goods_id+",1)'>取消监控</a></th></tr></table>");
	       				    goodsWrapx.append($trt);
	       				      var watchData={"goods_id":data.goods_search_response.goods_list[0].goods_id,"keywords":$('#keyword_form').val(),"sort_type":$('.keyword_type .keyword_btn.active').attr('key'),"watch_type":"1","goods_pm":paimingresults};
	       		             $.post("watch/watch_add.action",watchData,function(result){
	       		            	 if(result==1){
	       		            		 alert("商品监听成功"); 
	       		            	 }else{
	       		            		 alert("商品监控已经达上线，仅能查看");
	       		            	 }
	       		            	
	       		             });
	       			    	//alert(  $("#search_table")[0].innerHTML);
//	       				  $trt.click(function(){
//	       					  
//	       					window.location.href="http://mobile.yangkeduo.com/goods.html?goods_id="+data.goods_search_response.goods_list[0].goods_id;
//	       					  
//	       				  });
	       				 
	       				  
	       			  }else{
	       				 // alert("else--");
	       				
	       				  var $tr=$("<table class='table'><tr ><td colspan='7'><font color='red'>对不起，您输入的查询信息有误，请核对后在进行查询。</font></td></tr></table>");
	       				  goodsWrapx.append($tr);
	       			  }
	       			  $(".keyword_result").show();
	       			//  $(".result_wrap .keyword_result ").css("display","block");
	       			
	    			
		    		
	       			
	       		   });
	       		  
	       	  });
	    	
	 }
				 
			 });
		}
		if ($("#ranking1")[0].checked == true) {
			var goodsWrapx = $('#rankingTable .item_wrap');
			goodsWrapx.html("");
			 var category3=$('#category3').val();
	           var formData = {
	               opt:category3,
	               sku:$('#category_sku').val()
	              
	           };
	    		
	    		$.get("goods/search_opt.action",formData,function(url){
	       		 var hrsvalues= url.split("*");
	       	         
	       		   $.post(hrsvalues[1],function(data){
	       			//   alert(data.error_response.error_code);
	       			//   $("#search_table tr").remove();
	       			
	       			  if(hrsvalues[0]!='null'&&data.goods_search_response.goods_list.length==1){
	       				  //alert('dddddddaaaaa');
	       				  var paimings=hrsvalues[0].split("-");
	       				  var paimingresults=(parseInt(paimings[0])-1)*100+parseInt(paimings[1]);
	       				  var $trt=$("<table class='table'><tr align='center'><th>栏目名</th><th>商品信息</th><th>单价</th><th>团购价</th><th>排名</th><th>销量</th></tr><tr align='center'><th>"+$('#category3').text()+"</th><th><img src='"+data.goods_search_response.goods_list[0].goods_thumbnail_url+"' width='100px'>"+data.goods_search_response.goods_list[0].goods_name+"</th><th>￥"+data.goods_search_response.goods_list[0].min_normal_price/100+"</th><th>￥"+data.goods_search_response.goods_list[0].min_group_price/100+"</th><th><font color='red'>第 "+paimingresults+" 名</font></th><th>"+data.goods_search_response.goods_list[0].sold_quantity+"件</th></tr></table>");
	       				    goodsWrapx.append($trt);
	       		
	       			    	//alert(  $("#search_table")[0].innerHTML);
//	       				  $trt.click(function(){
//	       					  
//	       					window.location.href="http://mobile.yangkeduo.com/goods.html?goods_id="+data.goods_search_response.goods_list[0].goods_id;
//	       					  
//	       				  });
	       				 
	       				  
	       			  }else{
	       				 // alert("else--");
	       				
	       				  var $tr=$("<table class='table'><tr ><td colspan='7'><font color='red'>对不起，您输入的查询信息有误，请核对后在进行查询。</font></td></tr></table>");
	       				  goodsWrapx.append($tr);
	       			  }
	       			  $(".keyword_result").show();
	       			//  $(".result_wrap .keyword_result ").css("display","block");
	       			
	    			
		    		
	       			
	       		   });
	       		  
	       	  });
	    	
			
		}
		if ($("#ranking3")[0].checked == true) {

			alert("活动排名");
		}

	});

});

function initDataGoods(formDataKey){
	
	 var goodsWrapx = $('#keywordTable .item_wrap');
	 goodsWrapx.html("");
	 
		$(".keyword_result").hide();
		
		$.get("goods/search.action",formDataKey,function(url){
   		 var hrsvalues= url.split("*");
   	         
   		   $.post(hrsvalues[1],function(data){
   			//   alert(data.error_response.error_code);
   			//   $("#search_table tr").remove();
   			
   			  if(hrsvalues[0]!='null'&&data.goods_search_response.goods_list.length==1){
   				  //alert('dddddddaaaaa');
   				  var paimings=hrsvalues[0].split("-");
   				  var paimingresults=(parseInt(paimings[0])-1)*100+parseInt(paimings[1]);
   				  var $trt=$("<table class='table'><tr align='center'><th>关键字</th><th>商品信息</th><th>单价</th><th>团购价</th><th>排名</th><th>销量</th><th>操作</th></tr><tr align='center'><th>"+formDataKey.keyword+"</th><th><img src='"+data.goods_search_response.goods_list[0].goods_thumbnail_url+"' width='100px'>"+data.goods_search_response.goods_list[0].goods_name+"</th><th>￥"+data.goods_search_response.goods_list[0].min_normal_price/100+"</th><th>￥"+data.goods_search_response.goods_list[0].min_group_price/100+"</th><th><font color='red'>第 "+paimingresults+" 名</font></th><th>"+data.goods_search_response.goods_list[0].sold_quantity+"件</th><th><a class='btn active' href='javascript:cancleWatchGood("+data.goods_search_response.goods_list[0].goods_id+",1)' class='btn'>取消监控</a></th></tr></table>");
   				    goodsWrapx.append($trt);
//   				      var watchData={"goods_id":data.goods_search_response.goods_list[0].goods_id,"keywords":$('#keyword_form').val(),"sort_type":$('.keyword_type .keyword_btn.active').attr('key'),"watch_type":"1"};
//   		             $.post("watch/watch_add.action",watchData,function(result){
//   		            	 if(result==1){
//   		            		 alert("商品监听成功"); 
//   		            	 }else{
//   		            		 alert("已经监听过商品");
//   		            	 }
//   		            	
//   		             });
   			    	//alert(  $("#search_table")[0].innerHTML);
//   				  $trt.click(function(){
//   					  
//   					window.location.href="http://mobile.yangkeduo.com/goods.html?goods_id="+data.goods_search_response.goods_list[0].goods_id;
//   					  
//   				  });
   				 
   				  
   			  }else{
   				 // alert("else--");
   				
   				  var $tr=$("<table class='table'><tr ><td colspan='7'><font color='red'>对不起，您输入的查询信息有误，请核对后在进行查询。</font></td></tr></table>");
   				  goodsWrapx.append($tr);
   			  }
   			  $(".keyword_result").show();
   			//  $(".result_wrap .keyword_result ").css("display","block");
   			
			
    		
   			
   		   });
   		  
   	  });
	
	
	
}
function cancleWatchGood(goods_id,watch_type){
	if(confirm("你确定要取消该商品的监控吗？")){
	$.get("watch/watch_del.action",{"goods_id":goods_id,"watch_type":watch_type},function(result){
		if(result==1){
			
			alert("取消成功！！！");
			 $('#keywordTable .item_wrap').html("<center><font color='red'>您还没有添加监控的商品</font></center>");
		}
		
		
	});
	}
	
}


