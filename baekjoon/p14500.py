move = [(-1, 0), (1, 0), (0, -1), (0, 1)]
block = [
    [(0, 0), (0, 1), (0, 2), (1, 1)],
    [(0, 0), (0, 1), (-1, 1), (1, 1)],
    [(0, 0), (0, 1), (0, 2), (-1, 1)],
    [(0, 0), (0, 1), (-1, 0), (1, 0)],
]

def solution(height: int, width: int):
    global answer
    answer = 0
    # 방문처리를 위한 배열
    visited: list[list] = [[0 for _ in range(width)] for _ in range(height)]
    # 점수를 담을 배열
    score: list[list] = []

    # 점수를 담을 배열 입력받기
    for _ in range(height):
        line: list[int] = list(map(int, input().split()))
        score.append(line)
    

    # ㅜ 모양을 제외한 블록을 움직이는 dfs 함수
    def dfs(y, x, depth, score_sum):
        global answer

        if depth == 4:
            answer = max(answer, score_sum)
            return
        
        # 상 하 좌 우 이동
        for d in range(4):
            next_y = y + move[d][0]
            next_x = x + move[d][1]

            # 범위검사
            if (0 <= next_y < height) and (0 <= next_x < width):
                # 방문검사
                if visited[next_y][next_x] == 0:
                    visited[next_y][next_x] = 1
                    # 다음으로 이동
                    dfs(next_y, next_x, depth + 1, score_sum + score[next_y][next_x])
                    visited[next_y][next_x] = 0


    # ㅜ 모양을 블록을 움직이는 함수
    def exec(y, x):
        global answer

        for shape in range(4):
            score_sum: int = 0
            # 상 하 좌 우 이동
            for m in range(4):
                next_y = y + block[shape][m][0]
                next_x = x + block[shape][m][1]
                
                # 범위검사
                if (0 <= next_y < height) and (0 <= next_x < width):
                    score_sum += score[next_y][next_x]
                # 범위 나가는 경우 예외처리
                else:
                    score_sum = 0
                    break

            # 최댓값 갱신
            answer = max(answer, score_sum)


    # 칸 마다 진행
    for y in range(height):
        for x in range(width):
            # 4개 블록 갱신하기
            visited[y][x] = 1
            dfs(y, x, 1, score[y][x])
            visited[y][x] = 0

            exec(y, x)
    
    print(answer)

N, M = map(int, input().split())
solution(N, M)