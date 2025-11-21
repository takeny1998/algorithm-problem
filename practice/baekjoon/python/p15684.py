from tabnanny import check


def solution(N: int, M: int, H: int):
    global answer
    answer = 4
    # 사다리 만들기
    ladder: list[list] = [[0 for _ in range(N - 1)] for _ in range(H)]

    # 초기 가로간선 정보 얻기
    for _ in range(M):
        a, b = map(int, input().split())
        # a번 점선 위치에서 b, b+1 세로선 이음
        ladder[a - 1][b - 1] = 1

    def dfs(idx: int, y: int, x: int, added: int):
        global answer

        if added >= answer:
            return
            
        # 맨 밑으로 내려오면 끝
        if y >= len(ladder):
            if x != idx:
                return
            if x == idx:
                # 진짜 끝나면
                if idx == (N - 1):
                    if added < answer:
                        answer = added
                    return
                dfs(idx + 1, 0, idx + 1, added)
            return
        is_degree: bool = False

        # 오른쪽에 간선이 있는경우
        if x < (N - 1):
            if ladder[y][x] == 1:
                is_degree = True
                # 오른쪽으로 사다리 이동
                dfs(idx, y + 1, x + 1, added)
        if x > 0:
            # 왼쪽에 간선이 있는경우
            if ladder[y][x - 1]:
                is_degree = True
                # 왼쪽으로 사다리 이동
                dfs(idx, y + 1, x - 1, added)

        # 간선이 없는 경우
        if is_degree is False:
            # 간선 없음
            dfs(idx, y + 1, x, added)
            degree_list = []
            
            # 해당 행 간선 불러오기
            for check_x in range(N - 1):
                if ladder[y][check_x] == 1:
                    degree_list.append(check_x)
            
            # 오른쪽 간선 생성하기
            if x not in degree_list:
                if (x + 1) not in degree_list:
                    if (x - 1) not in degree_list:
                        if 0 <= x < (N - 1):
                            ladder[y][x] = 1
                            dfs(idx, y + 1, x + 1, added + 1)
                            ladder[y][x] = 0

            # 왼쪽 간선 생성하기
            if (x - 1) not in degree_list:
                if x not in degree_list:
                    if (x - 2) not in degree_list:
                        if 0 <= (x - 1) < (N - 1):
                            ladder[y][x - 1] = 1
                            dfs(idx, y + 1, x - 1, added + 1)
                            ladder[y][x - 1] = 0


    dfs(0, 0, 0, 0)
    if answer > 3:
        print(-1)
    else:
        print(answer)


N, M, H = map(int, input().split())
solution(N, M, H)