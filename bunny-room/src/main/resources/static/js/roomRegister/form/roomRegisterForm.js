let isRegistering = false;
let isSending = false;

console.log(123)


/*          Room Options             */

let appliedOptions = []; // 선택된 값을 저장할 배열

function createButtons() {
    const buttonsContainer = document.getElementById('optionButtons');
    roomOptions.forEach(roomOption => {
        const button = document.createElement('button');
        button.textContent = roomOption.optionName;
        button.addEventListener('click', () => toggleOption(roomOption, button));
        buttonsContainer.appendChild(button);
    });
}

function toggleOption(option, button) {
    const index = appliedOptions.indexOf(option.optionIdx);
    if (index > -1) {
        appliedOptions.splice(index, 1);
        button.classList.remove('selected'); // 선택 해제 시 클래스 제거
    } else {
        appliedOptions.push(option.optionIdx);
        button.classList.add('selected'); // 선택 시 클래스 추가
    }
    console.log('선택된 값:', appliedOptions);
}



window.onload = () => {
    createButtons();
};

function submitRoomRegisterForm() {

    if (isRegistering){
        alert("등록 중입니다. 잠시만 기다려 주세요.")
        return;
    }

    const roomName = document.getElementById('roomName').value;
    const roomIntro = document.getElementById('roomIntro').value;
    const roomDetail = document.getElementById('roomDetail').value;
    const roomMinPeople = document.getElementById('roomMinPeople').value;
    const roomMaxPeople = document.getElementById('roomMaxPeople').value;
    const roomFacilityInfo = document.getElementById('roomFacilityInfo').value;
    const roomNotice = document.getElementById('roomNotice').value;
    const price = document.getElementById('price').value;


    // 유효성 검사
    if (!roomName.trim()){
        alert("방 이름을 입력해주세요.")
        return;
    }
    else if (!roomIntro.trim()){
        alert("방 한줄 소개를 입력해주세요.")
        return;
    }
    else if (!roomDetail.trim()){
        alert("상세 소개를 입력해주세요.")
        return;
    }
    else if (!roomMinPeople){
        alert("최소 수용 인원을 입력 해주세요.")
        return;
    }
    else if (roomMinPeople <= 0){
        alert("최소 수용 인원은 0보다 작거나 같을 수 없습니다.")
        return;
    }
    else if (!roomMaxPeople){
        alert("최대 수용 인원을 입력 해주세요.")
        return;
    }
    else if (roomMaxPeople < roomMinPeople){
        alert("최대 수용 인원은 최소 수용 인원보다 적을 수 없습니다.")
        return;
    }
    else if (!roomFacilityInfo.trim()){
        alert("시설 안내를 입력해주세요.")
        return;
    }
    else if (!roomNotice.trim()){
        alert("유의 사항를 입력해주세요.")
        return;
    }
    else if (!price){
        alert("방 가격을 입력 해주세요.")
        return;
    }
    else if (price <= 0){
        alert("방 가격은 0보다 작거나 같을 수 없습니다.")
        return;
    }

    isRegistering = true;

    const data = {
        businessNo: businessNo,
        roomName: roomName,
        roomIntro: roomIntro,
        roomDetail: roomDetail,
        roomMinPeople: roomMinPeople,
        roomMaxPeople: roomMaxPeople,
        roomFacilityInfo: roomFacilityInfo,
        roomNotice: roomNotice,
        price: price,
        appliedOptions: appliedOptions
    };

    fetch('/roomRegister/room/register', {
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
        console.log('Success:', data);
        if (data === 1) {
            alert('방 등록 성공');
            window.location.href = `/roomRegister/business/businessDetail/${businessNo}`;
        } else {
            alert('방 등록 실패');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('네트워크 오류가 발생했습니다.');
    })
    .finally(()=>{
        isRegistering = false
    }) ;
}




///////////////////////////////////// GPT 코드

function submitRoomRegisterForm() {
    const roomName = document.getElementById('roomName').value;
    const roomIntro = document.getElementById('roomIntro').value;
    const roomDetail = document.getElementById('roomDetail').value;
    const roomMinPeople = document.getElementById('roomMinPeople').value;
    const roomMaxPeople = document.getElementById('roomMaxPeople').value;
    const roomFacilityInfo = document.getElementById('roomFacilityInfo').value;
    const roomNotice = document.getElementById('roomNotice').value;
    const price = document.getElementById('price').value;

    const formData = new FormData();
    const imageFile = document.getElementById('imageIdx').files[0];
    formData.append('roomImage', imageFile);
    formData.append('businessNo', businessNo);
    formData.append('roomName', roomName);
    formData.append('roomIntro', roomIntro);
    formData.append('roomDetail', roomDetail);
    formData.append('roomMinPeople', roomMinPeople);
    formData.append('roomMaxPeople', roomMaxPeople);
    formData.append('roomFacilityInfo', roomFacilityInfo);
    formData.append('roomNotice', roomNotice);
    formData.append('price', price);
    formData.append('appliedOptions', JSON.stringify(appliedOptions));

    fetch('/roomRegister/room/register', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Success:', data);
            if (data === 1) {
                alert('방 등록 성공');
                window.location.href = `/roomRegister/business/businessDetail/${businessNo}`;
            } else {
                alert('방 등록 실패');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('네트워크 오류가 발생했습니다.');
        });
}