<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  会员日期</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 10px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">${(ddViptime?? && ddViptime.id??)?string('编辑','添加')}会员日期</li>
                </ul>
            </div>
            <form id="saveForm" class="layui-form layui-form-pane mt10" >
                <input type="hidden" value="${(ddViptime.id)!}" name="id" />

                        <div class="layui-form-item">
                            <label class="layui-form-label">用户手机</label>
                            <div class="layui-input-block">
                                    <input type="text" name="userTelephone" value="${(ddViptime.userTelephone)!}" placeholder="请输入用户手机" class="layui-input" maxlength="25">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">vip类型</label>
                            <div class="layui-input-block">
                                    <input type="text" name="vipType" value="${(ddViptime.vipType)!}" placeholder="请输入vip类型" class="layui-input" maxlength="25">
                            </div>
                        </div>
                        <#--<div class="layui-form-item">
                            <label class="layui-form-label">VIP开始时间</label>
                            <div class="layui-input-block">
                                    <#if ddViptime?? && ddViptime.startTime??>
                                    <input type="text" name="startTime" value="${ddViptime.startTime?string('yyyy-MM-dd HH:mm:ss')}" placeholder="请输入VIP开始时间(格式为：yyyy-MM-dd HH:mm:ss)" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    <#else>
                                    <input type="text" name="startTime" value="" placeholder="请输入VIP开始时间(格式为：yyyy-MM-dd HH:mm:ss)" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    </#if>
                            </div>
                        </div>-->
                </div>
                <div class="layui-form">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">VIP开始时间</label>
                            <div class="layui-input-inline">
                                <#if ddViptime?? && ddViptime.startTime??>
                                <input type="text" name="startTime" value="${ddViptime.startTime?string('yyyy-MM-dd HH:mm:ss')}" placeholder="请输入VIP开始时间(格式为：yyyy-MM-dd HH:mm:ss)" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                <#else>
                                <input type="text" name="startTime" class="layui-input" id="test1" placeholder="yyyy-MM-dd HH:mm:ss">
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="layui-form">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">VIP结束时间</label>
                            <div class="layui-input-inline">
                                <#if ddViptime?? && ddViptime.startTime??>
                                <input type="text" name="startTime" value="${ddViptime.startTime?string('yyyy-MM-dd HH:mm:ss')}" placeholder="请输入VIP开始时间(格式为：yyyy-MM-dd HH:mm:ss)" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                <#else>
                                <input type="text" name="endTime" class="layui-input" id="test2" placeholder="yyyy-MM-dd HH:mm:ss">
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
                <@common.permission per='base:ddViptime:edit'>
                <div class="layui-form-item">
                    <button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存会员日期</button>
                </div>
                </@common.permission>
            </form>
        </div>
        <script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/layui/layui.js"></script>
        <script>
            layui.use('laydate', function(){
                var laydate = layui.laydate;
                //日期时间选择器
                laydate.render({
                    elem: '#test1'
                    ,type: 'datetime'
                    ,format:'yyyy-MM-dd HH:mm:ss'
                });
                //日期时间选择器
                laydate.render({
                    elem: '#test2'
                    ,type: 'datetime'
                    ,format:'yyyy-MM-dd HH:mm:ss'
                });
            });
            layui.use(['form','laypage','layer','laydate'],function(){
                var form = layui.form;
                var layer = layui.layer;
                var $ = layui.jquery;
                var laydate = layui.laydate;
                form.on('submit(demo2)',function(data){
                    layer.load();
                    $.ajax({
                        url:"${springMacroRequestContext.contextPath}/ddViptime/save",
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
