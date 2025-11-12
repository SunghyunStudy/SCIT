// 1) 반복문 (while, do~while, for, for in, for of)
let ary = [1, 3, 5, 7, 9];

// original for
for (let i = 0; i < ary.length; ++i)
    console.log(ary[i]);

// for~in (데이터의 index를 추출) : in 오른쪽에 위치한 배열의 인덱스 추출
for (let i in ary)
    console.log(ary[i]);

// for~of (데이터의 값을 추출) : ofn 오른쪽에 위치한 배열의 값 추출
for (let i of ary)
    console.log(i);

// [연습 1] 0~ 100 사이에 난수를 10개 발생한다. 
// 발생한 숫자를 배열에 수집
// 배열명.push(값);
let exam = [];
for (let i = 0; i < 10; ++i)
    exam.push(Math.floor(Math.random() * 100));

console.log(`1) 난수 10개 : ${exam} `); // 0.875678 => 87.5678 ==>

// [연습 2] exam 배열의 데이터를 정렬하여 출력.
// 결과를 확인한 후 잘나오나 확인!
// 배열명.sort() : 이 함수의 문제는?
exam.sort();
console.log(`1) 배열 정렬 : ${exam} `);

// [연습 3] exam 배열을 비운다.
// 난수를 발생한다.
// 마지막에 푸쉬된 데이터보다 커야만 push할 수 있다.
exam = [];
for (let i = 0; i < 10; ++i) {
    let temp = Math.random() * 100;
    if (exam.length < 1) {
        exam.push(temp);
        continue;
    }
    let a = exam.pop();
    exam.push(a);
    if (a <= temp) {
        exam.push(temp);
    }
    else {
        --i;
        continue;
    }
}

console.log('완료: ')
console.log(exam);




// 2) 제어문 (if, switch, break, continue)

