<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  积分日志</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 10px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">${(ddRebatelog?? && ddRebatelog.id??)?string('编辑','添加')}积分日志</li>
                </ul>
            </div>
            <form id="saveForm" class="layui-form layui-form-pane mt10" >
                <input type="hidden" value="${(ddRebatelog.id)!}" name="id" />

                        <div class="layui-form-item">
                            <label class="layui-form-label">邀请人</label>
                            <div class="layui-input-block">
                                    <input type="text" name="userTelephoneF" lay-verify="required" value="${(ddRebatelog.userTelephoneF)!}" placeholder="请输入邀请人" class="layui-input" maxlength="25">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">被邀请人</label>
                            <div class="layui-input-block">
                                    <input type="text" name="userTelephoneS" lay-verify="required" value="${(ddRebatelog.userTelephoneS)!}" placeholder="请输入被邀请人" class="layui-input" maxlength="25">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">记录创建日期</label>
                            <div class="layui-input-block">
                                    <#if ddRebatelog?? && ddRebatelog.createTime??>
                                    <input type="text" name="createTime" lay-verify="required" value="${ddRebatelog.createTime?string('yyyy-MM-dd HH:mm:ss')}" placeholder="请输入记录创建日期" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    <#else>
                                    <input type="text" name="createTime" lay-verify="required" value="" placeholder="请输入记录创建日期" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    </#if>
                            </div>
                        </div>

                <@common.permission per='base:ddRebatelog:edit'>
                <div class="layui-form-item">
                    <button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存积分日志</button>
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
                        url:"${springMacroRequestContext.contextPath}/ddRebatelog/save",
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
