$(document).ready(function(){
    $('.remove-btn').on('click', function(e){
        e.preventDefault();
        var id = $(this).data('id');
        if(confirm("Чё, реально?")) {
            $.ajax('/forms/'+id, {
                method: 'DELETE',
                success: function() {
                    window.location.reload();
                }
            })
        }
    });
    $('.remove-btn1').on('click', function(e){
        e.preventDefault();
        var id = $(this).data('id');
        if(confirm("Чё, реально?")) {
            $.ajax('/forms/'+id, {
                method: 'DELETE',
                success: function() {
                    window.location.href='/forms';
                }
            })
        }
    });
    $('#select_type').on('change', function(){
        var value = $(this).val();
        var url = /\/forms\/([\d]+)\/answers/.exec(window.location.pathname)[0];
        if(value !== "all") {
            url +='/'+value;
        }
        window.location.href = url;

    });
});