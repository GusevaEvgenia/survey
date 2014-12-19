<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<h3>Стандартная ошибка оценки уравнения регрессии SEE</h3>
Эта статистика представляет собой стандартное отклонение фактических значений У от предсказанных значений
У<br><br>
SEE = 0.3 <br>--%>
<h3>Оценка вида распределения по асимметрии и эксцессу</h3>
|${regress.acym}| ${regress.acym<regress.acymParam ? "<" : ">="} ${regress.acymParam}<br>
|${regress.ex}| ${regress.ex<regress.exParam ? "<" : ">="} ${regress.exParam}
<%--<input type="hidden" id="flag" data-val="${(regress.ex<regress.exParam && regress.acym<regress.acymParam) ? false : true}">Вернуть--%>
<ul class="pager">
    <li class="previous">
        <a href="#" data-step="6">&larr; Назад</a>
    </li>
    <li class="next">
        <a href="#" data-step="6">Далее &rarr;</a>
    </li>
</ul>
