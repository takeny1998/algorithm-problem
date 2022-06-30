# 두 시간의 차이를 분으로 계산해주는 함수
# 만약 -되면 0으로 반환
def diff_time_min(start_time, end_time):
    start_min = time_to_min(start_time)
    end_min = time_to_min(end_time)
    diff_min = end_min - start_min
    
    if diff_min > 0:
        return diff_min
    else:
        return 0
    
    
# 시간을 분으로 변환하는 함수
def time_to_min(time):
    hour, minute = time.split(':')
    return (int(hour) * 60) + int(minute)


# 추가시간 계산하는 함수
def cal_extra_time(minute, extra_time):
    remain = minute % extra_time
    moc = minute // extra_time
    
    if remain > 0:
        return moc + 1
    else:
        return moc
    

def solution(fees, records):
    answer = []
    
    # 기본시간, 기본요금, 추가시간, 추가금액
    start_time, start_fee, extra_time, extra_fee = fees
    car_dict = {}
    
    # 리스트 빌때까지 앞 원소 빼기
    while len(records):
        record = records.pop(0)
        in_time, car_num, _ = record.split(' ')
        out_record = None
        
        
        # 다음 원소부터 해당 차량번호인거 찾기
        for i in range(len(records)):
            if records[i].split(' ')[1] == car_num:
                # 차량번호 맞으면 해당원소 제거 후 out_record에 담기
                out_record = records.pop(i)
                break
        
        # 만약 출차기록 없으면
        if out_record is None:
            # 해당 차량번호의 23:59 출차기록 찾기
            out_record = f"23:59 {car_num} OUT"
        
        out_time, _, _ = out_record.split(' ')
        # 입, 출차 시간차이 계산
        park_time = diff_time_min(in_time, out_time)
        
        # 해당 차번호에 주차시간 추가하기
        if car_num in car_dict:
            car_dict[car_num] += park_time
        else:
            car_dict[car_num] = park_time
    
    car_dict = sorted(car_dict.items())
    
    # 요금 계산하기
    while len(car_dict):
        fee = 0
        _, park_min = car_dict.pop(0)
    
        # 기본요금 계산
        fee += start_fee
        park_min -= start_time
        
        #만약 시간 남았으면
        if park_min > 0:
            extra_iter = cal_extra_time(park_min, extra_time)
            fee += ((extra_iter) * extra_fee)
        answer.append(fee)
        
    return answer