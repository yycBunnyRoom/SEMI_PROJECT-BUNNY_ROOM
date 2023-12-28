
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
    const index = appliedOptions.findIndex(opt => opt.optionIdx === option.optionIdx);
    if (index > -1) {
        appliedOptions.splice(index, 1);
        button.classList.remove('selected'); // 선택 해제 시 클래스 제거
    } else {
        appliedOptions.push(option.optionIdx);
        button.classList.add('selected'); // 선택 시 클래스 추가
    }
    console.log('선택된 값:', appliedOptions);
    updateSelectedOptions();
}

function updateSelectedOptions() {
    const selectedOptionsElement = document.getElementById('selectedOptions');
    selectedOptionsElement.textContent = '선택된 옵션: ';

    appliedOptions.forEach(option => {
        const optionText = document.createElement('span');
        optionText.textContent = option.optionName + ', ';
        optionText.classList.add('selected-option');
        selectedOptionsElement.appendChild(optionText);
    });
}

window.onload = () => {
    createButtons();
    updateSelectedOptions();
};

function submitRoomRegisterForm() {
    const roomName = document.getElementById('roomName').value;
    const roomIntro = document.getElementById('roomIntro').value;
    const roomDetail = document.getElementById('roomDetail').value;

    const roomMinPeople = document.getElementById('roomMinPeople').value;
    const roomMaxPeople = document.getElementById('roomMaxPeople').value;
    const roomFacilityInfo = document.getElementById('roomFacilityInfo').value;
    const roomNotice = document.getElementById('roomNotice').value;
    const price = document.getElementById('price').value;


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
                window.location.href = '/roomRegister/hostMainView';
            } else {
                alert('방 등록 실패');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('네트워크 오류가 발생했습니다.');
        });
}
