//数组去重
function unRepeat(arr,value){
    var arrNew=[];
    arr.forEach(function(item){
        if(item!=$.trim(value)){
            arrNew.push(item)
        }
    });
    if(arr.length==arrNew.length){
        arr.push($.trim(value))
    }
    return arr;
}
//保存关键词和suk
var keepData = function(skuId,skuVal){
    var input = $('#'+skuId);
    var dataSku = window.localStorage.getItem(skuId);

    if(input.hasClass('keyword')&&skuVal){
        var objUserKey = dataSku?JSON.parse(dataSku):{};
        var keywordVal = input.val();
        if(dataSku&&objUserKey[keywordVal]){
            objUserKey[keywordVal]=unRepeat(objUserKey[keywordVal],skuVal);
            window.localStorage.setItem(skuId,JSON.stringify(objUserKey))
        }else{
            objUserKey[keywordVal]=[skuVal];
            window.localStorage.setItem(skuId,JSON.stringify(objUserKey))
        }
    }else{
        var arrValue = dataSku?JSON.parse(dataSku):[];
        var valSku = input.val();
        var skuFlag = true;
        if(arrValue){
            arrValue.forEach(function (item) {
                if($.trim(item) === $.trim(valSku)){
                    skuFlag = false;
                }
            });
            if(skuFlag){
                arrValue.unshift(valSku);
            }
        }
        window.localStorage.setItem(skuId,JSON.stringify(arrValue));
    }
};
$(function () {
	
    //导航切换
    $('.nav-tabs .nav_li').on('click',function () {
        var formWrap = $('.form_wrap');
        var navLi = $('.nav-tabs .nav_li');
        var indexLi = $(this).data('index');

        navLi.removeClass('active');
        $(this).addClass('active');
     
        formWrap.hide();
      
        formWrap.each(function (i,item) {
            // alert(i);
            var indexItem = $(item).data('index');
            if(indexItem == indexLi) {
            
            	   $(".result_wrap .result_wrap_table").hide();
                $(item).show();
             
            }
        })

    });
    //渲染关键词和sku
    function ulRender(_this,id,sku){//form-control和keywordId(选择传)
        var ul = $(_this).parents('.user-ul-box').find('.user-ul');
        var ulParent = $(ul).closest('.user-ul-box');
        if(sku){id='actSku'}
        var skuData = window.localStorage.getItem(id);
        var idVal = $('#'+id).val();
        //把ul清除，显示自己下的ul
        $('.user-ul').hide();
        ulParent.find('.user-ul').show();
        $(ul).find('li').remove();
        //只有sku
        if(sku){
            skuData = skuData?JSON.parse(skuData):[];
            skuData.forEach(function (item,i) {
                $(ul).append($('<li>').text(item).append($('<i>').addClass('glyphicon glyphicon-trash delete_li')));
            });
        }else{
            //keyword
            if($(_this).hasClass('keyword')){
                skuData = skuData?JSON.parse(skuData):{};
                $.each(skuData,function(key,val) {
                    $(ul).append($('<li>').text(key).append($('<i>').addClass('glyphicon glyphicon-trash delete_li')));
                });
            }
            //keyword关联sku
            if($(_this).hasClass('keyword_sku')){
                skuData = skuData?JSON.parse(skuData):{};
                var arrData = skuData&&skuData[idVal]?skuData[idVal]:[];
                arrData.forEach(function (item,i) {
                    $(ul).append($('<li>').text(item).append($('<i>').addClass('glyphicon glyphicon-trash delete_li')));
                });
            }
        }
    }

    //li点击
    $('.user-ul').on('click','li',function(){
        var inputDom = $(this).closest('.user-ul-box').find('.form-control');
        inputDom.val($(this).text());
        inputDom.parents('.item_option').find('.inputError').remove();
        if(inputDom.attr('id')==='keyword_form'){$('#keyword_form').blur();}
        $('.user-ul').hide();
        return false;
    }).on('click','.delete_li',function(){
        var liText=$(this).closest('li').text();
        var parentUl=$(this).closest('li').closest('.user-ul');
        var keywordDom = $('.form-control.keyword:visible');
        var userKey = keywordDom.eq(0).attr('id');
        var userValue = keywordDom.val()||'actSku';
        var objX = {
            category_sku:'category3',
            keyword_form:'keyword_form',
            keyword_sku:'keyword_form',
            actSku:$('#activity2').is(':visible')?'activity2':'',
        };
        var idX=$(this).parents('.item_option').find('.form-control').attr('id'),
            liKeyword = $('#'+objX[idX]).val();
        var obj=JSON.parse(window.localStorage.getItem(userKey));
        var userValueArr=JSON.parse(window.localStorage.getItem('actSku'));
        if(parentUl.hasClass('keyword-ul')){
            $.each(obj,function(key,value){
                if(key==liText){
                    delete(obj[key]);
                }
            });
        }else{
            if(!liKeyword){
                console.log(userValueArr);
                userValueArr&&userValueArr.forEach(function (item1,i1) {
                    if(item1 === liText){
                        userValueArr.splice(i1,1);
                    }
                })
            }else{
                $.each(obj,function(key,value){
                    if(key==liKeyword){
                        value.forEach(function(item,i){
                            if(item==liText){
                                obj[key].splice(i,1);
                            }
                        })
                    }
                })
            }
        }
        $(this).parents('li').remove();
        window.localStorage.setItem(userKey,JSON.stringify(obj));
        window.localStorage.setItem(userValue,JSON.stringify(userValueArr));
        return false;
    });
    $('.user-ul-box .form-control,.user-ul').on('click',false);
    //获得焦点显示ul
    $('#category_sku').on('focus',function(){
        ulRender(this,'category3');
    });
    $('#keyword_form').on('focus',function(){
        ulRender(this,'keyword_form');
    });
    $('#keyword_sku').on('focus',function(){
        ulRender(this,'keyword_form');
    });
    $('#actSku').on('focus',function(){
        if($('#activity2').is(':visible')){
            ulRender(this,'activity2');
        }else{
            ulRender(this,'activity2',1);
        }
    });

    $(document).on('click',function () {
       $('.user-ul') .hide();
    });

    var urlSearch = window.location.search;
    if(urlSearch.indexOf('_type=')!==-1){
        $('.register_btn').click();
    }
    var start = urlSearch.indexOf('?s_type=')===-1?urlSearch.indexOf('?s_type='):urlSearch.indexOf('?d_type=');
    var end = urlSearch.length;
    var share_code = urlSearch.slice(8,end);
    if(urlSearch.indexOf('?s_type=')!==-1){
        window.sessionStorage.setItem('s_type',share_code)
    }
    if(urlSearch.indexOf('?d_type=')!==-1){
        window.sessionStorage.setItem('d_type',share_code)
    }
    $('.close_float').click(function () {
        $('.fix_bottom').hide();
    })
    //提交反馈意见
    $(".feedback_submit").on('click',function () {
    	var formDataKey = {
                "suggestions_sort":"1",
                "suggestions":$('#content').val(),
                "contact_way":$('#contact').val()
        };
    	$.get("suggestion/addSuggestions",formDataKey,function(url){
    	alert("反馈成功");
    	});
    });
});