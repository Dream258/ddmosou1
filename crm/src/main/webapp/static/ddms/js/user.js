$(function () {
    var userPathName = window.location.pathname;
    var userObj = {
        '/member-center/index':'user_index',
        '/member-center/share-list':'user_share',
        '/member-center/buy-list':'user_buy',
        '/member-center/set':'user_set',
    };
    $('#user').addClass('active');
    $('.nav_user_list').find('.user_li').removeClass('active');
    $('#'+userObj[userPathName]).addClass('active');
   //btn切换
   $('.fun_btn').click(function () {
     $(this).parents('.fun_btn_group').find('.fun_btn').removeClass('active');
     $(this).addClass('active');
     return false;
   });

   $('.user_li').on('click',function () {
       $(this).parents('.nav_user_list').find('.user_li').removeClass('active');
       $(this).addClass('active');
   });

});