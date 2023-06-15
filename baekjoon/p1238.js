class PriorityQueue {
  constructor() {
    this.heap = [null];
  }

  getLeftChildIdx = (index) => index * 2;
  getRightChildIdx = (index) => index * 2 + 1;
  getParentIdx = (index) => Math.floor(index / 2);
  getLastIdx = () => this.heap.length - 1;

  isEmpty = () => this.heap.length === 1;

  isLeftHigher(left, right) {
    return left.value < right.value;
  }

  enqueue(key, value) {
    this.heap.push({ key, value });

    let idx = this.getLastIdx();

    while (idx > 1) {
      let parentIdx = this.getParentIdx(idx);

      const node = this.heap[idx];
      const parentNode = this.heap[parentIdx];

      if (this.isLeftHigher(node, parentNode)) {
        this.heap[idx] = parentNode;
        this.heap[parentIdx] = node;
        idx = parentIdx;
      } else break;
    }
  }

  dequeue() {
    if (this.isEmpty()) return null;

    const rootNode = this.heap[1];

    this.heap[1] = this.heap[this.getLastIdx()];
    this.heap.pop();

    let idx = 1;

    while (this.getLeftChildIdx(idx) < this.getLastIdx()) {
      const node = this.heap[idx];
      const leftIdx = this.getLeftChildIdx(idx);
      const rightIdx = this.getRightChildIdx(idx);

      let childIdx = leftIdx;
      let childNode = this.heap[leftIdx];

      // 오른쪽 노드가 있는 경우
      if (rightIdx <= this.getLastIdx()) {
        // 오른쪽 노드가 우선순위 더 높으면, 갱신한다.
        const rightNode = this.heap[rightIdx];
        if (this.isLeftHigher(rightNode, childNode)) {
          childNode = rightNode;
          childIdx = rightIdx;
        }
      }

      // 부모 노드와 비교한다.
      if (this.isLeftHigher(childNode, node)) {
        this.heap[childIdx] = node;
        this.heap[idx] = childNode;
        idx = childIdx;
      } else break;
    }

    return rootNode;
  }
}

function dijkstra(start, roads) {
  const minDist = Array.from({ length: N + 1 }, () => Infinity);
  const queue = new PriorityQueue();
  queue.enqueue(start, 0);
  minDist[start] = 0;

  while (!queue.isEmpty()) {
    const { key: from, value: curt_dist } = queue.dequeue();

    for (const { to, dist } of roads[from]) {
      const next_dist = curt_dist + dist;
      if (next_dist > minDist[to]) {
        continue;
      }

      if (next_dist <= minDist[to]) {
        minDist[to] = next_dist;
        queue.enqueue(to, next_dist);
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
