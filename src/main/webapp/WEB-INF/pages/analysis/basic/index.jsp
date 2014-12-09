<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <c:if test='${questions.size()>0}'>
            <form class="margin-left50" action="/forms/${form.idForm}/analysis/basic/methods" id="questions-form" method="GET">
                    <%-- <input type="hidden" name="page" value="2">--%>
                <label class="checkbox">
                    <input id="all-questions" type="checkbox" checked="checked">
                    Выбрать все
                </label>
                <c:forEach items='${questions}' var="question">
                    <label class="checkbox">
                        <input class="questions" type="checkbox" name="question" value="${question.idQuestion}" checked="checked">
                            ${question.text}
                    </label>
                </c:forEach>
            </form>
            <ul class="pager">
                <li class="next">
                    <a id="button-next" href="">Далее &rarr;</a>
                </li>
            </ul>
        </c:if>
        <c:if test='${questions.size()==0}'>
            <h4 class="text-center">У вас нет ответов на анкету.</h4>
        </c:if>
        <br>
    </div>
</div>

<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>
