<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  配置测试</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 10px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">${(seoBack?? && seoBack.id??)?string('编辑','添加')}配置测试</li>
                </ul>
            </div>
            <form id="saveForm" class="layui-form layui-form-pane mt10" >
                <input type="hidden" value="${(seoBack.id)!}" name="id" />

                        <div class="layui-form-item">
                            <label class="layui-form-label">编号</label>
                            <div class="layui-input-block">
                                    <input type="text" name="idSeo" lay-verify="required|number" value="${(seoBack.idSeo)!}" placeholder="请输入编号" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">标题副加字</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入标题副加字" name="btSeo" class="layui-textarea" maxlength="255">${(seoBack.btSeo)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">商城关键字</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入商城关键字" name="scSeo" class="layui-textarea" maxlength="255">${(seoBack.scSeo)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">关键词描述</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入关键词描述" name="gjcSeo" class="layui-textarea" maxlength="255">${(seoBack.gjcSeo)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">其他页头信息</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入其他页头信息" name="qtySeo" class="layui-textarea" maxlength="255">${(seoBack.qtySeo)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">电话</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入电话" name="phoneSeo" class="layui-textarea" maxlength="255">${(seoBack.phoneSeo)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">网站备案号</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入网站备案号" name="wzbaSeo" class="layui-textarea" maxlength="255">${(seoBack.wzbaSeo)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">公关备案</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入公关备案" name="ggbaSeo" class="layui-textarea" maxlength="255">${(seoBack.ggbaSeo)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">公关备案链接</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入公关备案链接" name="ggljSeo" class="layui-textarea" maxlength="255">${(seoBack.ggljSeo)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">第三方统计代码</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入第三方统计代码" name="dsfSeo" class="layui-textarea" maxlength="255">${(seoBack.dsfSeo)!}</textarea>
                            </div>
                        </div>

                <@common.permission per='base:seoBack:edit'>
                <div class="layui-form-item">
                    <button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存配置测试</button>
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
                        url:"${springMacroRequestContext.contextPath}/seoBack/save",
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
