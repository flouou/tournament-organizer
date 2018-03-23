$(function () {
    $('.listing-table').sortable({
        containerSelector: 'table',
        itemPath: '> tbody',
        itemSelector: 'tr',
        placeholder: '<tr class="placeholder"/>',
        onDragStart: function ($item, container, _super) {
            console.log("dragged");
          }
    });
});