<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h3>Оценка значимости уравнения</h3><br>
Коэффициентом детерминации (теснота связи)
<div class="math">
    \Large r^2 = ${regress.determination}
</div>
Проверка значимости коэффициента детерминации с помощью F-критерия.<br>
<div class="math">
    \Large F_{вычисленное}= ${regress.f}
</div>
<div class="math">
    \Large F_{критическое} = ${regress.fTable}
</div>
<div class="math">
    \Large |${regress.f}| ${regress.fMod>regress.fTable ? ">" : "<"}  ${regress.fTable}
</div>

<input type="hidden" id="flag" data-val="${regress.fMod>regress.fTable ? false : true}">
<ul class="pager">
    <li class="previous">
        <a href="#" data-step="4">&larr; Назад</a>
    </li>
    <li class="next">
        <a href="#" data-step="4">Далее &rarr;</a>
    </li>
</ul>

<SCRIPT>
    jsMath.Process(document);
</SCRIPT>