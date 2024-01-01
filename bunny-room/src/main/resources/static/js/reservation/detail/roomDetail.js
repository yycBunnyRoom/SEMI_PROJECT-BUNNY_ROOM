

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

            // 인포윈도우를 사용하지 않으므로 해당 부분은 제거합니다.
            // var infowindow = new kakao.maps.InfoWindow({
            //     content: '<div style="width:150px;text-align:center;padding:6px 0;"></div>'
            // });

            // 인포윈도우를 지도에 표시하는 대신 마커의 클릭 이벤트를 사용하여 원하는 동작을 추가할 수 있습니다.
            // marker.on('click', function() {
            //     // 마커를 클릭했을 때 원하는 동작을 구현합니다.
            // });

            // 검색한 좌표를 지도의 중심으로 설정합니다.
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
        beforeShowDay: disableDays,
        onSelect: function(dateText, inst) {
            $("#selectedDate").text("선택한 날짜: " + dateText);
            reservationDate = dateText;
            createButtons(timeUnits);
        }
    });

    $("#showCalendar").click(function() {
        $(".ui-datepicker").toggle();
    });


    // function createButtons(timeUnits) {
    //     // 이전에 남아있는 버튼들 삭제
    //     $('#btn_timeUnits').empty();
    //
    //     timeUnits.forEach(function(timeUnit) {
    //         var button = $('<button></button>').text(timeUnit.startTime + ':00 - ' + timeUnit.endTime + ':00');
    //
    //         // 클릭 이벤트 추가
    //         button.click(function() {
    //             // 선택된 버튼 스타일 변경
    //             $('#btn_timeUnits button').removeClass('selected');
    //             $(this).addClass('selected');
    //             reservationUnit = $(this).text();
    //         });
    //
    //         $('#btn_timeUnits').append(button);
    //     });
    // }
});

// 예약된 정보를 사용하여 중복 확인 및 버튼 생성
function createButtons(timeUnits) {
    // 이전에 남아있는 버튼들 삭제
    $('#btn_timeUnits').empty();

    timeUnits.forEach(function (timeUnit) {
        const isReserved = reserved.some(reservation => {
            /* reservation.reservationDate 가 ZoneDateTime 이므로 Date 형식으로 변환 */
            const reservationDateObj = new Date(reservation.reservationDate);
            const currentDateObj = new Date(reservationDate);

            console.log("1번",reservationDateObj)
            console.log("2번",currentDateObj)
            return (
                reservation.reservationUnit === timeUnit.startTime + ':00 - ' + timeUnit.endTime + ':00' &&
                reservationDateObj.getTime() === currentDateObj.getTime()
            );
        });

        if (!isReserved) {
            var button = $('<button></button>').text(timeUnit.startTime + ':00 - ' + timeUnit.endTime + ':00');

            button.click(function () {
                $('#btn_timeUnits button').removeClass('selected');
                $(this).addClass('selected');
                reservationUnit = $(this).text();
            });

            $('#btn_timeUnits').append(button);
        }
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
        const reservationPeopleInput = document.getElementById('reservation_people');
        const totalPriceElement = document.getElementById('totalPrice');
        const reservationPeopleValue = parseInt(reservationPeopleInput.value);

        // 총 인원 수 업데이트

        totalPeople = document.getElementById('reservation_people').value;


        if (!isNaN(reservationPeopleValue)) {
            totalPrice = roomDetails.price * reservationPeopleValue;
            totalPriceElement.textContent =  totalPrice + '원';
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








/* 예약을 신청하는 폼*/

let reservationDate = null;
let reservationUnit = null;
let totalPrice = null;
let totalPeople = null;


function addReservation(){

    const data = {
        date: reservationDate,
        reservationUnit: reservationUnit,
        totalCost: totalPrice,
        roomNo: roomNo,
        people: Number(totalPeople)
    };

    console.log("good1")

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
            }
        })
        .catch(error => {
            console.error('에러 발생:', error);
        });
}




