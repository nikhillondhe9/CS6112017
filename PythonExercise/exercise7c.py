# implementation of len function
def len_function(list):
    list_len = 0
    for element in list:
        list_len += 1
    return list_len


print(len_function([1, 2, 3, 4, 5, 'six', 'seven', 'eight']))
