const roomsContainer = document.getElementById('rooms');

let currentIndex = 0;
const itemsPerPage = 4;

function displayRooms(startIndex, endIndex) {
    const roomsContainer = document.getElementById('rooms');
    for (let i = startIndex; i < endIndex && i < rooms.length; i++) {
        const room = rooms[i];
        const roomDiv = document.createElement('div');
        roomDiv.classList.add('room-item');
        roomDiv.innerHTML = `
            <h2>방 이름: ${room.roomName}</h2>
            <p>방 소개: ${room.roomIntro}</p>
            <p>수용가능 인원: ${room.roomMinPeople} ~ ${room.roomMaxPeople}명</p>
        `;
        roomDiv.dataset.roomNo = room.roomNo;

        // 클릭 이벤트를 새로운 div에 추가
        roomDiv.addEventListener('click', function () {
            goToRoomPage(room.roomNo);
        })
        roomsContainer.appendChild(roomDiv);
    }
}




if (rooms.length === 0){
    console.log(1)
    roomsContainer.innerHTML = '<p>검색된 방이 없습니다.</p>';
}
else if (rooms.length <= itemsPerPage) {
    displayRooms(0, itemsPerPage);

    const allResultsText = document.createElement('p');
    allResultsText.textContent = '모든 결과가 표시되었습니다.';
    roomsContainer.appendChild(allResultsText);
}
else{

    displayRooms(0, itemsPerPage);

    const moreButton = document.createElement('button');
    moreButton.textContent = '더 보기';
    moreButton.classList.add('more-results-button'); // 클래스 추가
    moreButton.addEventListener('click', showMoreRooms);

    roomsContainer.appendChild(moreButton);

}

function goToRoomPage(roomNo) {
    window.location.href = '/search/roomDetail?roomNo='+roomNo;
}



function showMoreRooms() {
    let nextIndex = currentIndex + itemsPerPage;
    if (currentIndex === 0){
        currentIndex += itemsPerPage;
        nextIndex += itemsPerPage;
        displayRooms(currentIndex, nextIndex);
    } else {
        displayRooms(currentIndex, nextIndex);
        currentIndex = nextIndex;
    }

    const moreButton = document.querySelector('.more-results-button');

    // 결과가 모두 표시되었는지 확인 후 텍스트 및 더 보기 버튼 표시
    if (rooms.length <= itemsPerPage) {
        moreButton.style.display = 'none';
        const allResultsText = document.createElement('p');
        allResultsText.textContent = '모든 결과가 표시되었습니다.';
        roomsContainer.appendChild(allResultsText);
    } else if (nextIndex >= rooms.length) {
        moreButton.style.display = 'none';
        const allResultsText = document.createElement('p');
        allResultsText.textContent = '모든 결과가 표시되었습니다.';
        roomsContainer.appendChild(allResultsText);
    } else {
        moreButton.style.display = 'block';
    }
}


