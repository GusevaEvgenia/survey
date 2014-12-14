<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Подключение хейдера--%>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>
<div class="row-fluid">
    <div class="span10 offset1">
        <%--Заголовок страницы--%>
        <div class="hero-unit hero">
            <h2>Создание анкеты<!--br><small>Каталог сохраненных анкет:</small--></h2>
        </div>
        <%--Форма для создания новой анкеты--%>
        <div class="row-fluid">
            <%--Ввод информации--%>
            <div class="span8">
                <form class="margin-button0" id="save-form" action="/forms" method="POST" commandName="form">
                    <div class="margin-button15">
                        <h4>Введите название анкеты</h4>
                        <p>
                            <input class="input-xxlarge" name="title" type="type" size="100">
                        </p>
                    </div>
                    <div class="margin-button15">
                        <h4>Введите краткое описание анкеты</h4>
                        <p>
                            <textarea class="width530" name="description" rows="3" ></textarea>
                        </p>
                    </div>
                    <div class="margin-button15">
                        <h4>Введите вводный текст анкеты:</h4>
                        <p>
                            <textarea class="width530" name="startText" rows="3"></textarea>
                        </p>
                    </div>
                    <div class="">
                        <h4>Отображать текст на странице завершения:</h4>
                        <p>
                            <textarea class="width530" name="finishText" rows="3"></textarea>
                        </p>
                    </div>
                    <textarea class="hidden" name="status">draft</textarea>
                    <textarea class="hidden" name="idUser">1</textarea>
                    <input type="hidden" id="link" name="link" value="1111">
                    <input type="hidden" name="picture" value="/uploads/draft.jpg">
                </form>
            </div>
            <%--Выбор логотипа анкеты--%>
            <%--<div class="span4">
                <h4>
                    Вставьте свой логотип анкеты
                    <br>
                    <small>Его будете видеть только Вы</small>
                </h4>
                <form action="" enctype="multipart/form-data" method="post">
                    <p>
                        <input class="hidden" type="file" name="picture" accept="image/*,image/jpeg">
                        <input class="btn btn-primary" type="submit" value="Загрузить">
                    </p>
                </form>
                <img src="${false ? param.picture : "/images/active.jpg"}" width="180" height="200"  alt="">
            </div>--%>
        </div>
        <div class="row-fluid" >
            <div class="span2">
                <button class="btn btn-primary btn-block" id="btn_hash" type="submit" form="save-form">Создать</button>
            </div>
        </div>
    </div>
</div>
<%--Подключение футера--%>
<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>
