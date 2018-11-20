

var datasourceUser;

$(function(){

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
        columns:[
            {
                dataField: "name",
                caption: "Name"
            },{
                dataField: "role",
                caption: "Role"
            }
        ]
    })

});