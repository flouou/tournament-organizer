$(function () {
$('tbody').sortable({
    update: function(event, ui){
        var order = [];
        $('tbody tr').each( function(e){
            order.push($(this).attr('id'));
            order[$(this).attr('id')] = [];
            order[$(this).attr('id')].push($(this).index());
        });
        console.log(order);
        //an der Stelle AJAX-Call, um Reihenfolge in der DB anzupassen
    }
});
});