function DesignerTemplate(node, question) {
    this.context = $(node);
    this.context.data('instance', this);
    this.type = $(this.context).data('type');
    this.context.css('height', 'auto');
    this.OPTION_ROW= '.option-index';
    if (typeof question != "undefined") {
        this.initValues(question);
    }
    this.initEvents();
}
DesignerTemplate.prototype.initEvents = function() {
    var question = this;
    this.$('.icon-remove').click(function() {
        question.context.fadeOut(300, function(){ question.remove()});
    });
    this.context.delegate('.icon-minus', 'click', function(){
        if(question.$(question.OPTION_ROW).length > 1) {
            $(this).parents('label').remove();
            question.reindex();
        }
    });
    this.context.delegate('.icon-plus', 'click', function(){
        var parent = $(this).parents('label');
        var template = parent.clone();
//TODO clean template
        parent.after(template);
        question.reindex();
    })
};
DesignerTemplate.prototype.initValues = function(data) {
    this.$('.question').val(data['text']);
    var template = this.$('.option-index').first().clone();
    var optionsContainer = this.$('.options-container');
    optionsContainer.empty();
    for(var o in data.options) {
        var localTemplate = template.clone();
        localTemplate.find('.option').val(data.options[o].text);
        optionsContainer.append(localTemplate)
    }
    this.reindex();
};
DesignerTemplate.prototype.reindex = function(question){
    var indexer = function(i){
        var option = $(this).find(".option, .matrix-option");
        var name = option.attr("name");
        var questionId = typeof question == "undefined" ? "$1" : question;
        var newName = name.replace(/questions\[(\d+)\].options\[(\d+)\](\..*)/, "questions[" + questionId + "].options[" + i + "]$3");
        option.attr("name", newName);
    }
    this.$('.options-container').find(".option-index").each(indexer);
    this.$('.matrix-options-container').find(".option-index").each(indexer);

    if (typeof question != "undefined") {
        this.$(".question").attr("name", "questions[" + question + "].text");
    }
};
DesignerTemplate.prototype.remove = function() {
    this.context.remove();
    Designer.reindex();
};
DesignerTemplate.prototype.$ = function(selector) {
    return $(this.context).find(selector);
};