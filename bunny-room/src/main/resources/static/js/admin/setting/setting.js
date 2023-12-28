    /* 방 옵션에서 프라이머리키 받아오기 */
    function fn_giveNo(button){
    let valueNo = button.getAttribute('data-option-idx');
    document.getElementById('getNo').value = valueNo;
    console.log(valueNo);
}

    function submitOptionNo() {
    let optionIdx = document.getElementById('getNo').value;
    let newOptionName = document.getElementById('getNewName').value;

    // FormData 객체를 생성해서 그 안에 값을 담기
    let jsonData = {
    optionIdx: optionIdx,
    optionName: newOptionName
}

    // Fetch 사용해서 보내기
    fetch('/admin/setting/changeOptionName',{
    method: 'POST',
    headers: {
    'Content-Type': 'application/json'
},
    body: JSON.stringify(jsonData)
})
    .then(response => {
    if(!response.ok){
    throw new Error("`HTTP error! Status: ${response.status}`");
}
    return response.text();
})
    .then(data => {
    // 서버 응답 처리
    console.log(data);

    // 모달 닫기
    $('#staticBackdrop').modal('hide');

    // 테이블에서 해당 행 찾아서 업데이트
    let tableRow = $('button[data-option-idx="' + optionIdx + '"]').closest('tr');
    tableRow.find('td:first-child').text(newOptionName);
})
    .catch(error => {
    // 에러 처리
    console.error('Fetch error', error);
})
}