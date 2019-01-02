<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  客户签约列表</title>
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
                    <li class="layui-this">客户签约列表</li>
                </ul>
            </div>
            <form id="searchForm" class="layui-form" method="post" style="margin-top: 20px;">
                <div class="layui-form-item">
                    <input type ="hidden" name="pageNo" value="${(pager.pageNo)!}">
                    <input type ="hidden" name="pageSize" value="15">
                    <div class="layui-inline">
                        <input  type = "text" name="customer" value="${(signing.customer)!}" placeholder="所属客户" class="layui-input" />
                    </div>
                    <div class="layui-inline">
                        <input  type = "text" name="department" value="${(signing.department)!}" placeholder="所属部门" class="layui-input" />
                    </div>
                    <div class="layui-inline">
                        <input  type = "text" name="product" value="${(signing.product)!}" placeholder="所属产品" class="layui-input" />
                    </div>
                    <div class="layui-inline">
                        <input  type = "text" name="number" value="${(signing.number)!}" placeholder="合同编号" class="layui-input" />
                    </div>
                    <div class="layui-inline">
                        <button onclick="layui.submitForm()" class="layui-btn">搜索&nbsp;<i class="layui-icon" >&#xe615;</i></button>
                    </div>
                    <@common.permission per='base:signing:edit'>
                    <div class="layui-inline">
                        <a href="javascript:layui.add();" class="layui-btn">添加&nbsp;<i class="layui-icon" >&#xe61f;</i></a>
                    </div>
                    <div class="layui-inline">
                        <a href="javascript:layui.delete();" class="layui-btn">删除&nbsp;<i class="layui-icon" >&#xe640;</i></a>
                    </div>
                    </@common.permission>
                </div>
            </form>

            <div style="margin-top: 20px;">
                <table class="layui-table">
                    <thead>
                    <tr>
                        <th>序号</th>
                                <th>所属客户</th>
                                <th>所属部门</th>
                                <th>所属产品</th>
                                <th>合同编号</th>
                                <th>兑付状态</th>
                                <th>回访状态</th>
                                <th>合同状态</th>
                                <th>经办人</th>
                        <@common.permission per='base:signing:edit'>
                        <th>操作</th>
                        </@common.permission>
                    </tr>
                    </thead>
                    <tbody>
                    <#list pager.list as entity>
                    <tr>
                        <td>${(entity_index+1)!}</td>
                                <td><a href="javascript:layui.showInfo('${(entity.id)!}')">${(entity.customer)!}</a></td>
                                <td>${(entity.department)!}</td>
                                <td>${(entity.product)!}</td>
                                <td>${(entity.number)!}</td>
                                <td>${(entity.deal)!}</td>
                                <td>${(entity.visit)!}</td>
                                <td>${(entity.contract)!}</td>
                                <td>${(entity.agent)!}</td>
                        <@common.permission per='base:signing:edit'>
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
                }

                //查看
                layui.showInfo = function(id){
                    layui.save("查看客户签约",id);
                }

                //编辑
                layui.edit = function(id){
                    layui.save("编辑客户签约",id)
                }

                //添加
                layui.add = function(){
                    layui.save("添加客户签约");
                }

                //添加or编辑ifream
                layui.save = function(title,id){
                    var url ='${springMacroRequestContext.contextPath}/signing/saveFrom';
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
                        title = "您是否确定要开启该客户签约！";
                        b = ['开启', '取消'];
                    }else{
                        title = "您是否确定要禁用该客户签约！";
                        b = ['禁用', '取消'];
                    }
                    layer.msg(title, {
                        btn: b,
                        yes:function(){
                            window.location.href="${springMacroRequestContext.contextPath}/signing/accredit?id="+id+"&status="+status;
                        }
                    });
                }

                //确定
                layui.del = function(id){
                    layer.msg("您是否确定要删除该客户签约！", {
                        btn: ['确定', '取消'],
                        yes:function(){
                            window.location.href="${springMacroRequestContext.contextPath}/signing/delById?id="+id;
                        }
                    });
                }
            });
        </script>
    </body>
</html>