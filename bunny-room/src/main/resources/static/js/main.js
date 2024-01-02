

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
   ;

    if (userAuth === "ADMIN"){
        // document.getElementById("btn_adminPage").style.display = 'block';
        document.getElementById("btn_goToAdmin").style.display = 'block';
        document.getElementById("btn_goToHost").style.display = 'block';
    }
    else if (userAuth === "HOST"){
        document.getElementById("btn_goToHost").style.display = 'block';
    }
}

// function goToAdmin() {
//     // 추가 버튼 클릭 시, 방 등록 화면으로 이동
//     window.location.href = "/admin/adminPage"; // 이동할 주소로 변경해주세요
// }

document.getElementById('btn_goToAdmin').addEventListener('click', function() {
    window.location.href = "/admin/adminPage"
});

document.getElementById('btn_goToHost').addEventListener('click', function() {
    window.location.href ="/roomRegister/hostMainView"
});

document.getElementById('btn_login').addEventListener('click', function() {
    window.location.href = "/security/auth/login"
});


function createCategoryButtons() {
    const buttonsContainer = document.getElementById('categoryButtons');
    categories.forEach(category => {
        const button = document.createElement('button');
        button.textContent = category.businessCategoryName;
        button.style.backgroundColor = category.businessCategoryColorCode;
        button.addEventListener('click', function () {
            window.location.href = '/search/category?businessCategoryName='+category.businessCategoryName;
        })
        buttonsContainer.appendChild(button);
    });
}

window.onload = () => {
    createCategoryButtons();
};


// URL에서 메시지 파라미터를 읽어옴
if (message && typeof message === 'string' && message !== "") {
    alert(decodeURIComponent(message)); // URL 디코딩하여 메시지 출력
    message="";
}