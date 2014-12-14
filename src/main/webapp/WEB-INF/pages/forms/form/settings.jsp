<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%--Подключение хейдера--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value="Анкета"/>
</jsp:include>

<script>
    $(document).ready(function() {
        $(".statusForm").change(function() {
            if ($(this).is(":checked")) {
                $(".statusFormSet").val("archive");
            }else{
                $(".statusFormSet").val(${form.status});
            }
        });
    });
</script>

<jsp:include page="/WEB-INF/pages/forms/form/_menu.jsp"/>

        <div class="row-fluid">
            <div class="span7 offset3">
                <form class="margin-button0" id="hh" action="/forms/${form.idForm}/settings" method="POST"
                      commandName="formUpdate" accept-charset="UTF-8">
                    <div class="margin-button15">
                        <h4>Название анкеты:</h4>
                        <p>
                            <input class="input-xxlarge" name="title" value="${form.title}" type="text" size="100">
                        </p>
                    </div>
                    <div class="margin-button15">
                        <h4>Краткое описание анкеты:</h4>
                        <p>
                            <textarea class="width530" name="description" rows="3" >${form.description}</textarea>
                        </p>
                    </div>
                    <div class="margin-button15">
                        <h4>Текст приветствия анкеты:</h4>
                        <p>
                            <textarea class="width530" name="startText" rows="3">${form.startText}</textarea>
                        </p>
                    </div>
                    <div class="margin-button15">
                        <h4>Текст на странице завершения:</h4>
                        <p>
                            <textarea class="width530" name="finishText"  rows="3">${form.finishText}</textarea>
                        </p>
                    </div>
                    <div class="margin-button15">
                        <h4>Введите количество анкет необходимых для исследования</h4>
                        <p>
                            <input class="input-mini" name="maximumForms" type="number"  value="${form.maximumForms}" >
                        </p>
                    </div>
                    <div class="margin-button15">
                            <label class="checkbox">
                                <h4>Переместить анкету в архив <input class="statusForm" type="checkbox" name="arch" value="true"></h4>
                            </label>
                    </div>
                    <textarea class="hidden" name="idForm" rows="3">${form.idForm}</textarea>
                    <input type="hidden" class="statusFormSet" name="status" value="${form.status}" type="text">
                </form>
            </div>
            <%--<div class="span4">
                <h4>
                    Вставьте свой логотип анкеты
                    <br>
                    <small>Его будете видеть только вы</small>
                </h4>
                <form action="" enctype="multipart/form-data" method="post">
                    <p>
                        <input class="hidden" type="file" name="picture" accept="image/*,image/jpeg">
                        <input class="btn btn-primary" type="submit" value="Загрузить">
                    </p>
                </form>
                <img src="${false ? param.picture : "/images/form.jpg"}" width="180" height="200"  alt="">
            </div>--%>
        </div>
        <div class="row-fluid" >
            <div class="span2 offset3">
                <button class="btn btn-primary btn-block" form="hh" type="submit">Сохранить</button>
            </div>
        </div>


    </div>
</div>
<%--Подключение футера--%>
<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>