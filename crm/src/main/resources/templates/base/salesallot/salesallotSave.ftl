<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  销售分配</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 10px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">${(salesallot?? && salesallot.id??)?string('编辑','添加')}销售分配</li>
                </ul>
            </div>
            <form id="saveForm" class="layui-form layui-form-pane mt10" >
                <input type="hidden" value="${(salesallot.id)!}" name="id" />

                        <div class="layui-form-item">
                            <label class="layui-form-label">所属目标</label>
                            <div class="layui-input-block">
                                    <input type="text" name="target" value="${(salesallot.target)!}" placeholder="请输入所属目标" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">所属用户</label>
                            <div class="layui-input-block">
                                    <input type="text" name="user" value="${(salesallot.user)!}" placeholder="请输入所属用户" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">分配金额</label>
                            <div class="layui-input-block">
                                    <input type="text" name="sum" value="${(salesallot.sum)!}" placeholder="请输入分配金额" class="layui-input" maxlength="22">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">达成率</label>
                            <div class="layui-input-block">
                                    <input type="text" name="rate" value="${(salesallot.rate)!}" placeholder="请输入达成率" class="layui-input" maxlength="22">
                            </div>
                        </div>

                <@common.permission per='base:salesallot:edit'>
                <div class="layui-form-item">
                    <button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存销售分配</button>
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
                        url:"${springMacroRequestContext.contextPath}/salesallot/save",
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
