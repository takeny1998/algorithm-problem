moves = {0: (-1, 0), 1: (0, 1), 2: (1, 0), 3: (0, -1)}

def solution(places):
    answer = []
    
    def bfs(field):
        for r in range(5):
            for c in range(5):
                if field[r][c] == 'P':
                    queue = [(r, c, 0)]
                    visited = [[0 for _ in range(5)] for _ in range(5)]
                    visited[r][c] = 1
                    
                    while queue:
                        r, c, dist = queue.pop(0)
                        
                        if dist < 2:
                            for direct in range(4):
                                move = moves[direct]
                                next_r = r + move[0]
                                next_c = c + move[1]

                                if (0 <= next_r < 5) and (0 <= next_c < 5):
                                    if visited[next_r][next_c] == 0:
                                        visited[next_r][next_c] = 1
                                        if field[next_r][next_c] == 'O':
                                            queue.append((next_r, next_c, dist + 1))
                                        elif field[next_r][next_c] == 'P':
                                            return 0
        return 1
    
    for place in places:
        answer.append(bfs(place))
    
    return answer