$(function () {
$('tbody').sortable({
    update: function(event, ui){
        var order = [];
        $('tbody>tr').each( function(e){
            order[$(this).index()] = $(this).attr('id');
        });
        console.log("test");
        console.log(order);
        $.ajax({
            type : "POST",
            url : "/updateOrder",
            data : {
                "order":order,
            },
            success : function(data,status,response){
                console.log(data);
            },
            error : function(e){
                console.log("Error!!!--- "+e);
            }
        });
    }
});
});