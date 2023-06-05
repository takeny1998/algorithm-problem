function findPairs (target)  {
    let low = 0, high = N - 1;
    const results = [];

    while(low < high) {
        let sum = blocks[low] + blocks[high];

        if (sum === target) {
            results.push([blocks[low], blocks[high]]);
            low ++;
            high --;
        } else if (sum < target) {
            low ++;
        } else if (sum > target) {
            high --;
        }
    }

    return results;
}

function findMaxAbsDiff(arr) {
    let maxAbsDiff = 0;
    let result = []

    for (let i = 0; i < arr.length; i ++) {
        let absDiff = Math.abs(arr[i][0] - arr[i][1]);
        // 만약 절댓값 차이도 같으면, 사전 순으로 출력하기
        if (absDiff > maxAbsDiff) {
            result = arr[i];
            maxAbsDiff = absDiff;
        }
    }

    return result;
}

function solution() {
    blocks.sort();
    const pairs = findPairs(X);
    
    if (pairs.length === 0) {
        console.log('danger');
    } else {
        const result = findMaxAbsDiff(pairs);
        console.log(`yes ${result[0]} ${result[1]}`);
    }
}


// while 통해 여러개 test case 구현해야함
let fs = require("fs");
const input = fs.readFileSync("input.txt").toString().trim().split("\n");
let inputPtr = 0;

let X, N, blocks;

while (true) {
    if (inputPtr >= input.length) {
        break;
    }
    // 입력받은 X는 cm 단위라서 nm로 변환함
    X = parseInt(input[inputPtr]) * 10000000;
    N = parseInt(input[inputPtr + 1]);
    blocks = [];

    for (let i = 0; i < 4; i ++) {
        blocks.push(parseInt(input[inputPtr + (i + 2)]));
    }

    solution();
    inputPtr += (2 + N);
}