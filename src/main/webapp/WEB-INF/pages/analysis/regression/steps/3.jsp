<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<h3>Проверка значимости с помощью t-критерия</h3><br>
<strong>Гипотеза</strong><br><br>
H0:β1=0<br>
H1:β1≠0<br><br>
Нулевая гипотеза предполагает, что между Х и У не существует линейной зависимости.<br>
Альтернативная гипотеза утверждает, что между X и У существует зависимость, либо положительная, либо
отрицательная.<br><br>
t-вычисленное = ${regress.tCount}<br>
t-расчетное = ${regress.tTable}<br><br>
|${regress.tCount}| ${regress.tCountMod>regress.tTable ? ">" : "<"}  ${regress.tTable} Следовательно нулевую гипотезу ${regress.tCountMod>regress.tTable ? "подтверждают" : "отклоняют"}
<%--<c:if test="${regress.tCountMod>regress.tTable}"> Вернеть--%>
<br>
Выберите уровень значимости для следующего шага
<select name="important_level2">
    <option>0.005</option>
    <option>0.01</option>
    <option>0.025</option>
    <option>0.05</option>
    <option>0.1</option>
    <option>0.25</option>
</select>
<%--</c:if>--%>
<%--<input type="hidden" id="flag" data-val="${regress.tCountMod>regress.tTable ? false : true}">Вернуть--%>
<ul class="pager">
    <li class="previous">
        <a href="#" data-step="3">&larr; Назад</a>
    </li>
    <li class="next">
        <a href="#" data-step="3">Далее &rarr;</a>
    </li>
</ul>