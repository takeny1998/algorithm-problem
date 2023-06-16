function dfs(node, dist) {
  visited[node] = true;
  if (dist > maxDist) {
    maxDist = dist;
    farestNode = node;
  }

  tree[node].forEach(({ vertex: nextNode, dist: nextDist }) => {
    if (!visited[nextNode]) {
      dfs(nextNode, dist + nextDist);
    }
  });
}

const fs = require("fs");
const input = fs.readFileSync("input.txt").toString().trim().split("\n");

const N = parseInt(input[0].trim());

const tree = Array.from({ length: N + 1 }, () => []);

for (let i = 0; i < N; i++) {
  const line = input[1 + i]
    .trim()
    .split(" ")
    .map((e) => parseInt(e));

  const curtVertex = line[0];
  for (let r = 1; line[r] != -1; r += 2) {
    const nextVertex = line[r];
    const dist = line[r + 1];

    tree[curtVertex].push({ vertex: nextVertex, dist });
  }
}

let maxDist = 0;
let farestNode = -1;
const visited = new Array(N + 1).fill(false);

for (let i = 1; i <= N; i++) {
  if (tree[i].length > 0) {
    dfs(i, 0);
    break;
  }
}

const a = farestNode;
maxDist = 0;
farestNode = -1;
visited.fill(false);
dfs(a, 0);

console.log(maxDist);
