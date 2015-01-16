<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h3>Проверка устойчивости корреляции</h3>
Кореляция
<div class="math">
    \Large r_{xy} = <span id="corelation">${regress.corelletion}</span>
</div>
Детерменация
<div class="math">
    \Large r_{xy}^2 = <span id="">${regress.determination}</span>
</div>
Проверим корреляцию с помощью t-теста
<div class="math">
    \Large t_{вычисленное} = <span id="">${regress.tParam}</span>
</div>
<div class="math">
    \Large t_{расчетное} = <span id="">${regress.tTable}</span>
</div>
<div class="math">
    \Large |${regress.tParam}| ${regress.tParamMod>regress.tTable ? ">" : "<"}  ${regress.tTable}
</div>
<input type="hidden" id="flag" data-val="${regress.tParamMod>regress.tTable ? false : true}">
<br>
<ul class="pager">
    <li class="previous">
        <a href="#" data-step="5">&larr; Назад</a>
    </li>
    <li class="next">
        <a href="#" data-step="5">Далее &rarr;</a>
    </li>
</ul>

<SCRIPT>
    jsMath.Process(document);
</SCRIPT>