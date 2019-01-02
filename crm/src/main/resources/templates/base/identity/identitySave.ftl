<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  身份信息</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 10px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">${(identity?? && identity.id??)?string('编辑','添加')}身份信息</li>
                </ul>
            </div>
            <form id="saveForm" class="layui-form layui-form-pane mt10" >
                <input type="hidden" value="${(identity.id)!}" name="id" />

                        <div class="layui-form-item">
                            <label class="layui-form-label">所属用户</label>
                            <div class="layui-input-block">
                                    <input type="text" name="user" value="${(identity.user)!}" placeholder="请输入所属用户" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">姓名</label>
                            <div class="layui-input-block">
                                    <input type="text" name="name" value="${(identity.name)!}" placeholder="请输入姓名" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">手机号</label>
                            <div class="layui-input-block">
                                    <input type="text" name="phone" value="${(identity.phone)!}" placeholder="请输入手机号" class="layui-input" maxlength="11">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">身份证正面</label>
                            <div class="layui-input-block">
                                    <input type="text" name="imgA" value="${(identity.imgA)!}" placeholder="请输入身份证正面" class="layui-input" maxlength="100">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">身份证反面</label>
                            <div class="layui-input-block">
                                    <input type="text" name="imgB" value="${(identity.imgB)!}" placeholder="请输入身份证反面" class="layui-input" maxlength="100">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">创建时间</label>
                            <div class="layui-input-block">
                                    <#if identity?? && identity.createtime??>
                                    <input type="text" name="createtime" value="${identity.createtime?string('yyyy-MM-dd HH:mm:ss')}" placeholder="请输入创建时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    <#else>
                                    <input type="text" name="createtime" value="" placeholder="请输入创建时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    </#if>
                            </div>
                        </div>

                <@common.permission per='base:identity:edit'>
                <div class="layui-form-item">
                    <button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存身份信息</button>
                </div>
                </@common.permission>
            </form>
        </div>
        <script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/layui/layui.js"></script>
        <script>
            layui.use(['form','laypage','layer','laydate'],function(){
                var form = layui.form;
                var layer = layui.layer;
                var $ = layui.jquery;
                var laydate = layui.laydate;
                form.on('submit(demo2)',function(data){
                    layer.load();
                    $.ajax({
                        url:"${springMacroRequestContext.contextPath}/identity/save",
                        type:"post",
                        data:$("#saveForm").serialize(),
                        dataType:"json",
                        success:function(d){
                            layer.closeAll('loading');
                            if(d.code == 200){
                                window.top.layer.msg("保存成功！", {icon: 1});
                                parent.layui.submitForm();
                            }else{
                                layer.msg("对不起，访问不成功！错误编码：" + d.code);
                            }
                        }
                    })
                    return false;
                });

            });
        </script>
    </body>
</html>
