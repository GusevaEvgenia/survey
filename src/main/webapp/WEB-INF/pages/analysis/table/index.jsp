<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>

<div class="row-fluid">
    <div class="span10 offset1">
        <div class="page-header height120" style="background-image: url(/images/background.jpg); padding: 30px; color: #ffffff">
            <h1>Анализ собранных данных по выбранной анкете<!--br><small>Каталог сохраненных анкет:</small--></h1>
        </div>

        <jsp:include page="/WEB-INF/pages/analysis/_menu.jsp"/>

        <script>
            $(document).ready(function() {
                $(".select-parameter").change(function() {
                    controllToggle($(this).val());
                });
                controllToggle($(".select-parameter:checked").val());

                $(".d_statictic").change(function() {
                    if ($(this).is(":checked")) {
                        $(".sl_statictic").removeAttr("disabled");
                        $(".level").removeClass("hidden");
                    }else{
                        $(".sl_statictic").attr("disabled", "disabled");
                        $(".sl_statictic").removeAttr("checked");
                        $(".level").addClass("hidden");
                    }
                });
            });

            function controllToggle(val) {
                if (val === "2") {
                    $(".paramert2").addClass("hidden");
                    $("#parameter-wording").text("");
                } else {
                    $(".paramert2").removeClass("hidden");
                    $("#parameter-wording").text("одна");
                }
            }
        </script>
        <form action="/forms/123/analysis/table/result">
            <div class="row-fluid">
                <div class="span12">
                    <input type="hidden" name="page" value="2">
                    <h4>Выберите параметры для построения таблиц сопряженности признаков</h4>
                    <strong>Выберите количество переменных:</strong>
                    <div class="form-inline">
                        <label class="radio">
                            <input class="select-parameter" type="radio" name="parameter_size" id="optionsRadios1" value="2" checked>
                            Два переменных &nbsp
                        </label>
                        <label class="radio">
                            <input class="select-parameter" type="radio" name="parameter_size" id="optionsRadios2" value="3">
                            Три переменных
                        </label>
                    </div><br>
                    <strong>Выберите переменные:</strong>
                    <table>
                        <tr>
                            <td class="width220">Зависимая переменная</td>
                            <td>
                                <select name="main_parameter">
                                    <% for (int i = 1; i < 6; i++) {%>
                                    <option value="<%=i%>">
                                        Вопрос№<%=i%>
                                    </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="width220">Независимая переменная <span id="parameter-wording"></span></td>
                            <td>
                                <select name="first_parameter">
                                    <% for (int i = 1; i < 6; i++) {%>
                                    <option value="<%=i%>">
                                        Вопрос№<%=i%>
                                    </option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr class="paramert2 width220">
                            <td>Независимая переменная два</td>
                            <td><select name="second_parameter">
                                <% for (int i = 1; i < 6; i++) {%>
                                <option value="<%=i%>">
                                    Вопрос№<%=i%>
                                </option>
                                <%}%>
                            </select>
                            </td>
                        </tr>
                    </table>

                    <strong>Выберите статистики</strong>
                    <label class="checkbox">
                        <input class="d_statictic" type="checkbox" name="dependence" value="1">
                        Статистическая зависимость наблюдаемой связи
                    </label>
                    <div class="margine-left30 level hidden">
                        Выберите уровень значимости
                        <select>
                            <option>0,5</option>
                            <option>0,025</option>
                            <option>0,01</option>
                            <option>0,005</option>
                            <option>0,001</option>
                        </select>
                    </div>
                    <label class="checkbox">
                        <input class="sl_statictic" type="checkbox" name="strength_links" value="2" disabled="disabled">
                        Тоснота связи
                    </label>

                    <button class="btn btn-primary">Провести анализ</button>
                </div>
            </div>
        </form>
        <br>
    </div>
</div>

<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>
