(function() {
    addDetailListener();
    addCloseButtonListener();
} ());

function addDetailListener() {
    $('.detail-button').click(function(event) {
        let id = $(this).attr('data-id');

        $.ajax({
            url: `/api/log/${id}`,
            success: function(response) {
                $('.modal-layer').addClass('modal-layer--opened');
                $('.detail-dialog').addClass('popup-dialog--opened');
                $('.detail-dialog .logDate').text(response.logDate);
                $('.detail-dialog .note').text(response.note);
                $('.detail-dialog .activity').text(response.activity);
            }
        })
    })
}

function addCloseButtonListener() {
    $('.close-button').click(function(event) {
        $('.modal-layer').removeClass('modal-layer--opened');
        $('.popup-dialog').removeClass('popup-dialog--opened');
    })
}