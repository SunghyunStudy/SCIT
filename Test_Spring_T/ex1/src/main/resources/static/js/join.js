// join.js
/*
    join.html 유효성 검사 작성.
      1. 아이디 : 3 ~ 14자, 영문/숫자/특수문자 포함 허용.
      2. 비밀번호 : 입력값(비밀번호, 비밀번호 확인)의 일치.
      3. 이름 : 필수 입력(값 존재 여부).
      4. 핸드폰 : 필수 입력(값 존재 여부).
*/

window.onload = function(){
    document.querySelector("form").onsubmit = function(e){
        const id = document.querySelector("#id").value.trim();
        const pw1 = document.querySelector("#pw1").value.trim();
        const pw2 = document.querySelector("#pw2").value.trim();
        const name = document.querySelector("#name").value.trim();
        const phone = document.querySelector("#phone").value.trim();

        // idRegex = 아이디 유효성 검사(3-14자), 영문 / 숫자 / 특수문자 포함 허용
        const idRegex = /^[a-zA-Z0-9!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]{3,14}$/;
        if(!idRegex.test(id)){
            alert("아이디는 3~14자 영문, 숫자, 특수문자를 포함해야 합니다.");
            e.preventDefault();
            return;
        }

        if(!pw1 || !pw2 || pw1 !== pw2){
            alert("비밀번호가 비어 있거나 일치하지 않습니다.");
            e.preventDefault();
            return;
        }

        if(name === ""){
            alert("이름을 입력해주세요.");
            e.preventDefault();
            return;
        }

        if(phone === ""){
            alert("핸드폰 번호를 입력해주세요.");
            e.preventDefault();
            return;
        }
    }


}