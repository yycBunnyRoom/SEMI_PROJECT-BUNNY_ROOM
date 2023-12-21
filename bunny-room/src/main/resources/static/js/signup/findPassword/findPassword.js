
<!--비밀번호 찾는 방법-->
if (method == "userPhone"){
    document.getElementById('phone1').style.display = 'block';
    document.getElementById('phone2').style.display = 'block';
    document.getElementById('phone3').style.display = 'block';
    document.getElementById('userPhone').style.display = 'block';
    document.getElementById('btn_phoneCheck').style.display = 'block';
}
else {
    document.getElementById('label3').style.display = 'block';
    document.getElementById('inputUserEmail').style.display = 'block';
    document.getElementById('emailCheck').style.display = 'block';

}

let isSendingEmail = false;


// 이메일 중복 확인
function fn_emailCheck() {
    $.ajax({
        url : "/signup/emailCheck",
        type : "POST",
        dataType :"JSON",
        data : {"userEmail" : $("#inputUserEmail").val()},
        success : function (data) {
            if(data == 1) {
                alert("가입된 이메일입니다");
                document.getElementById('sendAuthNumber').style.display = 'block';
                document.getElementById('emailCheck').style.display = 'none';

                var confirmedEmail = document.getElementById('inputUserEmail').value;
                var inputUserEmail = document.getElementById('inputUserEmail');

                // 이메일 확인했으면 이메일 변경불가
                inputUserEmail.disabled = true;
                // 인증관련 버튼들 사라져
                document.getElementById('userEmail').value = confirmedEmail;

            } else if (data == 0) {
                alert("가입된 이메일이 없습니다")

                // 이메일 중복확인을 누르면 이메일 인증하기 버튼이 보이게

            }
        }

    })
}
// 인증 이메일 보내기
function fn_sendAuthEmail(){
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
            "userEmail" : $("#userEmail").val()
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
            "userEmail" : $("#userEmail").val(),
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

                document.getElementById('userEmail').style.display = 'block';
                document.getElementById('userPassword').style.display = 'block';
                document.getElementById('userPassword2').style.display = 'block';
                document.getElementById('resetPasswordBtn').style.display = 'block';
                document.getElementById('label').style.display = 'block';
                document.getElementById('label2').style.display = 'block';

            } else if (data == 0) {
                $("#emailCheck").attr("value", "Y");
                alert("잘못된 인증번호입니다.")
            }
        }
    })
}
function fn_phoneCheck() {
    $.ajax({
        url : "/signup/phoneCheck",
        type : "POST",
        dataType :"JSON",
        data : {"userPhone" : $("#userPhone").val()},
        success : function (data) {
            if(data == 1) {
                alert("가입된 전화번호가 존재합니다");

                document.getElementById('userPassword').style.display = 'block';
                document.getElementById('userPassword2').style.display = 'block';
                document.getElementById('resetPasswordBtn').style.display = 'block';
                document.getElementById('label').style.display = 'block';
                document.getElementById('label2').style.display = 'block';

            } else if (data == 0) {
                alert("가입된 전화번호가 없습니다")

            }
        }

    })
}

<!-- 비밀번호 재설정 -->
document.getElementById('resetPassword').addEventListener('submit', function(event) {
    const password = document.getElementById("userPassword").value;
    const confirmPassword = document.getElementById("userPassword2").value;

    if (password !== confirmPassword) {
        alert("입력하신 비밀번호가 다릅니다.");
        event.preventDefault(); // 제출 이벤트 취소
    }
});