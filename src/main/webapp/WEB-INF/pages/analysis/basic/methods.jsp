<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>

<div class="row-fluid">
    <div class="span10 offset1">
        <div class="page-header height120 hero">
            <h1>Анализ собранных данных по выбранной анкете<!--br><small>Каталог сохраненных анкет:</small--></h1>
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
                    if (form.serialize() === '') {
                        alert('Необходимо выбрать хотя бы 1 параметр для анализа');
                        return;
                    }
                    $(".ba-quation-tab").each(function() {
                        analys_data($(this), form);
                    });
                    setTimeout(function() {
                        $('.question-lable a').first().click();
                    }, 1000);
                });
                $(".analys-quation").click(function() {
                    var tab = $("#" + $(this).data('tab-name'));
                    analys_data(tab);
                });
            });

            function analys_data(tab, form) {
                if (typeof form === "undefined") {
                    form = $(tab).find(".analys-method");
                }
                var data = form.serialize();
                if (data === '') {
                    alert('Необходимо выбрать хотя бы 1 параметр для анализа');
                    return;
                }
                $.ajax({
                    url: "/forms/123/analysis/basic/result",
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
                <% int size = request.getParameterValues("questions").length;
                    for (int i = 1; i <= size; i++) {
                        String value = request.getParameterValues("questions")[i - 1];%>
                <li class="question-lable"><a href="#question<%=value%>" data-toggle="tab">Вопрос№<%=value%></a></li>
                <%}%>
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
                    <button class="analys-all pull-left btn btn-primary">Провести анализ</button>

                </div>
                <%for (int i = 1; i <= size; i++) {
                    String value = request.getParameterValues("questions")[i - 1];
                    String tab_id = "question" + value;%>
                <div class="tab-pane ba-quation-tab" id="<%=tab_id%>">
                    <div class="ba-settings">
                        Доступны только те статистики которые допустимы для данного вопроса
                        <jsp:include page="/WEB-INF/pages/analysis/basic/_types.jsp">
                            <jsp:param name="types" value="1"/>
                            <jsp:param name="tab_name" value="<%=tab_id%>" />
                        </jsp:include>
                        <button class="analys-quation pull-left btn btn-primary" data-tab-name="<%=tab_id%>">Провести анализ</button>
                    </div>
                    <div class="ba-analyse-result hidden"></div>
                </div>
                <%}%>
            </div>

        </div>
        <ul class="pager">
            <li class="previous">
                <a href="/forms/123/analysis/basic">&larr; Назад</a>
            </li>
        </ul>

        <br>
    </div>
</div>

<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>
