

// 비동기 요청으로 사업자번호 중복 확인
document.addEventListener('DOMContentLoaded', function () {
    let inputBusinessRegistNo = document.getElementById('businessRegistNo');
    let confirmBusinessRegistNo = document.getElementById('checkBusinessRegistNo');

    inputBusinessRegistNo.addEventListener('input', function () {
        let businessRegistNo = this.value;

        if (!businessRegistNo) {
            // 입력 필드에 값이 없을 때 텍스트를 숨깁니다.
            confirmBusinessRegistNo.style.display = 'none';
            return; // 값이 없으면 여기서 함수 종료
        }

        // 값이 있을 때는 텍스트를 보이도록 처리
        fetch('/roomRegister/business/checkBusinessRegistNo/' + businessRegistNo)
            .then(function (response) {
                return response.json();
            })
            .then(function (data) {
                confirmBusinessRegistNo.style.display = 'block'; // 텍스트를 보이도록 설정

                if (data.duplicate) {
                    confirmBusinessRegistNo.textContent = '중복된 사업자번호입니다.';
                    confirmBusinessRegistNo.classList.remove('available');
                    confirmBusinessRegistNo.classList.add('duplicate');
                } else {
                    confirmBusinessRegistNo.textContent = '사용 가능한 사업자번호입니다.';
                    confirmBusinessRegistNo.classList.remove('duplicate');
                    confirmBusinessRegistNo.classList.add('available');
                }
            })
            .catch(function (error) {
                console.error('Error:', error);
            });
    });
});




<!--    핸드폰 관련-->
// 자동으로 businessPhone 을 update
function updateBusinessPhone() {
    var phone1 = document.getElementById('phone1').value;
    var phone2 = document.getElementById('phone2').value;
    var phone3 = document.getElementById('phone3').value;

    var businessPhone = phone1 + phone2 + phone3;
    document.getElementById('businessPhone').value = businessPhone;
}

// 입력값이 변경될 때마다 updateUserPhone() 함수 호출하여 userPhone 업데이트
document.getElementById('phone1').addEventListener('change', updateBusinessPhone);
document.getElementById('phone2').addEventListener('input', updateBusinessPhone);
document.getElementById('phone3').addEventListener('input', updateBusinessPhone);


/* 우편 코드 */



//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function fn_searchAddress() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('businessZipCode').value = data.zonecode;
            document.getElementById("businessAddressRoad").value = roadAddr;

            document.getElementById('showBusinessZipCode').value = data.zonecode;
            document.getElementById("showBusinessAddressRoad").value = roadAddr;



            //입력된 주소와 우편 번호를 보여준다
            document.getElementById('showBusinessAddressRoad').style.display = 'block';
            document.getElementById('showBusinessZipCode').style.display = 'block';
            document.getElementById('businessAddressDetail').style.display = 'block';

            //보여진 값을 변경 불가능하게 한다
            var confirmedAddressRoad = document.getElementById('showBusinessAddressRoad');
            var confirmedZipCode = document.getElementById('showBusinessZipCode');

            confirmedAddressRoad.disabled = true;
            confirmedZipCode.disabled = true;

        }
    }).open();
}




/* 영업 시간*/

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

// 시작시간의 default를 8시로 설정
document.addEventListener('DOMContentLoaded', function () {

    /* 영업 시간의 default 시간 설정 */
    // startTime 요소 가져오기
    let startTimeSelect = document.getElementById('startTime');

    // startTime 요소의 디폴트 값을 9로 설정
    startTimeSelect.selectedIndex = 8;
    // minRsvTime 요소의 디폴트 값을 1로 설정

});













/* 업체 등록하기 */

function submitBusinessRegisterForm() {

    /* 입력된 값에 대한 유효성 검사*/
    if (!document.getElementById('businessRegistNo').value.trim()) {
        alert('사업자 번호는 필수 입니다.');
        return;
    }
    else if (!document.getElementById('businessName').value.trim()){
        alert('사업체 이름은 필수 입니다.');
        return;
    }
    else if (!document.getElementById('businessCategoryNo').value.trim()){
        alert('카테고리를 선택해주세요.');
        return;
    }
    if (!document.getElementById('businessAddressRoad').value.trim()) {
        alert('주소를 선택해주세요.');
        return;
    }
    if (!document.getElementById('businessPhone').value.trim()) {
        alert('사업체 전화번호는 필수입니다.');
        return;
    }
    if (!document.getElementById('startTime').value.trim()) {
        alert('영업 시작 시간을 선택해주세요.');
        return;
    }
    else if (!document.getElementById('minRsvTime').value.trim()){
        alert('최소 예약 시간을 선택해주세요.');
        return;
    }
    else if (!document.getElementById('endTime').value.trim()){
        alert('영업 종료 시간을 선택해주세요.');
        return;
    }


    const businessRegistNo = document.getElementById('businessRegistNo').value;
    const businessName = document.getElementById('businessName').value;
    const businessCategoryNo = document.getElementById('businessCategoryNo').value;

    const businessAddressRoad = document.getElementById('businessAddressRoad').value;
    const businessAddressDetail = document.getElementById('businessAddressDetail').value;
    const businessZipCode = document.getElementById('businessZipCode').value;
    const businessPhone = document.getElementById('businessPhone').value;

    const startTime = document.getElementById('startTime').value;
    const minRsvTime = document.getElementById('minRsvTime').value;
    const endTime = document.getElementById('endTime').value;





    if (endTime !== null){
        const data = {
            businessRegistNo: businessRegistNo,
            businessName: businessName,
            businessCategoryNo: businessCategoryNo,

            businessAddressRoad: businessAddressRoad,
            businessAddressDetail: businessAddressDetail,
            businessZipCode: businessZipCode,
            businessPhone: businessPhone,

            startTime: startTime,
            minRsvTime: minRsvTime,
            endTime: endTime

        };

        fetch('/roomRegister/business/register', {
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
                if (data === 1) {
                    alert('사업체를 성공적으로 등록하셨습니다.');
                    window.location.href = '/roomRegister/hostMainView';
                } else {
                    alert('사업체를 등록 실패했습니다.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('네트워크 오류가 발생했습니다.');
            });
    }
    else {
        alert("영업시간 입력은 필수 입니다")
    }
}