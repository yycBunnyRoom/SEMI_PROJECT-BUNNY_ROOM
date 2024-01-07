let isRegistering = false;

function createAppliedOptionsButtons() {
    const buttonsContainer = document.getElementById('appliedOptionsButtons');
    appliedOptions.forEach(appliedOption => {
        const button = document.createElement('button');
        button.textContent = appliedOption.appliedOptionDescription;
        button.classList.add('applied-option-button'); // 클래스 추가
        buttonsContainer.appendChild(button);
    });
}


window.onload = () => {
    createAppliedOptionsButtons();
};


//카카오 지도 API

document.addEventListener('DOMContentLoaded', function() {
    // 지도를 표시할 div 요소를 가져옵니다.
    var mapContainer = document.getElementById('map');

    // 지도의 옵션을 설정합니다.
    var mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 초기 중심 좌표
        level: 3 // 지도의 확대 레벨
    };

    // 지도를 생성합니다.
    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 주소로 좌표를 검색하는 Geocoder 객체를 생성합니다.
    var geocoder = new kakao.maps.services.Geocoder();

    // 주소로 좌표를 검색합니다. address는 해당 주소로 설정된 변수입니다.
    geocoder.addressSearch(address, function(result, status) {
        // 정상적으로 주소를 검색하고 결과를 받은 경우
        if (status === kakao.maps.services.Status.OK) {
            // 검색 결과에서 좌표를 가져와서 LatLng 객체를 생성합니다.
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            // 마커를 생성하고 지도에 추가합니다.
            var marker = new kakao.maps.Marker({
                map: map,
                position: coords
            });

            map.setCenter(coords);
        }
    });
});




/* 달력 */

$(document).ready(function() {
    function disableDays(date) {
        var day = date.getDay();
        var dayName = getDayName(day);
        var dateString = $.datepicker.formatDate('yy-mm-dd', date);

        var isClosedDay = closedDays.some(function(closedDayDTO) {
            return closedDayDTO.closedDay.includes(dayName);
        });

        var isHoliday = holidays.some(function(holidayDTO) {
            // startDate와 endDate 사이의 날짜를 disable
            return date >= new Date(holidayDTO.startDate) && date <= new Date(holidayDTO.endDate);
        });

        if (isClosedDay || isHoliday) {
            return [false, "holidayStyle", "Closed or Holiday"];
        } else {
            return [true, ""];
        }
    }

    function getDayName(dayIndex) {
        var days = ["일", "월", "화", "수", "목", "금", "토"];
        return days[dayIndex];
    }

    $("#datepicker").datepicker({
        dateFormat: 'yy-mm-dd',
        beforeShowDay: function(date) {

            /* 오늘 포함 오늘 이전의 날은 다 비활성화 */
            let today = new Date();

            // 오늘 이후의 날짜는 활성화, 이전 날짜는 비활성화
            return date > today ? [true] : [false];
        },
        onSelect: function(dateText, inst) {
            $("#selectedDate").text("선택한 날짜: " + dateText);
            reservationDate = dateText;
            createButtons(timeUnits);
        }
    });

    // $("#showCalendar").click(function() {
    //     $(".ui-datepicker").toggle();
    // });

    // 초기에는 예약 폼을 숨깁니다.
    $('#reservation_form').hide();

    // 예약 버튼 클릭 시 예약 폼을 토글합니다.
    $('#btn_reservation').on('click', function() {
        $('#reservation_form').toggle();
        $(this).hide();
    });



});


function createButtons(timeUnits) {
    // 이전에 남아있는 버튼들 삭제
    $('#btn_timeUnits').empty();

    timeUnits.forEach(function (timeUnit) {
        const isReserved = reserved.some(reservation => {
            const reservationDateObj = new Date(reservation.reservationDate);
            const currentDateObj = new Date(reservationDate);

            return (
                reservation.reservationUnit === timeUnit.startTime + ':00 - ' + timeUnit.endTime + ':00' &&
                reservationDateObj.getTime() === currentDateObj.getTime()
            );
        });

        var button = $('<button></button>').text(timeUnit.startTime + ':00 - ' + timeUnit.endTime + ':00');

        if (isReserved) {
            button.addClass('reserved');
            button.prop('disabled', true); // 예약된 시간은 클릭 불가능하도록 설정
        } else {
            button.click(function () {
                $('#btn_timeUnits button').removeClass('selected');
                $(this).addClass('selected');
                reservationUnit = $(this).text();
            });
        }

        $('#btn_timeUnits').append(button);
    });
}



$(document).ready(function() {
    // roomDetails는 서버에서 전달된 정보로 초기값을 가진다고 가정합니다.

    // pricePerPerson 업데이트 함수
    function updatePricePerPerson() {
        const pricePerPersonElement = document.getElementById('pricePerPerson');
        pricePerPersonElement.textContent = roomDetails.price + '원 / 인';
    }

    // totalPrice 업데이트 함수
    function updateTotalPrice() {
        totalPeople = document.getElementById('reservation_people').value;

        const totalPriceElement = document.getElementById('totalPrice');

        if (!isNaN(totalPeople)) {
            // 총 인원 수 업데이트
            totalPrice = roomDetails.price * totalPeople;
            totalPriceElement.textContent =  totalPrice + '원';
            if (totalPeople == 0){
                totalPriceElement.textContent = '';
            }
        }
        else {
            // 입력값이 없을 때의 처리
            totalPriceElement.textContent = '';
        }

    }

    // 초기 pricePerPerson 설정
    updatePricePerPerson();

    // reservation_people 값 변경 시 totalPrice 업데이트
    $('#reservation_people').on('input', function() {
        updateTotalPrice();
    });
});



let reservationDate = "";
let reservationUnit = "";
let totalPrice = 0;
let totalPeople = 0;

/* 예약을 신청하는 폼*/
function addReservation(){

    if (isRegistering){
        alert("예약 요청을 보내고 있습니다. 잠시만 기달려 주세요~")
    }
    else {
        // 받는 값에 대한 유효성 검사
        if (!reservationDate.trim()){
            alert("예약 날짜를 선택해주세요.")
            return;
        }
        else if (!reservationUnit.trim()){
            alert("예약 시간을 입력해주세요.")
            return;
        }
        else if (totalPeople == 0){
            alert("예약 인원을 입력해주세요.")
            return;
        }
        else if (totalPeople < 0){
            alert("예약 인원은 음수일 수 없습니다.")
            return;
        }
        else if (totalPeople < roomDetails.roomMinPeople){
            alert("총 예약 인원은 최소 인원 보다 적을 수 없습니다")
            return;
        }
        else if (totalPeople > roomDetails.roomMaxPeople){
            alert("총 예약 인원은 최대 인원을 초과할 수 없습니다")
            return;
        }

        isRegistering = true;

        const data = {
            date: reservationDate,
            reservationUnit: reservationUnit,
            totalCost: totalPrice,
            roomNo: roomNo,
            people: Number(totalPeople)
        };

        fetch('/reservation/addReservation', {
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
                return response.json();
            })
            .then(data => {
                if (data === 1){
                    alert('예약 성공!')
                    isRegistering = false;
                    window.location.href = "/main";
                }
            })
            .catch(error => {
                console.error('에러 발생:', error);
            });
    }
}




