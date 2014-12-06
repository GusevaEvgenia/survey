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

        <script>
            $(document).ready(function() {
                $(".all-types").change(function() {
                    var container_id = $(this).data('container-id');
                    var types = $('#' + container_id).find(".type-method");
                    types.prop('checked', $(this).is(':checked'));
                });
                $(".analys-all").click(function() {
                    var form = $("#questionAll").find(".analys-method");
                    var id = $(this).data('id-form');
                    if (form.serialize() === '') {
                        alert('Необходимо выбрать хотя бы 1 параметр для анализа');
                        return;
                    }
                    $(".ba-quation-tab").each(function() {
                        analys_data($(this), id, form);
                    });
                    setTimeout(function() {
                        $('.question-lable a').first().click();
                    }, 1000);
                });
                $(".analys-quation").click(function() {
                    var tab = $("#" + $(this).data('tab-name'));
                    var id = $(this).data('id-form');
                    analys_data(tab, id);
                });
            });

            function analys_data(tab, id, form) {
                var data, single = false;
                if (typeof form === "undefined") {
                    form = $(tab).find(".analys-method");
                    single=true;
                }

                data = form.serialize();

                if (data === '') {
                    alert('Необходимо выбрать хотя бы 1 параметр для анализа');
                    return;
                }
                if (!single){
                    data += "&idQuestion=" + $(tab).find(".analys-method").find('input[name="idQuestion"]').val();
                }

                $.ajax({
                    url: "/forms/"+id+"/analysis/basic/result",
                    data: data,
                    success: function(analys_data) {
                        $(tab).find(".ba-settings").addClass("hidden");
                        $(tab).find('.ba-analyse-result').html(analys_data).removeClass("hidden");
                    }
                });
            }
        </script>
        <div class="tabbable tabs-left">
            <ul class="nav nav-tabs">
                <li class="active"><a href="#questionAll" data-toggle="tab">Настройки</a></li>
                <%--<c:forEach begin="0" end="${fn:length(selectQ)-1}" var="q">--%>
                <c:forEach begin="0" end="${questions.size()-1}" var="q">
                    <li class="question-lable">
                        <a href="#question${questions[q].idQuestion}" data-toggle="tab">Вопрос№${q+1}</a>
                    </li>
                </c:forEach>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="questionAll">
                    <!--Предупреждение о методах-->
                    <div class="alert <%if (false) {%>hidden<%}%>">
                        <button type="button" class="close" data-dismiss="alert">х</button>
                        <strong>Внимание!</strong> Выбор статистик зависит от шкалы вопроса
                    </div>
                    Общие настройки для всех вопросов.
                    Выберите тип расчета для вопросов.
                    <br>
                    <small> Если вы выбрали тип, не соответствующий шкале вопроса, то он рассчитыватся не будет. </small>
                    <jsp:include page="/WEB-INF/pages/analysis/basic/_types.jsp">
                        <jsp:param name="types" value="0"/>
                        <jsp:param name="tab_name" value="questionAll"/>
                    </jsp:include>
                    <button class="analys-all pull-left btn btn-primary" data-id-form="${form.idForm}">Провести анализ</button>

                </div>
                <c:forEach begin="0" end="${questions.size()-1}" var="q1">
                    <div class="tab-pane ba-quation-tab" id="question${questions[q1].idQuestion}">
                        <div class="ba-settings">
                            <h4>Вопрос: ${questions[q1].text} </h4>
                            Доступны только те статистики которые допустимы для данного вопроса
                            <jsp:include page="/WEB-INF/pages/analysis/basic/_types.jsp">
                                <jsp:param name="types" value="${questions[q1].scale}"/>
                                <jsp:param name="tab_name" value="question${questions[q1].idQuestion}"/>
                                <jsp:param name="idQuestion" value="${questions[q1].idQuestion}"/>
                            </jsp:include>
                            <button class="analys-quation pull-left btn btn-primary" data-tab-name="question${questions[q1].idQuestion}" data-id-form="${form.idForm}">Провести анализ</button>
                        </div>
                        <div class="ba-analyse-result hidden"></div>
                    </div>
                </c:forEach>
            </div>

        </div>
        <ul class="pager">
            <li class="previous">
                <a href="/forms/${form.idForm}/analysis/basic">&larr; Назад</a>
            </li>
        </ul>

        <br>
    </div>
</div>

<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>
