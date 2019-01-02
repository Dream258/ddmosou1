$(function () {
    var parentsObj = {
      '1':$('.ran_wrap'),
      '2':$('.ran_col_wrap'),
      '3':$('.cj_wrap'),
    };
    //用户服务购买及时长选择
    var serviceInfo = function (formdata) {
        var key = formdata.service_type;
        var parent = parentsObj[key];
        parent.find('.loadingLog').show();
        parent.find('.num_input').val(0);
        $.post('/personal/service-info',formdata,function (param) {
            if(param.result*1===1){
                var data=param.data;
                parent.find('.price_before').text(data.original_price);
                parent.find('.price').text(data.price);
                parent.find('.query_num').text(data.service_limit);
                parent.find('.query_fun_wrap .fun_explain').text(data.remark);
                parent.find('.fun_item').text(data.service_name);
                parent.find('.loadingLog').hide();
            }else{
                alertError(param.msg)
            }
        }).error(function () {
//            alertError('系统错误');
        })
    };

    var formList = function (service_type,month) {
        month = month||$('.vip_time_btn.active:visible').data('key');
        service_type   = service_type||$('.fun_btn.active').data('key');
        var formData = {
            month:month,
            service_type:service_type,
        };
        serviceInfo(formData);
    };

    var fun_btn = $('.fun_btn');
    fun_btn.on('click',function () {
        $('.vip_wrap').hide();
        var parent = parentsObj[$(this).data('key')];
        parent.show();
    }).one('click',function () {
        var service_type  = $(this).data('key');
        var parent = parentsObj[service_type];
        var month = parent.find('.vip_time_btn.active').data('key');
        formList(service_type,month);
    });
    if(window.location.search.indexOf('?id=3')!==-1){
        fun_btn.removeClass('active');
        fun_btn.each(function (i,item) {
            if($(item).data('key')*1===3){
                $(item).addClass('active');
            }
        });
    }
    $('.fun_btn.active').click();
    //月份点击
    $('.vip_time_btn').on('click',function () {
        if(!$(this).hasClass('active')){
            var parent = $(this).parents('.vip_wrap');
            parent.find('.vip_time .vip_time_btn').removeClass('active');
            $(this).addClass('active');
            formList();
        }
    });

    //选择支付方式
    $('.pay_type_btn').on('click',function () {
        var parent = $(this).parents('.vip_wrap');
        parent.find('.pay_type .pay_type_btn').removeClass('active');
        $(this).addClass('active');
    });


    //监控扩容价格计算
    var priceInfo = function (formdata) {
        $('.loadingLog').show();
        $.post('/personal/price-fix',formdata,function (param) {
            if(param.result*1===1){
                var data=param.data;
                $('.price').text(data.price);
                $('.price_before').text(data.original_price);
                $('.loadingLog').hide();
            }else{
                $('.loadingLog').hide();
                alertError(param.msg)
            }
        }).error(function () {
            $('.loadingLog').hide();
//            alertError('系统错误');
        })
    };
    //扩容
    $('.alone_kr_btn').on('click',function () {
       $('.time_row,.input_box1,.input_box').addClass('alone_kr');
    });
    $('.vip_kr_btn').on('click',function () {
       $('.time_row,.input_box1,.input_box').removeClass('alone_kr');
    });

    $('.num_input').on('input',function () {
        var add_num = $(this).val();
        var service_type = $('.fun_btn.active').data('key');
        var priceData = {
            num:add_num,
            month:parentsObj[service_type].find('.vip_time_btn.active').data('key'),
            service_type:service_type,
        };
        priceInfo(priceData);
    });


    //确认支付
    var personPay = function (payData,_this) {
        $.post('/recharge/platform-pay',payData,function (param) {
            $(_this).button('reset');
            if (param.indexOf('result')===-1){
                document.write(param);
            }else {
                var data = JSON.parse(param);
                if (data.result==1){
                    alertSuccess("会员购买成功");
                    setTimeout(function(){window.location.href = data.url},2000);
                }else {
                    alertError(data.msg);
                }
            }

        },'html').error(function () {
            $(_this).button('reset');
//            alertError('系统错误');
        })
    };
    var shopName = $('#shopName');
    $('.vip_buy_btn').on('click',function () {
        var _this = this;
        var service_type = $('.fun_btn.active').data('key');
        var parent = $(_this).parents('.vip_wrap');
        var month = parent.find('.vip_time_btn.active').data('key');
        var add_num = parent.find('.num_input').val()||'';
        var price = parent.find('.price').text();
        var pay_type = parent.find('.pay_type_btn.active').data('key');
        var payData = {
            pay_type:pay_type,
            service_type:service_type,
            month:month,
            add_num:add_num,
            amount:price,

        };
        if($(_this).hasClass('vip_buy_btn1')){
            var shopVal = $.trim(shopName.val());
            if(shopVal){
                payData.name = shopVal;
                $(_this).button('loading');
                personPay(payData,_this);
            }else{
                shopName.addClass('error');
                alertError('用户名必填');
            }
        }else{
            $(_this).button('loading');
            personPay(payData,_this);
        }
    });
    shopName.on('focus',function () {
       $(this).removeClass('error');
    });

    function swiperFun() {
        var mySwiper = new Swiper('.swiper-container',{
            slidesPerView :4.8,
            spaceBetween : 20,
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
            observer:true,
            // 如果需要滚动条
            scrollbar: {
                el: '.swiper-scrollbar',
            },
        });
    }
    swiperFun();
});