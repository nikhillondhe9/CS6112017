def is_prime(n):
    if n == 2:
        return True
    else:
        for x in range(2, n):
            if n % x == 0:
                return False
            else:
                return True


print(is_prime(5))
