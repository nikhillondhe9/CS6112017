# Write a function that prints list elements in reverse
def print_reverse(list):
    i = len(list)
    while i > 0:
        print(list[i - 1])
        i -= 1


L = [1, 2, 3, "four", "five", "list elements"]
print_reverse(L)