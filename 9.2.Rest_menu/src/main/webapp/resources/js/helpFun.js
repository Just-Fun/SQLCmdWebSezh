$(window).load(function(){
    // send ajax to
    // on answer
    $.get("help/fun", function(element) {
        $("#loading").hide();
        // find div with id = menu_container
        var container = $("#help_fun");
        // add <a href="element">element</a><br> inside div
         var element = element;
        container.append('<a href="https:www.google.com.ua">' + element + '</a></br>');
        })
    });