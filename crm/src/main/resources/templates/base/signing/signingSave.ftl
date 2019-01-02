<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  客户签约</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 10px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">${(signing?? && signing.id??)?string('编辑','添加')}客户签约</li>
                </ul>
            </div>
            <form id="saveForm" class="layui-form layui-form-pane mt10" >
                <input type="hidden" value="${(signing.id)!}" name="id" />

                        <div class="layui-form-item">
                            <label class="layui-form-label">所属客户</label>
                            <div class="layui-input-block">
                                    <input type="text" name="customer" value="${(signing.customer)!}" placeholder="请输入所属客户" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">所属部门</label>
                            <div class="layui-input-block">
                                    <input type="text" name="department" value="${(signing.department)!}" placeholder="请输入所属部门" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">所属产品</label>
                            <div class="layui-input-block">
                                    <input type="text" name="product" value="${(signing.product)!}" placeholder="请输入所属产品" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">合同编号</label>
                            <div class="layui-input-block">
                                    <input type="text" name="number" value="${(signing.number)!}" placeholder="请输入合同编号" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">投资时间</label>
                            <div class="layui-input-block">
                                    <#if signing?? && signing.time??>
                                    <input type="text" name="time" value="${signing.time?string('yyyy-MM-dd HH:mm:ss')}" placeholder="请输入投资时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    <#else>
                                    <input type="text" name="time" value="" placeholder="请输入投资时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    </#if>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">投资金额</label>
                            <div class="layui-input-block">
                                    <input type="text" name="sum" value="${(signing.sum)!}" placeholder="请输入投资金额" class="layui-input" maxlength="22">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">兑付状态</label>
                            <div class="layui-input-block">
                                    <input type="text" name="deal" value="${(signing.deal)!}" placeholder="请输入兑付状态" class="layui-input" maxlength="1">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">回访状态</label>
                            <div class="layui-input-block">
                                    <input type="text" name="visit" value="${(signing.visit)!}" placeholder="请输入回访状态" class="layui-input" maxlength="1">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">合同状态</label>
                            <div class="layui-input-block">
                                    <input type="text" name="contract" value="${(signing.contract)!}" placeholder="请输入合同状态" class="layui-input" maxlength="1">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">开始时间</label>
                            <div class="layui-input-block">
                                    <#if signing?? && signing.start??>
                                    <input type="text" name="start" value="${signing.start?string('yyyy-MM-dd HH:mm:ss')}" placeholder="请输入开始时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    <#else>
                                    <input type="text" name="start" value="" placeholder="请输入开始时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    </#if>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">结束时间</label>
                            <div class="layui-input-block">
                                    <#if signing?? && signing.end??>
                                    <input type="text" name="end" value="${signing.end?string('yyyy-MM-dd HH:mm:ss')}" placeholder="请输入结束时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    <#else>
                                    <input type="text" name="end" value="" placeholder="请输入结束时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    </#if>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">封闭期限</label>
                            <div class="layui-input-block">
                                    <input type="text" name="closed" value="${(signing.closed)!}" placeholder="请输入封闭期限" class="layui-input" maxlength="5">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">收益金额</label>
                            <div class="layui-input-block">
                                    <input type="text" name="profit" value="${(signing.profit)!}" placeholder="请输入收益金额" class="layui-input" maxlength="22">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">经办人</label>
                            <div class="layui-input-block">
                                    <input type="text" name="agent" value="${(signing.agent)!}" placeholder="请输入经办人" class="layui-input" maxlength="10">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">审核状态</label>
                            <div class="layui-input-block">
                                    <input type="text" name="state" value="${(signing.state)!}" placeholder="请输入审核状态" class="layui-input" maxlength="1">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">创建时间</label>
                            <div class="layui-input-block">
                                    <#if signing?? && signing.createtime??>
                                    <input type="text" name="createtime" value="${signing.createtime?string('yyyy-MM-dd HH:mm:ss')}" placeholder="请输入创建时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    <#else>
                                    <input type="text" name="createtime" value="" placeholder="请输入创建时间" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" maxlength="19">
                                    </#if>
                            </div>
                        </div>

                <@common.permission per='base:signing:edit'>
                <div class="layui-form-item">
                    <button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存客户签约</button>
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
                        url:"${springMacroRequestContext.contextPath}/signing/save",
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
