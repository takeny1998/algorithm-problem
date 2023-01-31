import sys

def fill_table(key):
    alpha_list = [
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L',
        'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
        'X', 'Y', 'Z']
    
    key_list = list(dict.fromkeys(key))

    for index, alpha in enumerate(key_list):
        alpha_list.remove(alpha)
        table[alpha] = (index // 5, index % 5)
    
    for index, alpha in enumerate(alpha_list):
        table[alpha] = (
                (len(key_list) + index) // 5,
                (len(key_list) + index) % 5
            )


def divide_message(message):
    message_list = list(message)
    divided_message = []

    while True:
        if len(message_list) == 0:
            break
        if len(message_list) == 1:
            divided_message += [message_list[0], 'X']
            break
        
        left, right = message_list[0], message_list[1]

        if left == right:
            if (left == 'X') and (right == 'X'):
                divided_message += [left, 'Q']
            else:
                divided_message += [left, 'X']
            message_list = message_list[1:]
        else:
            divided_message += [left, right]
            message_list = message_list[2:]

    return divided_message


def encode_message(divded_message):
    encoded_message = []

    for i in range(0, len(divded_message), 2):
        left, right = divded_message[i], divded_message[i + 1]
        
        left_y, left_x = table[left]
        right_y, right_x = table[right]

        # 같은 행 여부
        if left_y == right_y:
            # 오른쪽으로 한칸 이동해 암호화
            for alpha in [left, right]:
                y, x = table[alpha]
                next_x = 0 if (x + 1 == 5) else x + 1 
                encoded_message.append(reversed_table[(y, next_x)])
        # 같은 열 여부
        elif left_x == right_x:
            # 아래쪽으로 한칸 이동해 암호화
            for alpha in [left, right]:
                y, x = table[alpha]
                next_y = 0 if (y + 1 == 5) else y + 1 
                encoded_message.append(reversed_table[(next_y, x)])
        else:
            # 서로의 열을 바꿔 암호화
            encoded_message.append(reversed_table[(left_y, right_x)])
            encoded_message.append(reversed_table[(right_y, left_x)])

    return encoded_message


# input = sys.stdin.readline
table = {}

message = input()
key = input()

fill_table(key)
reversed_table = {value : key for (key, value) in table.items()}
divided_message = divide_message(message)
print(''.join(encode_message(divided_message)))
