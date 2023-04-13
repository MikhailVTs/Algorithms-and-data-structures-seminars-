import random
import time


def left_part(Left_num):
    return 2 * Left_num + 1


def right_part(right_num):
    return 2 * right_num + 2


def replacing_indexes(random_list, index_i, index_j):

    temp = random_list[index_i]
    random_list[index_i] = random_list[index_j]
    random_list[index_j] = temp


def pile_on(random_list, index_i, size):

    left = left_part(index_i)
    right = right_part(index_i)

    biggest = index_i

    if left < size and random_list[left] > random_list[index_i]:
        biggest = left

    if right < size and random_list[right] > random_list[biggest]:
        biggest = right

    if biggest != index_i:
        replacing_indexes(random_list, index_i, biggest)
        pile_on(random_list, biggest, size)


def item_removal(random_list, size):

    if size <= 0:
        return -1

    upwards = random_list[0]

    random_list[0] = random_list[size - 1]

    pile_on(random_list, 0, size - 1)

    return upwards


def heap_sorting(random_list):

    list_size = len(random_list)

    index_i = (list_size - 2) // 2
    while index_i >= 0:
        pile_on(random_list, index_i, list_size)
        index_i = index_i - 1

    while list_size:
        random_list[list_size - 1] = item_removal(random_list, list_size)
        list_size = list_size - 1


if __name__ == '__main__':

    random_list = list(range(1, 900001))
    random.shuffle(random_list)

    # print()
    # print("Список до сортировки")
    # print(random_list)

    print()
    start_time = time.time()
    heap_sorting(random_list)
    stop_time = time.time()
    print(f"Время сортировки - {stop_time - start_time}")
    print()

    # print("Список после сортировки")
    # print(random_list)
