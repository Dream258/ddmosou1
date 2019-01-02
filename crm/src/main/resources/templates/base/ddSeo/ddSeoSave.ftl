<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  seo管理</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 10px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">${(ddSeo?? && ddSeo.id??)?string('编辑','添加')}seo管理</li>
                </ul>
            </div>
            <form id="saveForm" class="layui-form layui-form-pane mt10" >
                <input type="hidden" value="${(ddSeo.id)!}" name="id" />

                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">标题副加字</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入标题副加字" name="bt" class="layui-textarea" maxlength="255">${(ddSeo.bt)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">商城关键字</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入商城关键字" name="sc" class="layui-textarea" maxlength="255">${(ddSeo.sc)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">关键词描述</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入关键词描述" name="gjc" class="layui-textarea" maxlength="255">${(ddSeo.gjc)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">其他页头信息</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入其他页头信息" name="qty" class="layui-textarea" maxlength="255">${(ddSeo.qty)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">电话</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入电话" name="phone" class="layui-textarea" maxlength="255">${(ddSeo.phone)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">网站备案号</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入网站备案号" name="wzba" class="layui-textarea" maxlength="255">${(ddSeo.wzba)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">公关备案</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入公关备案" name="ggba" class="layui-textarea" maxlength="255">${(ddSeo.ggba)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">公关备案链接</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入公关备案链接" name="gglj" class="layui-textarea" maxlength="255">${(ddSeo.gglj)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">第三方统计代码</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入第三方统计代码" name="dsfSeo" class="layui-textarea" maxlength="255">${(ddSeo.dsfSeo)!}</textarea>
                            </div>
                        </div>

                <@common.permission per='base:ddSeo:edit'>
                <div class="layui-form-item">
                    <button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存seo管理</button>
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
                        url:"${springMacroRequestContext.contextPath}/ddSeo/save",
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
