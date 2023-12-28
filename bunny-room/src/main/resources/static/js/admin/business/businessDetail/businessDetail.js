// 방 정보 중 일부 가져오기
function getRoomInfo(button){
    let roomNo = button.getAttribute('data1');
    let roomStatus = button.getAttribute('data2');

    if(roomStatus === 'inactive'){
        alert("이미 삭제된 방입니다.")
        return;
    }
    document.getElementById('getNo').value = roomNo;
    document.getElementById('getStatus').value = roomStatus;
}

function submitRoomInfo(){
    let roomNo = document.getElementById('getNo').value;
    let roomStatus = document.getElementById('getStatus').value;
    // let reason = document.getElementById('getReason').value;

    // json 데이터에 담기
    const jsonData = {
        roomNo: roomNo,
        roomStatus: roomStatus,
        // reason: reason
    }

    // Fetch를 사용해 서버단으로 전송
    fetch(`/admin/business/roomDelete?roomNo=${jsonData.roomNo}&roomStatus=${jsonData.roomStatus}&reason=${jsonData.reason}`,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)
    })
        .then(response => {
            if(!response.ok){
                throw new Error("`HTTP error! Status: ${response.status}`");
            }
            return response.text();
        })
        .then(data => {
            // 서버 응답 처리
            console.log(data);

            // 모달 닫기
            $('#staticBackdrop').modal('hide');
        })
        .catch(error => {
            // 에러 처리
            console.error('Fetch error', error);
        })

    location.reload();
}


