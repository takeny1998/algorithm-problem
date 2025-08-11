def solution(rows, columns, queries):
    answer = []
    
    # 수가 증가하는 배열 생성
    arr = []
    num = 1
    for _ in range(rows):
        row = []
        for _ in range(columns):
            row.append(num)
            num += 1
        arr.append(row)
    
    
     # 배열을 회전하는 함수
    def rotate_arr(r1, c1, r2, c2):
        prev_elm = arr[r1][c1]
        min_elm = prev_elm
        
        row = r1
        col = c1
        
        row_move = (r2 - r1)
        col_move = (c2 - c1)
        weight = 1
        
        for _ in range(2):
            # 수평 이동
            for _ in range(col_move):
                col += weight
                temp = arr[row][col]
                arr[row][col] = prev_elm
                prev_elm = temp
                if prev_elm < min_elm:
                    min_elm = prev_elm
                
            # 수직 이동
            for _ in range(row_move):
                row += weight
                temp = arr[row][col]
                arr[row][col] = prev_elm
                prev_elm = temp
                if prev_elm < min_elm:
                    min_elm = prev_elm
            
            # 이동방향 반전
            weight *= -1
        
        return min_elm
    
        
    for query in queries:
        r1, c1, r2, c2 = [(elm - 1) for elm in query]
        min_elm = rotate_arr(r1, c1, r2, c2)
        answer.append(min_elm)
        
    return answer