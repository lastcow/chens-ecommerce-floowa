
$(function(){

    // Active menu
    $('#main_menu_coupon_nvidia').addClass('selected');


});

function confirmCodeUsage (code) {
    var result = DevExpress.ui.dialog.confirm("Are you sure this code [" + code + "] been used ?", "Confirm used");
    result.done(function (dialogResult) {
        if(dialogResult){
            // Calling backend to make code been used.
            $.ajax({
                url: "/admin/system/coupon/nvidia/use",
                type: "POST",
                data: JSON.stringify({code: code}),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function(data){
                    // Update menu total count
                    $('#main_menu_nvidia_coupon_count').html(data);

                    // Remove div block
                    $('#coupon_'+code).remove();
                },error: function(){

                }
            })
        }else{

        }
    });
};