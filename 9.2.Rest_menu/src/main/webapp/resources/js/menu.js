$(window).load(function(){
    // send ajax to
    // on answer
    $.get("menu/content", function(elements) {
        $("#loading").hide();
        // find div with id = menu_container
        var container = $("#menu_container");
        // for each elements
        for (var index in elements) {
            var element = elements[index];
            // add <a href="element">element</a><br> inside div
            container.append('<a href="' + element + '">' + element + '</a></br>');
        }
    });
});