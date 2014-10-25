<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%int size = Integer.parseInt(request.getParameter("size"));%>
<div class="thumbnails">
    <div class="row-fluid">
        <% for (int i = 1; i <= size; i++) { %>
            <div class="span3">
                <div class="thumbnail">
                    <a href="/forms/123"><img src="/images/form.jpg" alt=""></a> <%--Картинка и id анкеты берется из БД--%>
                    <p></p>
                    <div class="caption">
                        <h4>Название анкеты</h4> <%--Берется из БД--%>
                        <p>
                            Краткое описание:<br>
                            Анкета для проведения опроса <%--Берется из БД--%>
                        </p>
                        <p>
                            <a href="/forms/123" class="btn btn-primary">Подробнее</a> <%--id анкеты берется из БД--%>
                            <a href="#" class="btn btn-primary pull-right">Удалить</a>
                        </p>
                    </div>
                </div>
            </div>
            <%--Переход на новую строку если уже есть 4 анкеты в строке--%>
            <% if (i % 4 == 0) {%>
    </div>
    <br>
    <div class="row-fluid">
            <% }%>
        <%}%>
    </div>
</div>