/* 최신 방 보여주기 */
$(document).ready(function()
{


    let trendsSlider = $('.bbb_slider');





    if (latestRooms && trendsSlider.length) {
        latestRooms.forEach(function(room) {
            var newItem = $(
                '<div class="owl-item item_fixed">' +
                /* 이미지를 불러온다*/
                '<img class="bbb_image d-flex flex-column align-items-center justify-content-center img_fixed" id="' + room.roomNo + '" src="" alt="">' +
                '<div class="bbb_content">' +
                '<div class="bbb_category">' + room.categoryName + '</a></div>' +

                '<div class="bbb_info clearfix">' +
                '<div class="bbb_name"><a href="#">' + room.roomName + '</a></div><br>' +
                '<div class="bbb_price">' + room.price + '원/인' + '</div>' +
                '</div>' +

                '<div class="bbb_category"> ' + room.roomIntro + '</a></div>' +
                '</div>' +

                '<ul class="bbb_marks">' +
                '<li class="bbb_mark bbb_new">new</li>' +
                '</ul>' +
                '<div class="bbb_fav"><i class="fas fa-heart"></i></div>' +
                '</div>' +
                '</div>');
            downloadImage(room.roomNo);
            // 해당방 상세 페이지
            newItem.find('.bbb_name a').attr('url', '/search/roomDetail?roomNo=' + room.roomNo);

            newItem.find('.bbb_name a').on('click', function(event) {
                event.preventDefault(); // 기본 이벤트 방지
                let url = $(this).attr('url');
                window.location.href = url; // 해당 URL로 이동
            });

            trendsSlider.append(newItem);
        });
    }




    if($('.bbb_slider').length)
    {
        trendsSlider = $('.bbb_slider');
        trendsSlider.owlCarousel(
            {
                loop:false,
                margin:30,
                nav:false,
                dots:false,
                autoplayHoverPause:true,
                autoplay:false,
                responsive: {
                    0: { items: 1 },
                    575: { items: 2 },
                    991: { items: 3 }
                }
            });

        trendsSlider.on('click', '.bbb_fav', function (ev)
        {
            $(ev.target).toggleClass('active');
        });

        if($('.bbb_prev').length)
        {
            var prev = $('.bbb_prev');
            prev.on('click', function()
            {
                trendsSlider.trigger('prev.owl.carousel');
            });
        }

        if($('.bbb_next').length)
        {
            var next = $('.bbb_next');
            next.on('click', function()
            {
                trendsSlider.trigger('next.owl.carousel');
            });
        }
    }
});

function downloadImage(roomNo) {

    fetch(`/image/download/${roomNo}`, {method: 'GET'}).then(response => {
        if (response.ok) {
            return response.blob();
        }
        throw new Error('이미지 다운로드 실패');
    }).then(imageBlob => {
        console.log(imageBlob)
        const imageUrl = URL.createObjectURL(imageBlob);
        const downloadedImage = document.getElementById(roomNo);
        downloadedImage.src = imageUrl;
        console.log("성공")
    }).catch(error => {
        console.error('이미지 다운로드 실패:', error);
    });

}





// JavaScript 변수 정의
// var myString = userAuth+" "+userNickname+"님";

// 변수 값을 <p> 태그 안에 넣기
// document.getElementById("output").innerHTML = myString;


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
        document.getElementById("btn_goToHost").style.display = 'none';
    }
    else if (userAuth === "HOST"){
        document.getElementById("btn_goToHost").style.display = 'block';
        document.getElementById("btn_goToAdmin").style.display = 'none';
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
    window.location.href ="/roomRegister/hostBusinessView"
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
        button.classList.add('category-buttons')
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