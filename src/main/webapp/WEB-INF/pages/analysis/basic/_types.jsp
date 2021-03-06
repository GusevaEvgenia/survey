<%@ page pageEncoding="UTF-8" %>
<form class="analys-method">
    <input type="hidden" name="idQ" value="${param.idQuestion}">
    <div class="row-fluid">
        <div class="span10">
            <h5>Вариационный ряд:</h5>
            <label class="checkbox margin-left30 type-method">
                <input class="type-method" type="checkbox" name="type" value="1">
                Распределение частот значений переменной
            </label>
        </div>
        <div class="span2 margin-top20">
            <label class="checkbox">
                <input class="all-types ${param.types=="0" ? "all-settings" : ""}" type="checkbox"  data-container-id="${param.tab_name}">
                Выбрать все
            </label>
        </div>
    </div>
    <h5>Статистики. Связаные с распределение частот.</h5>
    <div class="row-fluid">
        <div class="span4">
            <em class="">Показатели центра распределения:</em>
            <div class="margin-left30">
                <label class="checkbox ${(param.types=="0" || param.types=="nominal" || param.types=="ordinal") ? "" : "hidden"}">
                    <input class="type-method" type="checkbox" name="type" value="2">
                    Среднее арифметическое
                </label>
                <label class="checkbox ${(param.types=="0" || param.types=="interval") ? "" : "hidden"}">
                    <input class="type-method" type="checkbox" name="type" value="3">
                    Мода
                </label>
                <label class="checkbox ${(param.types=="0" || param.types=="nominal"||param.types=="ordinal"||param.types=="ratio") ? "" : "hidden"}">
                    <input class="type-method" type="checkbox" name="type" value="4">
                    Медиана
                </label>
            </div>
        </div>
        <div class="span4 ${(param.types=="0" || param.types=="nominal" || param.types=="ordinal") ? "" : "hidden"}">
            <em class="">Показатели вариации:</em>
            <br>
            <div class="margin-left30">
                <label class="checkbox">
                    <input class="type-method" type="checkbox" name="type" value="5">
                    Размах вариации
                </label>
                <label class="checkbox">
                    <input class="type-method" type="checkbox" name="type" value="6">
                    Межквартильный размах
                </label>
                <label class="checkbox">
                    <input class="type-method" type="checkbox" name="type" value="7">
                    Дисперсия
                </label>
                <label class="checkbox">
                    <input class="type-method" type="checkbox" name="type" value="8">
                    Среднеквадратическое отклонение
                </label>
                <label class="checkbox">
                    <input class="type-method" type="checkbox" name="type" value="9">
                    Коэффициент вариации
                </label>
            </div>
        </div>
        <div class="span4">
            <em class="">Показатели формы распределения:</em>
            <br>
            <div class="margin-left30">
                <label class="checkbox">
                    <input class="type-method" type="checkbox" name="type" value="10">
                    Асимметрия
                </label>
                <label class="checkbox">
                    <input class="type-method" type="checkbox" name="type" value="11">
                    Эксцесс
                </label>
            </div>
        </div>
    </div>
</form>