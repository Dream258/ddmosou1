var goodsWrap2 = $('#keywordTable .item_wrap');
var page2 = 2;
var goodsItem2 = function (data) {
    var tr="";
    var goodUrl = data.good_url || 'http://mobile.yangkeduo.com/goods.html?goods_id='+data.sku;
    data.keywords.forEach(function (item,i) {
        var text = item.page_order*1 ? '第'+item.page+'页第'+item.page_position+'个，总排名第'+item.page_order+'名，'+textFun(item.rank_change)+'名' : '未查询到排名';
        tr+='<tr>' +
            '<td>'+item.keyword+'</td>' +
            '<td>'+item.update_at+'</td>' +
            '<td>'+item.normal_price+'</td>' +
            '<td>'+item.group_price+'</td>' +
            '<td>'+item.saler_num+'</td>' +
            '<td>'+text+'</td>' +
            // '<td>'+'第'+item.page+'页，第'+item.page_position+'个，第'+item.page_order+'名，变化'+item.rank_change+'名'+'</td>' +
            '<td>' +
            '<button class="btn good_btn1 queryTrend query-td" data-days="'+item.days+'" data-keyword_id="'+item.keyword_id+'" data-good_id="'+data.good_id+'">走势</button>' +
            '<button class="btn good_btn1 unfollow" data-keyword_id="'+item.keyword_id+'" data-good_id="'+data.good_id+'">取消监控</button>' +
            '</td>' +
            '</tr>'
    });
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
        '<div class="goods_hea_box goods_hea_box1 KeywordBtn">' +
        '<button class="btn add_keyword_btn good_btn2 KeywordBtn1" data-toggle="modal" data-target="#addKeywordModal" data-good_id="'+data.good_id+'">+添加关键词</button>' +
        '<button class="btn add_keyword_btn good_btn1 deleteBtn" data-toggle="modal" data-target="#deleteTableModal"><i class="glyphicon glyphicon-trash" data-good_id="'+data.good_id+'"></i></button>' +
        '</div>' +
        '</div>' +
        '<div class="item_table_box">' +
        '<table class="table table-ran-control table-bordered table-condensed">' +
        '<thead>' +
        '<tr>' +
        '<th>关键词</th>' +
        '<th>最后更新时间</th>' +
        '<th>单价</th>' +
        '<th>团购价</th>' +
        '<th>销量</th>' +
        '<th>排名</th>' +
        '<th>操作</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>' +
        tr+
        '</tbody>' +
        '</table>' +
        '</div>' +
        '</div>' +
        '</div>');
    goodsWrap2.append(goodsDiv);
};


var renderTable2 = function (data) {
    goodsWrap2.find('.item_wrap').remove();
    data.forEach(function (item,i) {
        goodsItem2(item);
    });
    if(!data.length){
        var goodsDiv = $('<div class="item_wrap row"></div>');
        goodsDiv.css({'text-align':'center','color':'red'});
      
    }
};