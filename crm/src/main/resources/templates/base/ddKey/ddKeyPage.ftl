<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  关键词列表</title>
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
                    <li class="layui-this">关键词列表</li>
                </ul>
            </div>
            <form id="searchForm" class="layui-form" method="post" style="margin-top: 20px;">
                <div class="layui-form-item">
                    <input type ="hidden" name="pageNo" value="${(pager.pageNo)!}">
                    <input type ="hidden" name="pageSize" value="15">
                    <div class="layui-inline">
                        <button onclick="layui.submitForm()" class="layui-btn">搜索&nbsp;<i class="layui-icon" >&#xe615;</i></button>
                    </div>
                    <@common.permission per='base:ddKey:edit'>
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
                <table class="layui-table layui-form">
                    <thead>
                    <tr>
                            <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
                        <th>序号</th>
                                <th>用户ID</th>
                                <th>商品关键词</th>
                                <th>商品ID</th>
                                <th>商品名称</th>
                                <th>商品销量</th>
                                <th>商品图片</th>
                                <th>商品团购价</th>
                                <th>商品单价</th>
                                <th>商品排名</th>
                                <th>商品时间</th>
                                <th>类型</th>
                        <@common.permission per='base:ddKey:edit'>
                        <th>操作</th>
                        </@common.permission>
                    </tr>
                    </thead>
                    <tbody>
                    <#list pager.list as entity>
                    <tr>
                            <td><input type="checkbox" name="" lay-skin="primary"></td>
                        <td>${(entity_index+1)!}</td>
                                <td><a href="javascript:layui.showInfo('${(entity.id)!}')">${(entity.userId)!}</a></td>
                                <td>${(entity.goodsKey)!}</td>
                                <td>${(entity.goodsId)!}</td>
                                <td>${(entity.goodsName)!}</td>
                                <td>${(entity.goodsSold)!}</td>
                                <td>${(entity.goodsThumbnail)!}</td>
                                <td>${(entity.groupPrice)!}</td>
                                <td>${(entity.normalPrice)!}</td>
                                <td>${(entity.goodsTop)!}</td>
                                <td><#if entity.goodsTime?? >${entity.goodsTime?string("yyyy年MM月dd日 HH:mm:ss")}</#if></td>
                                <td>${(entity.type)!}</td>
                        <@common.permission per='base:ddKey:edit'>
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

                form.on('checkbox(allChoose)', function(data){
                    var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
                    child.each(function(index, item){
                        item.checked = data.elem.checked;
                    });
                    form.render('checkbox');
                });

                layui.delete = function(){
                    var ids = '';
                    $('input:checkbox:checked').each(function(){
                        if($(this).val()!='on'){
                            ids += $(this).val()+',';
                        }
                    });
                    ids = ids.substring(0,ids.length-1);
                    layer.msg("您是否确定要删除该关键词！", {
                        btn: ['确定', '取消'],
                        yes:function(){
                            window.location.href="${springMacroRequestContext.contextPath}/ddKey/delByIds?ids="+ids;
                        }
                    });
                }

                //搜索
                layui.submitForm = function(){
                    $("#searchForm").submit();
                }

                //查看
                layui.showInfo = function(id){
                    layui.save("查看关键词",id);
                }

                //编辑
                layui.edit = function(id){
                    layui.save("编辑关键词",id)
                }

                //添加
                layui.add = function(){
                    layui.save("添加关键词");
                }

                //添加or编辑ifream
                layui.save = function(title,id){
                    var url ='${springMacroRequestContext.contextPath}/ddKey/saveFrom';
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
                        title = "您是否确定要开启该关键词！";
                        b = ['开启', '取消'];
                    }else{
                        title = "您是否确定要禁用该关键词！";
                        b = ['禁用', '取消'];
                    }
                    layer.msg(title, {
                        btn: b,
                        yes:function(){
                            window.location.href="${springMacroRequestContext.contextPath}/ddKey/accredit?id="+id+"&status="+status;
                        }
                    });
                }

                //确定
                layui.del = function(id){
                    layer.msg("您是否确定要删除该关键词！", {
                        btn: ['确定', '取消'],
                        yes:function(){
                            window.location.href="${springMacroRequestContext.contextPath}/ddKey/delById?id="+id;
                        }
                    });
                }
            });
        </script>
    </body>
</html>