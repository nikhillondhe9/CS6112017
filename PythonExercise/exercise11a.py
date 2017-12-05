# using iteration
def iter_func(l):
    product = 1
    for num in l:
        product *= num
    return product


some_list = [1, 2, 3, 4, 5]
print(iter_func(some_list))
