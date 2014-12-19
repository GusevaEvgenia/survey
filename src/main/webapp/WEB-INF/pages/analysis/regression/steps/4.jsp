<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h3>Оценка тесноты и оценка значимости уравнения</h3><br>
Коэффициентом детерминации г2 (теснота связи) = ${regress.determination}<br><br>
Проверка значимости коэффициента детерминации с помощью F-критерия.<br>

F-вычисленное = ${regress.f}<br>
F-расчетное = ${regress.fTable}<br><br>
|${regress.f}| ${regress.fMod>regress.fTable ? ">" : "<"}  ${regress.fTable}
<%--<input type="hidden" id="flag" data-val="${regress.fMod>regress.fable ? false : true}">Вернуть--%>
<ul class="pager">
    <li class="previous">
        <a href="#" data-step="4">&larr; Назад</a>
    </li>
    <li class="next">
        <a href="#" data-step="4">Далее &rarr;</a>
    </li>
</ul>