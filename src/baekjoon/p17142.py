

dyx = [(-1, 0), (0, 1), (1, 0), (0, -1)]

def bfs(field, active_virus):
    global answer

    infest = 0
    queue = [(y, x, 0) for y, x in active_virus]
    visited = [[0 for _ in range(N)] for _ in range(N)]

    # for vy, vx in virus_list:
    #     if (vy, vx, 0) not in active_virus:
    #         field[vy][vx] = '*'
    
    # for y, x, _ in queue:
    #     field[y][x] = 0
    max_sec = 0

    while queue:
        y, x, sec = queue.pop(0)

        if max_sec <= answer:

            # 현재 위치에 바이러스 감염
            if field[y][x] == 'b':
                infest += 1
                max_sec = max(sec, max_sec)
                # field[y][x] = sec

            for d in range(4):
                dy, dx = dyx[d]
                ny, nx = y + dy, x + dx
                
                if (0 <= ny < N) and (0 <= nx < N):
                    if visited[ny][nx] == 0:
                        if field[ny][nx] != '-':
                            visited[ny][nx] = 1
                            queue.append((ny, nx, sec + 1))

    # for line in field:
    #     for elm in line:
    #         print('%2s' % elm, end= ' ')
    #     print()
    # print()
    if infest == blank_num:
        # max_sec = 0
        # for line in field:
        #     for elm in line:
        #         if str(elm).isdigit():
        #             max_sec = max(max_sec, elm)
        return max_sec
    return -1


def combinations(arr, r):
    for i in range(len(arr)):
        if r == 1:
            yield [arr[i]]
        else:
            for next in combinations(arr[i+1:], r - 1):
                yield [arr[i]] + next


def solution():
    global answer
    answer = 9999999

    for comb in combinations(virus_list, M):
        sec = bfs([line[:] for line in field], comb)
        # print(sec, comb)
        if sec >= 0:
            answer = min(answer, sec)
    
    if answer == 9999999:
        print(-1)
        return

    print(answer)


field = []
N, M = map(int, input().split())
for _ in range(N):
    field.append(list(map(int, input().split())))

virus_list = []
blank_num = 0

# 바이러스 list 확인
for y in range(N):
    for x in range(N):
        if field[y][x] == 2:
            virus_list += [(y, x)]
        elif field[y][x] == 0:
            field[y][x] = 'b'
            blank_num += 1
        else:
            field[y][x] = '-'

solution()