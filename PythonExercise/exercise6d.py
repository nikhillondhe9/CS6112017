def find_n_primes(N):
    prime_list = []
    n = 2
    while len(prime_list) < N:
        prime_test = []
        for i in prime_list:
            if n % i == 0:
                prime_test.append(i)

        prime_list += [] if prime_test else [n]
        n += 1
    return prime_list


print(find_n_primes(10))
