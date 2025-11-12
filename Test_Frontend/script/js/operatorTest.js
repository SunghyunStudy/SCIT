"use strict";   // 문법 검사를 엄격하게 하도록 설정

/*
산술연산자 :  + - * / % **     ++ --
*/
let a = 3;
console.log(`거듭제곱 연산자 : ${a ** 3}`);

/*
비교연산자: >  <   >=   <=   ==(동등)  !=   ===(일치)   !==
*/
let b = 15;
let c = '15';

// 타입이 달라도 값을 비교하여 같으면 true (같다)
(b == c) ? console.log(`같다`) : console.log(`다르다`);

// 타입과 값 둘다 같아야 true (다르다)
(b === c) ? console.log(`같다`) : console.log(`다르다`);

/* 
논리연산자 : &&(and)  ||(or)    !(not)

논리연산자의 단축평가(short circuit)
&& : 둘다 t여야 t
    && 연산의 앞부분의 평가값이 false이면 && 연산의 뒷부분처리를 안함
    t && t ==> t
    t && f ==> f
    f && t ==> f
    f && f ==> f

|| : 둘 중 하나만 t이면 t
    || 연산의 앞부분의 평가값이 true이면 || 연산의 뒷부분처리를 안함
    t || t ==> t
    t || f ==> t
    f || t ==> t
    f || f ==> f
*/

let age = 25;    // 나이가 특정 연령대(20~30)인지 확인하려고 함
let ok1 = age >= 20 && age <= 30; // t
let ok2 = 20 <= age <= 30;        //

// 단축평가 연습 : 식, 표현
// && 연산의 앞부분의 평가값이 false이면 && 연산의 뒷부분처리를 안함
(10 > 5) && console.log("and 단축평가 1 ");   // 뒤 한다!
(10 < 5) && console.log("and 단축평가 2 ");   // 뒤 안한다.!

// || 연산의 앞부분의 평가값이 true이면 || 연산의 뒷부분처리를 안함
(10 > 5) || console.log("or 단축평가 1 ");   // 뒤 안한다!
(10 < 5) || console.log("or 단축평가 2 ");   // 뒤 한다!

// spread 연산자(전개 연산자)
let obj1 = { name: "홍길동" };
let obj2 = { name: "손오공" };
let obj3 = { addr: "부산시 사하구" };

let obj13 = { ...obj1, ...obj3 };  // { name: "홍길동", addr: "부산시 사하구" }
console.log(obj13);
console.log(JSON.stringify(obj13)); // 일반문자열로 변환됨

let obj12 = { ...obj1, ...obj2 };   // 키값이 같으면 뒤의 값만 남는다.
console.log(JSON.stringify(obj12)); // 일반문자열로 변환됨

// 같은 타입으로 할것
let ary1 = [1, 3, 5, 7, 9];
let ary3 = [2, 4, 6, 8];
let ary13 = [...ary1, ...ary3];
console.log(ary13); // 일반문자열로 변환됨


