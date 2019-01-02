$(function () {
    //错误与成功
    function successInfo(param){
        param.closest('.pay-option').find('.error-info').remove();
        param.closest('.pay-option').find('.success-info').remove();
        param.closest('.pay-option').append($('<i>').addClass('success-info glyphicon glyphicon-ok-circle'));
    }
    function errorInfo(param,text){
        param.closest('.pay-option').find('.error-info').remove();
        param.closest('.pay-option').find('.success-info').remove();
        param.closest('.pay-option').append($('<i>').addClass('error-info glyphicon glyphicon-remove-circle'));
        param.closest('.pay-option').append($('<div>').addClass('error-info').text(text));
    }
    //支付密码修改
    $('.pay-header .btn').on('click',function(){
        $('.pay-header').find('.btn').removeClass('activeBtn');
        $(this).addClass('activeBtn');
    });
    var classNameArr=['beforePasLogin','newPasLogin','newPasLogin1','beforePasPay','newPasPay','newPasPay1'];
    //登录密码校验
    function beforePasLogin(item){
        var param=$(item).val();
        if(param==""){
            errorInfo($(item),'请输入原登录密码');
        }else{
            var numPassword=param.split('');
            if(numPassword.length<6||numPassword.length>16){
                errorInfo($(item),'密码为6-16位')
            }else{
                successInfo($(item))
            }
        }
    }
    function newPasLogin(item){
        var param=$(item).val();
        if(param==""){
            errorInfo($(item),'请输入新登录密码');
        }else{
            var numPassword=param.split('');
            if(numPassword.length<6||numPassword.length>16){
                errorInfo($(item),'密码为6-16位')
            }else{
                successInfo($(item))
            }
        }
    }
    function newPasLogin1(item){
        var param=$(item).val();
        var param1=$(item).closest('.row').prev('.row').find('.form-control').val();
        if(param==""){
            errorInfo($(item),'请再次输入密码');
        }else{
            var numPassword=param.split('');
            if(numPassword.length<6||numPassword.length>16){
                errorInfo($(item),'密码为6-16位')
            }else if(param!=param1){
                errorInfo($(item),'两次输入密码不一致')
            }else{
                successInfo($(item))
            }
        }
    }
    function beforePasPay(item){
        var param=$(item).val();
        if(param==""){
            errorInfo($(item),'请输入原支付密码');
        }else{
            var numPassword=param.split('');
            var arrFlag=[];
            numPassword.forEach(function(item,i){
                if(!isNaN(item)){
                    arrFlag.push(item)
                }
            });
            if(numPassword.length!=6||arrFlag.length!=6){
                errorInfo($(item),'支付密码为6位数字')
            }else{
                successInfo($(item))
            }
        }
    }
    function newPasPay(item){
        var param=$(item).val();
        if(param==""){
            errorInfo($(item),'请输入新支付密码');
        }else{
            var numPassword=param.split('');
            var arrFlag=[];
            numPassword.forEach(function(item,i){
                if(!isNaN(item)){
                    arrFlag.push(item)
                }
            });
            if(numPassword.length!=6||arrFlag.length!=6){
                errorInfo($(item),'支付密码为6位数字')
            }else{
                successInfo($(item))
            }
        }
    }
    function newPasPay1(item){
        var param=$(item).val();
        var param1=$(item).closest('.row').prev('.row').find('.form-control').val();
        if(param==""){
            errorInfo($(item),'请再次输入支付密码');
        }else{
            var numPassword=param.split('');
            var arrFlag=[];
            numPassword.forEach(function(item,i){
                if(!isNaN(item)){
                    arrFlag.push(item)
                }
            });
            if(numPassword.length!=6||arrFlag.length!=6){
                errorInfo($(item),'支付密码为6位数字')
            }else if(param!=param1){
                errorInfo($(item),'两次输入密码不一致')
            }else{
                successInfo($(item))
            }
        }
    }
    $('.pay-option .beforePasLogin').on('blur',function(){
        beforePasLogin(this)
    });
    $('.pay-option .newPasLogin').on('blur',function(){
        newPasLogin(this)
    });
    $('.pay-option .newPasLogin1').on('blur',function(){
        newPasLogin1(this)
    });
    $('.pay-option .beforePasPay').on('blur',function(){
        pay_set==1?beforePasPay(this):beforePasLogin(this);
    });
    $('.pay-option .newPasPay').on('blur',function(){
        newPasPay(this)
    });
    $('.pay-option .newPasPay1').on('blur',function(){
        newPasPay1(this)
    });

    $('.login-password-btn').on('click',function(){
        $('.login-form').show();
        $('.pay-form').hide();
    });
    $('.pay-password-btn').on('click',function(){
        $('.login-form').hide();
        $('.pay-form').show();
    });
    //支付密码
    var payUrlSearch=window.location.search;
    var payUrlFlag=payUrlSearch.indexOf('?url=')!=-1?true:false;
    if(payUrlFlag){
        $('.pay-password-btn').click();
    }



    //支付密码提交
    $('.loginPasswordSubmit').on('click',function(){
        $('.login-form').find('input').blur();
        if(!!$('.login-form').find('i.error-info').length){
            console.log($('.login-form').find('div').hasClass('.error-info'),68796);
        }else{
            $.ajax({
                type: 'POST',
                url:'/personal/password-reset',
                data:{
                    password:$('.beforePasLogin').val(),
                    pass_new:$('.newPasLogin').val(),
                    pass_new_confirm:$('.newPasLogin1').val(),
                },
                success: function (param) {
                    if (param.result) {
                        alertInfo(param.msg);
                        window.location.reload();
                    } else {
                        alertInfo(param.msg);
                    }
                },
                error: function (){
                }
            })
        }
    });
    $('.payPasswordSubmit').on('click',function(){
        $('.pay-form').find('input').blur();
        if(!!$('.pay-form').find('i.error-info').length){
            console.log($('.pay-form').find('div').hasClass('.error-info'),68796);
        }else{
            $.ajax({
                type: 'POST',
                url: urlStart + '/personal/pay-password-set',
                data:{
                    password_login:$('.beforePasPay').val(),
                    password_pay:$('.newPasPay').val(),
                    password_confirm:$('.newPasPay1').val(),
                    pay_set:pay_set,
                },
                success: function (param) {
                    if (param.result) {
                        alertInfo('success',param.msg);
                        if(payUrlFlag){
                            var payUrl=payUrlSearch.slice(payUrlSearch.indexOf('=')+1,payUrlSearch.length);
                            window.location.href=payUrl+'?backKey=1'
                        }
                    } else {
                        alertInfo('error',param.msg);
                    }
                },
                error: function (){
                }
            })
        }
    });

    $('.pay-content input').on('focus',function(){
        $(this).closest('.pay-option').find('.error-info').remove();
        $(this).closest('.pay-option').find('.success-info').remove();
    });
});