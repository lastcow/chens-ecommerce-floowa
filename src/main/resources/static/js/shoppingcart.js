
$(function(){


    // Highlight menu
    $('#main_menu_shop').addClass('selected');

});

/**
 * Confirm order from shopping cart
 * @param cartId
 */
function confirmOrder(cartId){
    console.log(cartId);

    // Convert shopping cart to invoice
    $.ajax({
        url: '/admin/convertcarttoinvoice',
        method: 'POST',
        data: JSON.stringify({id: cartId}),
        dataType: 'json',
        contentType: 'application/json',
        success: function(data){
            // Show modal and display the loading status
            $('#cartConfirm').modal('show');
        },
        error: function(){
            // Show error
        }
    });

}
