<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>

<div class="row-fluid">
    <div class="span10 offset1">
        <div class="hero-unit hero">
            <h2>Анализ данных<!--br><small>Каталог сохраненных анкет:</small--></h2>
        </div>
        <div class="row-fluid">
            <div class="span12 margine-botton15">
                <h3>Выберите анкету для анализа из выпадающего списка:</h3>
                <select>
                    <option>Название анкеты№1</option>
                    <option>Название анкеты№2</option>
                    <option>Название анкеты№3</option>
                    <option>Название анкеты№4</option>
                    <option>Название анкеты№5</option>
                </select>
                <br><br>
                <a href="/forms/123/analysis/basic" class="btn btn-primary">Провести анализ</a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>
