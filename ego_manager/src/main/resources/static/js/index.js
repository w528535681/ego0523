
$(document).ready(function(){
    $("#riframe").height($(window).height()-50);// 浏览器当前窗口可视区域高度 静态页面下-100
    $("#rightContent").height($(window).height()-39);// 静态页面下-100
    $('.main-sidebar').height($(window).height()-50);

});

var tmpmenu = 'index_Index';
function makecss(obj){
    $('li[data-id="'+tmpmenu+'"]').removeClass('active');
    $(obj).addClass('active');
    tmpmenu = $(obj).attr('data-id');
}

function callUrl(url){
    layer.closeAll('iframe');
    rightContent.location.href = url;
}
var now_num = 0; //现在的数量
var is_close=0;
function ajaxOrderNotice(){
    var url = '/index/Admin/Order/ajaxOrderNotice';
    if(is_close > 0) return;
    $.get(url,function(data){
        //有新订单且数量不跟上次相等 弹出提示
        if(data > 0 && data != now_num){
            now_num = data;
            if(document.getElementById('ordfoo').style.display == 'none'){
                $('#orderAmount').text(data);
                $('#ordfoo').show();
            }
        }
    })
    //setTimeout('ajaxOrderNotice()',5000);
}
//setTimeout('ajaxOrderNotice()',5000);


function closes(){
    is_close = 1;
    document.getElementById('ordfoo').style.display = 'none';
}
// 没有点击收货确定的按钮让他自己收货确定
var timestamp = Date.parse(new Date());
$.ajax({
    type:'post',
    url:"/index/Admin/System/login_task",
    data:{timestamp:timestamp},
    timeout : 100000000, //超时时间设置，单位毫秒
    success:function(){
        // 执行定时任务
    }
});
