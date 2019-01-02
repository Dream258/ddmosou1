//.substr(0,17) H:i:s
function getLocalTime(time) {
    return new Date(parseInt(time) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
}

//init
$(document).ready(function(){
    getHyzxList();
    getZcfgList();
    getJczsList();
    getGdplList();
});

function getJczsList() {
    var id = $('input[name="jczs"]').val();
    $.get('/newsList?id='+id+'&table=mt_tzlc&limit=0,8&type=0&posids=5', function(res){
        res.list.forEach(function (item,index) {
            $(".jczs").append('<li><a href="/details?id='+item.id+'&type=3">'+item.fbt+'</a></li>');
        });
    });
}

function getZcfgList() {
    var id = $('input[name="zcfg"]').val();
    $.get('/newsList?id='+id+'&table=mt_tzlc&limit=0,8&type=0&posids=5', function(res){
        res.list.forEach(function (item,index) {
            $(".zcfg").append('<li><a href="/details?id='+item.id+'&type=3">'+item.fbt+'</a></li>');
        });
    });
}

function getHyzxList() {
    var id = $('input[name="hyzx"]').val();
    $.get('/newsList?id='+id+'&table=mt_tzlc&limit=0,6&type=0&posids=5', function(res){
        res.list.forEach(function (item,index) {
            if(index<2){
                $(".hyzx").append('<li class="pull-left">' +
                    '<div class="pull-left content_mid_img tv_hot f1">' +
                    '<a href="/details?id='+item.id+'&type=3" class="avatar">' +
                    '<img src="'+item.thumb+'" alt=""/></a></div>' +
                    '<div class="content_mid_new pull-left">' +
                    '<h3 class="pull-left"><a href="/details?id='+item.id+'&type=3">'+item.fbt+'</a></h3>' +
                    '<p class="pull-left">'+item.description+'</p></div></li>');
            }else{
                $(".hyzx").append('<li class="bg_new_sm"><a href="/details?id='+item.id+'&type=3">'+item.fbt+'</a></li>');
            }
        });
    });
}

function getGdplList() {
    var id = $('input[name="gdpl"]').val();
    $.get('/newsList?id='+id+'&table=mt_tzlc&limit=0,6&type=0&posids=5', function(res){
        res.list.forEach(function (item,index) {
            if(index<2){
                $(".gdpl").append('<li class="pull-left">' +
                    '<div class="pull-left content_mid_img tv_hot f1">' +
                    '<a href="/details?id='+item.id+'&type=3" class="avatar mj">' +
                    '<img src="'+item.thumb+'" alt=""/></a></div>' +
                    '<div class="content_mid_new pull-left">' +
                    '<h3 class="pull-left"><a href="/details?id='+item.id+'&type=3">'+item.fbt+'</a></h3>' +
                    '<p class="pull-left">'+item.description+'</p></div></li>');
            }else{
                $(".gdpl").append('<li class="bg_new_sm"><a href="/details?id='+item.id+'&type=3">'+item.fbt+'</a></li>');
            }
        });
    });
}