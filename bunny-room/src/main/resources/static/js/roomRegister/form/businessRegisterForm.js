function submitBusinessRegisterForm() {
    const businessRegistNo = document.getElementById('businessRegistNo').value;
    const businessName = document.getElementById('businessName').value;
    const businessCategoryNo = document.getElementById('businessCategoryNo').value;

    const businessAddressRoad = document.getElementById('businessAddressRoad').value;
    const businessAddressDetail = document.getElementById('businessAddressDetail').value;
    const businessZipCode = document.getElementById('businessZipCode').value;
    const businessPhone = document.getElementById('businessPhone').value;


    const data = {
        businessRegistNo: businessRegistNo,
        businessName: businessName,
        businessCategoryNo: businessCategoryNo,

        businessAddressRoad: businessAddressRoad,
        businessAddressDetail: businessAddressDetail,
        businessZipCode: businessZipCode,
        businessPhone: businessPhone
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



            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            // if(roadAddr !== ''){
            //     document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            // } else {
            //     document.getElementById("sample4_extraAddress").value = '';
            // }

            // var guideTextBox = document.getElementById("guide");
            // // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            // if(data.autoRoadAddress) {
            //     var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
            //     guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
            //     guideTextBox.style.display = 'block';
            //
            // } else if(data.autoJibunAddress) {
            //     var expJibunAddr = data.autoJibunAddress;
            //     guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
            //     guideTextBox.style.display = 'block';
            // } else {
            //     guideTextBox.innerHTML = '';
            //     guideTextBox.style.display = 'none';
            // }
        }
    }).open();
}