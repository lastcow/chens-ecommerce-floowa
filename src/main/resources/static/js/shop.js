
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



});
