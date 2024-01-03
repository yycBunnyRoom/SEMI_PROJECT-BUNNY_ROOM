function cancelReservation(button){
    let reservationNo = button.getAttribute('reservationNo');
    document.getElementById('getReservationNo').value = reservationNo;
    console.log(reservationNo);
}

function submitReservationNo(){
    let reservationNo = document.getElementById('getReservationNo').value;
    let reason = document.getElementById('getReason').value;

    let json = {
        reservationNo : reservationNo,
        cancelReason : reason
    }

    fetch('/myPage/cancelReservation', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(json)
    })
        .then(response => {
            if(!response.ok){
                throw new Error("`HTTP error! Status: ${response.status}`");
            }
            return response.text();
        })
        .then(data => {
            console.log(data);
            /* 정상적으로 동작이 끝나면 새로고침을 하도록 실행 */
            location.reload();
        })
        .catch(error => {
            console.error('Fetch Error', error);
        })
}