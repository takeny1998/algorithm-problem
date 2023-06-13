function solution(inputArr, bombStr) {
    const bombStrLen = bombStr.length;
    let stack = []

    for (let index = 0; index < inputArr.length; index ++) {
        stack.push(inputArr[index]);
        
        // 첫 글자가 같은 경우
        if (inputArr[index] === bombStr[bombStrLen - 1] && stack.length >= bombStrLen) {
            let lastStr = stack.slice(-bombStrLen).join("");
            if (lastStr === bombStr) {
                stack.splice(-bombStrLen);
            }
        }
    }

    if (stack.length === 0) {
        console.log('FRULA');
    } else {
        console.log(stack.join(""));
    }
}

let fs = require("fs");
const input = fs.readFileSync("input.txt").toString().trim().split("\n");

const inputArr = input[0].trim();
const bombStr = input[1].trim();
solution(inputArr, bombStr);