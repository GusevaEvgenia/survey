<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  int size;
    if (request.getParameter("type").equals("1")) {
        size=7;
    }else{
        size=3;
    }%>
<div class="thumbnails">
    <div class="row-fluid">
        <% for (int i = 1; i < size; i++) { %>
        <div class="span3">
            <div class="thumbnail">
                <a href="/forms/123"><img src="/images/form.jpg" alt=""></a>
                <p></p>
                <div class="caption">
                    <h4>Название анкеты</h4>
                    <p>
                        Краткое описание:<br>
                        Анкета для проведения опроса
                    </p>
                    <p>
                        <a href="/forms/123" class="btn btn-primary">Подробнее</a>
                        <a href="#" class="btn btn-primary pull-right">Удалить</a>
                    </p>
                </div>
            </div>
        </div>
        <% if (i % 4 == 0) {%>
    </div>
    <br>
    <div class="row-fluid">
        <% }%>
        <%}%>
    </div>
</div>
<p>
</p>