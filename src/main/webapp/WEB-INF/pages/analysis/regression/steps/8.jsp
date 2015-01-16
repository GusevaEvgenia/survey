<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<h3>Стандартная ошибка оценки уравнения регрессии SEE</h3>
Эта статистика представляет собой стандартное отклонение фактических значений У от предсказанных значений
У<br><br>
SEE = 0.3 <br>--%>
<h3>Средняя ошибка аппроксимации (характеризует адекватность регрессионной модели) </h3>
Средняя ошибка аппроксимации
<div class="math">
    \Large ${regress.exepOproc}
</div>

<input type="hidden" id="a" data-val="${regress.a}">
<input type="hidden" id="b" data-val="${regress.b}">

<ul class="pager">
    <li class="previous">
        <a href="#" data-step="8">&larr; Назад</a>
    </li>
    <li class="next">
        <a href="#" data-step="8">Далее &rarr;</a>
    </li>
</ul>

<SCRIPT>
    jsMath.Process(document);
</SCRIPT>