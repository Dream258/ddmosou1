//.substr(0,17) H:i:s
function getLocalTime(time) {
    return new Date(parseInt(time) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
}

//init
$(document).ready(function(){
    getXwzxList();
    getJczsList();
    getZcfgList();
    getDsjList();
    getMjsdList();
});

function getXwzxList() {
    var id = $('input[name="hyzx"]').val();
    $.get('/newsList?id='+id+'&table=mt_qyrz&limit=0,8&type=0&posids=5', function(res){
        res.list.forEach(function (item,index) {
            $(".hyzx").append('<li><a href="/details?id='+item.id+'&type=4">'+item.fbt+'</a></li>');
        });
    });
}

function getJczsList() {
    var id = $('input[name="jczs"]').val();
    $.get('/newsList?id='+id+'&table=mt_qyrz&limit=0,8&type=0&posids=5', function(res){
        res.list.forEach(function (item,index) {
            $(".jczs").append('<li><a href="/details?id='+item.id+'&type=4">'+item.fbt+'</a></li>');
        });
    });
}

function getZcfgList() {
    var id = $('input[name="zcfg"]').val();
    $.get('/newsList?id='+id+'&table=mt_qyrz&limit=0,8&type=0&posids=5', function(res){
        res.list.forEach(function (item,index) {
            $(".zcfg").append('<li><a href="/details?id='+item.id+'&type=4">'+item.fbt+'</a></li>');
        });
    });
}

function getDsjList() {
    var id = $('input[name="dsj"]').val();
    $.get('/newsList?id='+id+'&table=mt_qyrz&limit=0,6&type=0&posids=5', function(res){
        res.list.forEach(function (item,index) {
            if(index<2){
                $(".dsjs").append('<li class="pull-left">' +
                    '<div class="pull-left content_mid_img tv_hot f1">' +
                    '<a href="/details?id='+item.id+'&type=4" class="avatar">' +
                    '<img src="'+item.thumb+'" alt=""/></a></div>' +
                    '<div class="content_mid_new pull-left">' +
                    '<h3 class="pull-left"><a href="/details?id='+item.id+'&type=4">'+item.fbt+'</a></h3>' +
                    '<p class="pull-left">'+item.description+'</p></div></li>');
            }else{
                $(".dsjs").append('<li class="bg_new_sm"><a href="/details?id='+item.id+'&type=4">'+item.fbt+'</a></li>');
            }
        });
    });
}

function getMjsdList() {
    var id = $('input[name="hyzj"]').val();
    $.get('/newsList?id='+id+'&table=mt_qyrz&limit=0,6&type=0&posids=5', function(res){
        res.list.forEach(function (item,index) {
            if(index<2){
                $(".hyzj").append('<li class="pull-left">' +
                    '<div class="pull-left content_mid_img tv_hot f1">' +
                    '<a href="/details?id='+item.id+'&type=4" class="avatar mj">' +
                    '<img src="'+item.thumb+'" alt=""/></a></div>' +
                    '<div class="content_mid_new pull-left">' +
                    '<h3 class="pull-left"><a href="/details?id='+item.id+'&type=4">'+item.fbt+'</a></h3>' +
                    '<p class="pull-left">'+item.description+'</p></div></li>');
            }else{
                $(".hyzj").append('<li class="bg_new_sm"><a href="/details?id='+item.id+'&type=4">'+item.fbt+'</a></li>');
            }
        });
    });
}