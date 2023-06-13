const fs = require("fs");
const input = fs.readFileSync("input.txt").toString().trim().split("\n");
const strA = input[0].trim();
const strB = input[1].trim();

// Array.prototype.fill 은 얕은 복사(shadow copy)로 값을 채운다.
// 배열이나, 객체 등을 중첩으로 사용할 땐 map을 사용하자.
const dp = [...new Array(strB.length + 1)].map(() =>
  new Array(strA.length + 1).fill(0)
);

for (let y = 1; y <= strB.length; y++) {
  for (let x = 1; x <= strA.length; x++) {
    let charA = strA.charAt(x - 1);
    let charB = strB.charAt(y - 1);

    if (charA === charB) {
      dp[y][x] = dp[y - 1][x - 1] + 1;
    } else {
      dp[y][x] = Math.max(dp[y - 1][x], dp[y][x - 1]);
    }
  }
}

console.log(dp.at(-1).at(-1));
