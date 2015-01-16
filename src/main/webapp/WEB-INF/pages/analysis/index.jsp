<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>

<script>
$(document).ready(function(){

    $("#select-form").bind('change focus', function(){
        var id = $(this).val();
        //alert(id);
        changeUrl(id);
    });

    function changeUrl (id) {
        $("#href-basic").attr("href", "/forms/" + id + "/analysis/basic");
    }
    changeUrl($("#select-form").val());
});
</script>

<div class="row-fluid">
    <div class="span10 offset1">
        <div class="hero-unit hero">
            <h2>Анализ данных<!--br><small>Каталог сохраненных анкет:</small--></h2>
        </div>
        <div class="row-fluid">
            <div class="span12 margine-botton15">
                <h3>Выберите анкету для анализа из выпадающего списка:</h3>
                <select id="select-form">
                    <optgroup label="qqqq">
                    <c:forEach items='${activeForms}' var="form">
                        <option value="${form.idForm}">${form.title}</option>
                    </c:forEach>
                    </optgroup>
                    <optgroup label="zzzzzzzz">
                    <c:forEach items='${archiveForms}' var="form">
                        <option value="${form.idForm}">${form.title}</option>
                    </c:forEach>
                    </optgroup>
                    <c:forEach items='${smForms}' var="form">
                        <option value="${form.id}">${form.title}</option>
                    </c:forEach>
                </select>
                <br><br>
                <a id="href-basic" href="/forms/${forms[0].idForm}/analysis/basic" class="btn btn-primary">Провести анализ</a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>
