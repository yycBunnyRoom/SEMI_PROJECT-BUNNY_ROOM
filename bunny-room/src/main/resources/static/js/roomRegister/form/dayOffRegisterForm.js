
/* 정기 휴무 */


// 선택한 요일을 담을 변수를 선언
let selectedDays = [];

function selectDay(day) {
    // 선택한 날(day) 의 값을 콘솔에 출력 - 확인용
    console.log("선택된 요일:", day);

    const btn = document.getElementById(day);
    if (!selectedDays.includes(day)) {
        selectedDays.push(day);
        btn.classList.add("selected");
        updateSelectedDays();
    } else {
        selectedDays = selectedDays.filter(item => item !== day);
        btn.classList.remove("selected");
        updateSelectedDays();
    }
}

function updateSelectedDays() {
    document.getElementById("selectedDays").innerText = "선택된 요일: " + selectedDays.join(", ");
}

function addClosedDays() {
    // 여기에 선택된 요일을 서버로 전송하는 코드를 추가할 수 있습니다.
    // 예를 들어 AJAX 또는 fetch를 사용하여 Spring Boot의 컨트롤러로 전송할 수 있습니다.

    const requestData = {
        businessNo : businessNo,
        selectedDays: selectedDays // 선택된 요일 데이터
        // 다른 필요한 데이터도 추가 가능
    };

    fetch('/roomRegister/closedDays/register', {
        method: 'POST', // POST 요청 설정
        headers: {
            'Content-Type': 'application/json' // JSON 형태로 데이터 전송
        },
        body: JSON.stringify(requestData) // 데이터를 JSON 형태로 변환하여 전송
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text(); // 응답을 텍스트로 반환
            // return response.json(); // 서버로부터의 응답을 JSON 형태로 반환
        })
        .then(data => {
            console.log('서버로부터 받은 데이터:', data);

           if (data === "성공"){
               alert("등록 "+data+"!")
           }
        })
        .catch(error => {
            console.error('에러 발생:', error);
            // 에러 처리
        });


    console.log("DB로 전송: ", selectedDays);
    // 선택된 요일을 초기화할 수도 있습니다.
    selectedDays = [];
    updateSelectedDays(); // 선택된 요일 표시를 초기화합니다.
}






/* 지정 휴무 */
let formCount = 1;

function addHolidayForm() {
    formCount++;

    const newForm = createHolidayForm(formCount);
    document.getElementById('forms').appendChild(newForm);
}

function createHolidayForm(formCount) {
    const newForm = document.createElement('div');
    newForm.className = 'holiday-form';
    newForm.id = `holidayForm${formCount}`;
    newForm.innerHTML = `
        <label for="holidayName${formCount}">휴가 이름:</label>
        <input type="text" id="holidayName${formCount}"><br><br>

        <label for="reason${formCount}">휴가 사유:</label>
        <textarea id="reason${formCount}" rows="4" cols="50"></textarea><br><br>

        <label for="startDate${formCount}">휴가 시작일:</label>
        <input type="date" id="startDate${formCount}"><br><br>

        <label for="endDate${formCount}">휴가 종료일:</label>
        <input type="date" id="endDate${formCount}"><br><br>
        
        <button class="cancelBtn">취소</button>
    `;

    // 취소 버튼에 이벤트 리스너 추가
    newForm.querySelector('.cancelBtn').addEventListener('click', () => {
        removeHolidayForm(formCount);
    });

    return newForm;
}

function removeHolidayForm(id) {
    const formToRemove = document.getElementById(`holidayForm${id}`);
    formToRemove.remove();
}

function addHolidays() {
    const allForms = document.getElementsByClassName('holiday-form');
    const holidayList = [];

    for (let i = 0; i < allForms.length; i++) {
        const holidayName = document.getElementById(`holidayName${i + 1}`).value;
        const reason = document.getElementById(`reason${i + 1}`).value;
        const startDate = document.getElementById(`startDate${i + 1}`).value;
        const endDate = document.getElementById(`endDate${i + 1}`).value;

        holidayList.push({
            holidayName,
            reason,
            startDate,
            endDate,
            businessNo
        });
    }

    // 데이터 전송
    sendDataToServer(holidayList);
}

function sendDataToServer(formData) {

    console.log("보내는 데이터:", formData); // day 변수의 값을 콘솔에 출력합니다.

    fetch('/roomRegister/holidays/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(data => {
            console.log('서버로부터 받은 데이터:', data);

            if (data === "성공"){
                alert("휴무 등록 성공!")
                window.location.href = `/roomRegister/business/businessDetail/${businessNo}`;
            }
        })
        .catch(error => {
            console.error('에러 발생:', error);
        });
}

// 초기화
document.getElementById('addFormBtn').addEventListener('click', addHolidayForm);
document.getElementById('confirmBtn').addEventListener('click', addHolidays);



