
$(function(){

    // Get useable nvidia coupon code count
    $.ajax({
        method: 'POST',
        url: '/data/statistics/nvidiacouponcount',
        success: function(data){
            $('#main_menu_nvidia_coupon_count').html(data);
        }
    });

    // Get total items
    $.ajax({
        method: 'POST',
        url: '/data/statistics/itemscount',
        success: function(data){
            $('#main_menu_items_count').html(data + ' Items');
        }
    });

    updateShoppingCartTotal();


});

/**
 * Update my shopping cart
 */
function updateShoppingCartTotal(){
    // Get shopping cart total items
    $.ajax({
        method: 'POST',
        url: '/data/statistics/totalinshoppingcart',
        success: function(data){
            $('#scTotal').html(data);
        }
    });
}