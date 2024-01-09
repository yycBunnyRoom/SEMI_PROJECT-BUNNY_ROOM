
<!--    이메일 관련-->
let isSendingEmail = false;
let emailConfirmed = false;
let userEmail = "";
let isCheckingEmail = false;
let isCheckingNickname = false;
let nicknameChecked = false;

function checkPassword(password) {
    // 정규 표현식을 이용하여 비밀번호 검사
    const pattern = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\W)(?=\S+$).{8,20}$/;
    return pattern.test(password);
}




document.addEventListener('DOMContentLoaded', function () {

    // 동적으로 inputUserEmail의 값을 userEmail에 담는다
    let inputUserEmail = document.getElementById('inputUserEmail');
    inputUserEmail.addEventListener('input', function () {
        userEmail = this.value;
    });


    let passwordMessage1 = document.getElementById('checkPassword1');
    let passwordMessage2 = document.getElementById('checkPassword2');
    let passwordInput1 = document.getElementById("password1");
    let passwordInput2 = document.getElementById("password2");

    passwordInput1.addEventListener('input', function(){
        let inputPassword1 = passwordInput1.value;

        if (!inputPassword1) {
            passwordMessage1.style.display = "none";
            return;
        }

        passwordMessage1.style.display = "block";

        if (!checkPassword(inputPassword1)){
            passwordMessage1.textContent = '비밀번호는 대,소문자,숫자,특수기호가 각각 하나 이상씩 포함되어야 하며, 총 8~20자 사이여야 합니다.';
            passwordMessage1.classList.remove('available');
            passwordMessage1.classList.add('duplicate');
        } else {
            passwordMessage1.textContent = '올바른 비밀번호 형식입니다';
            passwordMessage1.classList.remove('duplicate');
            passwordMessage1.classList.add('available');
        }
    });

    passwordInput2.addEventListener('input', function(){

        let inputPassword2 = passwordInput2.value;

        if (!inputPassword2) {
            passwordMessage2.style.display = "none";
            return;
        }

        passwordMessage2.style.display = "block";

        if (verifyPassword()){
            passwordMessage2.textContent = '비밀번호가 일치합니다';
            passwordMessage2.classList.remove('duplicate');
            passwordMessage2.classList.add('available');
        } else {
            passwordMessage2.textContent = '입력하신 비밀번호가 다릅니다';

            passwordMessage2.classList.remove('available');
            passwordMessage2.classList.add('duplicate');
        }
    });


});



function fn_checkEmail(){
    if (isCheckingEmail){
        alert("이메일 중복확인 중입니다. 잠시만 기달려 주세요.")
    }
    else{
        isCheckingEmail = true;

        fetch('/check/email/' + userEmail)
            .then(function (response) {
                return response.json();
            })
            .then(function (data) {

                if (data.duplicate) {
                    alert("가입된 이메일이 있습니다")

                    document.getElementById('sendAuthNumber').style.display = 'none';
                } else {
                    alert("사용가능한 이메일입니다.")

                    document.getElementById("btn_checkEmail").style.display = 'none';

                    // 이메일 중복확인을 누르면 이메일 인증하기 버튼이 보이게
                    document.getElementById('sendAuthNumber').style.display = 'block';
                }
            })
            .catch(function (error) {
                console.error('Error:', error);
            })
            .finally(()=>isCheckingEmail=false)
    }


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
    alert("인증 이메일을 발송했습니다.")




    $.ajax({
        url:"/mailSend",
        type:"post",
        dataType:"json",
        data: {
            "userEmail" : $("#inputUserEmail").val()
        },
        success: function(data){
            alert("인증번호가 도착했습니다");

            // 인증메일을 보내면 인증 번호 확인 버튼이 보이게
            document.getElementById('sendAuthNumber').style.display = 'none';
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

                document.getElementById('sendAuthNumber').style.display = 'none';
                document.getElementById('authNum').style.display = 'none';
                document.getElementById('confirmAuthNumberBtn').style.display = 'none';

                emailConfirmed =true;

            } else if (data == 0) {
                alert("잘못된 인증번호입니다.")
                document.getElementById('sendAuthNumber').style.display = 'block';
                let sendAuthNumber = document.getElementById('sendAuthNumber');
                sendAuthNumber.textContent = '인증번호 다시 보내기'
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
}








/* 가입하기 */
function registUser() {

    /* 입력된 값에 대한 유효성 검사*/
    if (!emailConfirmed) {
        alert('이메일 인증은 필수 입니다.');
        return;
    }
    else if (!verifyPassword()){
        alert("입력하신 비밀번호가 다릅니다")
        return;
    }
    else if (!nicknameChecked){
        alert('닉네임 중복확인은 필수입니다.');
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

            if(data.result){
                alert(data.message)
                window.location.href = '/main';
                return;
            }
            alert(data.message)
        })
        .catch(error => {
            console.error('Error:', error);
            alert('네트워크 오류가 발생했습니다.');
        });

}


/* 이메일 중복 체크하는 메소드 */

function fn_checkNickname(){
    if (isCheckingNickname){
        alert("닉네임 중복확인 중입니다. 잠시만 기달려 주세요.")
    }
    else{
        isCheckingNickname = true;

        let inputNickname = document.getElementById("userNickname").value;

        fetch('/check/nickname/' + inputNickname)
            .then(function (response) {
                return response.json();
            })
            .then(function (data) {

                if (data.duplicate) {
                    alert("중복된 닉네임이 있습니다")

                } else {
                    alert("사용가능한 닉네임입니다.")

                    document.getElementById("btn_checkNickname").style.display = 'none';
                    nicknameChecked = true;
                }
            })
            .catch(function (error) {
                console.error('Error:', error);
            })
            .finally(()=>isCheckingNickname=false)
    }


}