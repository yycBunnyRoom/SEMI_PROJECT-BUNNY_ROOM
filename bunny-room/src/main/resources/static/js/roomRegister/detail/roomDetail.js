function createAppliedOptionsButtons() {
    const buttonsContainer = document.getElementById('appliedOptionsButtons');
    appliedOptions.forEach(appliedOption => {
        const button = document.createElement('button');
        button.textContent = appliedOption.appliedOptionDescription;
        button.addEventListener('click', () => toggleOption(appliedOption, button));
        buttonsContainer.appendChild(button);
    });
}

window.onload = () => {
    createAppliedOptionsButtons();
};



let isSending = false;
function uploadImage() {

    if (!isSending) {

        isSending = true;

        const fileInput = document.getElementById('imageIdx');
        if (fileInput.files.length === 1) {
            // 이미지가 한 개만 선택된 경우
            const file = fileInput.files[0];
            const formData = new FormData();

            formData.append('file', file);
            formData.append('roomNo', roomNo);


            fetch('/image/upload', {method: 'POST', body: formData}).then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error('이미지 업로드 실패');
            }).then(imageNo => {
                if (parseInt(imageNo) > 0){
                    alert("이미지 등록 성공")
                }
            }).catch(error => {
                console.error('이미지 업로드 실패:', error);
            }).finally(() => {
                isSending = false
            });

        } else {
            alert("대표 이미지는 하나만 선택해주세요.");
        }

    } else {
        alert("이미지를 등록하고 있습니다.")
    }
}