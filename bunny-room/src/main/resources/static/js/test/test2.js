$(document).ready(function(){

    $('.input-daterange').datepicker({
        format: 'dd-mm-yyyy',
        todayHighlight: true,
        startDate: '0d'
    });

    $('#inputDate').datepicker({
        format: 'yyyy-mm-dd',
        todayHighlight: true,
        startDate: '0d'
    });
});