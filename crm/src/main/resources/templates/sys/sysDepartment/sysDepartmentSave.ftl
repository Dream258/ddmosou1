<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  部门管理</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 10px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">${(sysDepartment?? && sysDepartment.id??)?string('编辑','添加')}部门</li>
                </ul>
            </div>
            <form id="saveForm" class="layui-form layui-form-pane mt10">
                <input type="hidden" value="${(sysDepartment.id)!}" name="id" />
                <input type="hidden" value="${(sysDepartment.fid)!}" name="fid" />
                <input type="hidden" value="${(sysDepartment.level)!}" name="level" />

                <div class="layui-form-item">
                    <label class="layui-form-label">名称</label>
                    <div class="layui-input-block">
                            <input type="text" name="name" lay-verify="required" value="${(sysDepartment.name)!}" placeholder="请输入名称" class="layui-input" maxlength="32">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">上级</label>
                    <div class="layui-input-block">
                            <input type="text" id="fname" value="${(fname)!}" lay-verify="fname" onclick="layui.showTree();" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">排序</label>
                    <div class="layui-input-block">
                            <input type="text" name="sort" lay-verify="required|number" value="${(sysDepartment.sort)!}" placeholder="请输入排序" class="layui-input" maxlength="10">
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-block">
                            <textarea placeholder="请输入备注" name="remarks" class="layui-textarea" maxlength="200">${(sysDepartment.remarks)!}</textarea>
                    </div>
                </div>

                <@common.permission per='department:sysDepartment:edit'>
                <div class="layui-form-item">
                    <button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存部门管理</button>
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

                //表单验证
                form.verify({
                    fname: function(value){
                        var fid = $("input[name='fid']").val();
                        if(fid == ''){
                            return '上级菜单要选择呀！';
                        }
                    }
                });

                //展示上级菜单树弹窗
                layui.showTree = function(){
                    layer.open({
                        type: 2,
                        title:'请选择上级菜单',
                        area: ['400px', '500px'],
                        fixed: false, //不固定
                        maxmin: true,
                        content: '${springMacroRequestContext.contextPath}/sysDepartment/getTreeFrom'
                    });
                }

                //上级菜单
                layui.addPValue = function(name,fid,level){
                    $("#fname").val(name);
                    $("input[name='fid']").val(fid);
                    $("input[name='level']").val(level);
                }

                form.on('submit(demo2)',function(data){
                    layer.load();
                    $.ajax({
                        url:"${springMacroRequestContext.contextPath}/sysDepartment/save",
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
