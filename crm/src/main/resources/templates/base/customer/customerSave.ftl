<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  客户信息</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 10px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">${(customer?? && customer.id??)?string('编辑','添加')}客户信息</li>
                </ul>
            </div>
            <form id="saveForm" class="layui-form layui-form-pane mt10" >
                <input type="hidden" value="${(customer.id)!}" name="id" />

                <div class="layui-form-item">
                    <label class="layui-form-label">客户名称</label>
                    <div class="layui-input-block">
                            <input type="text" name="name" value="${(customer.name)!}" placeholder="请输入客户名称" class="layui-input" maxlength="20">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">联系电话</label>
                    <div class="layui-input-block">
                            <input type="text" name="phone" value="${(customer.phone)!}" placeholder="请输入联系电话" class="layui-input" maxlength="11">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">电子邮箱</label>
                    <div class="layui-input-block">
                            <input type="text" name="email" value="${(customer.email)!}" placeholder="请输入电子邮箱" class="layui-input" maxlength="20">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">微信QQ</label>
                    <div class="layui-input-block">
                            <input type="text" name="wechat" value="${(customer.wechat)!}" placeholder="请输入微信QQ" class="layui-input" maxlength="11">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">身份证正面</label>
                    <div class="layui-input-block">
                            <input type="text" name="imgA" value="${(customer.imgA)!}" placeholder="请输入身份证正面" class="layui-input" maxlength="100">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">身份证反面</label>
                    <div class="layui-input-block">
                            <input type="text" name="imgB" value="${(customer.imgB)!}" placeholder="请输入身份证反面" class="layui-input" maxlength="100">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">企业类型</label>
                    <div class="layui-input-block">
                            <input type="text" name="company" value="${(customer.company)!}" placeholder="请输入企业类型" class="layui-input" maxlength="1">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">所属部门</label>
                    <div class="layui-input-block">
                            <input type="text" name="department" value="${(customer.department)!}" placeholder="请输入所属部门" class="layui-input" maxlength="10">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">成交状态</label>
                    <div class="layui-input-block">
                            <select name="deal">
                                <@common.mdictOptions title="deal" value="${(customer.deal)!}" />
                            </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">对付状态</label>
                    <div class="layui-input-block">
                            <select name="cash">
                                <@common.mdictOptions title="cash" value="${(customer.cash)!}" />
                            </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">经办人</label>
                    <div class="layui-input-block">
                            <input type="text" name="agent" value="${(customer.agent)!}" placeholder="请输入经办人" class="layui-input" maxlength="10">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">芝麻西瓜</label>
                    <div class="layui-input-block">
                            <input type="text" name="zmxg" value="${(customer.zmxg)!}" placeholder="请输入芝麻西瓜" class="layui-input" maxlength="32">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">类型个人1机构2</label>
                    <div class="layui-input-block">
                            <input type="text" name="type" value="${(customer.type)!}" placeholder="请输入类型个人1机构2" class="layui-input" maxlength="1">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">0未审核1审核通过2公海</label>
                    <div class="layui-input-block">
                            <input type="text" name="state" value="${(customer.state)!}" placeholder="请输入0未审核1审核通过2公海" class="layui-input" maxlength="1">
                    </div>
                </div>

                <@common.permission per='base:customer:edit'>
                <div class="layui-form-item">
                    <button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存客户信息</button>
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

                laydate.render({
                    elem: '#time' //指定元素
                    ,type: 'datetime'
                });

                form.on('submit(demo2)',function(data){
                    layer.load();
                    $.ajax({
                        url:"${springMacroRequestContext.contextPath}/customer/save",
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
