<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/pages/partials/header.jsp">
    <jsp:param name="title" value=""/>
</jsp:include>

<div class="row-fluid">
    <!--УДАЛИТЬ СТИЛЬ-->
    <div class="span10 offset1">
        <div class="page-header" style="background-image: url(/images/background.jpg); padding: 30px; color: #ffffff">
            <h1>Создание анкеты<!--br><small>Каталог сохраненных анкет:</small--></h1>
        </div>
        <div class="row-fluid">
            <div class="span8">
                <form class="margine-botton0" id="save-form" action="/forms" method="POST">
                    <div class="margine-botton15">
                        <h4>Введите название анкеты</h4>
                        <p>
                            <input class="input-xxlarge" name="name" type="type" size="100" name="#">
                        </p>
                    </div>
                    <div class="margine-botton15">
                        <h4>Введите краткое описание анкеты</h4>
                        <p>
                            <textarea class="width530" name="description" rows="3" ></textarea>
                        </p>
                    </div>
                    <div class="margine-botton15">
                        <h4>Введите вводный текст анкеты:</h4>
                        <p>
                            <textarea class="width530" name="start-text" rows="3"></textarea>
                        </p>
                    </div>
                    <div class="">
                        <h4>Отображать текст на странице завершения:</h4>
                        <p>
                            <textarea class="width530" name="finish-text" rows="3"></textarea>
                        </p>
                    </div>
                </form>
            </div>
            <div class="span4">
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
                <img src="${false ? param.picture : "/img/form.jpg"}" width="180" height="200"  alt="">
            </div>
        </div>
        <div class="row-fluid" >
            <div class="span2">
                <button class="btn btn-primary btn-block" type="submit" form="save-form">Создать</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/pages/partials/footer.jsp"/>
