<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="model-regression" id="function-1">
    <h3>Модель парной регрессии</h3>
    Уравнение в виде линейной регресии имеет вид<br><br>
    yi = a + b * xi<br><br>
</div>
<%--TODO преобразование нелинейных форм--%>
<div class="model-regression hidden" id="function-2">
    <h3>Модель парной регрессии</h3>
    Нелинейная регрресия относится к степенной и имеет уравнение вида:<br>
    yi = a + b * xi<br>

    Уравнение в виде линейной регресии имеет вид<br>
    yi = a + b * xi
    <small>i</small>
    <br>
</div>
<div>
    где a = ${regress.a}<br>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; b = ${regress.b}<br><br>

    Выберите уровень значимости l
    <select name="important_level">
        <% for (double i = 1; i < 6; i++) {%>
        <option value="<%=i / 16%>">
            <%=i / 16%>
        </option>
        <%}%>
    </select>
</div>
<ul class="pager">
    <li class="previous">
        <a href="#" data-step="2">&larr; Назад</a>
    </li>
    <li class="next">
        <a href="#" data-step="2">Далее &rarr;</a>
    </li>
</ul>