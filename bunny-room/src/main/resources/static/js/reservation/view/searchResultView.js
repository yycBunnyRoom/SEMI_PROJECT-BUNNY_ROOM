const roomsContainer = document.getElementById('rooms');


if (rooms.length === 0){
    console.log(1)
    roomsContainer.innerHTML = '<p>검색된 방이 없습니다.</p>';
}
else{
    rooms.forEach(room =>{
        // 새로운 div 생성
        const roomDiv = document.createElement('div');
        roomDiv.textContent = `방 이름: ${room.roomName} \n  방소개: ${room.roomIntro}`;
        // 클래스 추가
        roomDiv.classList.add('room-item');
        // 데이터 속성 추가
        roomDiv.dataset.roomNo = room.roomNo;
        roomsContainer.appendChild(roomDiv);

        // 클릭 이벤트를 새로운 div에 추가
        roomDiv.addEventListener('click', function () {
            goToRoomPage(room.roomNo);
        });
        roomsContainer.appendChild(roomDiv);
    })
}

function goToRoomPage(roomNo) {
    window.location.href = `/roomRegister/room/roomDetail/${roomNo}`;
}