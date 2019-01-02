<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  邮箱通知</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 10px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">${(ddEmail?? && ddEmail.id??)?string('编辑','添加')}邮箱通知</li>
                </ul>
            </div>
            <form id="saveForm" class="layui-form layui-form-pane mt10" >
                <input type="hidden" value="${(ddEmail.id)!}" name="id" />

                        <div class="layui-form-item">
                            <label class="layui-form-label">编号</label>
                            <div class="layui-input-block">
                                    <input type="text" name="emailId" lay-verify="required|number" value="${(ddEmail.emailId)!}" placeholder="请输入编号" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">1.启动2.禁用</label>
                            <div class="layui-input-block">
                                    <input type="text" name="emailOpen" value="${(ddEmail.emailOpen)!}" placeholder="请输入1.启动2.禁用" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">SMTP服务器</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入SMTP服务器" name="emailSm" class="layui-textarea" maxlength="255">${(ddEmail.emailSm)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">SMTP端口</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入SMTP端口" name="emailTp" class="layui-textarea" maxlength="255">${(ddEmail.emailTp)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">发信人邮件地址</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入发信人邮件地址" name="emailDz" class="layui-textarea" maxlength="255">${(ddEmail.emailDz)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">SMTP身份验证用户名</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入SMTP身份验证用户名" name="emailSf" class="layui-textarea" maxlength="255">${(ddEmail.emailSf)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">SMTP身份验证码</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入SMTP身份验证码" name="emailYzm" class="layui-textarea" maxlength="255">${(ddEmail.emailYzm)!}</textarea>
                            </div>
                        </div>

                <@common.permission per='email:ddEmail:edit'>
                <div class="layui-form-item">
                    <button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存邮箱通知</button>
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
                        url:"${springMacroRequestContext.contextPath}/ddEmail/save",
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
