$(function(){
	var code ; //在全局定义验证码
    var urlStart="";
    //错误与成功
    function successInfo(param){
        param.closest('.login-input-wrapper').find('.error-info').remove();
        param.closest('.login-input-wrapper').find('.success-info').remove();
        param.closest('.login-input-wrapper').append($('<i>').addClass('success-info glyphicon glyphicon-ok-circle'));
        if(param.hasClass('input1')){
            param.closest('.login-input-wrapper').find('.success-info').css('right',"52%");
        }
    }
    function errorInfo(param,text){
        param.closest('.login-input-wrapper').find('.error-info').remove();
        param.closest('.login-input-wrapper').find('.success-info').remove();
        param.closest('.login-input-wrapper').append($('<div>').addClass('error-info').text(text));
    }
    //清空提示方法
    function clearInfo(param){
        param.closest('.login-input-wrapper').find('.error-info').remove();
        param.closest('.login-input-wrapper').find('.success-info').remove();
    }

    //手机号验证
    function checkPhone(phone){
        if(!(/^1[2345678]\d{9}$/.test(phone))){
            return false;
        }else{
            return true;
        }
    }
    //btn倒计时
    var countdown=59;
    function settime(val,t) {
        if (countdown == 0) {
            val.attr("disabled",false);
            val.text("发送验证码");
            countdown = 60;
            clearInterval(t)
        } else {
            val.text("重新发送(" + countdown + ")");
            countdown--;
        }
    }
    //forget倒计时
    var countdown1=59;
    function settime1(val,t) {
        if (countdown1 == 0) {
            val.attr("disabled",false);
            val.text("发送验证码");
            countdown1 = 60;
            clearInterval(t)
        } else {
            val.text("重新发送(" + countdown1 + ")");
            countdown1--;
        }
    }

    //登录注册
    $('.login-form-title span').on('click',function(){
        $('.login-form-title').find('span').removeClass('active');
        $(this).addClass('active')
    });
    $('.jd-login-btn').on('click',function(){
        $('.login-form').css('transform','translate3d(0,0,0)').show();
        $('.register-form').css('transform','translate3d(100%,0,0)').hide();
    });
    $('.jd-register-btn').on('click',function(){
        $('.login-form').css('transform','translate3d(100%,0,0)').hide();
        $('.register-form').css('transform','translate3d(0,0,0)').show();
        $('.before-btn').click();
    });

    //登录
    function yanzheng(param,text){
        if(!param.val()){
            errorInfo(param,text)
        }
    }
    //input 获得焦点
    $('.ranking-wrapper input').on('focus',function(){
        $(this).closest('.login-input-wrapper').find('.error-info').text('').hide();
    });
    //input 失去焦点
    $('#login-user').on('blur',function(){
        if(checkPhone($(this).val())){
            successInfo($(this))
        }else{
            if($(this).val()==""){
                errorInfo($(this),'请输入手机号')
            }else{
                errorInfo($(this),'请输入正确的手机号')
            }
        }
    });
    $('#login-password').on('blur',function(){
        var numPassword=$(this).val().split('');
        if(numPassword.length<6||numPassword.length>16){
            if($(this).val()==""){
                errorInfo($(this),'请输入密码')
            }else{
                errorInfo($(this),'密码为6-16位')
            }
        }else{
            successInfo($(this))
        }
    }).on('keydown',function(e){
        if (e.keyCode == "13") {//keyCode=13是回车键
            $('.login-submit').click();
        }
    });
  //登录提交
    $('.login-submit').on('click',function(){
        var formData={mphone:$('#login-user').val(),password:$('#login-password').val()};
        $('#login-user').blur();
        $('#login-password').blur();
        if($('.login-form').find('div').hasClass('error-info')){
            alert("本来就错了");
        }else{
            $('.jd_form_loading').show();
            $.ajax({
                url:'/api/login_pc',
                data:"mphone="+$('#login-user').val()+"&password="+$('#login-password').val(),
                type:'POST',
                success:function(param){
                    $('.jd_form_loading').hide();
                    if(param.result == 1){
                        alertSuccess('登录成功');
                        newUserInfo();
                        $('.login-wrapper').fadeOut('slow');
                        $('body').removeClass('modal-open');
                       // $(".user_header_box").css("display","block");
                       // $(".user_header_box_login").css("display","none");
                        // location.reload();
                        //window.location.href = "/templates/index.html";
                        window.location.reload();
                    }else{
                        alertError(param.msg)
                    }
                },
                error:function(){
                    $('.jd_form_loading').hide();
                    alertError('系统错误')
                }
            })
        }
    });

    //退出
    $('#exit_btn').click(function(){
 	   $.ajax({
 		   url:"login/exit.action",
 		   success:function()
 		   {
 			   window.location.reload();
 		   }
 	   })
    });

    //注册
    $('#register-user').on('blur',function(){
        if(checkPhone($(this).val())){
            successInfo($(this))
        }else{
            if($(this).val()==""){
                errorInfo($(this),'请输入手机号')
            }else{
                errorInfo($(this),'请输入正确的手机号!')
            }
        }
    });
   // var tpcode=${sessionScope.code};
   /* $('#register-tp').on('blur',function(){
     // 
    	 $.get("user/checkImage.do",function(code){
    		 if(code==$("#register-tp").val()){
    		 }else{
    			 errorInfo($(this),'图片验证码输入有误') ;
    		 }
    		 
    	 });
       
       
    });
    $('#register-info').on('blur',function(){
        if(!$(this).val()){
            errorInfo($(this),'请输入验证码')
        }else{
            clearInfo($(this));
        }
    });

    //获取验证码ajax
    var codeHash2=0;
    function codeAjax(){
        $('#code-img').attr('src','user/code.do?refresh=1&_='+Date.parse(new Date()));
       
    }
    $('#code-btn').on('click',function(){
        codeAjax();
    });

    //短信验证码
    function codeInfoAjax(t,type){
        var formdata={
            mphone:type=='1'?$('#register-user').val():$('#forget-user').val(),
            verifyCode:type=='1'?$('#register-tp').val():'',
            type:type
        };
        $.ajax({
            type:'POST',
            url:urlStart+'/site/send-mphone-code',
            data:formdata,
            success:function(param){
                if(param.result==0){
                    if(type=="1"){
                        $('.register-phone-btn').attr("disabled",false).text("发送验证码");
                        countdown = 60;
                        clearInterval(t);
                    }else{
                        $('#forget-code-btn').attr("disabled",false).text("发送验证码");
                        countdown1 = 60;
                        clearInterval(t);
                    }
                }
            },
            error:function(param){
                if(type=="1"){
                    $('.register-phone-btn').attr("disabled",false).text("发送验证码");
                    countdown = 60;
                    clearInterval(t);
                }else{
                    $('.forget-code-btn').attr("disabled",false).text("发送验证码");
                    countdown1 = 60;
                    clearInterval(t);
                }
            }
        })

    }

    //注册 下一步
    $('.register-next').on('click',function(){
        $('#register-user').blur();
        $('#register-tp').blur();
        $('#register-info').blur();
        if(!$('.register-form').find('div').hasClass('error-info')){
            $('.jd_form_loading').show();
            $.ajax({
                type:'POST',
                url:urlStart+'/site/check-code',
                data:{validate_code:$('#register-info').val(),mphone:$('#register-user').val()},
                success:function(param){
                    $('.jd_form_loading').hide();
                    if(param.result==1){
                        $('.next-input').show();
                        $('.before-input').hide();
                        successInfo($('#register-info'));
                        $('#register-user').attr('disabled',true);
                    }else{
                        errorInfo($('#register-info'),param.msg)
                    }
                },
                error:function(param){
                    $('.jd_form_loading').hide();
                }
            });
        }
    });*/
    


    $('.register-phone-btn').on('click',function(){
        if(checkPhone($('#register-user').val())){
        	$.ajax({
                type:'POST',
                url:'${Path}/user/checkPicCode',
                data:{
                    phone:$('#register-user').val(),
                    picCode:$('#register-tp').val(),
                },
                success:function(data){
                	if(data){
                        $(this).attr("disabled",'true');
                        $(this).text("重新发送(" + 60 + ")");
                        var _this=this;
                        var t=setInterval(function(){settime($(_this),t)},1000);
                        codeInfoAjax(t,'1');
                	}else{
                		alertError('请输入正确验证码');
                	}
                },
                error:function(){
                    $('.jd_form_loading').hide();
                }
            })
        }else{
            alertError('手机号填写错误，请核对')
        }
    });

    /*$('.register-next').on('click',function(){
        $('.next-input').show();
        $('.before-input').hide();

    });*/
    
    //password注册
    $('#register-password').on('blur',function(){
        var numPas=$(this).val().split('');
        if(numPas.length<6||numPas.length>16){
            if($(this).val()==""){
                errorInfo($(this),'请输入密码')
            }else{
                errorInfo($(this),'密码为6-16位')
            }
        }else{
            successInfo($(this))
        }
    });
    /*$('#register-password1').on('blur',function(){
        if($(this).val()!=''&&$(this).val()==$('#register-password').val()){
            successInfo($(this))
        }else{
            if($(this).val()==""){
                errorInfo($(this),'请再次输入密码');
            }else{
                errorInfo($(this),'两次输入不一致');
            }
        }
    }).on('keydown',function(e){
        if (e.keyCode == "13") {//keyCode=13是回车键
            $('.register-submit').click();
        }
    });*/

    //注册提交
   /* $('.register-submit').on('click',function(){
        $('#register-password').blur();
        $('#register-password1').blur();
        var s_type = window.sessionStorage.getItem('s_type')||'';
        var d_type = window.sessionStorage.getItem('d_type')||'';
        if(!$('.register-form').find('div').hasClass('error-info')){
            $('.jd_form_loading').show();
            $.ajax({
                type:'POST',
                url:urlStart+'/site/signup',
                data:{
                    mphone:$('#register-user').val(),
                    d_type:d_type,
                    s_type:s_type,
                    password:$('#register-password').val(),
                    confirm_password:$('#register-password1').val(),
                },
                success:function(param){
                    $('.jd_form_loading').hide();
                    if(param.result==1){
                        alertSuccess('注册成功');
                        newUserInfo();
                        $('.login-wrapper').fadeOut('slow');
                        $('body').removeClass('modal-open');
                    }
                },
                error:function(){
                    $('.jd_form_loading').hide();
                }
            })
        }
    });*/
    
    $("#code").click(function(){
    	code = "";
    	var codeLength = 4;//验证码的长度
    	var checkCode = document.getElementById("code");
    	var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',
    	'S','T','U','V','W','X','Y','Z');//随机数字或字母
    	for(var i = 0; i < codeLength; i++) {//循环操作
    	var index = Math.floor(Math.random()*36);//取得随机数的索引（0~35）
    	code += random[index];//根据索引取得随机数加到code上
    	}
    	checkCode.value = code;//把code值赋给验证码
    })
    
    $('.jd-register-btn').click(function(){
    	$("#code").click();
    })
    
    
    /*//注册提交
    $(".register-submit").click(function() {
    	var user_telephone = $("#register-user").val();
    	var user_password = $("#register-password").val();
    	var user_password1 = $("#register-password1").val();
    	var inputCode = document.getElementById("register-tp").value.toUpperCase(); //取得输入的验证码并转化为大写
    	//var phoneCode = $("#register-info").val();
    	//alert(user_telephone);
    	if(user_password==user_password1&&user_telephone!=""&&user_password!=""&&inputCode==code) {
    		$.ajax({
	            type:'POST',
	            url:urlStart+'user/sysRegister',
	            data:{
	            	user_telephone:user_telephone,
	            	user_password:user_password
	            	//phoneCode:phoneCode
	            },
	            dataType:"text",
	            success:function(data){
	            	if(data=="0"){
	            		alert("注册成功");
	            		window.location.reload();
	            	}else if(data=="1"){
	            		alert("该手机号已注册");
	            	}else{
	            		alert("注册失败");
	            	}
	            	if(data){	
	            		alert(data);

	            	}else{
	            		alertError('注册失败，请重新注册');
	            	}
	            },
	            error:function(){
	                $('.jd_form_loading').hide();
	            }
	        })
    	}
    })*/
    //从地址栏获取参数
    function GetQueryString(name)
    {
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)
    	 return  unescape(r[2]); 
     return null;
    }
    //注册提交
    $(".register-submit").click(function() {
    	var user_telephone = $("#register-user").val();
    	var user_password = $("#register-password").val();
    	var user_password1 = $("#register-password1").val();
    	var user_code= " ";
    	if(GetQueryString("user_code")!=""){
    		user_code=GetQueryString("user_code");
    	}
    	var inputCode = document.getElementById("register-tp").value.toUpperCase(); //取得输入的验证码并转化为大写
    	//var phoneCode = $("#register-info").val();
    	if(user_telephone==""){
    		errorInfo($(this),'请输入手机号!');
    	}else if(checkPhone(user_telephone)==false){
    		errorInfo($(this),'请输入正确的手机号!!!');
    	}else if(inputCode!=code){
    		errorInfo($(this),'验证码不正确');
    	}else if(user_password==""){
    		errorInfo($(this),'请输入密码');
    	}else if(user_password!=user_password1){
    		errorInfo($(this),'两次密码输入不一致');
    	}else{
    		errorInfo($(this),'');
    		$("#pwdfont").val("");
    		$.ajax({
	            type:'POST',
	            url:'sysRegister',
	            data:{
	            	user_telephone:user_telephone,
	            	user_password:user_password,
	            	user_code:user_code
	            	//phoneCode:phoneCode
	            },
	            dataType:"text",
	            success:function(data){
	            	if(data==0){
	            		alert("注册成功");
                        window.location.href = "homepage";
	            	}else if(data==1){
	            		alert("该手机号已注册");
	            	}else{
	            		alert("注册失败");
	            	}
	            	/*if(data){	
	            		alert(data);

	            	}else{
	            		alertError('注册失败，请重新注册');
	            	}*/
	            },
	            error:function(){
	                $('.jd_form_loading').hide();
	            }
	        })
    	}
    })
    
    
    //上一步
    /*$('.before-btn').on('click',function(){
        $('.next-input').hide();
        $('.before-input').show();
        $('#register-user').attr('disabled',false);
        $('#register-password').val('');
        $('#register-password1').val('');
        clearInfo($('#register-password'));
        clearInfo($('#register-password1'));
    });*/


    //忘记密码
    $('.forget-btn').on('click',function(){
        $('.jd-form-wrapper').each(function(){
            if($(this).hasClass('form-forget')){
                $(this).find('input').val('');
                $(this).show();
            }else{
                $(this).hide();
            }
        })
    });
    $('.forget-back').on('click',function(){
        $('.jd-form-wrapper').each(function(){
            if($(this).hasClass('form-forget')){
                $(this).hide();
            }else{
                $(this).show();
            }
            $('#login-password').val('');
            clearInfo($('#login-password'))
        })
    });
    //header click
    $('.header-login-btn').on('click',function(){
        $('.jd-login-btn').click();
        $('.header .btn').removeClass('active');
        $(this).addClass('active');
    });
    $('.header-register-btn').on('click',function(){
        $('.jd-register-btn').click();
        $('.header .btn').removeClass('active');
        $(this).addClass('active');
    });

    $('#forget-user').on('blur',function(){
        if(checkPhone($(this).val())){
            successInfo($(this))
        }else{
            if($(this).val()==""){
                errorInfo($(this),'请输入手机号')
            }else{
                errorInfo($(this),'请输入正确的手机号')
            }
        }
    });
    $('#forget-code').on('blur',function(){
        if(!$(this).val()){
            errorInfo($(this),'请输入验证码')
        }else{
            clearInfo($(this));
        }
    });
    $('#forget-password').on('blur',function(){
        var numPassword=$(this).val().split('');
        if(numPassword.length<6||numPassword.length>16){
            if($(this).val()==""){
                errorInfo($(this),'请输入密码')
            }else{
                errorInfo($(this),'密码为6-16位')
            }
        }else{
            successInfo($(this))
        }
    });
    $('#forget-password1').on('blur',function(){
        if($(this).val()!=''&&$(this).val()==$('#forget-password').val()){
            successInfo($(this))
        }else{
            if($(this).val()==""){
                errorInfo($(this),'请再次输入密码');
            }else{
                errorInfo($(this),'两次输入不一致');
            }
        }
    });


    //验证码
    $('#forget-code-btn').on('click',function(){
        if(checkPhone($('#forget-user').val())){
            $(this).attr("disabled",'true');
            $(this).text("重新发送(" + 60 + ")");
            var _this=this;
            var t1=setInterval(function(){settime1($(_this),t1)},1000);
            codeInfoAjax(t1,'2');
        }else{
            alertError('信息有误请核对');
        }
    });

    $('.forget-submit').on('click',function(){
        $('.form-forget').find('input').blur();
        if(!$('.form-forget').find('div').hasClass('error-info')){
            $('.jd_form_loading').show();
            $.ajax({
                type:'POST',
                url:urlStart+'/site/request-password-reset',
                data:{
                    mphone:$('#forget-user').val(),
                    validate_code:$('#forget-code').val(),
                    password:$('#forget-password').val(),
                    password_confirm:$('#forget-password1').val()
                },
                success:function(param){
                    $('.jd_form_loading').hide();
                    if(param.result==1){
                        alertSuccess("修改成功");
                    }
                },
                error:function(){
                    $('.jd_form_loading').hide();
                }
            })
        }
    });
    if(window.location.search.indexOf('?key=1')!=-1){
        $('.jd-register-btn').click()
    }
    
   /* $("#manage_btn").click(function(){
    	$.get("user/loginFoward",function(url){
        });
    })*/
    //空格禁止输入
    $('.jd-form-wrapper input').on('keypress',function(e){
        var keynum;
        if(window.event) // IE
        {
            keynum = e.keyCode
        }
        else if(e.which) // Netscape/Firefox/Opera
        {
            keynum = e.which
        }
        if(keynum == 32){
            return false;
        }
        return true;
    });
    //咨询
    $('.jd_remove_item').on('click',function () {
        $('.consult-wrapper').show();
        return false;
    });
    $('.consult-wrapper').on('click',false);
    $(document).on('click',function () {
        $('.consult-wrapper').hide();
    });
    
});