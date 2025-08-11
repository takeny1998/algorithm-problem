function strToIntArr(string) {
  return string
    .trim()
    .split(" ")
    .map((e) => parseInt(e));
}

function floydWarshall() {
  for (let wayPoint = 1; wayPoint <= N; wayPoint++) {
    for (let start = 1; start <= N; start++) {
      if (start === wayPoint) continue;
      for (let end = 1; end <= N; end++) {
        if (start === end || end === wayPoint) continue;

        const nextDist = distMap[start][wayPoint] + distMap[wayPoint][end];
        distMap[start][end] = Math.min(distMap[start][end], nextDist);
      }
    }
  }
}

const fs = require("fs");
const input = fs.readFileSync("input.txt").toString().trim().split("\n");

const [N, M, R] = strToIntArr(input[0]);

const distMap = Array.from({ length: N + 1 }, () =>
  new Array(N + 1).fill(Infinity)
);
const items = [null, ...strToIntArr(input[1])];

for (let n = 1; n <= N; n++) {
  distMap[n][n] = 0;
}

for (let i = 0; i < R; i++) {
  const [a, b, dist] = strToIntArr(input[2 + i]);
  distMap[a][b] = dist;
  distMap[b][a] = dist;
}

floydWarshall();

let maxGetNumOfItem = 0;

for (let n = 1; n <= N; n++) {
  let canGetNumOfItem = 0;

  for (let i = 1; i <= N; i++) {
    if (distMap[n][i] <= M) {
      canGetNumOfItem += items[i];
    }
  }
  maxGetNumOfItem = Math.max(maxGetNumOfItem, canGetNumOfItem);
}

console.log(maxGetNumOfItem);
