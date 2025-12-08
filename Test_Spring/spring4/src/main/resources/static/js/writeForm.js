"use strict";

document.addEventListener("DOMContentLoaded", () => common.init());

const common = {
    init: () => {
        console.log("회원가입 페이지 로드완료");
        const btn = document.querySelector("#btn");

        btn.addEventListener("click", (e) => {

            if (!common.nameVal()) {
                e.preventDefault();
                return;
            }
            if (!common.pwVal()) {
                e.preventDefault();
                return;
            }
        });
    },

    nameVal: () => {
        let idChk = document.querySelector("#name")
        if(idChk.value.length < 3){
            alert("이름은 3자 이상");
            return false;
        }
        return true;
    },

    pwVal: () => {
        let pwChk = document.querySelector("#password")
        if(pwChk.value.length < 5){
            alert("비밀번호는 5자 이상");
            return false;
        }
        return true;
    }
}