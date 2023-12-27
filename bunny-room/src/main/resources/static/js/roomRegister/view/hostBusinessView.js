document.addEventListener('DOMContentLoaded', getBusinessList);

function getBusinessList() {
    fetch('/roomRegister/REST/getAllBusiness') // 백엔드에서 API 엔드포인트 설정 필요
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            if (data.length === 0) {
                document.getElementById('businessList').innerHTML = '<p>등록된 방이 없습니다.</p>';
                document.getElementById('addBusinessButton').style.display = 'block';
            } else {
                data.forEach(business => {
                    const businessDiv = document.createElement('div');
                    document.getElementById('businessList').innerHTML += `<div>${business.businessName} - ${business.businessNo}</div>`;
                    businessDiv.addEventListener('click', function () {
                        goToBusinessPage(business.businessNo);
                    });
                    document.getElementById('businessList').appendChild(businessDiv);
                });
            }
        })
        .catch(error => {
            console.error('Error:', error);
            // 에러 처리
        });
}


function goToBusinessPage(businessNo) {
    window.location.href = `/businessDetailPage/${businessNo}`;
}















function goToAddBusiness() {
    // 추가 버튼 클릭 시, 방 등록 화면으로 이동
    window.location.href = "/roomRegister/roomRegisterForm"; // 이동할 주소로 변경해주세요
}