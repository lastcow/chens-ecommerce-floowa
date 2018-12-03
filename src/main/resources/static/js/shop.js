
$(function(){


    // Highlight menu
    $('#main_menu_shop').addClass('selected');

    // Form validator
    if ($('#formNewItem').length) {
        $('#formNewItem').validator();
    }
    if ($('#formItemRequest').length) {
        $('#formItemRequest').validator();
    }

    /**
     * On modal hiden, reset form
     */
    $('#newItemModel').on('hidden.bs.modal', function (e) {
        // Clean form content
        $('#formNewItem')[0].reset();
    });
    $('#itemRequestModel').on('hidden.bs.modal', function (e) {
        // Clean form content
        $('#formItemRequest')[0].reset();
    });
    $('#addToCartModal').on('hidden.bs.modal', function (e) {
        // Clean form content
        $('#formAddToCart')[0].reset();
    });

    // Add to cart form submission
    submitAddToCart();

});

function showAddToCartModal(id, name, image, moq){
    // Set id
    $('#itemIdAddToCart').val(id);
    // Set name
    $('#titleAddToCart').html(name);
    // Set image
    $('#imgAddToCart').attr('src', image);
    // Set MOQ
    $('#addToCartOrderCount').val(moq);
    $('#addToCartOrderCount').attr('min', moq);

    $('#formAddToCart').validator();

    $('#addToCartModal').modal('show');

    // Set value to success/error dialog
    // Set name
    $('#titleAddToCartSuccess').html(name);
    // Set image
    $('#imgAddToCartSuccess').attr('src', image);

}

function submitAddToCart(){
    $('#addToCartModal').validator().on('submit', function (e) {
        if (e.isDefaultPrevented()) {
            // handle the invalid form...
            console.log("ERROR");
        } else {
            // everything looks good!
            // Reading data and send to backend.
            var itemId = $('#itemIdAddToCart').val();
            var orderQty = $('#addToCartOrderCount').val();

            // Ajax call
            $.ajax({
                method: 'POST',
                url: '/data/shop/item/addtocart',
                data: JSON.stringify({itemId: itemId, orderQty: orderQty}),
                dataType: 'json',
                contentType: 'application/json',
                success: function(data){
                    $('#addToCartModal').modal('hide');
                    // Show success message dialog
                    $('#addToCartModalSuccess').modal('show');

                    if(updateShoppingCartTotal){
                        updateShoppingCartTotal();
                    }
                },
                error: function(){

                }
            });
        }

        return false;
    })
}
