const roomListContainer = document.getElementById('roomList');

console.log(roomList)

if (roomList.length === 0){
    console.log(1)
    roomListContainer.innerHTML = '<p>등록된 방이 없습니다.</p>';
}
else{
    roomList.forEach(room =>{
        // 새로운 div 생성
        const roomDiv = document.createElement('div');
        roomDiv.textContent = `${room.roomName} - ${room.roomIntro}`;
        // 클래스 추가
        roomDiv.classList.add('room-item');
        // 데이터 속성 추가
        roomDiv.dataset.roomNo = room.roomNo;
        roomListContainer.appendChild(roomDiv);

        // 클릭 이벤트를 새로운 div에 추가
        // roomDiv.addEventListener('click', function () {
        //     goToRoomPage(room.roomNo);
        // });
        roomListContainer.appendChild(roomDiv);
    })
}




// /* Rooms */
//
// document.addEventListener('DOMContentLoaded', getRoomList);
//
// console.log(1)
// console.log(businessNo)
// function getRoomList() {
//     fetch(`/roomRegister/room/getAllRooms?businessNo=${businessNo}`) // 백엔드에서 API 엔드포인트 설정 필요
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error('Network response was not ok');
//             }
//             return response.json();
//         })
//         .then(data => {
//             console.log(2)
//             const roomListContainer = document.getElementById('roomList');
//
//             if (data.length === 0) {
//                 roomListContainer.innerHTML = '<p>등록된 방이 없습니다.</p>';
//             } else {
//                 data.forEach(room => {
//                     // 새로운 div 생성
//                     const roomDiv = document.createElement('div');
//                     roomDiv.textContent = `${room.roomName} - ${room.roomIntro}`;
//                     // 클래스 추가
//                     roomDiv.classList.add('room-item');
//                     // 데이터 속성 추가
//                     roomDiv.dataset.roomNo = room.roomNo;
//                     roomListContainer.appendChild(roomDiv);
//
//                     // 클릭 이벤트를 새로운 div에 추가
//                     roomDiv.addEventListener('click', function () {
//                         goToRoomPage(room.roomNo);
//                     });
//                     roomListContainer.appendChild(roomDiv);
//                 });
//             }
//         })
//         .catch(error => {
//             console.error('Error:', error);
//             // 에러 처리
//         });
// }
//
//
// function goToRoomPage(roomNo) {
//     window.location.href = `/roomRegister/room/roomDetail/${roomNo}`;
// }
//
//
//
//
//
//

document.getElementById('addRoomButton').addEventListener('click', function() {
    if (businessNo) {
        console.log('businessNo value:', businessNo);
        window.location.href = '/roomRegister/roomRegisterForm?businessNo=' + businessNo;
    } else {
        console.error('businessNo is null or undefined');
        alert("businessNo is null or undefined");
    }
});





/* 휴무 */
document.getElementById('dayOffButton').addEventListener('click', function() {
    console.log('businessNo value:', businessNo);
    if (businessNo) {
        console.log('businessNo value:', businessNo);
        window.location.href = '/roomRegister/dayOffRegisterForm?businessNo=' + businessNo;
    } else {
        console.error('businessNo is null or undefined');
        alert("businessNo is null or undefined");
    }
});

