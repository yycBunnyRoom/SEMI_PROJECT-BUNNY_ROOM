let selectedStartTime;
let selectedMinRsvTime;


/* 동적으로 시간 선택 요소 생성*/
    // 시작 시간 선택 요소를 가져옵니다.
const startTimeSelect = document.getElementById('startTime');

    // option을 만들어 시작 시간 선택 요소에 추가합니다.
for (let i = 1; i <= 12; i++) {
    const startTimeOption = createStartTimeOption(i);
    startTimeSelect.appendChild(startTimeOption);
}
startTimeSelect.selectedIndex = 0;

    // 선택한 시간 출력을 위한 이벤트 리스너를 추가할 수도 있습니다.
startTimeSelect.addEventListener('change', function() {
    const selectedTime = startTimeSelect.value;
    selectedStartTime = Number(selectedTime);

    updateEndTime();
    if (selectedTime <10){
        alert(`시작시간: 0${selectedTime}:00`);
    }
    else {
        alert(`시작시간: ${selectedTime}:00`);
    }
});



/* 동적으로 최소 예약 시간 선택 요소 생성 */

    // minRsvTimeOption 을 동적으로 만들어서 minRsvTime 에 추가
const minRsvTime = document.getElementById('minRsvTime');
for (let i = 1; i <= 8; i++){
    const minRsvTimeOption = createMinRsvTimeOption(i);
    minRsvTime.appendChild(minRsvTimeOption);
}
    // minRsvTime 을 선택할 시 alert가 뜨게
minRsvTime.addEventListener('change',function (){
    const selectedMinRsvTimeOption = minRsvTime.value;
    selectedMinRsvTime = Number(selectedMinRsvTimeOption);
    updateEndTime();
    alert(`최소 예약시간: ${selectedMinRsvTimeOption} 시간`);
})



const endTime = document.getElementById('endTime');
let minEndTime;
let maxRsvUnit;

function updateEndTime() {
    endTime.innerHTML = '';
    /* 동적으로 종료시간 옵션을 만든다 */


    // endTimeOption 을 동적으로 만들어서 endTime 에 추가
    minEndTime = Number(selectedStartTime + selectedMinRsvTime);
// const maxEndTime = selectedStartTime + selectedMinRsvTime* (((24 - selectedStartTime) / selectedMinRsvTime) - ((24 - selectedStartTime) % selectedMinRsvTime));


// (12 - selectedStartTime)은 선택된 시작 시간 이후에 남은 시간을 나타낸다
// 이 남은 시간을 최소 예약 시간으로 나누어서 해당 구간에 가능한 예약 횟수를 계산한다
// Math.floor() 함수는 소수점 이하를 버리고 정수 부분만을 반환함
    maxRsvUnit = (Math.floor((24 - selectedStartTime) / selectedMinRsvTime));

    const maxEndTime =
        selectedStartTime +
        selectedMinRsvTime * maxRsvUnit;

    for (let i = minEndTime; i <= maxEndTime; i += selectedMinRsvTime) {
        const endTimeOption = createEndTimeOption(i);
        endTime.appendChild(endTimeOption);
    }
}

// minRsvTime 을 선택할 시 alert가 뜨게
endTime.addEventListener('change',function (){
    const selectedEndTime = endTime.value;
    alert(`종료시간: ${selectedEndTime}`);
})





/* SELECT 에서 동적으로 옵션을 만들어수는 function */
    // option을 만들고 select에 추가하는 함수를 정의합니다.
function createStartTimeOption(value) {
    const option = document.createElement('option');
    option.value = value;
    if (option.value < 10){
        option.textContent = `0${value}:00`;
    }
    else {
        option.textContent = `${value}:00`;
    }

    return option;
}

function createMinRsvTimeOption(value) {
    const option = document.createElement('option');
    option.value = value;
    option.textContent = `${value} 시간`;
    return option;
}

function createEndTimeOption(value) {
    const option = document.createElement('option');
    option.value = value;
    option.value = value;
    if (option.value < 10){
        option.textContent = `0${value}:00`;
    }
    else {
        option.textContent = `${value}:00`;
    }
    return option;
}




/* addTimeSchedule */

document.getElementById('addTimeSchedule').addEventListener('click', function() {
    if (businessNo) {

        const startTime = document.getElementById('startTime').value;
        const minRsvTime = document.getElementById('minRsvTime').value;
        const endTime = document.getElementById('endTime').value;

        const data = {
            startTime: startTime,
            minRsvTime: minRsvTime,
            endTime: endTime,

            businessNo: businessNo
        };

        fetch('/roomRegister/business/addTimeSchedule', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json' // JSON 형태로 데이터 전송
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                console.log('Success:', data);
                if (data > 0 ) {
                    alert('예약 테이블을 등록 성공!');
                    window.location.href = `/roomRegister/business/businessDetail/${businessNo}`;
                } else {
                    alert('예약 테이블을 등록 실패!');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('네트워크 오류가 발생했습니다.');
            });


        console.log('businessNo value:', businessNo);
        window.location.href = '/roomRegister/roomRegisterForm?businessNo=' + businessNo;
    } else {
        console.error('businessNo is null or undefined');
        alert("businessNo is null or undefined");
    }
});


