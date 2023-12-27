// 모달 열기 버튼
const modalBtn = document.getElementById('modalBtn');

// 모달
const modal = document.getElementById('myModal');

// 모달 닫기 버튼
const closeBtn = document.getElementsByClassName('close')[0];

// 모달 열기
modalBtn.onclick = function() {
    modal.style.display = 'block';
};

// 모달 닫기
closeBtn.onclick = function() {
    modal.style.display = 'none';
};

// 모달 외부 영역 클릭 시 닫기
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = 'none';
    }
};