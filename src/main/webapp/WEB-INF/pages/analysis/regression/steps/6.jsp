<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<h3>Стандартная ошибка оценки уравнения регрессии SEE</h3>
Эта статистика представляет собой стандартное отклонение фактических значений У от предсказанных значений
У<br><br>
SEE = 0.3 <br>--%>
<h3>Критерии Дарбина-Уотсона</h3>
<div class="math">
    \Large Критерии = ${regress.darbin}
</div>
<ul class="pager">
    <li class="previous">
        <a href="#" data-step="6">&larr; Назад</a>
    </li>
    <li class="next">
        <a href="#" data-step="6">Далее &rarr;</a>
    </li>
</ul>

<SCRIPT>
    jsMath.Process(document);
</SCRIPT>