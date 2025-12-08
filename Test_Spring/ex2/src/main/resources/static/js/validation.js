
/* enroll.html , updateForm.html */
window.onload = function() {

    const form = document.querySelector('form');
    form.addEventListener('submit', function(event) {
        const name  = document.querySelector("#name" ).value.trim();
    	const major = document.querySelector("#major").value.trim();
    	const java  = document.querySelector("#java" ).value;
    	const db    = document.querySelector("#db"   ).value;
    	const web   = document.querySelector("#web"  ).value;

    	// 이름 3~10자
    	if (name.length < 3 || name.length > 10) {
    	  alert("이름은 3자 이상 10자 이하로 입력해주세요.");
    	  event.preventDefault();       // submit 막기
    	  return;                       // function 종료
    	}

    	// 전공 1글자 이상
    	if (major.length < 1) {
    	  alert("전공을 입력해주세요.");
    	  event.preventDefault();
    	  return;
    	}

    	// 상, 중, 하
    	const levels = ["상", "중", "하"];

    	// java 변수에 담긴 값이 "상", "중", "하" 중 하나인지 확인
    	// 만약 포함되어 있지 않다면 조건이 true
    	if (!levels.includes(java)) {
    		alert("자바 등급을 선택해주세요.");
    		event.preventDefault();
    		return;
    	}

    	if (!levels.includes(db)) {
    		alert("DB 등급을 선택해주세요.");
    		event.preventDefault();
    		return;
    	}

    	if (!levels.includes(web)) {
    		alert("웹 등급을 선택해주세요.");
    		event.preventDefault();
    		return;
    	}
    });
}