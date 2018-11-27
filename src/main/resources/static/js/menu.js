
$(function(){

    // Get useable nvidia coupon code count
    $.ajax({
        method: 'POST',
        url: '/data/statistics/nvidiacouponcount',
        success: function(data){
            $('#main_menu_nvidia_coupon_count').html(data);
        }
    })
});