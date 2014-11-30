var Designer = {
    modalPopup: null,
    context: null,
    questionList: null,
    questions: [],

    init: function() {
        this.modalPopup  = $('#preview-popup');
        this.context = $('#form-constructor');
        this.templateContainer = this.$('#template-container');
        this.questionList = this.$('#question-list');
        this.initEvents();
    },

    initEvents: function() {
        var designer = this;
        $("#form-preview").click(function() {
            designer.preview();
        });
        $(this.questionList).sortable({
            handle: ".icon-resize-vertical",
            revert: true
        });
        $('.template-list a').draggable({
            cursor: "move",
            revert: true,
            connectToSortable: $(this.questionList),
            helper: function( event ) {
                return designer.getTemplate($(this).data('type'));
            },
            stop: function(event, position) {
                designer.addQuestion(position.helper);
            }
        });


    },

    $: function(selector) {
        return $(this.context).find(selector);
    },
    //TODO сделать это=)
    addQuestion: function(node) {
        var question = new DesignerTemplate(node);

        this.questions.push(question);
    },

    //TODO Сделаьть индекс вопроса
    getTemplate: function(name) {
        var main_template = $(this.templateContainer).find('#main-template').clone();
        var template = $(this.templateContainer).find('#'+name).clone();
        main_template.find('.title').html(template.data('title'));
        main_template.find('.designer-item-body').append(template.children());
        main_template.removeAttr('id');
        return main_template.find('.designer-item');
    },

    preview: function() {
        var name = $("input[name='name']").val();
        var form = $("#form-constructor").html();
        this.modalPopup.find(".modal-body").html(/*"<h2>" + name + "</h2>" + form*/);
        this.modalPopup.modal('show');
    }
};

$(document).ready(function() {
    Designer.init();
});