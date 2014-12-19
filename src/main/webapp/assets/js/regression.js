$(document).ready(function () {
    var type_changed = false;
    var $tabs = $(".tab-content");
    $tabs.delegate('.next a','click', function () {
        var $target =  $(".regression-tabs li.active").next().find("a"),
            $targetTab = $($target.attr('href')),
            targetUrl = $targetTab.data("target"),
            params = {};
        var flag = false;
        switch ($(this).data("step")) {
            case 1:
                reset_results();
                break;
            case 2:
                reset_results();
                params.important_level = $("select[name='important_level']").val();
                break;
            case 3:
                reset_results();
                params.important_level = $("select[name='important_level2']").val();
                if ($("#flag").data("val")) {
                    flag = true;
                }
                break;
            case 5:
                reset_results();
                if ($("#flag").data("val")) {
                    flag = true;
                }
                break;
            case 4:
                reset_results();
                if ($("#flag").data("val")) {
                    flag = true;
                }
                break;
            case 6:
                reset_results();
                break;
            default:
//                switch_tab();
        }
        if (flag) {
            finishTab();
        }else {
            getTab($targetTab, targetUrl + '?' + $.param(params));
        }
    });
    $tabs.delegate("select[name='important_level']", "change", function () {
        type_changed = true;
    });
    $tabs.delegate(".previous a", "click", function () {
        switch ($(this).data("step")) {
            case 1:
                var id = window.location.pathname.match(/forms\/(\d+)\/.*/)[1];
                window.location.href = "/forms/"+ id +"/analysis/regression";
                break;
            case "finish":
                $(".regression-tabs li.active").prevAll(':visible').first().find("a").click();
                break;
            default:
                $(".regression-tabs li.active").prev().find("a").click();
        }
    });
    $tabs.delegate(".function-type", "change", function () {
        type_changed = true;
        $(".model-regression").addClass("hidden");
        $("#" + $(this).val()).removeClass("hidden");

        if ($(this).val() === "function-2") {
            $(".unline_form").removeClass("hidden");
        } else {
            $(".unline_form").addClass("hidden");
        }
    });
    $tabs.delegate("#prognoz_value", "keypress", function () {
        $("#prognoz_result").text(prognoz($(this).val()));
    });

    function getTab(tab, url) {
        $.get(url, function(data) {
            $(tab).html(data);
            switch_tab();
        });
    }

    function switch_tab() {
        $(".regression-tabs li.active").next().find("a").click();
        $(".regression-tabs li.active").removeClass("hidden");
    }

    function finishTab() {
        $(".regression-tabs li#finish-handler").find("a").click();
        $(".regression-tabs li.active").removeClass("hidden");
    }

    function reset_results() {
        if (type_changed) {
            $(".regression-tabs li.active").nextAll().addClass("hidden");
            type_changed = false;
        }
    }

    function prognoz(x) {
        return x * 12 + 0.45;
    }
});