<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html style="height: 100%">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>JeeBoom — 系统菜单树</title>
    <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
    <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    <style>
        .left{
            float: left;
            margin-top: 15px;
            margin-left: 10px;
            width: 18%;
            border: 1px solid #ddd;
            height: 100%;
        }
        .right{
            height: 100%;
        }
        .iframe{
            float: right;
            border: 0;
            width: 80%;
            height: 100%;
        }
        @media screen and (min-width:1200px) {
            body{
                height: 97%;
            }
            .left{
                width: 14%;!important;
            }
            .iframe{
                width: 85%;!important;
            }
        }
        @media screen and (max-width:1199px) {
            body{
                height: 96%;
            }
        }
    </style>
</head>
<body class="anim-fadeInUp">
    <div class="left">
        <div class="layui-form layui-form-pane mt20" >
            <ul id="departmentTree"></ul>
        </div>
    </div>
    <div id="frame" class="right">
        <iframe src="${springMacroRequestContext.contextPath}/sysUser/page" scrolling="no" class="iframe"></iframe>
    </div>
    <script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/layui/layui.js"></script>
    <script>
        layui.use(['tree', 'layer'],function(){
            var layer = layui.layer;
            var $ = layui.jquery;
            //获取菜单列表
            $.ajax({
                url:"${springMacroRequestContext.contextPath}/sysDepartment/getTree",
                type:"POST",
                async: false,
                dataType:"json",
                success:function(d){
                    if(d.code == 200){
                        layui.tree({
                            elem:"#departmentTree",
                            nodes:d.data,
                            click:function(item){
                                if(item.alias==2){
                                    $('iframe').remove();
                                    $("#frame").html('<iframe src="${springMacroRequestContext.contextPath}/sysUser/page?departmentId='+item.id+'" class="iframe"></iframe>');
                                }else{
                                    layer.msg('请选择部门！');
                                }
                            }
                        });
                    }else{
                        layer.msg("对不起，访问不成功！错误编码：" + d.code);
                    }
                }
            });
        });
    </script>
</body>
</html>
