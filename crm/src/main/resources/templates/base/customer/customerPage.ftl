<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  客户信息列表</title>
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
                    <li class="layui-this">客户信息列表</li>
                </ul>
            </div>
            <form id="searchForm" class="layui-form" method="post" style="margin-top: 20px;">
                <div class="layui-form-item">
                    <input type ="hidden" name="pageNo" value="${(pager.pageNo)!}">
                    <input type ="hidden" name="pageSize" value="15">
                    <div class="layui-inline">
                        <input  type = "text" name="name" value="${(customer.name)!}" placeholder="客户名称" class="layui-input" />
                    </div>
                    <div class="layui-inline">
                        <select id="department" name="department" lay-filter="myselect">
                            <option value="">选择部门</option>
                        </select>
                    </div>
                    <div class="layui-inline">
                        <select id="agent" name="agent">
                            <option value="">经办人</option>
                        </select>
                    </div>
                    <div class="layui-inline">
                        <select name="deal">
                            <option value="">成交状态</option>
                            <@common.mdictOptions title="deal" value="${(customer.deal)!}" />
                        </select>
                    </div>
                    <div class="layui-inline">
                        <select name="cash">
                            <option value="">对付状态</option>
                            <@common.mdictOptions title="cash" value="${(customer.cash)!}" />
                        </select>
                    </div>
                    <div class="layui-inline">
                        <button onclick="layui.submitForm()" class="layui-btn">搜索&nbsp;<i class="layui-icon" >&#xe615;</i></button>
                    </div>
                    <@common.permission per='base:customer:edit'>
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
                        <th>客户名称</th>
                        <th>联系电话</th>
                        <th>所属部门</th>
                        <th>成交状态</th>
                        <th>对付状态</th>
                        <th>审核状态</th>
                        <th>经办人</th>
                        <@common.permission per='base:customer:edit'>
                        <th>操作</th>
                        </@common.permission>
                    </tr>
                    </thead>
                    <tbody>
                    <#list pager.list as entity>
                    <tr>
                        <td>${(entity_index+1)!}</td>
                        <td><a href="javascript:layui.showInfo('${(entity.id)!}')">${(entity.name)!}</a></td>
                        <td>${(entity.phone)!}</td>
                        <td>${(entity.department)!}</td>
                        <td><@common.mdictInfo title='deal' value='${(entity.deal)!}' /></td>
                        <td><@common.mdictInfo title='cash' value='${(entity.cash)!}' /></td>
                        <td><@common.mdictInfo title='state' value='${(entity.state)!}' /></td>
                        <td>${(entity.agent)!}</td>
                        <@common.permission per='base:customer:edit'>
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

                $(document).ready(function(){
                    $.ajax({
                        url:"/sysDepartment/list",
                        type:"get",
                        data:"",
                        dataType:"json",
                        success:function(data){
                            $.each(data.list,function(index,item){
                                var option = $("<option>").val(item.id).text(item.name);
                                $("#department").append(option);
                                form.render('select');
                            });
                        }
                    });
                });

                form.on('select(myselect)', function(data){
                    $.ajax({
                        url:"/sysUser/list",
                        type:"get",
                        data:"&department="+data.value,
                        dataType:"json",
                        success:function(data){
                            $("#agent").html("");
                            var option = $("<option>").val("").text("经办人");
                            $("#agent").append(option);
                            $.each(data.list,function(index,item){
                                option = $("<option>").val(item.id).text(item.name);
                                $("#agent").append(option);
                                form.render('select');
                            });
                        }
                    });
                });

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
                    layui.save("查看客户信息",id);
                }

                //编辑
                layui.edit = function(id){
                    layui.save("编辑客户信息",id)
                }

                //添加
                layui.add = function(){
                    layui.save("添加客户信息");
                }

                //添加or编辑ifream
                layui.save = function(title,id){
                    var url ='${springMacroRequestContext.contextPath}/customer/saveFrom';
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
                        title = "您是否确定要开启该客户信息！";
                        b = ['开启', '取消'];
                    }else{
                        title = "您是否确定要禁用该客户信息！";
                        b = ['禁用', '取消'];
                    }
                    layer.msg(title, {
                        btn: b,
                        yes:function(){
                            window.location.href="${springMacroRequestContext.contextPath}/customer/accredit?id="+id+"&status="+status;
                        }
                    });
                }

                //确定
                layui.del = function(id){
                    layer.msg("您是否确定要删除该客户信息！", {
                        btn: ['确定', '取消'],
                        yes:function(){
                            window.location.href="${springMacroRequestContext.contextPath}/customer/delById?id="+id;
                        }
                    });
                }
            });
        </script>
    </body>
</html>