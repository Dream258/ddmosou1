<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  签约收益</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 10px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">${(profit?? && profit.id??)?string('编辑','添加')}签约收益</li>
                </ul>
            </div>
            <form id="saveForm" class="layui-form layui-form-pane mt10" >
                <input type="hidden" value="${(profit.id)!}" name="id" />

                        <div class="layui-form-item">
                            <label class="layui-form-label">收益金额</label>
                            <div class="layui-input-block">
                                    <input type="text" name="sum" value="${(profit.sum)!}" placeholder="请输入收益金额" class="layui-input" maxlength="22">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">收益时间</label>
                            <div class="layui-input-block">
                                    <#if profit?? && profit.time??>
                                    <input type="text" name="time" value="${profit.time?string('yyyy-MM-dd HH:mm:ss')}" placeholder="请输入收益时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    <#else>
                                    <input type="text" name="time" value="" placeholder="请输入收益时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    </#if>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">收益类型</label>
                            <div class="layui-input-block">
                                    <input type="text" name="type" value="${(profit.type)!}" placeholder="请输入收益类型" class="layui-input" maxlength="1">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">所属签约</label>
                            <div class="layui-input-block">
                                    <input type="text" name="signing" value="${(profit.signing)!}" placeholder="请输入所属签约" class="layui-input" maxlength="10">
                            </div>
                        </div>

                <@common.permission per='base:profit:edit'>
                <div class="layui-form-item">
                    <button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存签约收益</button>
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
                        url:"${springMacroRequestContext.contextPath}/profit/save",
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
