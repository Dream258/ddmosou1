var pageA=1;
$(function () {
    var getTable = function (key, page) {
        page = page ? page : 1;
        $('.loadingLog').show();
        var obj = {1: renderTable1, 2: renderTable2, 3: renderTable3};
        var objUrl = {1: '/monitor-search/cat-goods', 2: '/monitor-search/key-goods', 3: '/monitor-search/act-goods'};
        var mBox =$('.M-box' + key);
        $.post(objUrl[key], {page: page, page_size: '10'}, function (param) {
            if (param.result * 1) {
                $('.form'+key).find('input.form-control').val('');
                mBox.pagination({
                    pageCount: param.total_page ? param.total_page : 1,
                    coping: true,
                    current: page,
                    homePage: '首页',
                    endPage: '末页',
                    prevContent: '<',
                    nextContent: '>',
                    callback: function (item) {
                        getTable(key, item.getCurrent());
                        $("body").scrollTop(0);
                    }
                });
                obj[key](param.data);
                if (param.total_page < 2) {
                    mBox.hide();
                } else {
                    mBox.show();
                }
            } else {
                alertError(param.msg);
                obj[key]([]);
                mBox.hide();
            }
            $('.loadingLog').hide();
        }).error(function () {
            obj[key]([]);
            mBox.hide();
//            alertError('系统错误');
            $('.loadingLog').hide();
        })
    };
    var ranking_canvas = echarts.init(document.getElementById('ranking_canvas'));
    var ranking_canvas1 = echarts.init(document.getElementById('ranking_canvas1'));
    var ranking_canvas2 = echarts.init(document.getElementById('ranking_canvas2'));
    // 指定图表的配置项和数据
    var colors = ['#491ef5', '#f51eac', '#1EA5F5', '#967FF3', '#D8D0F8', '#EFA8D7', '#B5DCF4', '#FFE1F5'];
    var option = {
        color: colors,
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                animation: false,
                label: {
                    precision: 0,
                    backgroundColor: '#505765'
                }
            }
        },
        // legend: {
        //     left: 'center',
        //     data: ['商品1', '移动端']
        // },
        xAxis: {
            type: 'category',
            name: '时间',
            boundaryGap: false,
            axisLine: {
                lineStyle: {
                    color: '#999'
                },
                onZero: false
            },
            data: []
        },
        grid: {
            left: '3%',
            right: '6%',
            bottom: '3%',
            containLabel: true
        },
        yAxis: {
            type: 'value',
            name: '排名',
            inverse: true,
            min: 1,
            minInterval: 1,
            nameLocation: 'start',
            axisLine: {
                lineStyle: {
                    color: '#999'
                },
            },
            axisLabel: {
                formatter: '第{value}名'
            }
        },
        series: [{
            name: '商品1',
            type: 'line',
            yAxisIndex: 0,
            data: []
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    ranking_canvas.setOption(option);
    ranking_canvas1.setOption(option);
    ranking_canvas2.setOption(option);
    getTable(2);
    //导航切换
    $('.dd_nav').on('click', '.nav_li', function () {
        var parentUl = $('.dd_nav');
        var key = $(this).data('key');
        parentUl.find('.nav_li').removeClass('active');
        $(this).addClass('active');
        $('.ran_table').hide();
        if (key === 1) {
            $('#rankingTable').show();
        }
        if (key === 2) {
            $('#keywordTable').show();
        }
        if (key === 3) {
            $('#activityTable').show();
        }
    });
    $('.nav_li1').one('click', function () {
        getTable(1);
    });
    $('.nav_li3').one('click', function () {
        getTable(3);
    });

    $('.add_type .radio-inline').on('click', function (i) {

        $('.form1,.form2,.form3').hide();
        var formVal = $(this).find('input').val();
        $('.form' + formVal).show();
    });
    //trendAjax
    //查看点击
    var good_id = 0;
    var keyword_id = 0;
    function arrFun(arr) {
        var arr1 = [];
        arr&&arr.forEach(function (item,i) {
            if(item*1===0){
                item = '-';
            }
            arr1.push(item);
        });
        return arr1;
    }
    var trendFun = function () {
        //类目趋势
        $('.canvas_div').hide();
        if($('.nav_li.active').data('key')==1){
            $('#ranking_canvas').show();
            ranking_canvas.showLoading();
            $.post('/trend/cat-trend',{days:$('input[name=time]:checked').val(),good_id:good_id},function (param) {
                ranking_canvas.hideLoading();
                if(param.result*1===1){
                    $('.goods_img_span').attr('src',param.data.good_pic);
                    $('.goods_name_span').html('<a href="http://mobile.yangkeduo.com/goods.html?goods_id='+param.data.sku+'" target="_blank">' +param.data.good_name+'</a>');
                    $('.goods_id_span').text(param.data.sku);
                    $('.goods_type').text('类目：'+param.data.cat_name);
                    //图标渲染
                    ranking_canvas.setOption({
                        xAxis:{
                            data: param.data.date
                        },
                        yAxis: [{
                            type: 'value',
                            name: '排名',
                            inverse:true,
                            min:1,
                            minInterval: 1,
                            nameLocation:'start',
                            axisLabel:{
                                formatter:'第{value}名'
                            }
                        }],
                        series:[{
                            name: '一级类目排名',
                            type: 'line',
                            yAxisIndex: 0,
                            data:arrFun(param.data.cat_level_1)
                        },{
                            name: '二级类目排名',
                            type: 'line',
                            yAxisIndex: 0,
                            data:arrFun(param.data.cat_level_2)
                        },{
                            name: '三级类目排名',
                            type: 'line',
                            yAxisIndex: 0,
                            data:arrFun(param.data.cat_level_3)
                        }
                        ]
                    });
                }else{
                    alertError(param.msg)
                }
            }).error(function () {
                ranking_canvas.hideLoading();
//                alertError('系统错误');
            })
        }
        //关键词趋势
        if($('.nav_li.active').data('key')==2){
            $('#ranking_canvas1').show();
            ranking_canvas1.showLoading();
            $.post('/trend/keyword-trend',{days:$('input[name=time]:checked').val(),good_id:good_id,keyword_id:keyword_id},function (param) {
                ranking_canvas1.hideLoading();
                if(param.result*1===1){
                    $('.goods_img_span').attr('src',param.data.good_pic);
                    $('.goods_name_span').html('<a href="http://mobile.yangkeduo.com/goods.html?goods_id='+param.data.sku+'" target="_blank">' +param.data.good_name+'</a>');
                    $('.goods_id_span').text(param.data.sku);
                    $('.goods_type').text('关键词：'+param.data.keyword);
                    //图标渲染
                    ranking_canvas1.setOption({
                        xAxis:{
                            data: param.data.date
                        },
                        yAxis: [{
                            type: 'value',
                            name: '排名',
                            inverse:true,
                            min:1,
                            minInterval: 1,
                            nameLocation:'start',
                            axisLabel:{
                                formatter:'第{value}名'
                            }
                        }],
                        series:[{
                            name: '关键词排名',
                            type: 'line',
                            yAxisIndex: 0,
                            data: arrFun(param.data.page_order)
                        },
                        ]
                    });
                }else{
                    alertError(param.msg)
                }
            }).error(function () {
                ranking_canvas1.hideLoading();
//                alertError('系统错误');
            })
        }
        //活动趋势
        if($('.nav_li.active').data('key')==3){
            $('#ranking_canvas2').show();
            ranking_canvas2.showLoading();
            $.post('/trend/active-trend',{days:$('input[name=time]:checked').val(),good_id:good_id},function (param) {
                ranking_canvas2.hideLoading();
                if(param.result*1===1){
                    $('.goods_img_span').attr('src',param.data.good_pic);
                    $('.goods_name_span').html('<a href="http://mobile.yangkeduo.com/goods.html?goods_id='+param.data.sku+'" target="_blank">' +param.data.good_name+'</a>');
                    $('.goods_id_span').text(param.data.sku);
                    $('.goods_type').text('类目：'+param.data.cat_name);
                    //图标渲染
                    ranking_canvas2.setOption({
                        xAxis:{
                            data: param.data.date,
                        },
                        yAxis: [{
                            type: 'value',
                            name: '排名',
                            inverse:true,
                            min:1,
                            minInterval: 1,
                            nameLocation:'start',
                            axisLabel:{
                                formatter:'第{value}名'
                            }
                        }],
                        series:[{
                            name: '关键词排名',
                            type: 'line',
                            yAxisIndex: 0,
                            data: arrFun(param.data.page_order)
                        }
                        ]
                    });
                }else{
                    alertError(param.msg)
                }
            }).error(function () {
                ranking_canvas2.hideLoading();
//                alertError('系统错误');
            })
        }
    };
    $('.tableBox').on('click', '.query-td', function () {
        $('#queryModal').modal('show');
        good_id = $(this).data('good_id');
        keyword_id = $(this).data('keyword_id');
        trendFun();

    });


    //渲染表格
    var index = 0;
    var renderData = function (url, form, callback, key, cat_text) {
        $('#addGoodModal').modal('hide');
        $('.loadingLog').show();
        $.post(url, form, function (param) {
            if (param.result * 1) {
                index = 0;
                $('.loadingLog').hide().find('p').remove();
                callback(key, cat_text);
            } else {
                index++;
                if(index<3&&param.msg !== '超过监控上限'){
                    $('.loadingLog').find('p').remove();
                    $('.loadingLog .spinner').prepend('<p>第'+(index+1)+'次排名查找...</p>');
                    renderData(url, form, callback, key, cat_text);
                }else{
                    index = 0;
                    $('.loadingLog').hide().find('p').remove();
                    alertError(param.msg);
                }
            }
        }).error(function () {
            index = 0;
            $('.loadingLog').hide().find('p').remove();
//            alertError('系统错误')
        })
    };
    //添加商品点击
    $('.dd_add_btn').on('click',function () {
        var val = $('.nav_li.active').data('key');
        var input = $('.add_type input');
        input.removeAttr('checked');
        input.each(function (i,item) {
            if($(item).val()==val){
                $(item).click();
            }
        })
    });
    //添加商品点击
    $('.add_keywords_sure').on('click', function () {
        if ($('#ranking1').is(':checked')) {
            if (form_btn($('.form1'))) {
                var category1 = $('#category1').val() + ',';
                var category2 = $('#category2').val() + ',';
                var category3 = $('#category3').val();
                var formData = {
                    cat_list: category1 + category2 + category3,
                    sku: $('#category_sku').val(),
                    is_home_cat: 1,
                };
                renderData('/monitor-search/cat-monitor', formData, getTable, 1);
            }
            if (!$('.nav_li1').hasClass('active')) $('.nav_li1').click();
        }
        var flagKey = true;
        if ($('#ranking2').is(':checked')) {
            flagKey = false;
            if (form_btn($('.form2'))) {
                flagKey = true;
                var formDataKey = {
                    keyword: $('#keyword_form').val(),
                    sku: $('#keyword_sku').val(),
                    sort: $('.keyword_type .keyword.active').attr('key'),
                    filter: $('.price_wrap .keyword_btn1.active').text(),
                    is_group: $('.only_btn').hasClass('active') ? 1 : 0,
                };
                renderData('/monitor-search/keyword-monitor', formDataKey, getTable, 2);
            }
            if (!$('.nav_li2').hasClass('active')) $('.nav_li2').click();
        }
        if ($('#ranking3').is(':checked')) {
            if (form_btn($('.form3'))) {
                var formDataAct = {
                    active_type: $('.activity_home').find('.keyword_btn.active').data('key'),
                    sku: $('#actSku').val(),
                    entrance: $('#activity1').val(),
                    tab_id: $('#activity2').val(),
                    cat_second: $('#activity2').find('option:selected').text(),
                };
                renderData('/monitor-search/active-monitor', formDataAct, getTable, 3);
            }
            if (!$('.nav_li3').hasClass('active')) $('.nav_li3').click();
        }
    });
    //置顶、取消置顶
    $('.dd_content_wrap').on('click', '.close_top', function () {
        var good_id = $(this).data('good_id');
        var key = $('.nav_li.active').data('key');
        var key_temp = key;
        if (key == 1) {
            key_temp = 2;
        }
        if (key == 2) {
            key_temp = 1;
        }
        $.post('/trend/goods-first', {search_type: key_temp, good_id: good_id}, function (param) {
            if (param.result * 1 == 1) {
                alertSuccess(param.msg);
                getTable(key);
            } else {
                alertError(param.msg);
            }
        }).error(function () {
//            alertError('系统错误');
        })

    });

    //添加关键词

    var keywordControl = function (data) {

        var good_id = data.good_id;
        var keyword_id = $("input#addKeyword1").val();
        setTimeout(function(){$('.loadingLog').show()},1);

        $.post('/monitor-search/keyword-add', {keyword: keyword_id, good_id: good_id}, function (param) {
            if (param.result * 1 === 1) {
                alertSuccess(param.msg);
                getTable(2);
                $('#addKeyword1').val('');
            } else {
                $('.loadingLog').hide();
                alertError(param.msg);
            }
        }).error(function () {
            $('.loadingLog').hide();
//            alertError('系统错误');
        })

    };
    var deleteId = 0;
    $(document).on('click', '.KeywordBtn', function () {
        deleteId = $(this).find('.KeywordBtn1').data('good_id');
    });
    $('#addKeywordModal').on('click', '.add_keywords_sure1', function () {
        var data = {good_id: deleteId};
        keywordControl(data);
        $('#addKeywordModal').modal('hide');
    });
    $('#title_i').popover();


    //取消监控
    $('.dd_content_wrap').on('click', '.unfollow', function () {

        var good_id = $(this).data('good_id');
        var keyword_id = $(this).data('keyword_id');
        var key = $('.nav_li.active').data('key');
        var key_temp = key;
        if (key == 1) {
            key_temp = 2;
        }
        if (key == 2) {
            key_temp = 1;
        }
        $.post('/trend/monitor-cancel', {
            search_type: key_temp,
            good_id: good_id,
            keyword_id: keyword_id
        }, function (param) {
            if (param.result * 1 == 1) {
                alertSuccess(param.msg);
                getTable(key);
            } else {
                alertError(param.msg);
            }
        }).error(function () {
//            alertError('系统错误');
        })

    });


    //delete_sure_btn
    var closeControl = function (data) {
        var good_id = data.good_id;
        var keyword_id = '';
        var key = $('.nav_li.active').data('key');
        var key_temp = key;
        if (key == 1) {
            key_temp = 2;
        }
        if (key == 2) {
            key_temp = 1;
        }
        $.post('/trend/monitor-cancel', {search_type: key_temp, good_id: good_id, keyword_id: ''}, function (param) {
            if (param.result * 1 == 1) {
                alertSuccess(param.msg);
                getTable(key);
            } else {
                alertError(param.msg);
            }
        }).error(function () {
//            alertError('系统错误');
        })
    };

    var deleteId = 0;
    $(document).on('click', '.deleteBtn', function () {
        deleteId = $(this).find('.glyphicon-trash').data('good_id');
    });
    $('#deleteTableModal').on('click', '.delete_sure_btn', function () {
        var data = {good_id: deleteId};
        closeControl(data);
        $('#deleteTableModal').modal('hide');
    });
    $('#title_i').popover();


//时间切换
    $('.time_hea input[name=time]').change(function () {
        trendFun();
    });

});

