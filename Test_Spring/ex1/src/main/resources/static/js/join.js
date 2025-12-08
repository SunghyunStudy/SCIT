// join.js

/*
join.html 유효성 검사 작성.
1. 아이디 : 3 ~ 14자, 영문/숫자/특수문자 포함 허용.
2. 비밀번호 : 입력값(비밀번호, 비밀번호 확인)의 일치.
3. 이름 : 필수 입력(값 존재 여부).
4. 핸드폰 : 필수 입력(값 존재 여부).
*/

"use strict";

document.addEventListener("DOMContentLoaded", () => common.init());

const common = {
    init: () => {
        console.log("회원가입 페이지 로드완료");
        const btn = document.querySelector("#btn");

        btn.addEventListener("click", (e) => {

            if (!common.idVal()) {
                e.preventDefault();
                return;
            }
            if (!common.pwVal()) {
                e.preventDefault();
                return;
            }
            if (!common.nameVal()) {
                e.preventDefault();
                return;
            }
            if (!common.phoneVal()) {
                e.preventDefault();
                return;
            }
        });
    },

    idVal: () => {
        const id = document.querySelector("#id").value.trim();
        const idReg = /^[a-zA-Z0-9!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]{3,14}$/;

        if(id==="" || !idReg.test(id)){
            alert("아이디는 3~14자 영문, 숫자, 특수문자를 포함해야 합니다.");
            return false;
        }
        return true;
    },

    pwVal: () => {
        const pw1 = document.querySelector("#pw1").value.trim();
        const pw2 = document.querySelector("#pw2").value.trim();

        if(pw1 === "" || pw2 === "" || pw1 !== pw2){
            alert("비밀번호가 비어있거나 일치하지 않습니다.");
            return false;
        }
        return true;
    },

    nameVal: () => {
        const name = document.querySelector("#name").value.trim();
        if(name === "") {
            alert("이름을 입력하세요.");
            return false;
        }
        return true;
    },

    phoneVal: () => {
        const phone = document.querySelector("#phone").value.trim();
        if(phone === "") {
            alert("전화번호를 입력하세요.");
            return false;
        }
        return true;
    }
};