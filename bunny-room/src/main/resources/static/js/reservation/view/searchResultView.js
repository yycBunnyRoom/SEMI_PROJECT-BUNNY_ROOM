const roomsContainer = document.getElementById('rooms');
const itemsPerPage = 3;
let currentIndex = 0;

function displayRooms(startIndex, endIndex) {
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
        roomDiv.addEventListener('click', function () {
            goToRoomPage(room.roomNo);
        })
        roomsContainer.appendChild(roomDiv);
    }
}

if (rooms.length === 0) {
    roomsContainer.innerHTML = '<p>검색된 방이 없습니다.</p>';
} else {
    displayRooms(0, itemsPerPage);

    if (rooms.length <= itemsPerPage) {
        const allResultsText = document.createElement('p');
        allResultsText.textContent = '모든 결과가 표시되었습니다.';
        roomsContainer.appendChild(allResultsText);
    } else {
        const moreButton = document.createElement('button');
        moreButton.textContent = '더 보기';
        moreButton.classList.add('more-results-button');
        moreButton.addEventListener('click', showMoreRooms);
        roomsContainer.appendChild(moreButton);
    }
}

function goToRoomPage(roomNo) {
    window.location.href = '/search/roomDetail?roomNo=' + roomNo;
}

function showMoreRooms() {
    const nextIndex = currentIndex + itemsPerPage;
    displayRooms(currentIndex, nextIndex);
    currentIndex = nextIndex;

    const moreButton = document.querySelector('.more-results-button');
    if (nextIndex >= rooms.length) {
        moreButton.style.display = 'none';
        const allResultsText = document.createElement('p');
        allResultsText.textContent = '모든 결과가 표시되었습니다.';
        roomsContainer.appendChild(allResultsText);
    }
}