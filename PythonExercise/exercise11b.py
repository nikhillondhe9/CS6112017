# using recursion
def recursive_func(l):
    if not l:
        return 1

    return l[0] * recursive_func(l[1:])
    

some_list = [1, 2, 3, 4, 5]
print(recursive_func(some_list))
