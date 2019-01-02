<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  部门管理列表</title>
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
                    <li class="layui-this">部门管理列表</li>
                </ul>
            </div>
            <form id="searchForm" class="layui-form" method="post" style="margin-top: 20px;">
                <div class="layui-form-item">
                    <input type ="hidden" name="pageNo" value="${(pager.pageNo)!}">
                    <input type ="hidden" name="pageSize" value="15">
                    <@common.permission per='department:sysDepartment:edit'>
                    <div class="layui-inline">
                        <a href="javascript:layui.add();" class="layui-btn">添加部门&nbsp;<i class="layui-icon" >&#xe61f;</i></a>
                    </div>
                    </@common.permission>
                </div>
            </form>

            <div style="margin-top: 20px;">
                <table class="layui-table">
                    <thead>
                    <tr>
                        <th>名称</th>
                        <th>排序</th>
                        <th>备注</th>
                        <th>状态</th>
                        <@common.permission per='department:sysDepartment:edit'>
                        <th>操作</th>
                        </@common.permission>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- 定义宏 -->
                    <#macro treeArray entityArr>
                        <#if entityArr??>
                            <#list entityArr as ent>
                            <tr data_fid="${(ent.fid)!}" ${(ent.level>2)?string('class="hide"','')}>
                                <td>
                                    <#list 1..ent.level as t>&nbsp;&nbsp;&nbsp;&nbsp;</#list>
                                    <#if ent.sysDepartmentResps??>
                                        <#if ent.level lt 2>
                                            <a href="javascript:" title="关闭" class="tree" data_tree="${(ent.id)!}"><i class="layui-icon" style="color: #999;font-size: 14px;">&#xe625;</i></a>
                                        <#else>
                                            <a href="javascript:" class="tree" title="展开" data_tree="${(ent.id)!}"><i class="layui-icon" style="color: #999;font-size: 14px;">&#xe623;</i></a>
                                        </#if>
                                    </#if>
                                    <a href="javascript:layui.showInfo('${(ent.id)!}')">${(ent.name)!}</a>
                                </td>
                                <td>${(ent.sort)!}</td>
                                <td>${(ent.remarks)!}</td>
                                <td><@common.mdictInfo title='sys_status' value='${ent.status}'/></td>
                                <@common.permission per='sys:sysDepartment:edit'>
                                    <td>
                                        <a href="javascript:;" onclick="layui.edit('${(ent.id)!}')" class="layui-btn layui-btn-xs">编辑</a>
                                        <a href="javascript:;" onclick="layui.accredit('${(ent.id)!}','${(ent.status)!}')" class="layui-btn layui-btn-danger layui-btn-xs">
                                        ${(ent.status?? && ent.status == 2)?string('开启','禁用')}
                                        </a>
                                    </td>
                                </@common.permission>
                            </tr>
                                <#if ent.sysDepartmentResps??><@treeArray entityArr=ent.sysDepartmentResps /></#if>
                            </#list>
                        </#if>
                    </#macro>
                    <!-- 调用宏 -->
                    <@treeArray entityArr=treeList />
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
                    skin: '#1E9FFF',
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
                    layui.save("查看部门管理",id);
                }

                //编辑
                layui.edit = function(id){
                    layui.save("编辑部门管理",id)
                }

                //添加
                layui.add = function(){
                    layui.save("添加部门管理");
                }

                //添加or编辑ifream
                layui.save = function(title,id){
                    var url ='${springMacroRequestContext.contextPath}/sysDepartment/saveFrom';
                    if(id!=null&&id!='undfined'){
                        url += "?id="+id
                    }
                    layer.open({
                        type: 2,
                        title:title,
                        area: ['900px', '480px'],
                        fixed: false,
                        maxmin: true,
                        content: url
                    });
                }

                //开启or禁用
                layui.accredit = function(id,status){
                    var title,b;
                    if(status == 1){
                        title = "您是否确定要开启该部门?";
                        b = ['开启', '取消'];
                    }else{
                        title = "您是否确定要禁用该部门?";
                        b = ['禁用', '取消'];
                    }
                    layer.msg(title, {
                        btn: b,
                        yes:function(){
                            window.location.href="${springMacroRequestContext.contextPath}/sysDepartment/accredit?id="+id+"&status="+status;
                        }
                    });
                }

                //删除
                layui.del = function(id){
                    layer.msg("您是否确定要删除该部门?", {
                        btn: ['删除', '不删除'],
                        yes:function(){
                            window.location.href="${springMacroRequestContext.contextPath}/sysDepartment/delById?id="+id;
                        }
                    });
                }

                //菜单展开关闭图标被点击
                $(".tree").click(function(){
                    var a_tree = $(this);
                    var title = a_tree.attr("title");
                    if(title == '展开'){
                        openTr(a_tree);
                    }else{
                        closeTr(a_tree);
                    }
                });

                //开启子菜单
                function openTr(a){
                    a.attr("title","关闭");
                    a.find("i").html("&#xe625;");
                    $("tr[data_fid='"+a.attr("data_tree")+"']").show();
                }

                //关闭子菜单
                function closeTr(a){
                    a.attr("title","展开");
                    a.find("i").html("&#xe623;");
                    var childs = $("tr[data_fid='"+a.attr("data_tree")+"']");
                    childs.hide();
                    $.each(childs,function(idx,ele){
                        var array = $(ele).find(".tree");
                        if(array.size() > 0){
                            $.each(array,function(i,e){
                                closeTr($(e));
                            });
                        }
                    });
                }
            });
        </script>
    </body>
</html>