$(document).ready(function(){

    $('.add').click(function(){
        $(".list").append(
            '<div class="mb-2 row justify-content-between px-3">' +
            '<select class="mob mb-2">' +
            '<option value="opt1">Mon-Fri</option>' +
            '<option value="opt2">Sat-Sun</option>' +
            '</select>' +
            '<div class="mob">' +
            '<label class="text-grey mr-1">From</label>' +
            '<input class="ml-1" type="time" name="from">' +
            '</div>' +
            '<div class="mob mb-2">' +
            '<label class="text-grey mr-4">To</label>' +
            '<input class="ml-1" type="time" name="to">' +
            '</div>' +
            '<div class="mt-1 cancel fa fa-times text-danger">' +
            '</div>' +
            '</div>');
    });

    $(".list").on('click', '.cancel', function(){
        $(this).parent().remove();
    });

});