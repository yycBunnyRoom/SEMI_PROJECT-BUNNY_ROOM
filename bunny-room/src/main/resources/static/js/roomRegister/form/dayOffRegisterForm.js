
/* weekDays 버튼을 생성하는 */
console.log(closedDays)
function createWeekDaysButtons() {
    const buttonsContainer = document.getElementById('weekDaysButtons');
    weekDays.forEach(weekday => {
        const button = document.createElement('button');
        button.textContent = weekday;
        button.id = weekday; // 각 버튼에 요일에 해당하는 ID 부여

        // closedDays에 해당 요일이 있는 경우 버튼을 비활성화
        if (closedDays.some(closedDay => closedDay.closedDay === weekday)) {
            button.disabled = true;
            button.classList.add('closed-day');
        } else {
            button.addEventListener('click', () => selectDay(weekday)); // 클릭 이벤트에 selectDay 함수 추가
        }

        buttonsContainer.appendChild(button);
    });
}

window.onload = () => {
    createWeekDaysButtons();
    updateSelectedDays(); // 선택된 요일 표시를 초기화 시킴
    if (closedDays.length > 0) {
        closedDays.forEach(closedDay => {
            selectDay(closedDay.closedDay);
        })
    }
    else {
    }
}

// 선택한 요일을 담을 변수를 선언
let selectedDays = [];


if (closedDays.length > 0) {
    // 배열 안에 값이 존재합니다.

} else {
    // 배열이 비어있습니다.

    function addClosedDays() {
        const requestData = {
            businessNo: businessNo,
            selectedDays: selectedDays
        };

        sendRequestToServer('/roomRegister/closedDays/register', requestData);
    }

    function sendRequestToServer(url, data) {
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text();
            })
            .then(data => {
                console.log('서버로부터 받은 데이터:', data);
                if (data === "성공") {
                    alert("등록 " + data + "!")
                }
            })
            .catch(error => {
                console.error('에러 발생:', error);
            })
            .finally(() => {
                // 요청 후에 선택된 요일 초기화
                selectedDays = [];
                updateSelectedDays(); // 선택된 요일 표시를 초기화합니다.
            });
    }

    document.getElementById('btn_addClosedDays').style.display = 'block';

}


function selectDay(day) {
    // 선택한 날(day) 의 값을 콘솔에 출력 - 확인용
    console.log("선택된 요일:", day);

    const btn = document.getElementById(day);
    if (!selectedDays.includes(day)) {
        selectedDays.push(day);
        btn.classList.add("selected");
    } else {
        selectedDays = selectedDays.filter(item => item !== day);
        btn.classList.remove("selected");
    }
    updateSelectedDays();
}

function updateSelectedDays() {
    document.getElementById("selectedDays").innerText = "선택된 요일: " + selectedDays.join(", ");
    console.log(selectedDays)
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



