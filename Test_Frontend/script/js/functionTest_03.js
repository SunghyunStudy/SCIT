init();

function init() {
    const submitBtn = document.querySelector("#submitBtn"); // 이거 시험  
    submitBtn.addEventListener('click', process);
}

// 검증코드가 처리되는 함수
// 폼 데이터는 대부분 value로 읽어올 수 있다.
function process() {
    // 아이디: 3~5글자가 입력되어야 함
    let userid = document.getElementById("userid").value; // document.getElementById("userid") : html 객체임.
    let complete = '<ul><li>';

    if (userid.length <= 2 || userid.length > 5) {
        alert("아이디는 3~5글자로 입력하시오");
        document.getElementById("userid").select();
        return;
    }
    complete += `아이디 : ${userid}</li>`;  // userid + '</li>' 랑 같음


    let userpwd = document.getElementById("userpwd").value; // document.getElementById("userid") : html 객체임.

    if (userpwd.length <= 2 || userpwd.length > 5) {
        alert("비밀번호는 3~5글자로 입력하시오");
        document.getElementById("userpwd").select();
        return;
    }
    complete += `비밀번호 : ${userpwd}</li>`;


    // 성별(라디오)
    let gender = document.querySelector("input[type=radio]:checked").value;
    complete += '<li>' + gender + '</li>';


    // 취미 (체크상자)
    let hobby = document.querySelectorAll("input[type=checkbox]:checked");
    let temp = '';
    hobby.forEach((item) => temp += (item.value) + " ");
    complete += '<li>' + temp + '</li>';


    // 퍼스널 컬러 얻어오기
    let color = document.getElementById("personalcolor").value;
    complete += '<li>' + color + '</li>';


    // 날짜 얻어오기
    let date = document.getElementById("now").value;
    if (!date) {
        alert("날짜를 입력하시오");
        document.getElementById("date").select();
        return;
    }
    date = date.replace("T", " ");
    complete += '<li>' + date + '</li>';


    // 이메일 얻어오기
    let email = document.getElementById("email").value;

    if (!email.includes("@")) {
        alert("이메일 형식으로 입력하시오");
        document.getElementById("email").select();
        return;
    }
    complete += '<li>' + email + '</li>';


    // 진척도 얻어오기
    let range = document.getElementById("range").value;
    complete += '<li>' + range + '</li>';

    // 히든 값 얻어오기 reqPage
    let hidden = document.getElementById("reqPage").value;
    complete += '<li>' + reqPage + '</li>';

    // 지역 얻어오기 // 이거 쿼리 셀렉터 (#address option:checked)
    let address = document.getElementById("address").value;
    complete += '<li>' + address + '</li>';

    // 평가 얻어오기

    // 완성된 값을 #target에 삽입
    document.getElementById("target").innerHTML = complete;
}