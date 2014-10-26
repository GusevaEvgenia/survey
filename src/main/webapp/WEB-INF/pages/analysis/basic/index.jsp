<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>

<div class="row-fluid">
    <div class="span10 offset1">
        <div class="hero-unit hero">
            <h2>Анализ собранных данных по выбранной анкете<!--br><small>Каталог сохраненных анкет:</small--></h2>
        </div>
        <jsp:include page="/WEB-INF/pages/analysis/_menu.jsp"/>

        <script>
            $(document).ready(function(){
                $("#all-questions").change(function(){
                    if($(this).is(':checked')){
                        $(".questions").prop('checked', true);
                    }else{
                        $(".questions").prop('checked', false);
                    }
                });
                $("#button-next").click(function(){
                    $("#questions-form").submit();
                    return false;
                });
            });
        </script>

        <h4>Выберите вопросы для проведения анализа</h4>
        <form class="margin-left50" action="/forms/123/analysis/basic/methods" id="questions-form" method="GET">
            <input type="hidden" name="page" value="2">
            <label class="checkbox">
                <input id="all-questions" type="checkbox" checked="checked">
                Выбрать все
            </label>
            <% for (int i = 1; i < 6; i++) {%>
            <label class="checkbox">
                <input class="questions" type="checkbox" name="questions" value="<%=i%>" checked="checked">
                Вопрос№<%=i%>
            </label>
            <%}%>
        </form>
        <ul class="pager">
            <li class="next">
                <a id="button-next" href="">Далее &rarr;</a>
            </li>
        </ul>

        <br>
    </div>
</div>

<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>
