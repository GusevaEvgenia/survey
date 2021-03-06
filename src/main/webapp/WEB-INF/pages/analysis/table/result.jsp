<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>

<div class="row-fluid">
    <div class="span10 offset1">
        <div class="hero-unit hero">
            <h2>Анализ собранных данных по выбранной анкете<!--br><small>Каталог сохраненных анкет:</small--></h2>
        </div>

        <jsp:include page="/WEB-INF/pages/analysis/_menu.jsp"/>

       <%-- <script>
            $(document).ready(function() {
                /*function tableToggle(size) {
                    if (size === "2") {
                        $(".table_2parameter").removeClass("hidden");
                        $(".table_3parameter").addClass("hidden");
                    } else if (size === "3") {
                        $(".table_3parameter").removeClass("hidden");
                        $(".table_2parameter").addClass("hidden");
                    }
                }*/
                tableToggle(getQueryVariable('parameter_size'));

                /*function dependence(tstatictic) {
                    if (tstatictic === "1") {
                        $(".dependence").removeClass("hidden");
                    } else {
                        $(".dependence").addClass("hidden");
                    }
                }*/
                dependence(getQueryVariable('dependence'));

                function strength_links(tstatictic) {
                    if (tstatictic === "2") {
                        $(".strength_links").removeClass("hidden");
                    } else {
                        $(".strength_links").addClass("hidden");
                    }
                }
                strength_links(getQueryVariable('strength_links'));

            });

            function getQueryVariable(variable) {
                var query = window.location.search.substring(1);
                var vars = query.split("&");
                for (var i = 0; i < vars.length; i++) {
                    var pair = vars[i].split("=");
                    if (pair[0] === variable) {
                        return pair[1];
                    }
                }
                return null;
            }
        </script>--%>
        <table class="table table-bordered table_2parameter">
            <tr>
                <th></th>
                <th>${table.firstQuestionData.question.text} (Независимая переменная)</th>
            </tr>
            <tr>
                <th>${table.mainQuestionData.question.text} Зависимая переменная)</th>
                <c:forEach items='${table.answerOptions[1]}' var="text">
                    <td>${text}</td>
                </c:forEach>
            </tr>
            <c:forEach items='${table.answerOptions[0]}' var="text" varStatus="rloop">
            <tr>
                <td>${text}</td>
                <c:forEach begin="0" end="${table.columnsLength-1}" varStatus="cloop">
                    <td>${table.getCellVal(cloop.index, rloop.index)}%</td>
                </c:forEach>
            </tr>
            </c:forEach>
            <tr>
                <th>Итого</th>
                <c:forEach begin="0" end="${table.columnsLength-1}" varStatus="summ">
                    <td>${table.getCellVal(summ.index, table.rowsLength-1)}%</td>
                </c:forEach>
            </tr>
        </table>
        <c:if test="${fn:contains(table.types,1)}">
            <div class="dependence margin-button15">
                <strong>Зависимость наблюдаемой связи</strong><br>
                    Расчитаное значение хи-квадрата = ${table.xi2Calc}<br>
                    Критического значение хи-квадрата = ${table.xi2Tabl}<br>
                    ${table.xi2Calc} ${xi2Calc>table.xi2Tabl ? ">" : "<"} ${table.xi2Tabl}
                    <br>
            </div>
        </c:if>
        <c:if test="${fn:contains(table.types,2)}">
            <div class="strength_links margin-button15">
                <strong>Теснота связи </strong>= ${table.strengthLinks}
            </div>
        </c:if>

        <a class="tb pull-left btn btn-primary" href="/forms/${form.idForm}/analysis/table">Назад</a><br>

        <br>
    </div>
</div>

<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>
