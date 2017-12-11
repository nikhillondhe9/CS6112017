# return prime up to n
def find_upto_nprimes(N):
    prime_list = []
    n = 2
    c = 0
    while c < N - 1:
        prime_test = []
        for i in prime_list:
            if n % i == 0:
                prime_test.append(i)

        prime_list += [] if prime_test else [n]
        n += 1
        c += 1
    return prime_list


print(find_upto_nprimes(10))



