var Designer = {
    modalPopup: null,
    context: null,
    questionList: null,
    questions: [],

    init: function() {
        this.modalPopup  = $('#preview-popup');
        this.context = $('#form-constructor');
        this.templateContainer = $(this.context).find('#template-container');
        this.questionList = $(this.context).find('#question-list');
        this.initEvents();
    },

    initEvents: function() {
        var designer = this;
        $("#form-preview").click(function() {
            designer.preview();
        });
        $(this.questionList).sortable({
            handle: ".ui-icon-arrowthick-2-n-s",
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

    addQuestion: function(node) {
        var question = new DesignerTemplate(node);

        this.questions.push(question);
    },

    getTemplate: function(name) {
        var main_template = $(this.templateContainer).find('#main-template').clone();
        main_template.find('.designer-item-body').append($(this.templateContainer).find('#'+name).clone().children());
        main_template.removeAttr('id');
        return main_template;
    },

    preview: function() {
        var name = $("input[name='name']").val();
        var form = $("#form-constructor").html();
        this.modalPopup.find(".modal-body").html("<h2>" + name + "</h2>" + form);
        this.modalPopup.modal('show');
    }
};

$(document).ready(function() {
    Designer.init();
});