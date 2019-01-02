<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  客户回访</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 10px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">${(review?? && review.id??)?string('编辑','添加')}客户回访</li>
                </ul>
            </div>
            <form id="saveForm" class="layui-form layui-form-pane mt10" >
                <input type="hidden" value="${(review.id)!}" name="id" />

                        <div class="layui-form-item">
                            <label class="layui-form-label">所属客户</label>
                            <div class="layui-input-block">
                                    <input type="text" name="customer" value="${(review.customer)!}" placeholder="请输入所属客户" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">所属部门</label>
                            <div class="layui-input-block">
                                    <input type="text" name="department" value="${(review.department)!}" placeholder="请输入所属部门" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">所属产品</label>
                            <div class="layui-input-block">
                                    <input type="text" name="product" value="${(review.product)!}" placeholder="请输入所属产品" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">合同编号</label>
                            <div class="layui-input-block">
                                    <input type="text" name="number" value="${(review.number)!}" placeholder="请输入合同编号" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">回访状态</label>
                            <div class="layui-input-block">
                                    <select name="visit">
                                        <@common.mdictOptions title="visit" value="${(review.visit)!}" />
                                    </select>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">回访结果</label>
                            <div class="layui-input-block">
                                    <select name="result">
                                        <@common.mdictOptions title="result" value="${(review.result)!}" />
                                    </select>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">创建时间</label>
                            <div class="layui-input-block">
                                    <#if review?? && review.createtime??>
                                    <input type="text" name="createtime" value="${review.createtime?string('yyyy-MM-dd HH:mm:ss')}" placeholder="请输入创建时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    <#else>
                                    <input type="text" name="createtime" value="" placeholder="请输入创建时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    </#if>
                            </div>
                        </div>

                <@common.permission per='base:review:edit'>
                <div class="layui-form-item">
                    <button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存客户回访</button>
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
                        url:"${springMacroRequestContext.contextPath}/review/save",
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
