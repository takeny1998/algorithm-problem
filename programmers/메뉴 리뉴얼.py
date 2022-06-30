from itertools import combinations as combs
import time


    
def solution(orders, course):
    
    answer = []
    # 코스의 음식 개수대로 조합 생성
    for dish_num in course:
        comb_count = {}
        max_comb = []
        max_count = 0
        
        for order in orders:
            for comb in combs(sorted(order), dish_num):
                if comb in comb_count:
                    comb_count[comb] += 1
                else:
                    comb_count[comb] = 1
                    
        print(comb_count)
        for comb, count in comb_count.items():
            if count > 1:
                if count > max_count:
                    max_comb = []
                if count >= max_count:
                    max_count = count
                    max_comb.append("".join(comb))
                    
        answer += max_comb
        # print(max(comb_count, key=comb_count.get))
                    
    return sorted(answer)