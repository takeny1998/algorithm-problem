function dfs(index) {
    visited[index] = true;
    const nextIndex = wants[index];
    
    if (!visited[nextIndex]) {
        dfs(nextIndex);
    } else if (!done[nextIndex]) {
        count ++;
        for (let ni = nextIndex; ni !== index; ni = wants[ni]) {
            count ++;
        }
    }
    done[index] = true;
}

const fs = require('fs');
const [T, ... lines] = fs.readFileSync("input.txt").toString().trim().split("\n");
let wants, visited, done, count, numOfStudent;

for (let t = 0; t < (T * 2); t += 2) {
    numOfStudent = parseInt(lines[t].trim());
    wants = [0, ...lines[t + 1].trim().split(" ").map((el) => +el)];

    visited = Array(numOfStudent + 1).fill(false);
    done = Array(numOfStudent + 1).fill(false);
    count = 0;

    for (let i = 1; i <= numOfStudent; i ++) {
        if (!done[i]) {
            dfs(i);
        }
    }


    console.log(numOfStudent - count);
}