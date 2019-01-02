<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  关键词</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 10px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">${(ddKey?? && ddKey.id??)?string('编辑','添加')}关键词</li>
                </ul>
            </div>
            <form id="saveForm" class="layui-form layui-form-pane mt10" >
                <input type="hidden" value="${(ddKey.id)!}" name="id" />

                        <div class="layui-form-item">
                            <label class="layui-form-label">用户ID</label>
                            <div class="layui-input-block">
                                    <input type="text" name="userId" value="${(ddKey.userId)!}" placeholder="请输入用户ID" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">商品关键词</label>
                            <div class="layui-input-block">
                                    <input type="text" name="goodsKey" value="${(ddKey.goodsKey)!}" placeholder="请输入商品关键词" class="layui-input" maxlength="20">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">商品ID</label>
                            <div class="layui-input-block">
                                    <input type="text" name="goodsId" value="${(ddKey.goodsId)!}" placeholder="请输入商品ID" class="layui-input" maxlength="11">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">商品名称</label>
                            <div class="layui-input-block">
                                    <input type="text" name="goodsName" value="${(ddKey.goodsName)!}" placeholder="请输入商品名称" class="layui-input" maxlength="50">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">商品销量</label>
                            <div class="layui-input-block">
                                    <input type="text" name="goodsSold" value="${(ddKey.goodsSold)!}" placeholder="请输入商品销量" class="layui-input" maxlength="100">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">商品图片</label>
                            <div class="layui-input-block">
                                    <input type="text" name="goodsThumbnail" value="${(ddKey.goodsThumbnail)!}" placeholder="请输入商品图片" class="layui-input" maxlength="100">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">商品团购价</label>
                            <div class="layui-input-block">
                                    <input type="text" name="groupPrice" value="${(ddKey.groupPrice)!}" placeholder="请输入商品团购价" class="layui-input" maxlength="11">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">商品单价</label>
                            <div class="layui-input-block">
                                    <input type="text" name="normalPrice" value="${(ddKey.normalPrice)!}" placeholder="请输入商品单价" class="layui-input" maxlength="11">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">商品排名</label>
                            <div class="layui-input-block">
                                    <input type="text" name="goodsTop" value="${(ddKey.goodsTop)!}" placeholder="请输入商品排名" class="layui-input" maxlength="11">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">商品时间</label>
                            <div class="layui-input-block">
                                    <#if ddKey?? && ddKey.goodsTime??>
                                    <input type="text" name="goodsTime" value="${ddKey.goodsTime?string('yyyy-MM-dd HH:mm:ss')}" placeholder="请输入商品时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    <#else>
                                    <input type="text" name="goodsTime" value="" placeholder="请输入商品时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    </#if>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">类型</label>
                            <div class="layui-input-block">
                                    <input type="text" name="type" value="${(ddKey.type)!}" placeholder="请输入类型" class="layui-input" maxlength="1">
                            </div>
                        </div>

                <@common.permission per='base:ddKey:edit'>
                <div class="layui-form-item">
                    <button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存关键词</button>
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
                        url:"${springMacroRequestContext.contextPath}/ddKey/save",
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
