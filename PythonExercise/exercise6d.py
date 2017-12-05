# find first n prime numbers
def find_prime(n):
    prime_list = []
    prime_found = 0
    x = n
    while prime_found < n:
         for x in range(2, n):
            if n == 2:
                 prime_list.append(n)
                 prime_found += 1
            elif n % x == 0:
                pass
            else:
                prime_list.append(x)
                prime_found += 1
         x -= 1
    return prime_list



print(find_prime(10))