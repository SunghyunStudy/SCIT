init();

function init() {

    const oper = document.getElementById("oper");
    const calc = document.getElementById("calc");
    const clear = document.getElementById("clear");

    calc.addEventListener('click', calcProc);
    clear.addEventListener('click', clearProc);

}


// 계산 실시
function calcProc() {
    let val1 = document.getElementById("val1").value;
    let val2 = document.getElementById("val2").value;
    let result = document.getElementById("result");
    let oper = document.getElementById("oper");

    // 숫자만 = 입력을 했는지 안했는지 확인().

    if (isNaN(val1) || val1.trim().length == 0) {
        alert("숫자만 입력하시오!");
        document.getElementById("val1").selected;
        return;
    }

    if (isNaN(val2) || val2.trim().length == 0) {
        alert("숫자만 입력하시오!");
        document.getElementById("val2").selected;
        return;
    }

    let calcData;  // 요기는 undefined가 들어있는거임.
    switch (oper.value) {
        case "+": calcData = parseInt(val1) + parseInt(val2); break;
        case "-": calcData = parseInt(val1) - parseInt(val2); break;
        case "*": calcData = parseInt(val1) * parseInt(val2); break;
        case "/": calcData = parseInt(val1) / parseInt(val2); break;
    }
    result.value = calcData;



}

// 상자 클리어 
function clearProc() {
    document.getElementById("val1").value = "";
    document.getElementById("val2").value = "";
    document.getElementById("result").value = "";

}
