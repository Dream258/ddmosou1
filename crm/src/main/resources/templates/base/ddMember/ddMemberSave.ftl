<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  会员管理</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" media="all" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 10px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">${(ddMember?? && ddMember.id??)?string('编辑','添加')}会员管理</li>
                </ul>
            </div>
            <form id="saveForm" class="layui-form layui-form-pane mt10">
                <input type="hidden" value="${(ddMember.id)!}" name="id" />

                        <div class="layui-form-item">
                            <label class="layui-form-label">用户账号</label>
                            <div class="layui-input-block">
                                    <input type="text" name="userTelephone" lay-verify="required" value="${(ddMember.userTelephone)!}" placeholder="请输入用户账号" class="layui-input" maxlength="25">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户密码</label>
                            <div class="layui-input-block">
                                    <input type="text" name="userPassword" lay-verify="required" value="${(ddMember.userPassword)!}" placeholder="请输入用户密码" class="layui-input" maxlength="25">
                            </div>
                        </div>
                        <div class="layui-upload">
                            <label class="layui-form-label">用户头像:</label>
                            <button type="button" class="layui-btn" id="test1">上传图片</button>
                            <div class="layui-upload-list" style="position: relative;left: 110px;">
                                <img class="layui-upload-img" id="demo1" src="${(ddMember.userPhoto)!}" style="width: 100px;height: 100px;">
                                <input type="hidden" name="userPhoto" id="demoText"/>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户类型:</label>
                            <div class="layui-input-inline">
                                <select name="userType" lay-verify="required">
                                    <option value="">请选择</option>
                                    <option value="1">普通会员</option>
                                    <option value="2">VIP会员</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户金币</label>
                            <div class="layui-input-block">
                                    <input type="text" name="userGold" lay-verify="required|number" value="${(ddMember.userGold)!}" placeholder="请输入用户金币" class="layui-input" maxlength="10">
                            </div>
                        </div>

                <@common.permission per='base:ddMember:edit'>
                <div class="layui-form-item">
                    <button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存会员管理</button>
                </div>
                </@common.permission>
            </form>
        </div>
        <script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/layui/layui.js"></script>
        <script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/js/jquery-1.4.4.min.js"></script>
        <script>
            layui.use(['form','laypage','layer','laydate'],function(){
                var form = layui.form;
                var layer = layui.layer;
                var $ = layui.jquery;
                var laydate = layui.laydate;
                form.on('submit(demo2)',function(data){
                    layer.load();
                    $.ajax({
                        url:"${springMacroRequestContext.contextPath}/ddMember/save",
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
            /**
             * 上传图片
             */
            layui.use('upload', function(){
                var $ = layui.jquery
                    ,upload = layui.upload;

                //普通图片上传
                var uploadInst = upload.render({
                    elem: '#test1'
                    ,url: '${springMacroRequestContext.contextPath}/upload'
                    ,before: function(obj){
                        //预读本地文件示例，不支持ie8
                        obj.preview(function(index, file, result){
                            $('#demo1').attr('src', result); //图片链接（base64）
                        });
                    }
                    ,done: function(res){
                        // 上传成功
                        if (res.success.toString() =='000') {
                            $("#demoText").val(res.data.image);
                        }else{
                            alert("上传失败");
                        }
                    }
                    ,error: function(){
                    }
                });
            });
        </script>
    </body>
</html>
