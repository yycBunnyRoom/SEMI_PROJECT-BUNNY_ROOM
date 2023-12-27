document.getElementById('dayOffButton').addEventListener('click', function() {
    console.log('businessNo value:', businessNo);
    if (businessNo) {
        console.log('businessNo value:', businessNo);
        window.location.href = '/roomRegister/dayOffRegisterForm?businessNo=' + businessNo;
    } else {
        console.error('businessNo is null or undefined');
    }
});