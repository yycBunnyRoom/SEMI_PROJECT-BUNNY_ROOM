
<!--    이메일 관련-->
let isSendingEmail = false;
let emailConfirmed = false;


// 이메일 중복 확인
function fn_emailCheck() {
    $.ajax({
        url : "/signup/emailCheck",
        type : "POST",
        dataType :"JSON",
        data : {"userEmail" : $("#inputUserEmail").val()},
        success : function (data) {
            if(data == 1) {
                alert("중복된 이메일입니다.");
            } else if (data == 0) {
                $("#emailCheck").attr("value", "Y");
                alert("사용 가능한 이메일입니다.")

                // 이메일 중복확인을 누르면 이메일 인증하기 버튼이 보이게
                document.getElementById('sendAuthNumber').style.display = 'block';
            }
        }

    })
}
// 인증 이메일 보내기
function fn_sendAuthEmail(){

    alert("인증 이메일을 발송했습니다.")

    // 이메일 보낸 경우 중복 보내는 거 방지
    if (isSendingEmail) {
        alert("이미 이메일을 보내는 중입니다.");
        return;
    }

    // 보내지 않은 경우 전송 중인 상태로 변경
    isSendingEmail = true;

    $.ajax({
        url:"/mailSend",
        type:"post",
        dataType:"json",
        data: {
            "userEmail" : $("#inputUserEmail").val()
        },
        success: function(data){
            alert("인증번호 발송");

            // 인증메일을 보내면 인증 번호 확인 버튼이 보이게
            document.getElementById('confirmAuthNumberBtn').style.display = 'block';
            document.getElementById('authNum').style.display = 'block';

            // 전송 완료 후 전송 상태 변경
            isSendingEmail = false;
        },
        error: function () {

            // 에러 발생 시 전송 상태 변경
            isSendingEmail = false;
        }
    })
}

// 인증번호 확인
function fn_confirmAuthNumber(){
    $.ajax({
        url:"/mailAuthCheck",
        type:"post",
        dataType:"json",
        data:{
            "userEmail" : $("#inputUserEmail").val(),
            "authNum" : $("#authNum").val()
        },
        success: function(data){
            if(data == 1) {
                alert("인증 되었습니다");

                document.getElementById('emailCheck').style.display = 'none';
                document.getElementById('sendAuthNumber').style.display = 'none';
                document.getElementById('authNum').style.display = 'none';
                document.getElementById('confirmAuthNumberBtn').style.display = 'none';

                var confirmedEmail = document.getElementById('inputUserEmail').value;
                var inputUserEmail = document.getElementById('inputUserEmail');
                // 인증된 이메일 변경 불가능
                inputUserEmail.disabled = true;
                // 인증관련 버튼들 사라져



                document.getElementById('userEmail').value = confirmedEmail;
                emailConfirmed =true;

            } else if (data == 0) {
                $("#emailCheck").attr("value", "Y");
                alert("잘못된 인증번호입니다.")
            }
        }
    })
}
<!--   자바스크립트: 동적으로 입력한 핸드폰 번호를 숨겨진 userPhone에 입력 -->

function updateUserPhone() {
    var phone1 = document.getElementById('phone1').value;
    var phone2 = document.getElementById('phone2').value;
    var phone3 = document.getElementById('phone3').value;

    var userPhone = phone1 + phone2 + phone3;
    document.getElementById('userPhone').value = userPhone;
}

// 입력값이 변경될 때마다 updateUserPhone() 함수 호출하여 userPhone 업데이트
document.getElementById('phone1').addEventListener('change', updateUserPhone);
document.getElementById('phone2').addEventListener('input', updateUserPhone);
document.getElementById('phone3').addEventListener('input', updateUserPhone);

// 유저의 아이디 유지
function updateUserEmail(){

}
document.getElementById('phone2').addEventListener('input', updateUserPhone);



document.getElementById('signupForm').addEventListener('submit', function(event) {
    // updateUserPhone 함수를 호출하여 userPhone 값을 업데이트
    updateUserPhone();

// 이메일 인증이 됐는지 먼저 확인

if (!emailConfirmed) {
    alert("이메일 인증은 필수입니다");
    event.preventDefault(); // 제출 이벤트 취소
}

if (message){
    alert(message);
}

});