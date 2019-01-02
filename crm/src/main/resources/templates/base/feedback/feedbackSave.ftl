<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  客户反馈</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 10px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">${(feedback?? && feedback.id??)?string('编辑','添加')}客户反馈</li>
                </ul>
            </div>
            <form id="saveForm" class="layui-form layui-form-pane mt10" >
                <input type="hidden" value="${(feedback.id)!}" name="id" />

                        <div class="layui-form-item">
                            <label class="layui-form-label">所属客户</label>
                            <div class="layui-input-block">
                                    <input type="text" name="customer" value="${(feedback.customer)!}" placeholder="请输入所属客户" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">所属部门</label>
                            <div class="layui-input-block">
                                    <input type="text" name="department" value="${(feedback.department)!}" placeholder="请输入所属部门" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">机构/客户</label>
                            <div class="layui-input-block">
                                    <input type="text" name="type" value="${(feedback.type)!}" placeholder="请输入机构/客户" class="layui-input" maxlength="1">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">创建时间</label>
                            <div class="layui-input-block">
                                    <#if feedback?? && feedback.createtime??>
                                    <input type="text" name="createtime" value="${feedback.createtime?string('yyyy-MM-dd HH:mm:ss')}" placeholder="请输入创建时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    <#else>
                                    <input type="text" name="createtime" value="" placeholder="请输入创建时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    </#if>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">回复时间</label>
                            <div class="layui-input-block">
                                    <#if feedback?? && feedback.replytime??>
                                    <input type="text" name="replytime" value="${feedback.replytime?string('yyyy-MM-dd HH:mm:ss')}" placeholder="请输入回复时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    <#else>
                                    <input type="text" name="replytime" value="" placeholder="请输入回复时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    </#if>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">提出内容</label>
                            <div class="layui-input-block">
                                    <input type="text" name="content" value="${(feedback.content)!}" placeholder="请输入提出内容" class="layui-input" maxlength="100">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">回复内容</label>
                            <div class="layui-input-block">
                                    <input type="text" name="answer" value="${(feedback.answer)!}" placeholder="请输入回复内容" class="layui-input" maxlength="100">
                            </div>
                        </div>

                <@common.permission per='base:feedback:edit'>
                <div class="layui-form-item">
                    <button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存客户反馈</button>
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
                        url:"${springMacroRequestContext.contextPath}/feedback/save",
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
