<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  订单管理列表</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css" />
        <style>
            .layui-table th{
                text-align: center;
            }
            td{
                text-align: center;
            }
        </style>
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 15px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">订单管理列表</li>
                </ul>
            </div>
            <form id="searchForm" class="layui-form" method="post" style="margin-top: 20px;">
                <div class="layui-form-item">
                    <input type ="hidden" name="pageNo" value="${(pager.pageNo)!}">
                    <input type ="hidden" name="pageSize" value="15">
                    <div class="layui-inline">
                        <input type ="text" name="userTelephone" value="${(pager.userTelephone)!}" placeholder="请输入用户手机号" id="phone" style="width:200px;height:30px;/"/>
                        <input type ="text" name="orderNumber" value="${(pager.orderNumber)!}" placeholder="请输入订单号"  style="width:200px;height:30px;/"/>
                        <input type ="text" name="goodType" value="${(pager.goodType)!}" placeholder="请输入商品类型" id="phone" style="width:200px;height:30px;/"/>

                        <button onclick="layui.submitForm()" class="layui-btn">搜索&nbsp;<i class="layui-icon" >&#xe615;</i></button>
                    </div>
                    <@common.permission per='base:ddOrders:edit'>
                    <div class="layui-inline">
                        <a href="javascript:layui.add();" class="layui-btn">添加&nbsp;<i class="layui-icon" >&#xe61f;</i></a>
                    </div>

                    </@common.permission>
                </div>
            </form>
            <div style="margin-top: 20px;">
                <table class="layui-table">
                    <thead>
                    <tr>
                        <th>序号</th>
                                <th>用户手机</th>
                                <th>支付宝交易号</th>
                                <th>商户订单号</th>
                                <th>商品类型</th>
                                <th>会员时长</th>
                                <th>付款金额</th>
                                <th>创建时间</th>
                        <@common.permission per='base:ddOrders:edit'>
                        <th>操作</th>
                        </@common.permission>
                    </tr>
                    </thead>
                    <tbody>
                    <#list pager.list as entity>
                    <tr>
                        <td>${(entity_index+1)!}</td>
                                <td><a href="javascript:layui.showInfo('${(entity.id)!}')">${(entity.userTelephone)!}</a></td>
                                <td>${(entity.alipayNumber)!}</td>
                                <td>${(entity.orderNumber)!}</td>
                                <td>${(entity.goodType)!}</td>
                                <td>${(entity.vipTime)!}</td>
                                <td>${(entity.orderMoney)!}</td>
                                <td><#if entity.orderCurrentDate?? >${entity.orderCurrentDate?string("yyyy年MM月dd日 HH:mm:ss")}</#if></td>
                        <@common.permission per='base:ddOrders:edit'>
                        <td>
                            <a href="javascript:layui.edit('${(entity.id)!}')" class="layui-btn layui-btn-xs">编辑</a>
                            <a href="javascript:layui.del('${(entity.id)!}')" class="layui-btn layui-btn-danger layui-btn-xs">删除</a>
                        </td>
                        </@common.permission>
                    </tr>
                        </#list>
                    </tbody>
                </table>
                <div id="pager" class="fr"></div>
            </div>
        </div>
        <script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/layui/layui.js"></script>
        <script>
            layui.use(['form','laydate','laypage','layer','upload'],function(){
                var form = layui.form;
                var laypage = layui.laypage;
                var layer = layui.layer;
                var $ = layui.jquery;

                laypage.render({
                    elem: 'pager',
                    count: '${(pager.count)!1}',
                    curr: '${(pager.pageNo)!1}',
                    limit: '15',
                    jump:function(obj,first){
                        if(!first){
                            $("input[name=pageNo]").val(obj.curr);
                            layui.submitForm();
                        }
                    }
                });

                //搜索
                layui.submitForm = function(){
                    $("#searchForm").submit();
                    /**/
                }


                //查看
                layui.showInfo = function(id){
                    layui.save("查看订单",id);
                }

                //编辑
                layui.edit = function(id){
                    layui.save("编辑订单",id)
                }

                //添加
                layui.add = function(){
                    layui.save("添加订单");
                }
                //删除
                layui.del = function(){
                    layui.del("删除订单",id);
                }

                //添加or编辑ifream
                layui.save = function(title,id){
                    var url ='${springMacroRequestContext.contextPath}/ddOrders/saveFrom';
                    if(id!=null&&id!='undfined'){
                        url += "?id="+id
                    }
                    layer.open({
                        type: 2,
                        title:title,
                        area: ['800px', '500px'],
                        fixed: false,
                        maxmin: true,
                        content: url
                    });
                }


                //开启or禁用
                layui.accredit = function(id,status){
                    var title,b;
                    if(status == 1){
                        title = "您是否确定要开启该订单管理！";
                        b = ['开启', '取消'];
                    }else{
                        title = "您是否确定要禁用该订单管理！";
                        b = ['禁用', '取消'];
                    }
                    layer.msg(title, {
                        btn: b,
                        yes:function(){
                            window.location.href="${springMacroRequestContext.contextPath}/ddOrders/accredit?id="+id+"&status="+status;
                        }
                    });
                }

                //确定
                layui.del = function(id){
                    layer.msg("您是否确定要删除该订单！", {
                        btn: ['确定', '取消'],
                        yes:function(){
                            $.ajax({
                                url:"${springMacroRequestContext.contextPath}/ddOrders/delById",
                                type:"post",
                                data:"id="+id,
                                dataType:"json",
                                success:function(d){
                                    layer.closeAll('loading');
                                    if(d.code == 200){
                                        window.top.layer.msg("删除成功！", {icon: 1});
                                        layui.submitForm();
                                    }else{
                                        layer.msg("对不起，访问不成功！错误编码：" + d.code);
                                    }
                                }
                            })
                        }
                    });
                }


            });
        </script>
    </body>
</html>