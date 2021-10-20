$('#password, #confirm_password').on('keyup', function () {
    if ($('#password').val() == $('#confirm_password').val() && $('#password').val() != '') {
        $('.message').html('Matching').css('color', 'green');
        $("#button_activate").prop('disabled', false);
    } else {
        $('.message').html('Not Matching').css('color', 'red');
        $("#button_activate").prop('disabled', true);
    }
});
