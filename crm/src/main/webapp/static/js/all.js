layui.use(['carousel','util','form'], function(){
    var carousel = layui.carousel;
    carousel.render({
        elem: '#banner'
        ,width: '100%'
        ,height:'100%'
        ,arrow: 'always'
        //,anim: 'updown'
    });
    var util = layui.util;
    util.fixbar({
        bar1: true
        , bar2: false
        , css: {right: 50, bottom: 100}
        , bgcolor: '#cf0c14'
        ,click: function (type) {
            if(type==='bar1') {
                window.open('/contact');
            }
        }
    });
    var form = layui.form;
    form.verify({
        pass: [
            /^[\S]{6,12}$/
            ,'密码必须6到12位，且不能出现空格'
        ]
        ,repass:function(value){
            var passwordValue = $('#password').val();
            if(value != passwordValue){
                return '两次输入的密码不一致!';
            }
        }
        ,exist:function(value) {
            var result = 0;
            $.ajax({
                url:"/whether_exist",
                type:"get",
                data:"&phone="+value,
                dataType:"json",
                async:false,
                success:function(d){
                    if(d.success!=null){
                        result = 1;
                    }
                }
            });
            if(result==1){
                return '手机号已被注册!';
            }
        }
        ,forget:function(value) {
            var result = 0;
            $.ajax({
                url:"/whether_exist",
                type:"get",
                data:"&phone="+value,
                dataType:"json",
                async:false,
                success:function(d){
                    if(d.success!=null){
                        result = 1;
                    }
                }
            });
            if(result==0){
                return '请填写已注册的手机号!';
            }
        }
        ,real:function(value) {
            var real = $('#real').val();
            if(value != real){
                return '验证码输入错误!';
            }
        }
    });
});

$(".scroll").click(function() {
    $("html, body").animate({
        scrollTop: $($(this).attr("href")).offset().top + "px"
    }, 1500);
    return false;
});

$("#find_left ul li a").mouseenter(function(){
    $(this).parent().siblings().attr("class","");
    $(this).parent().attr("class","active");

});

$("#find_right ul li a").mouseenter(function(){
    $(this).parent().siblings().attr("class","");
    $(this).parent().attr("class","red");

});

var id = $('input[name="userId"]').val();
if(id==''){
    $('.dlq').css("display","inline-block");
    $('.dlh').css("display","none");
}else{
    $('.dlq').css("display","none");
    $('.dlh').css("display","inline-block");
}

