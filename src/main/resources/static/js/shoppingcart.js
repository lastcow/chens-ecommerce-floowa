
$(function(){


    // Highlight menu
    $('#main_menu_shop').addClass('selected');

});

/**
 * Make order submission button disabled.
 */
function updateItemQty(){
    // Disable order submission button.
    $('#btnSubmitOrder').attr('disabled', 'disabled');
}

/**
 * Confirm order from shopping cart
 * @param cartId
 */
function confirmOrder(cartId){
    console.log(cartId);
    var $this = $('#btnSubmitOrder');
    $this.html("Processing Order");

    // Convert shopping cart to invoice
    $.ajax({
        url: '/admin/convertcarttoinvoice',
        method: 'POST',
        data: JSON.stringify({id: cartId}),
        dataType: 'json',
        contentType: 'application/json',
        success: function(data){
            // Show modal and display the loading status
            $this.text('Order Processed');
            $this.attr('disabled', 'disabled');
            $('#cartConfirm').modal('show');
        },
        error: function(){
            // Show error
        }
    });

}
