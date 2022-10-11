from flask import g


dyx = [(-1, 0), (0, 1), (1, 0), (0, -1)]

def bfs():
    visited = [[0 for _ in range(N)] for _ in range(N)]
    divided = [[0 for _ in range(N)] for _ in range(N)]
    group_list = []
    index = 0
    
    for y in range(N):
        for x in range(N):
            if visited[y][x] == 0:
                visited[y][x] = 1
                divided[y][x] = index

                queue = [(y, x)]
                length = 1

                num = field[y][x]

                while queue:
                    y, x = queue.pop(0)
                    for d in range(4):
                        dy, dx = dyx[d]
                        ny, nx = y + dy, x + dx

                        if (0 <= ny < N) and (0 <= nx < N):
                            if field[ny][nx] == num and visited[ny][nx] == 0:
                                visited[ny][nx] = 1
                                divided[ny][nx] = index
                                queue.append((ny, nx))
                                length += 1

                index += 1
                group_list.append({'stan': (y, x), 'num': num, 'length': length})
    
    for line in divided:
        print(line)


    for i in range(len(group_list)):
        
        print('----')
        y, x = group_list[i]['stan']
        num = group_list[i]['num']
        visited = [[0 for _ in range(N)] for _ in range(N)]
        visited[y][x] = 1
        queue = [(y, x)]
        border = [0 for _ in range(len(group_list))]
        
        while queue:
            y, x = queue.pop(0)
            for d in range(4):
                dy, dx = dyx[d]
                ny, nx = y + dy, x + dx

                if (0 <= ny < N) and (0 <= nx < N):
                    if field[ny][nx] == num and visited[ny][nx] == 0:
                        visited[ny][nx] = 1
                        queue.append((ny, nx))
                    else:
                        idx = divided[ny][nx]
                        border[idx] += 1
        border[i] = 0
        group_list[i]['border'] = border
                                
    return group_list


def combinations(arr, r):
    for i in range(len(arr)):
        if r == 1:
            yield arr[i]
        else:
            for next in combinations(arr[i + 1:], r - 1):
                yield [arr[i]] + [next]




N = int(input())
field = []
for _ in range(N):
    field.append(list(map(int, input().split())))

group_list = bfs()

print(group_list)

# for a, b in combinations(group_list, 2):
#     print(get_score(a, b))