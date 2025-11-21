
vdyx = [(-1, 0), (0, 1), (1, 0), (0, -1)]
wdyx = [(-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1)]

def solution():
    def combinations(arr, r):
        for i in range(len(arr)):
            if r == 1:
                yield [arr[i]]
            else:
                for next in combinations(arr[i+1:], r - 1):
                    yield [arr[i]] + next


    def spread():
        queue = viruses[:]
        visited = [[0 for _ in range(M)] for _ in range(N)]
        infest = 0

        while queue:
            y, x = queue.pop(0)
            if infest <= min_infest:
                for d in range(4):
                    dy, dx = vdyx[d]
                    ny, nx = y + dy, x + dx
                    if (0 <= ny < N) and (0 <= nx < M):
                        if visited[ny][nx] == 0 and field[ny][nx] == 0:
                            visited[ny][nx] = 1
                            infest += 1
                            queue.append((ny, nx))

        return infest


    min_infest = 999999999
    for comb in combinations(blank, 3):
        for wy, wx in comb:
            field[wy][wx] = 1
        min_infest = min(min_infest, spread())
        for wy, wx in comb:
            field[wy][wx] = 0

    print(len(blank) - 3 - min_infest)



field = []
viruses = []
blank = []

N, M = map(int, input().split())
for _ in range(N):
    field.append(list(map(int, input().split())))

for y in range(N):
    for x in range(M):
        if field[y][x] == 2:
            viruses.append((y, x))
        elif field[y][x] == 0:
            blank += [(y, x)]
solution()