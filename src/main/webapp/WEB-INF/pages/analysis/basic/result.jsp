<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String[] raw_types = request.getParameterValues("type");
    if (raw_types == null) {
        raw_types = new String[0];
    }
    ArrayList<String> types = new ArrayList(Arrays.asList(raw_types));
%>
<script>
    $(document).ready(function() {
        $(".bb").click(function() {
            var tab = $(this).parents('.ba-quation-tab');
            $(tab).find(".ba-settings").removeClass("hidden");
            $(tab).find('.ba-analyse-result').addClass("hidden");
        });
    });
</script>
<button class="bb pull-left btn btn-primary">Назад</button><br><br>
<c:if test="${fn:contains(basic.types,1)}">
    <!--Вариационны ряд-->
    <div>
        <h5>Вариационны ряд:</h5>
        <table class="table table-bordered">
            <tr>
                <th>№</th>
                <th>Вариант ответа</th>
                <th>Выбранное количество</th>
                <th>Выбрано(%)</th>
            </tr>
            <c:forEach items='${basic.frequency}' var="answer" varStatus="loop">
                <tr>
                    <td>${loop.index+1}</td>
                    <td>${answer.get(0)}</td>
                    <td>${answer.get(1)}</td>
                    <td>${answer.get(2)}</td>
                </tr>
            </c:forEach>
        </table>

        <img src="/images/graf.png" alt="">

    </div>
</c:if>
<table id="result-analys" class="table">
    <c:if test="${fn:contains(basic.types,2)}">
        <tr>
            <th>Среднее арифметическое</th>
            <td>${basic.average}</td>
        </tr>
    </c:if>
    <c:if test="${fn:contains(basic.types,3)}">
        <tr>
            <th>Мода</th>
            <td>${basic.moda}</td>
        </tr>
    </c:if>
    <c:if test="${fn:contains(basic.types,4)}">
        <tr>
            <th>Медиана</th>
            <td>${basic.median}</td>
        </tr>
    </c:if>
    <c:if test="${fn:contains(basic.types,5)}">
        <tr>
            <th>Размах вариации</th>
            <td>${basic.variationScale}</td>
        </tr>
    </c:if>
    <c:if test="${fn:contains(basic.types,6)}">
        <tr>
            <th>Межкватериальный размах</th>
            <td>${basic.interQuartile}</td>
        </tr>
    </c:if>
    <c:if test="${fn:contains(basic.types,7)}">
        <tr>
            <th>Дисперсия</th>
            <td>${basic.dispersion}</td>
        </tr>
    </c:if>
    <c:if test="${fn:contains(basic.types,8)}">
        <tr>
            <th>Среднекватериальное отклонение</th>
            <td>${basic.deviation}</td>
        </tr>
    </c:if>
    <c:if test="${fn:contains(basic.types,9)}">
        <tr>
            <th>Коэфициент вариации</th>
            <td>${basic.variation}</td>
        </tr>
    </c:if>
    <c:if test="${fn:contains(basic.types,10)}">
        <tr>
            <th>Ассиметрия</th>
            <td>${basic.asymmetry}</td>
        </tr>
        <tr>
            <td colspan="2">Коментарий</td>
        </tr>
        <tr>
            <td colspan="2">
                <img src="/images/graf1.png" alt="">
            </td>
        </tr>
    </c:if>
    <c:if test="${fn:contains(basic.types,11)}">
        <tr>
            <th>Эксцесс</th>
            <td>${basic.excess}</td>
        </tr>
        <tr>
            <td colspan="2">Коментарий</td>
        </tr>
        <tr>
            <td colspan="2">
                <img src="/images/graf1.png" alt="">

            </td>
        </tr>
    </c:if>
</table>