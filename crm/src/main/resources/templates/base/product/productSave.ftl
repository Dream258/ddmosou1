<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  产品管理</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 10px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">${(product?? && product.id??)?string('编辑','添加')}产品管理</li>
                </ul>
            </div>
            <form id="saveForm" class="layui-form layui-form-pane mt10" >
                <input type="hidden" value="${(product.id)!}" name="id" />

                        <div class="layui-form-item">
                            <label class="layui-form-label">产品名称</label>
                            <div class="layui-input-block">
                                    <input type="text" name="name" value="${(product.name)!}" placeholder="请输入产品名称" class="layui-input" maxlength="40">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">产品管理人</label>
                            <div class="layui-input-block">
                                    <input type="text" name="custodian" value="${(product.custodian)!}" placeholder="请输入产品管理人" class="layui-input" maxlength="40">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">产品类型</label>
                            <div class="layui-input-block">
                                    <input type="text" name="type" value="${(product.type)!}" placeholder="请输入产品类型" class="layui-input" maxlength="30">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">募集规模</label>
                            <div class="layui-input-block">
                                    <input type="text" name="scale" value="${(product.scale)!}" placeholder="请输入募集规模" class="layui-input" maxlength="30">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">年华收益率</label>
                            <div class="layui-input-block">
                                    <input type="text" name="rate" value="${(product.rate)!}" placeholder="请输入年华收益率" class="layui-input" maxlength="30">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">开始时间</label>
                            <div class="layui-input-block">
                                    <#if product?? && product.start??>
                                    <input type="text" name="start" value="${product.start?string('yyyy-MM-dd HH:mm:ss')}" placeholder="请输入开始时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    <#else>
                                    <input type="text" name="start" value="" placeholder="请输入开始时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    </#if>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">结束时间</label>
                            <div class="layui-input-block">
                                    <#if product?? && product.end??>
                                    <input type="text" name="end" value="${product.end?string('yyyy-MM-dd HH:mm:ss')}" placeholder="请输入结束时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    <#else>
                                    <input type="text" name="end" value="" placeholder="请输入结束时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    </#if>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">合伙企业</label>
                            <div class="layui-input-block">
                                    <input type="text" name="partnership" value="${(product.partnership)!}" placeholder="请输入合伙企业" class="layui-input" maxlength="40">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">认购起点</label>
                            <div class="layui-input-block">
                                    <input type="text" name="point" value="${(product.point)!}" placeholder="请输入认购起点" class="layui-input" maxlength="40">
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">项目详情</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入项目详情" name="details" class="layui-textarea" maxlength="65,535">${(product.details)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">信息披露</label>
                            <div class="layui-input-block">
                                    <textarea placeholder="请输入信息披露" name="disclosure" class="layui-textarea" maxlength="65,535">${(product.disclosure)!}</textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">停售状态</label>
                            <div class="layui-input-block">
                                    <select name="halt">
                                        <@common.mdictOptions title="halt" value="${(product.halt)!}" />
                                    </select>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">创建时间</label>
                            <div class="layui-input-block">
                                    <#if product?? && product.createtime??>
                                    <input type="text" name="createtime" value="${product.createtime?string('yyyy-MM-dd HH:mm:ss')}" placeholder="请输入创建时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    <#else>
                                    <input type="text" name="createtime" value="" placeholder="请输入创建时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    </#if>
                            </div>
                        </div>

                <@common.permission per='base:product:edit'>
                <div class="layui-form-item">
                    <button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存产品管理</button>
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
                        url:"${springMacroRequestContext.contextPath}/product/save",
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
