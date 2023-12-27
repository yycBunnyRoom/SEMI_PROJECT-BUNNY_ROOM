document.addEventListener('DOMContentLoaded', getBusinessDetails);

function getBusinessDetails() {
    const businessNo = getBusinessNoFromURL(); // URL에서 비즈니스 번호 가져오기

    fetch(`/businessDetailPage/${businessNo}`) // 비즈니스 상세 정보를 가져오는 API 엔드포인트
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // 받아온 데이터를 화면에 표시 (예시로 div에 표시)
            const businessDetailsDiv = document.getElementById('businessDetails');
            businessDetailsDiv.innerHTML = `<p>${JSON.stringify(data)}</p>`;
        })
        .catch(error => {
            console.error('Error:', error);
            // 에러 처리
        });
}

function getBusinessNoFromURL() {
    const url = window.location.href;
    const parts = url.split('/');
    return parts[parts.length - 1]; // URL에서 마지막 부분인 비즈니스 번호를 가져옴
}