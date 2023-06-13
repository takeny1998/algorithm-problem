function dijkstra(start, roads) {
  const minDist = Array.from({ length: N + 1 }, () => Infinity);
  const queue = [{ from: start, curt_dist: 0 }];
  minDist[start] = 0;

  while (queue.length > 0) {
    const { from, curt_dist } = queue.shift();

    for (const { to, dist } of roads[from]) {
      const next_dist = curt_dist + dist;
      if (next_dist > minDist[to]) {
        continue;
      }

      if (next_dist <= minDist[to]) {
        minDist[to] = next_dist;
        queue.push({ from: to, curt_dist: next_dist });
      }
    }
  }
  minDist[0] = 0;
  return [...minDist];
}

const fs = require("fs");
const input = fs.readFileSync("input.txt").toString().trim().split("\n");

const [N, M, X] = input[0]
  .trim()
  .split(" ")
  .map((e) => parseInt(e));

const goRoads = Array.from({ length: N + 1 }, () => []);
const backRoads = Array.from({ length: N + 1 }, () => []);

for (let i = 1; i <= M; i++) {
  let [from, to, dist] = input[i]
    .trim()
    .split(" ")
    .map((e) => parseInt(e));

  goRoads[from].push({ to, dist });
  backRoads[to].push({ to: from, dist });
}
goMinDist = dijkstra(X, goRoads);
backMinDist = dijkstra(X, backRoads);

allDist = goMinDist.map((e1, index) => e1 + backMinDist[index]);
console.log(Math.max.apply(null, allDist));
