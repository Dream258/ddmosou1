<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  模块管理</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 10px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">${(ddManager?? && ddManager.id??)?string('编辑','添加')}模块管理</li>
                </ul>
            </div>
            <form id="saveForm" class="layui-form layui-form-pane mt10" >
                <input type="hidden" value="${(ddManager.id)!}" name="id" />

                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">用户名</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入用户名" name="managerTelephone" class="layui-textarea" maxlength="255">${(ddManager.managerTelephone)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">密码</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入密码" name="managerPassword" class="layui-textarea" maxlength="255">${(ddManager.managerPassword)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">外键</label>
                            <div class="layui-input-block">
                                    <input type="text" name="managerLimit" value="${(ddManager.managerLimit)!}" placeholder="请输入外键" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">1.正常 2.锁定</label>
                            <div class="layui-input-block">
                                    <input type="text" name="managerCondition" value="${(ddManager.managerCondition)!}" placeholder="请输入1.正常 2.锁定" class="layui-input" maxlength="10">
                            </div>
                        </div>

                <@common.permission per='base:ddManager:edit'>
                <div class="layui-form-item">
                    <button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存模块管理</button>
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
                        url:"${springMacroRequestContext.contextPath}/ddManager/save",
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
