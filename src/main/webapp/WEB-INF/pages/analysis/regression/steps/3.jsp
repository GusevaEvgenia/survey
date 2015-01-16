<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<h3>Проверка значимости с помощью t-критерия</h3><br>
<strong>Гипотеза</strong><br><br>
Предположим что чежду переменными не существует линейной зависимости.<br>
<div class="math">
    \Large t_{вычисленное} = ${regress.tCount}
</div>
<div class="math">
    \Large t_{критическое} = ${regress.tTable}
</div>
<div class="math">
    \Large |${regress.tCount}| ${regress.tCountMod>regress.tTable ? ">" : "<"}  ${regress.tTable}
</div>
Следовательно гипотезу ${regress.tCountMod>regress.tTable ? "подтверждают" : "отклоняют"}
<c:if test="${regress.tCountMod>regress.tTable}"></c:if>
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

<SCRIPT>
    jsMath.Process(document);
</SCRIPT>