
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
        appliedOptions.push(option);
        button.classList.add('selected'); // 선택 시 클래스 추가
    }
    console.log('선택된 값:', appliedOptions);
    updateSelectedOptions();
}

function saveValues() {
    console.log('DB로 전송할 값:', appliedOptions); // DB로 전송할 값 콘솔에 출력 (확인용)
    // 여기에 DB로 값을 전송하는 코드 작성
    // 예시: fetch('/your-endpoint', {
    //         method: 'POST',
    //         headers: {
    //             'Content-Type': 'application/json'
    //         },
    //         body: JSON.stringify(appliedOptions)
    //     })
    //     .then(response => {
    //         // 처리 결과에 따른 로직 작성
    //     })
    //     .catch(error => {
    //         console.error('에러 발생:', error);
    //     });
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