//.substr(0,17) H:i:s
function getLocalTime(time) {
    return new Date(parseInt(time) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
}

function getSyalList() {
    $.get('/newsList?id=20&table=mt_syal&limit=0,2&type=0&posids=2', function(res){
        res.list.forEach(function (item,index) {
            $("#al_ul").append('<li class="pull-left"><div class="tv_hot fl pull-left">' +
                '<a href="/details?id='+item.id+'&type=5" class="avatar" target="_blank">' +
                '<img src="'+item.thumb+'" alt=""/></a></div>' +
                '<div class="pull-right al_new"><a href="/details?id='+item.id+'&type=5" target="_blank">' +
                '<h4><span>'+item.fbt+'</span></h4>' +
                '<p>'+item.description+'</p></a><div>' +
                '<a href="/details?id='+item.id+'&type=5" target="_blank">'+getLocalTime(item.inputtime)+'&nbsp;&nbsp;' +
                '|&nbsp;&nbsp; 查看详情</a></div></div></li>');
        });
    });
}

function getYjbgList() {
    $.get('/newsList?id=23&table=mt_yjbg&limit=0,8&type=0&posids=2', function(res){
        res.list.forEach(function (item,index) {
            $("#sm_bg").append('<li><a href="/details?id='+item.id+'&type=6" target="_blank"><b>'+(index+1)+'</b>' +
                '&nbsp;&nbsp;'+item.fbt+'</a></li>');
        });
    });
}

$(".jrgc_nav li a").mouseenter(function(){
    var i=$(this).parent().index();
    $('.list_new ul').eq(i).show().siblings().hide();
    getJrgcListById(i);//
});

function getJrgcListById(i) {
    var id = $('input[name="catid_jrgc"]').eq(i).val();
    if($(".list_new ul").eq(i).html()==''){
        $.get('/newsList?id='+id+'&table=mt_jrgc&limit=0,3&type=0&posids=2', function(res){
            res.list.forEach(function (item,index) {
                $(".list_new ul").eq(i).append('<li><div class="new_content pull-left"><h2>' +
                    '<a href="/details?id='+item.id+'&type=1" target="_blank">'+item.fbt+'</a></h2><p>'+item.description+'</p></div></li>');
            });
        });
    }
}

$(".tt_nav li").mouseenter(function(){
    var i=$(this).index();
    $(".cjtt").eq(i).addClass("show").removeClass("hide").siblings().addClass("hide").removeClass("show");
    getCjrdListById(i);
});

function getCjrdListById(i) {
    var id = $('input[name="catid_cjrd"]').eq(i).val();
    if($(".cjtt").eq(i).html()==''){
        $.get('/newsList?id='+id+'&table=mt_cjrd&limit=0,8&type=0&posids=2', function(res){
            res.list.forEach(function (item,index) {
                var str = '';
                if(index<3){
                    str = '<li><a href="/details?id='+item.id+'&type=2" target="_blank">' +
                        '<b style="background: #cf0c14;">'+(index+1)+'</b>&nbsp;&nbsp;'+item.fbt+'</a></li>';
                }else{
                    str = '<li><a href="/details?id='+item.id+'&type=2" target="_blank">' +
                        '<b>'+(index+1)+'</b>&nbsp;&nbsp;'+item.fbt+'</a></li>';
                }
                $(".cjtt").eq(i).append(str);
            });
        });
    }
}

$(".list1 li").mouseenter(function(){
    var i = $(this).index();
    $(this).addClass("tzselect").siblings().removeClass("tzselect");
    $(".list11").eq(i).addClass("block").siblings().removeClass("block");
    getTzlc1ListById(i);
});

function getTzlc1ListById(i) {
    var id = $('input[name="catid_tzlc1"]').eq(i).val();
    if($(".list11").eq(i).html()==''){
        $.get('/newsList?id='+id+'&table=mt_tzlc&limit=0,3&type=2&posids=2', function(res){
            res.list.forEach(function (item,index) {
                if(index==0){
                    $(".list11").eq(i).append('<li class="tv_hot f1">' +
                        '<a href="/details?id='+item.id+'&type=3" class="avatar" target="_blank">' +
                        '<img src="'+item.thumb+'" alt=""/></a>' +
                        '<a href="/details?id='+item.id+'&type=3" target="_blank"><b >'+item.fbt+'</b>' +
                        '<p style="margin-top: 10px;">'+item.description+'</p></a></li>');
                }else{
                    $(".list11").eq(i).append('<li><a href="/details?id='+item.id+'&type=3" target="_blank">' +
                        '<b>'+item.fbt+'</b></a></li>');
                }
            });
        });
    }
}

$(".list2 li").mouseenter(function(){
    var i = $(this).index();
    $(this).addClass("tzselect").siblings().removeClass("tzselect");
    $(".list22").eq(i).addClass("block").siblings().removeClass("block");
    getTzlc2ListById(i);

});

function getTzlc2ListById(i) {
    var id = $('input[name="catid_tzlc2"]').eq(i).val();
    if($(".list22").eq(i).html()==''){
        $.get('/newsList?id='+id+'&table=mt_tzlc&limit=0,3&type=2&posids=2', function(res){
            res.list.forEach(function (item,index) {
                if(index==0){
                    if(i==3){
                        $(".list22").eq(i).append('<li class="tv_hot f1">' +
                            '<a href="http://zmxg.qljr.com/download.html" class="avatar" target="_blank">' +
                            '<img src="/static/images/tz_cpgj.png" alt=""/></a>' +
                            '<a href="/details?id='+item.id+'&type=3" target="_blank"><b>'+item.fbt+'</b>' +
                            '<p style="margin-top: 10px;">'+item.description+'</p></a></li>');
                    }else{
                        $(".list22").eq(i).append('<li class="tv_hot f1">' +
                            '<a href="/details?id='+item.id+'&type=3" class="avatar" target="_blank">' +
                            '<img src="'+item.thumb+'" alt=""/></a>' +
                            '<a href="/details?id='+item.id+'&type=3" target="_blank"><b>'+item.fbt+'</b>' +
                            '<p style="margin-top: 10px;">'+item.description+'</p></a></li>');
                    }
                }else{
                    $(".list22").eq(i).append('<li><a href="/details?id='+item.id+'&type=3" target="_blank">' +
                        '<b>'+item.fbt+'</b></a></li>');
                }
            });
        });
    }
}

$('#gq li').mouseenter(function() {
    var i = $(this).index();
    $(this).addClass('select').siblings().removeClass('select');
    $('.rzgq').eq(i).show().siblings().hide();
    getRzgqListById(i);
});

function getRzgqListById(i) {
    var id = $('input[name="catid_rzgq"]').eq(i).val();
    if($(".rzgq").eq(i).html().length==0){
        $.get('/newsList?id='+id+'&table=mt_qyrz&limit=0,3&type=1&posids=2', function(res){
            var content = '<div class="rz_left pull-left tv_hot fl">' +
                '<a href="/details?id='+res.top.id+'&type=4" class="avatar" target="_blank" >' +
                '<img src="'+res.top.thumb+'" alt=""/></a></div>' +
                '<div class="rz_middle pull-left"><h4>' +
                '<a href="/details?id='+res.top.id+'&type=4" target="_blank">'+res.top.fbt+'</a></h4>' +
                '<p>'+res.top.description+'</p><span>'+getLocalTime(res.top.inputtime)+
                ' &nbsp;&nbsp; | &nbsp;&nbsp; <a href="/details?id='+res.top.id+'&type=4" target="_blank">查看详情</a></span></div>' +
                '<div class="rz_right pull-right"><ul>';
            res.list.forEach(function (item,index) {
                content += '<li><a href="/details?id='+item.id+'&type=4" target="_blank">'+item.fbt+'</a></li>';
            });
            content += '</ul></div>';
            $(".rzgq").eq(i).append(content);
        });
    }
}

$('#zq li').mouseenter(function() {
    var i = $(this).index();
    $(this).addClass('select').siblings().removeClass('select');
    $('.rzzq').eq(i).show().siblings().hide();
    getRzzqListById(i);
});

function getRzzqListById(i) {
    var id = $('input[name="catid_rzzq"]').eq(i).val();
    if($(".rzzq").eq(i).html().length==0){
        $.get('/newsList?id='+id+'&table=mt_qyrz&limit=0,3&type=1&posids=2', function(res){
            var content = '<div class="rz_left pull-left tv_hot fl">' +
                '<a href="/details?id='+res.top.id+'&type=4" class="avatar" target="_blank">' +
                '<img src="'+res.top.thumb+'" alt=""/></a></div>' +
                '<div class="rz_middle pull-left"><h4>' +
                '<a href="/details?id='+res.top.id+'&type=4" target="_blank">'+res.top.fbt+'</a></h4>' +
                '<p>'+res.top.description+'</p><span>'+getLocalTime(res.top.inputtime)+
                ' &nbsp;&nbsp; | &nbsp;&nbsp; <a href="/details?id='+res.top.id+'&type=4" target="_blank">查看详情</a></span></div>' +
                '<div class="rz_right pull-right"><ul>';
            res.list.forEach(function (item,index) {
                content += '<li><a href="/details?id='+item.id+'&type=4" target="_blank">'+item.fbt+'</a></li>';
            });
            content += '</ul></div>';
            $(".rzzq").eq(i).append(content);
        });
    }
}

$("#nnnav li").eq(1).mouseenter(function(){
    $("#drop_down1").slideDown(300);
    $("#drop_down2").hide();
    $("#drop_down3").hide();
    $("#drop_down4").hide();
});

$("#drop_down1").mouseleave(function(){
    $(this).slideUp(300);
});

$("#nnnav li").eq(2).mouseenter(function(){
    $("#drop_down2").slideDown(300);
    $("#drop_down1").hide();
    $("#drop_down3").hide();
    $("#drop_down4").hide();
});

$("#drop_down2").mouseleave(function(){
    $(this).slideUp(300);

});

$("#nnnav li").eq(3).mouseenter(function(){
    $("#drop_down3").slideDown(300);
    $("#drop_down1").hide();
    $("#drop_down2").hide();
    $("#drop_down4").hide();
});

$("#drop_down3").mouseleave(function(){
    $(this).slideUp(300);
});

$("#nnnav li").eq(6).mouseenter(function(){
    $("#drop_down4").slideDown(300);
    $("#drop_down1").hide();
    $("#drop_down2").hide();
    $("#drop_down3").hide();
});
$("#drop_down4").mouseleave(function(){
    $(this).slideUp(300);

});

$(".li_leave").mouseenter(function(){
    $("#drop_down3").slideUp(300);
    $("#drop_down2").slideUp(300);
    $("#drop_down1").slideUp(300);
    $("#drop_down4").slideUp(300);
});