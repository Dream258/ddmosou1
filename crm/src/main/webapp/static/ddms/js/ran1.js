var page = 1;
var goodsWrap = $('#rankingTable .item_wrap');
var textFun = function (num) {
    if(num*1>=0){
        return '<i class="glyphicon glyphicon-arrow-up"></i>' + num
    }else{
        return '<i class="glyphicon glyphicon-arrow-down"></i>' + num*(-1)
    }
};
var goodsItem = function (data) {
    var cat_level_1 = data.cat_level_1,
        cat_level_2 = data.cat_level_2,
        cat_level_3 = data.cat_level_3,
        text1 = cat_level_1.page_order*1 ? '第'+cat_level_1.page+'页第'+cat_level_1.page_position+'个，总排名第'+cat_level_1.page_order+'名，'+textFun(cat_level_1.rank_change)+'名' : '未查询到排名',
        text2 = cat_level_2.page_order*1 ? '第'+cat_level_2.page+'页第'+cat_level_2.page_position+'个，总排名第'+cat_level_2.page_order+'名，'+textFun(cat_level_2.rank_change)+'名' : '未查询到排名',
        text3 = cat_level_3.page_order*1 ? '第'+cat_level_3.page+'页第'+cat_level_3.page_position+'个，总排名第'+cat_level_3.page_order+'名，'+textFun(cat_level_3.rank_change)+'名' : '未查询到排名';
    var goodUrl = data.good_url || 'http://mobile.yangkeduo.com/goods.html?goods_id='+data.sku;
    var goodsDiv = $('<div class="item_wrap row">' +
        '<div class="goods_item row">' +
        '<div class="item_header row">' +
        '<div class="goods_hea_box">' +
        '<button class="btn close_top'+(data.is_top*1===1?' good_btn1':' good_btn2')+'" data-good_id="'+data.good_id+'">'+(data.is_top*1===1?'取消置顶':'置顶')+'</button>' +
        '</div>' +
        '<div class="goods_hea_box">' +
        '<img src="'+data.good_pic+'" class="goods_img" />' +
        '<div class="goods_text">' +
        '<a class="goods_name" href="'+goodUrl+'" title="'+data.good_title+'" target="_blank">'+data.good_title+'</a>' +
        '<div class="goods_sku">商品id：'+ data.sku +'</div>' +
        '</div>' +
        '</div>' +
        '<div class="goods_hea_box goods_hea_box1">' +
        '<button class="btn add_keyword_btn good_btn1 deleteBtn" data-toggle="modal" data-target="#deleteTableModal"><i class="glyphicon glyphicon-trash" data-good_id="'+data.good_id+'"></i></button>' +
        '</div>' +
        '<div class="item_table_box">' +
        '<table class="table table-ran-control table-bordered table-condensed">' +
        '<thead>' +
        '<tr>' +
        '<th>类目</th>' +
        '<th>最后更新时间</th>' +
        '<th>单价</th>' +
        '<th>团购价</th>' +
        '<th>销量</th>' +
        '<th>一级类名排名</th>' +
        '<th>二级类名排名</th>' +
        '<th>三级类名排名</th>' +
        '<th>操作</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>' +
        '<td>'+data.cat_name+'</td>' +
        '<td>'+data.update_at+'</td>' +
        '<td>'+data.normal_price+'</td>' +
        '<td>'+data.group_price+'</td>' +
        '<td>'+data.saler_num+'</td>' +
        '<td>'+text1+'</td>' +
        '<td>'+text2+'</td>' +
        '<td>'+text3+'</td>' +
        // '<td>'+'第'+cat_level_1.page+'页，第'+cat_level_1.page_position+'个，第'+cat_level_1.page_order+'名，变化'+cat_level_1.rank_change+'名'+'</td>' +
        // '<td>'+'第'+cat_level_2.page+'页，第'+cat_level_2.page_position+'个，第'+cat_level_2.page_order+'名，变化'+cat_level_2.rank_change+'名'+'</td>' +
        // '<td>'+'第'+cat_level_3.page+'页，第'+cat_level_3.page_position+'个，第'+cat_level_3.page_order+'名，变化'+cat_level_3.rank_change+'名'+'</td>' +
        '<td><button class="btn query-td" data-good_id="'+data.good_id+'">走势</button></td>' +
        '</tbody>' +
        '</table>' +
        '</div>' +
        '</div>' +
        '</div>');
    goodsWrap.append(goodsDiv);
};
var renderTable1 = function (data) {
    goodsWrap.find('.item_wrap').remove();
    data.forEach(function (item,i) {
        goodsItem(item);
    });
    if(!data.length){
        var goodsDiv = $('<div class="item_wrap row">没有添加监控商品</div>');
        goodsDiv.css({'text-align':'center','color':'red'});
        goodsWrap.append(goodsDiv);
    }
};