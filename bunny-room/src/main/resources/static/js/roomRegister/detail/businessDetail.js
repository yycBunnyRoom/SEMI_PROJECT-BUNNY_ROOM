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
        roomDiv.addEventListener('click', function () {
            goToRoomPage(room.roomNo);
        });
        roomListContainer.appendChild(roomDiv);
    })
}

function goToRoomPage(roomNo) {
    window.location.href = `/roomRegister/room/roomDetail/${roomNo}`;
}


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


/*예약 시간 관리*/
document.getElementById('timeScheduleButton').addEventListener('click', function() {
    if (businessNo) {
        console.log('businessNo value:', businessNo);
        window.location.href = '/roomRegister/timeSchedule?businessNo=' + businessNo;
    } else {
        console.error('businessNo is null or undefined');
        alert("businessNo is null or undefined");
    }
});


function getOpeningHours(){
    /* 영업시간 계산하는 방법 */
    let openTime = Math.min(...timeUnits.map(unit => unit.startTime));
    let endTime = Math.max(...timeUnits.map(unit => unit.endTime));

    let openingHours = document.getElementById('openingHours')

    console.log(openingHours)

    openingHours.textContent = "영업시간: "+openTime+":00 - "+endTime+":00";
}


function createWeekDaysButtons() {
    const buttonsContainer = document.getElementById('btn_closedDays');
    weekDays.forEach(weekday => {
        const button = document.createElement('button');
        button.textContent = weekday;
        button.id = weekday; // 각 버튼에 요일에 해당하는 ID 부여

        button.disabled = true;
        // closedDays에 해당 요일이 있는 경우 버튼을 비활성화하고 다른 스타일 적용
        if (closedDays.some(closedDay => closedDay.closedDay === weekday)) {
            button.classList.add('closed-day');
        }
        else {
            button.classList.add('weekday');
            // button.addEventListener('click', () => selectDay(weekday)); // 클릭 이벤트에 selectDay 함수 추가
        }
        buttonsContainer.appendChild(button);
    });
}






window.onload = () => {
    getOpeningHours()
    createWeekDaysButtons()
};