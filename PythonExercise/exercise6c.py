# return prime up to n
def prime_upto(n):
    prime_list = []
    i = 2
    while i <= n:
        if i == 2:
            prime_list.append(i)
        else:
            for x in range(2, i):
                if i % x == 0:
                    pass
                else:
                    prime_list.append(x)
        i = i + 1
    return prime_list


print(prime_upto(10))



