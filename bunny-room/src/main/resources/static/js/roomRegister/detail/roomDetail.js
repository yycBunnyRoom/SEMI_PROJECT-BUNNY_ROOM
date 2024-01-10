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
    downloadImage(roomNo);
};


function triggerFileInput() {
    const fileInput = document.getElementById('imageIdx');
    if (fileInput) {
        fileInput.click(); // 파일 업로더 실행
    }
}


function handleFileChange(event) {
    const fileInput = document.getElementById('imageIdx');
    // 선택된 파일이 있으면 업로드 로직을 실행할 수 있습니다.
    if (fileInput && fileInput.files.length > 0) {
        // 여기에 파일 업로드 로직을 추가하세요
        const selectedFile = fileInput.files[0];
        console.log('선택된 파일:', selectedFile);
        uploadImage();
    }
}











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