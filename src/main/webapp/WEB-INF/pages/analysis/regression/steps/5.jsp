<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h3>Проверка устойчивости корреляции и условий по остаткам</h3>
Кореляция r_xy = <span id="corelation">${regress.corelletion}</span><br>
Детерменация r_xy^2 = <span id="">${regress.determination}</span><br>
tParam = <span id="">${regress.tParam}</span><br>
tParamTable = <span id="">${regress.tTable}</span><br>
|${regress.tParam}| ${regress.tParamMod>regress.tTable ? ">" : "<"}  ${regress.tTable}
<%--<input type="hidden" id="flag" data-val="${regress.tParamMod>regress.tTable ? false : true}">Вернуть--%>
<br>
<ul class="pager">
    <li class="previous">
        <a href="#" data-step="5">&larr; Назад</a>
    </li>
    <li class="next">
        <a href="#" data-step="5">Далее &rarr;</a>
    </li>
</ul>