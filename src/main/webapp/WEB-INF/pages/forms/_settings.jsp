<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row-fluid">
    <div class="span8">
        <form class="margin-button0" id="save-form" action="/forms/show.jsp" method="POST">
            <div class="margin-button15">
                <h4>Введите название анкеты:</h4>
                <p>
                    <input class="input-xxlarge" name="name" value="Название анкеты" type="text" size="100">
                </p>
            </div>
            <div class="margin-button15">
                <h4>Введите краткое описание анкеты:</h4>
                <p>
                    <textarea class="width530" name="description"  value="" rows="3" ></textarea>
                </p>
            </div>
            <div class="margin-button15">
                <h4>Введите вводный текст анкеты:</h4>
                <p>
                    <textarea class="width530" name="start-text"  value="" rows="3"></textarea>
                </p>
            </div>
            <div class="margin-button15">
                <h4>Отображать текст на странице завершения:</h4>
                <p>
                    <textarea class="width530" name="finish-text"  value="" rows="3"></textarea>
                </p>
            </div>
            <div class="margin-button15">
                <h4>Введите количество анкет необходимых для исследования</h4>
                <p>
                    <input class="input-mini" name="number" type="number"  value="" >
                </p>
            </div>
            <div>
                <h4>Статус анкеты</h4>
                <p>
                    <select>
                        <option>В работе</option>
                        <option>Архивная</option>
                    </select>
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
        <img src="${false ? param.picture : "/images/form.jpg"}" width="180" height="200"  alt="">
    </div>
</div>
<div class="row-fluid" >
    <div class="span3 offset0">
        <a class="btn btn-primary btn-block" form="save-form" type="submit">Сохранить</a>
    </div>
</div> 