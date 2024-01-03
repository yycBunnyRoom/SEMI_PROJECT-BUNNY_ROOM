
<!--    이메일 관련-->
let isSendingEmail = false;
let emailConfirmed = false;
let userEmail = "";

let checkEmail = document.getElementById('checkEmail');

// 비동기 이메일 확인
document.addEventListener('DOMContentLoaded', function () {
    let inputUserEmail = document.getElementById('inputUserEmail');


    inputUserEmail.addEventListener('input', function () {
        userEmail = this.value;

        if (!userEmail) {
            // 입력 필드에 값이 없을 때 텍스트를 숨깁니다.
            checkEmail.style.display = 'none';
            document.getElementById('sendAuthNumber').style.display = 'none';
            return; // 값이 없으면 여기서 함수 종료
        }

        // 값이 있을 때는 텍스트를 보이도록 처리
        fetch('/check/email/' + userEmail)
            .then(function (response) {
                return response.json();
            })
            .then(function (data) {
                checkEmail.style.display = 'block'; // 텍스트를 보이도록 설정

                if (data.duplicate) {
                    checkEmail.textContent = '가입된 이메일이 있습니다.';
                    checkEmail.classList.remove('available');
                    checkEmail.classList.add('duplicate');

                    document.getElementById('sendAuthNumber').style.display = 'none';
                } else {
                    checkEmail.textContent = '사용가능한 이메일입니다.';
                    checkEmail.classList.remove('duplicate');
                    checkEmail.classList.add('available');

                    // 이메일 중복확인을 누르면 이메일 인증하기 버튼이 보이게
                    document.getElementById('sendAuthNumber').style.display = 'block';
                }
            })
            .catch(function (error) {
                console.error('Error:', error);
            });
    });
});





// 인증 이메일 보내기
function fn_sendAuthEmail(){

    alert("인증 이메일을 발송했습니다.")

    document.getElementById('inputUserEmail').style.display = 'none';
    checkEmail.textContent = userEmail;
    checkEmail.classList.remove('duplicate');
    checkEmail.classList.remove('available');

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
            let sendAuthNumber = document.getElementById('sendAuthNumber');
            sendAuthNumber.textContent = '인증번호 다시 보내기'

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

                document.getElementById('sendAuthNumber').style.display = 'none';
                document.getElementById('authNum').style.display = 'none';
                document.getElementById('confirmAuthNumberBtn').style.display = 'none';

                emailConfirmed =true;

            } else if (data == 0) {
                alert("잘못된 인증번호입니다.")
            }
        }
    })
}



<!--핸드폰-->
let userPhone = "";

function updateUserPhone() {
    var phone1 = document.getElementById('phone1').value;
    var phone2 = document.getElementById('phone2').value;
    var phone3 = document.getElementById('phone3').value;

    userPhone = phone1 + phone2 + phone3;
}

// 입력값이 변경될 때마다 updateUserPhone() 함수 호출하여 userPhone 업데이트
document.getElementById('phone1').addEventListener('change', updateUserPhone);
document.getElementById('phone2').addEventListener('input', updateUserPhone);
document.getElementById('phone3').addEventListener('input', updateUserPhone);



<!--비밀번호 확인하는 코드-->
let userPassword;

// 비밀번호가 맞다면 true, 틀리면 false를 반환
function verifyPassword() {
    let password1 = document.getElementById("password1").value;
    let password2 = document.getElementById("password2").value;

    if (password1 === password2){
        userPassword = password2;
        return true;
    }
    else {
        alert("입력하신 비밀번호가 다릅니다")
    }
}








/* 가입하기 */
function registUser() {

    /* 입력된 값에 대한 유효성 검사*/
    if (!emailConfirmed) {
        alert('이메일 인증은 필수 입니다.');
        return;
    }
    else if (!verifyPassword()){
        return;
    }
    else if (!document.getElementById('userNickname').value.trim()){
        alert('닉네임은 필수 입니다.');
        return;
    }
    else if (!userPhone) {
        alert('전화번호를 입력해주세요');
        return;
    }

    const userNickname = document.getElementById('userNickname').value;


    const data = {
        userEmail: userEmail,
        userPassword: userPassword,
        userNickname: userNickname,
        userPhone: userPhone,
        userAuth: userAuth
    };

    console.log(data)

    fetch('/check/register', {
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
            console.log('Received data:', data);

            alert(data.message)

            if(data.result){
                window.location.href = '/security/auth/login';
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('네트워크 오류가 발생했습니다.');
        });

}