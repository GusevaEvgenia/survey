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

        <div>
            <jsp:include page="/WEB-INF/pages/analysis/regression/_prognoz.jsp" />

            <ul class="pager">
                <li class="previous">
                    <a href="/forms/${form.idForm}/analysis/regression">&larr; Назад</a>
                </li>
            </ul>
        </div>
        <br>
    </div>
</div>

<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>
