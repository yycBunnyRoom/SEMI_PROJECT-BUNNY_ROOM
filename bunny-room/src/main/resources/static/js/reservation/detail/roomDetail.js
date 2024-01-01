console.log(address)
console.log(closedDays)

function createAppliedOptionsButtons() {
    const buttonsContainer = document.getElementById('appliedOptionsButtons');
    appliedOptions.forEach(appliedOption => {
        const button = document.createElement('button');
        button.textContent = appliedOption.appliedOptionDescription;
        button.addEventListener('click', () => toggleOption(appliedOption, button));
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
    function disableClosedDays(date) {
        var day = date.getDay();
        var dayName = getDayName(day);

        // closedDays 배열에서 각 closedDayDTO의 closedDay 속성 값을 확인하여 비교
        var isClosedDay = closedDays.some(function(closedDayDTO) {
            return closedDayDTO.closedDay.includes(dayName);
        });

        if (isClosedDay) {
            return [false, "closedDayStyle", "Closed"]; // 날짜 비활성화, 특정 클래스 부여, 툴팁 메시지
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
        beforeShowDay: disableClosedDays,

        onSelect: function(dateText, inst) {
            // 선택한 날짜를 div에 표시
            $("#selectedDate").text("선택한 날짜: " + dateText);
        }
    });

    // 달력 보이기/숨기기 토글
    $("#showCalendar").click(function() {
        $(".ui-datepicker").toggle();
    });
});

