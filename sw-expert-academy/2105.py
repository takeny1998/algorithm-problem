from tkinter import N


move = [(1, 1), (1, -1), (-1, -1), (-1, 1)]

def solution(size: int):
    global iy, ix, answer
    field: list[list] = []
    answer = -1

    for _ in range(size):
        field.append(list(map(int, input().split())))
    
    def dfs(y: int, x: int, visited: list[tuple], meet: list):
        global iy, ix, answer
        
        for d in range(4):
            next_y: int = y + move[d][0]
            next_x: int = x + move[d][1]

            # 범위검사
            if (0 <= next_y < size) and (0 <= next_x < size):
                next_menu: int = field[next_y][next_x]
                
                # 끝나기 검사
                if len(visited) > 1:
                    end_y, end_x = visited[0][0] + (visited[1][0] * -1), visited[0][1] + (visited[1][1] * -1)
                    
                    if (end_y, end_x) == (next_y, next_x):
                        answer = max(answer, len(visited))
                        return
                # 방문검사
                if (next_y, next_x) not in visited:
                    if next_menu not in meet:
                        visited.append((next_y, next_x))
                        meet.append(next_menu)
                        dfs(next_y, next_x, visited[:], meet[:])
                        meet.pop()
                        visited.pop()
    
    for y in range(size):
        for x in range(size):
            dfs(y, x, [(y, x)], [field[y][x]])

    print(answer)

import sys
sys.stdin = open("input.txt", "r")

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    solution(int(input()))