$(document).ready(function(){
    $('.remove-btn').on('click', function(e){
        e.preventDefault();
        var id = $(this).data('id');
        if(confirm("Вы уверены что хотите удалить эту анкету?")) {
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
        if(confirm("Вы уверены что хотите удалить эту анкету?")) {
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
    $("#btn_hash").click(function(){
        var hash ='';
        while(hash.length < 20)
            hash += Math.random().toString(36);
        //$("link").text(hash);
        $('#link').attr('value', hash);
    });
    $('#btn-link').click(function(){
        var href = $(this).data('href');
        var link = "http://localhost:8080/forms/link/" + href;
        $('#link-popup').find(".modal-body").html("<a href='"+link+"'target='_blank'>"+link+"</a>");
        $('#link-popup').modal('show');
    });
});