$(function () {
    //类名渲染
    var category_ajax = function (primary_id,cat_level,callback) {
        category_render([{name:'loading...',id:''}],cat_level);
        $.post('/ranking/get-cats',{primary_id:primary_id,cat_level:cat_level},function (param) {
            if(param.result*1){
                category_render(param.data,cat_level);
                if(cat_level<=2){
                    callback(param.data[0].cat_id,cat_level*1+1,callback);
                }
            }
        })
    };
    $('.form1 .item_name').eq(0).click(function () {
        category_ajax('',1,category_ajax);
    });
    //渲染select
    var optionFun = function (categoryParent,data) {
        categoryParent.find('option').remove();
        data.forEach(function (item) {
            categoryParent.append($('<option value="'+item.cat_id+'">'+item.name+'</option>'));
        });
    };
    var category_render = function (data,cat_level) {
        if(cat_level*1===1){
            optionFun($('#category1'),data);
        }
        if(cat_level*1===2){
            optionFun($('#category2'),data);
        }
        if(cat_level*1===3){
            optionFun($('#category3'),data);
        }
    };

    //查询loading
    var LoadingFun1 = new LoadingFun($('.spinner1'));
    var category_form = $('.category_form');
    var render_category = function (param) {
        var categoryTable = $('#categoryTable');
        var arrData =[];
        arrData.push(param.data);
        categoryTable.bootstrapTable({
            idField: 'id',//主键
            columns: [{
                field: 'goods_name',
                title: '商品信息',
                formatter: function indexFormatter(value, item, index) {//在表格前加上序号
                    var self = item.self?'self':'';
                    var valTd='<div><div class="td_img '+self+'">' +
                        '<img src="'+item.good_img_url+'"></div>' +
                        '<div class="td_url">' +
                        '<a target="_blank" href="'+item.good_url+'" >'+item.good_title+'</a>' +
                        '</div></div>';
                    return valTd
                }
            },{
                field: 'normal_price',
                title: '单价',
                formatter: function indexFormatter(value, item, index) {//在表格前加上序号
                    var valTd='￥'+value;
                    return valTd
                }
            }, {
                field: 'group_price',
                title: '团购价',
                formatter: function indexFormatter(value, item, index) {//在表格前加上序号
                    var valTd='￥'+value;
                    return valTd
                }
            }, {
                field: 'page_order',
                title: '一级类名排名',
                formatter: function indexFormatter(value, item, index) {//在表格前加上序号
                    var cat_level_1 = item.cats.cat_level_1;
                    return cat_level_1.page*1?'第'+cat_level_1.page+'页,'+'第'+cat_level_1.page_position+'个,'+'第<span class="ran">'+cat_level_1.page_order+'</span>名':'未查询到数据'
                }
            }, {
                field: 'page_order',
                title: '二级类名排名',
                formatter: function indexFormatter(value, item, index) {//在表格前加上序号
                    var cat_level_2 = item.cats.cat_level_2;
                    return cat_level_2.page*1?'第'+cat_level_2.page+'页,'+'第'+cat_level_2.page_position+'个,'+'第<span class="ran">'+cat_level_2.page_order+'</span>名':'未查询到数据'
                }
            }, {
                field: 'page_order',
                title: '三级类名排名',
                formatter: function indexFormatter(value, item, index) {//在表格前加上序号
                    var cat_level_3 = item.cats.cat_level_3;
                    return cat_level_3.page*1?'第'+cat_level_3.page+'页,'+'第'+cat_level_3.page_position+'个,'+'第<span class="ran">'+cat_level_3.page_order+'</span>名':'未查询到数据'
                }
            }, {
                field: 'saler_num',
                title: '销量',
                formatter: function indexFormatter(value, item, index) {//在表格前加上序号
                    return value
                }
            }],
            data: arrData
        });
        categoryTable.find('.self').parents('tr').addClass('active');
        tdStyle(categoryTable);
    };
    //查询成功显示信息
    var parent_result = $('.category_result');
    var query_suc = function(param){
        parent_result.show().find('.result_title').text('');
        render_category(param);
    };
    //查询ajax
    var queryCategoryAjax = function(formData){
        parent_result.show().hide();
        $('#categoryTable').bootstrapTable('destroy');
        LoadingFun1.showLoading();
        //改成ajax
        $.post('/ranking/cat-search',formData,function (param) {
            LoadingFun1.hideLoading();
            if(param.result*1){
                keepData('category3',$('#category_sku').val());
                query_suc(param);
            }else{
                alertError(param.msg);
                parent_result.show().find('.result_title').text(param.msg);
            }
        }).error(function () {
            LoadingFun1.hideLoading();
            alertError('系统错误，请稍后重试');
            parent_result.show().find('.result_title').text('系统错误，请稍后重试');
        })
    };

    //事件
    //类名切换
    $('.category').on('change',function () {
       var id = $(this).val();
       var key = $(this).data('key')*1+1;
       var flag3 = $(this).attr('id') !== 'category3';
        if(flag3){category_ajax(id,key,category_ajax);}
    }).on('focus',function () {
        var flag3 = $(this).attr('id') !== 'category3';
        var optionLen = $(this).find('option').length;
        if(optionLen<2){
            if(flag3){category_ajax('',1,category_ajax);}
        }
    });
    //form内部按钮切换
    $('.item_option').on('click','.keyword_btn',function () {
        var btnParent = $(this).parents('.item_option');
        btnParent.find('.keyword_btn').removeClass('active');
        $(this).addClass('active');
    });
    $('.overall').on('click',function () {
        var btn = $(this).parents('ul').prev('.keyword_btn');
        btn.attr('key',$(this).attr('key'));
    });
    //判断是否填入
    $('.item_option input.form-control').on('blur',function () {
       inputErrorShow(this);
    }).on('focus',function () {
        inputErrorHide(this);
    });

    //查询点击
    $('#category_btn').click(function () {
    	alert("category_btn");
       //if(form_btn(category_form)){//判断是否发起请求
           var category1=$('#category1').val()+',';
           var category2=$('#category2').val()+',';
           var category3=$('#category3').val();
           var formData = {
               opt:1,
               sku:$('#category_sku').val()
              
           };
           
           //$('html,body').animate({'scrollTop':400});
          // queryCategoryAjax(formData);
       //}
           
           $(".category_result").hide();
     	  $.get("goods/search_opt.action",formData,function(url){
     		 var rsvalues= url.split("*");
     	
     		   $.post(rsvalues[1],function(data){
     			//   alert(data.error_response.error_code);
     			   $("#categoryTable tr").remove();
     			  if(rsvalues[0]!='null'&&data.goods_search_response.goods_list.length==1){
     				  var paiming=rsvalues[0].split("-");
     				  var paimingresult=(parseInt(paiming[0])-1)*100+parseInt(paiming[1]);
     				  var $trt=$("<tr align='center'><th>关键字</th><th>商品信息</th><th>单价</th><th>团购价</th><th>排名</th><th>销量</th></tr>");
     				  var $tr1=$("<tr align='center'><th>"+$('#keyword_form').val()+"</th><th><img src='"+data.goods_search_response.goods_list[0].goods_thumbnail_url+"' width='100px'>"+data.goods_search_response.goods_list[0].goods_name+"</th><th>"+data.goods_search_response.goods_list[0].min_normal_price/100+"</th><th>"+data.goods_search_response.goods_list[0].min_group_price/100+"</th><th><font color='red'>"+paimingresult+"</font></th><th>"+data.goods_search_response.goods_list[0].sold_quantity+"</th></tr>");
     				  $("#categoryTable").append($trt);
     				  $("#categoryTable").append($tr1);
     				  
     				 
     				  
     			  }else{
     				
     				  var $tr=$("<tr ><td colspan='7'><font color='red'>对不起，您输入的查询信息有误，请核对后在进行查询。</font></td></tr>");
     				   $("#categoryTable").append($tr);
     			  }
     			  $(".category_result").show();
     			//  $(".result_wrap .keyword_result ").css("display","block");
     			
     			
     		   });
     		  
     	  });
           
           
    });

    $('.close_i').click(function () {
        $(this).next('.form-control').val('');
    });
});