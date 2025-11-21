dyx = [(-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1)]

def solution():
    def bfs(y, x):
        visited = [[0 for _ in range(N)] for _ in range(N)]
        visited[y][x] = 1
        queue = [(y, x)]
        house_left = len(houses)

        while queue:
            cy, cx = queue.pop(0)
            # 이동가능 좌표
            for d in range(8):
                dy, dx = dyx[d]
                ny, nx = cy + dy, cx + dx

                if (0 <= ny < N) and (0 <= nx < N):
                    if visited[ny][nx] == 0:
                        # 이동 가능할 경우
                        if atts[left] <= att[ny][nx] <= atts[right]:
                            visited[ny][nx] = 1
                            if field[ny][nx] == 'K':
                                house_left -= 1
                            queue.append((ny, nx))
        return house_left


    answer = 9999999999999999
    left, right = 0, 0
    while left < len(atts):
        house_left = len(houses)
        if atts[left] <= att[post_y][post_x] <= atts[right]:
            house_left = bfs(post_y, post_x)

        # 집 다 돌면
        if house_left == 0:
            answer = min(answer, atts[right] - atts[left])
            left += 1
        # 집 다 못돌면
        elif (right + 1) < len(atts):
            right += 1
        else: break
    print(answer)
    return


N = int(input())
field = []
for _ in range(N):
    field.append(list(input()))

att = []
for _ in range(N):
    att.append(list(map(int, input().split())))

atts = []
houses = []

post_y, post_x = 0, 0

for y in range(N):
    for x in range(N):
        atts.append(att[y][x])

        if field[y][x] == 'K':
            houses.append((y, x))
        elif field[y][x] == 'P':
            post_y, post_x = y, x

atts = list(set(atts))
atts.sort()

solution()
