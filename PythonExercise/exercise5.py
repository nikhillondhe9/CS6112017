# find first 20 numbers divisible by 5, 7 and 11
num_found = 0
x = 11
while num_found < 20:
    if x % 5 == 0 and x % 7 == 0 and x % 11 == 0:
        print(x)
        num_found += 1
    x += 1
