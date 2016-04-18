
function makelist() {
    $('.scrolllist').on('mousewheel', function (e) {
        var oEvent = e.originalEvent,
            delta = oEvent.deltaY;

        if (delta < 0) {
            $('.scrolllist').prepend($(".scrollitem:last-of-type"));
        } else if (delta > 0) {
            $('.scrolllist').append($(".scrollitem:first-of-type"));
        }
    });

    $('.buttonup').click(function() {$('.scrolllist').prepend($(".scrollitem:last-of-type"))});

    $('.buttondown').click(function() {$('.scrolllist').append($(".scrollitem:first-of-type"))});
}


