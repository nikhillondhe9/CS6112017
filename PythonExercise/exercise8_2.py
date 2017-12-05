# create function set_elem_to_zero()
def set_elem_to_zero(l):
    l[0] = "zero"
    return l


some_list = [1,2,3,4]
print(set_elem_to_zero(some_list))
print(some_list)