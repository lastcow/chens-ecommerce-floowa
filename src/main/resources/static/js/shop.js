

var datasourceUser;

$(function(){

    // Highlight menu
    $('#main_menu_shop').addClass('selected');

//    Define user data source
    datasourceUser = new DevExpress.data.CustomStore({
        load:function (loadOption){
            var deferred = $.Deferred();
            $.ajax({
                url: "/data/users/get",
                type: "GET",
                success: function(result){
                    deferred.resolve(result, { totalCount: result.length });
                },
                error: function(){
                    deferred.reject("Data loading error");
                }
            });

            return deferred.promise();
        },
        byKey: function(key, extra){

        },
        insert: function(values){

        },
        update: function(key, values){

        },
        remove: function(key){

        }
    });

    var dgUsers = $('#dgUsers').dxDataGrid({
        dataSource: datasourceUser,
        keyExpr: "id",
        selection: {
            mode: "single"
        },
        hoverStateEnabled: true,
        paging: {
            pageSize: 10
        },
        pager: {
            showInfo: true
        },
        searchPanel: {
            placeholder: "Search user ... ",
            visible: true,
            width: 200
        },
        columns:[
            {
                dataField: "name",
                caption: "Name"
            },{
                dataField: "email",
                caption: "Email"
            },{
                dataField: "contactNumber",
                caption: "Contact Number"
            },{
                dataField: "wechat",
                caption: "WeChat"
            },{
                dataField: "qq",
                caption: "QQ"
            },{
                dataField: "role",
                caption: "Role",
                width: 50,
                alignment: "center",
                cellTemplate: roleCellTemplate
            }
        ]
    })

});

var roleCellTemplate = function(container, options){
    var role = options.value;
    if(role === 'ROLE_ADMIN') {
        $("<div/>").addClass("fa fa-snapchat-ghost text-danger")
            .appendTo(container);
    }else if(role === 'ROLE_AGENT') {
        $("<div/>").addClass("fa fa-user-secret")
            .appendTo(container);
    }else if(role === 'ROLE_CUSTOMER') {
        $("<div/>").addClass("fa fa-user-o text-prime")
            .appendTo(container);
    }else{
        $("<div/>").addClass("fa fa-wheelchair-alt")
            .appendTo(container);
    }
};