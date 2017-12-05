def print_list(list):
    new_list = []
    i = 0
    while i < len(list):
        for element in list[i]:
            new_list.append(element)
        i += 1
    return new_list


given_List = [[1, 3], [3, 6]]
print(print_list(given_List))
