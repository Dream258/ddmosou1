$(function () {
    //查询loading
    var LoadingFun3 = new LoadingFun($('.spinner3'));
    var activity_form = $('.activity_form');
    var render_activity = function (param) {
        var activityTable = $('#activityTable');
        var arrData =[];
        arrData.push(param.data);
        activityTable.bootstrapTable({
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
                title: '排名',
                formatter: function indexFormatter(value, item, index) {//在表格前加上序号
                    return '第<span class="ran">'+value+'</span>名'
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
        activityTable.find('.self').parents('tr').addClass('active');
        tdStyle(activityTable);
    };
    //查询成功显示信息
    var parent_result = $('.activity_result');
    var query_suc_activity = function(param){
        parent_result.show().find('.result_title').text('');
        render_activity(param);
    };
    //查询ajax
    var queryActivityAjax = function(formDataAct){
        parent_result.show().hide();
        $('#activityTable').bootstrapTable('destroy');
        LoadingFun3.showLoading();
        var flag = true;
        if($('#activity2').is(':visible')){
            flag = true;
        }else{
            flag = false;
        }
        $.post('/ranking/active-search',formDataAct,function (param) {
            LoadingFun3.hideLoading();
            if(param.result*1){
                if(flag){
                    $('#actSku').removeClass('sku').addClass('.keyword_sku');
                    keepData('activity2',$('#actSku').val());
                }else{
                    $('#actSku').addClass('sku').removeClass('.keyword_sku');
                    keepData('actSku');
                }
                query_suc_activity(param);
            }else{
                alertError(param.msg);
                parent_result.show().find('.result_title').text(param.msg);
            }
        }).error(function () {
            LoadingFun3.hideLoading();
            alertError('系统错误，请稍后重试');
            parent_result.show().find('.result_title').text('系统错误，请稍后重试');
        })
    };
    //活动类目渲染
    var actAjax = function(name,callback){
        var dataArr = [{id: '', opt_name: "loading..."}];
        if(name==="首页"){
            selectAct($('#activity1'),dataArr,1);
        }else{
            selectAct($('#activity2'),dataArr);
        }
        if(name!=='loading...'){
            $.post('/ranking/active-cat',{from_name:name},function (param) {
                if(param.result*1){
                    if(name==="首页"){
                        selectAct($('#activity1'),param.data,1);
                        callback(param.data[0].opt_name)
                    }else{
                        selectAct($('#activity2'),param.data);
                    }
                }
            })
        }
    };
    $('.queryActivity').on('click',function(){if($('#activity1 option').length===1){actAjax('首页',actAjax)}});
    //select渲染
    var selectAct=function(parent,data,is_first){
        parent.find('option').remove();
        data.forEach(function (item) {
            var val_act = item.id;
            var name_act = item.opt_name;
            if(is_first){val_act = item.opt_name;}
            parent.append($('<option value="'+val_act+'">'+name_act+'</option>'))
        })
    };

    //类名切换
    $('#activity1').on('change',function () {
        actAjax($(this).val(),actAjax)
    });
    if(window.location.pathname==='/monitor/ranking'){actAjax('首页',actAjax);}
    //活动点击
    $('.activity_btn').on('click',function () {
        if($(this).hasClass('activity2')){
            $('.activity_box').hide();
        }else{
            $('.activity_box').show();
        }
    });

    $('#activity_btn').click(function () {
        if(form_btn(activity_form)){
            var formDataAct = {
                active_type:$('.activity_home').find('.keyword_btn.active').data('key'),
                sku:$('#actSku').val(),
                entrance:$('#activity1').val(),
                tab_id:$('#activity2').val(),
                cat_second:$('#activity2').find('option:selected').text(),
            };
            $('html,body').animate({'scrollTop':400});
            queryActivityAjax(formDataAct);
        }
    });
});