<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  日志</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 10px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">${(sysLogs?? && sysLogs.id??)?string('编辑','添加')}日志</li>
                </ul>
            </div>
            <form id="saveForm" class="layui-form layui-form-pane mt10" >
                <input type="hidden" value="${(sysLogs.id)!}" name="id" />

                        <div class="layui-form-item">
                            <label class="layui-form-label">操作人ID</label>
                            <div class="layui-input-block">
                                    <input type="text" name="operId" value="${(sysLogs.operId)!}" placeholder="请输入操作人ID" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">操作人</label>
                            <div class="layui-input-block">
                                    <input type="text" name="operator" value="${(sysLogs.operator)!}" placeholder="请输入操作人" class="layui-input" maxlength="32">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">操作时间</label>
                            <div class="layui-input-block">
                                    <#if sysLogs?? && sysLogs.createtime??>
                                    <input type="text" name="createtime" value="${sysLogs.createtime?string('yyyy-MM-dd HH:mm:ss')}" placeholder="请输入操作时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    <#else>
                                    <input type="text" name="createtime" value="" placeholder="请输入操作时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    </#if>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">操作备注</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入操作备注" name="remarks" class="layui-textarea" maxlength="200">${(sysLogs.remarks)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">操作类型</label>
                            <div class="layui-input-block">
                                    <select name="type">
                                        <@common.mdictOptions title="status" value="${(sysLogs.type)!}" />
                                    </select>
                            </div>
                        </div>

                <@common.permission per='sys:sysLogs:edit'>
                <div class="layui-form-item">
                    <button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存日志</button>
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
                        url:"${springMacroRequestContext.contextPath}/sysLogs/save",
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
