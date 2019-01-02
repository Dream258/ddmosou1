$(function () {
    var data = [
        {
            index:1,
            user_name:'15235481275',
            create_at:'2018-05-23',
            level:'普通用户',
            score:'25',
        },{
            index:2,
            user_name:'15235481275',
            create_at:'2018-05-23',
            level:'普通用户',
            score:'25',
        },{
            index:3,
            user_name:'15235481275',
            create_at:'2018-05-23',
            level:'普通用户',
            score:'25',
        }
        ];
    var render_table = function(data){
        $('#tableLog').bootstrapTable('destroy');
        $('#tableLog').bootstrapTable({
            idField: 'id',//主键
            columns: [{
                field: 'index',
                title: '序号'
            },{
                field: 'username',
                title: '用户名'
            }, {
                field: 'time',
                title: '创建时间'
            }, {
                field: 'level',
                title: '会员等级'
            }, {
                field: 'rebate',
                title: '邀请返利'

            }],
            data: data
        });
    };
    //获取邀请记录
    var numA = 1;
    function queryTableLog(one) {
        $('.loadingLog').show();
        $.ajax({
            type:'POST',
            url:'/personal/invite-de',
            data:{
                page:numA,
                page_size:10
            },
            success:function (param) {
                if (param.result*1 == 1){
                    $('.invit_sum').text(param.total_count);
                    $('.vip_sum').text(param.vip_count);
                    $('.rebate_sum').text(param.total_amount);
                    $('.M-box').pagination({
                        pageCount:param.total_page?param.total_page:1,
                        coping:true,
                        current:numA,
                        homePage:'首页',
                        endPage:'末页',
                        prevContent:'<',
                        nextContent:'>',
                        callback:function (item) {
                            numA=item.getCurrent();
                            queryTableLog();
                        }
                    });
                    $('.loadingLog').hide();
                    //ajax后调用
                    render_table(param.data);
                }else{
                    alertInfo(param.msg);
                }

            }
        });
    }
    queryTableLog();

    $(function () {
        //复制链接函数
        function copyUrl2() {
            var Url2=$('.point_code');
            Url2.select(); // 选择对象
            document.execCommand("Copy"); // 执行浏览器复制命令
            alertSuccess('复制成功');
        }
        //复制
        $('.copy_btn').on('click',function(){
            var Url2=$('#share_code_input');
            Url2.select(); // 选择对象
            document.execCommand("Copy"); // 执行浏览器复制命令
            alertSuccess('复制成功');
        });
    });
});