def solution():
    global schedule
    result = 0
    # 끝나는 시간으로 오름차순 정렬하기
    schedule.sort(key=lambda x: (x[1], x[0]))

    min_end = 0

    for i in range(N):
        start, end = schedule[i]
        if start >= min_end:
            result += 1
            min_end = end
        else:
            continue

    print(result)


N = int(input())
schedule = []
for _ in range(N):
    schedule.append(tuple(map(int, input().split())))

solution()