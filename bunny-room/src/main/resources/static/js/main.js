// JavaScript 변수 정의
var myString = userAuth+" "+userNickname+"님";

// 변수 값을 <p> 태그 안에 넣기
document.getElementById("output").innerHTML = myString;


<!--    로그인 버튼 관련 JS-->
if (userAuth == null){
    document.getElementById("btn_login").style.display = 'block';
    document.getElementById("btn_logout").style.display = 'none';
}
if (userAuth)
{
    document.getElementById("btn_login").style.display = 'none';

    if (userAuth == "ADMIN"){
        document.getElementById("btn_adminPage").style.display = 'block';
        document.getElementById("btn_hostMainView").style.display = 'block';
    }
    else if (userAuth == "HOST"){
        document.getElementById("btn_hostMainView").style.display = 'block';
    }
}