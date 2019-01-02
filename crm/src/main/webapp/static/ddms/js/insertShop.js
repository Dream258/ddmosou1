function insertShop(){
	if('' == $('#keyword_form').val()){
		alert("关键词不能为空");
		return;
	}
	if('' == $('#keyword_sku').val()){
		alert("商品链接或ID不能为空");
		return;
	}
	var formDataKey = {
	        "keyword":$('#keyword_form').val(),
	        "sku":$('#keyword_sku').val(),
	        "sort":$('.keyword_type .keyword_btn.active').attr('key'),
	        "sort":"0",
	        "type":"1"
	};
	$.get("goods/search.action",formDataKey,function(url){
		 var rsvalues= url.split("*");
		   $.post(rsvalues[1],function(data){
			   $("#keywordTable tr").remove();
			  if(rsvalues[0]!='null'&&data.goods_search_response.goods_list.length==1){
				  var paiming=rsvalues[0].split("-");
				  var paimingresult=(parseInt(paiming[0])-1)*100+parseInt(paiming[1]);
				  /*var $trt=$("<tr align='center'><th>关键字</th><th>商品信息</th><th>单价</th><th>团购价</th><th>排名</th><th>销量</th></tr>");
				  var $tr1=$("<tr align='center'><th>"+$('#keyword_form').val()+"</th><th><img src='"+data.goods_search_response.goods_list[0].goods_thumbnail_url+"' width='100px'>"+data.goods_search_response.goods_list[0].goods_name+"</th><th>￥"+data.goods_search_response.goods_list[0].min_normal_price/100+"</th><th>￥"+data.goods_search_response.goods_list[0].min_group_price/100+"</th><th><font color='red'>第 "+paimingresult+" 名</font></th><th>"+data.goods_search_response.goods_list[0].sold_quantity+"件</th></tr>");
				  $("#keywordTable").append($trt);
				  $("#keywordTable").append($tr1);
				  $tr1.click(function(){
					window.location.href="http://mobile.yangkeduo.com/goods.html?goods_id="+data.goods_search_response.goods_list[0].goods_id;
				  });*/
				  var insertShop = {
					    "keyword":$('#keyword_form').val(),
				        "unitPrice":data.goods_search_response.goods_list[0].min_normal_price/100,
				        "groupBuyPrice":data.goods_search_response.goods_list[0].min_group_price/100,
				        "salesVolume":data.goods_search_response.goods_list[0].sold_quantity,
				        "rank":paimingresult
				  };
				  $.get("shopController/insertShop",insertShop,function(url){
					  window.location.href="ranking.jsp";
				  });
			  }else{
				  var $tr=$("<tr ><td colspan='7'><font color='red'>对不起，您输入的查询信息有误，请核对后在进行查询。</font></td></tr>");
				   $("#keywordTable").append($tr);
			  }
			  $(".keyword_result").show();
		   });
	});
		  
}
