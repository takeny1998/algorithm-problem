def solution():
    def combinations(array, r):
        for i in range(len(array)):
            if r == 1: # 종료 조건
                yield [array[i]]
            else:
                for next in combinations(array[i+1:], r-1):
                    yield [array[i]] + next

    answer = 0

    harvest_list = []
    harvested = [[0 for _ in range(size)] for _ in range(size)]

    # 목표량 낮춰가면서 진행
    for y in range(size):
        for x in range(size):
            # 범위 검사
            if (x + width) > size:
                break
            window = field[y][x:x+width]
            harvest = {}
            # 1 ~ 벌꿀통 전체를 채취하는 경우
            for n in range(1, width + 1):
                for comb in combinations(window, n):
                    # 전체 체취량보다 많으면 채취하지 못함
                    if sum(comb) > honey_max:
                        continue
                    # 요소 다 더해서 저장
                    benefit = sum([elm ** 2 for elm in comb])
                    
                    harvest[benefit] = (y, x, x+width)
            top_harvested = sorted(harvest.items(), reverse=True)[0]
            harvest_list.append(top_harvested)

    harvest_list.sort(reverse=True)
    zero_mask = [0 for _ in range(width)]
    one_mask = [1 for _ in range(width)]
    chance = 2

    for benefit, coord in harvest_list:
        if chance == 0:
            break

        y, x1, x2 = coord[0], coord[1], coord[2]
        if harvested[y][x1:x2] == zero_mask:
            harvested[y][x1:x2] = one_mask
            answer += benefit
            chance -= 1

    return answer


T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    # ///////////////////////////////////////////////////////////////////////////////////
    size, width, honey_max = map(int, input().split())
    field = []
    for _ in range(size):
        field.append(list(map(int, input().split())))
    
    print(f'#{test_case} {solution()}')
    # ///////////////////////////////////////////////////////////////////////////////////