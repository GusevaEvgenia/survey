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
        <h5 >Вариационны ряд:</h5>
        <table class="table table-bordered">
            <tr>
                <th>№</th>
                <th>Вариант ответа</th>
                <th>Выбранное количество</th>
                <th>Выбрано(%)</th>
            </tr>
            <tr>
                <td>1</td>
                <td>1</td>
                <td>10</td>
                <td>0.66%</td>
            </tr>
            <tr>
                <td>2</td>
                <td>2</td>
                <td>5</td>
                <td>0.33%</td>
            </tr>
        </table>

        <img src="/images/graf.png" alt="">

    </div>
</c:if>
<table id="result-analys" class="table">
    <tr class="<%= types.contains("2") ? "" : "hidden"%>">
        <th>Среднее арифметическое</th>
        <td>${basic.average}</td>
    </tr>
    <tr class="<%= types.contains("3") ? "" : "hidden"%>">
        <th>Мода</th>
        <td>45</td>
    </tr>
    <tr class="<%= types.contains("4") ? "" : "hidden"%>">
        <th>Медиана</th>
        <td>75</td>
    </tr>
    <tr class="<%= types.contains("5") ? "" : "hidden"%>">
        <th>Розмах вариации</th>
        <td>55</td>
    </tr>
    <tr class="<%= types.contains("6") ? "" : "hidden"%>">
        <th>Межкватериальный розмах</th>
        <td>55</td>
    </tr>
    <tr class="<%= types.contains("7") ? "" : "hidden"%>">
        <th>Дисперсия</th>
        <td>55</td>
    </tr>
    <tr class="<%= types.contains("8") ? "" : "hidden"%>">
        <th>Среднекватериальное отклонение</th>
        <td>44</td>
    </tr>
    <tr class="<%= types.contains("9") ? "" : "hidden"%>">
        <th>Коэфициент вариации</th>
        <td>44</td>
    </tr>
    <tr class="<%= types.contains("10") ? "" : "hidden"%>">
        <th>Ассиметрия</th>
        <td>55</td>
    </tr>
    <tr class="no-border <%= types.contains("10") ? "" : "hidden"%>">
        <td colspan="2">Коментарий</td>
    </tr>
    <tr class="no-border <%= types.contains("10") ? "" : "hidden"%>">
        <td colspan="2">
            <img src="/images/graf1.png" alt="">

        </td>
    </tr>
    <tr class="<%= types.contains("11") ? "" : "hidden"%>">
        <th>Эксцесс</th>
        <td>55</td>
    </tr>
    <tr class="no-border <%= types.contains("11") ? "" : "hidden"%>">
        <td colspan="2">Коментарий</td>
    </tr>
    <tr class="no-border <%= types.contains("11") ? "" : "hidden"%>">
        <td colspan="2">
            <img src="/images/graf1.png" alt="">

        </td>
    </tr>
</table>