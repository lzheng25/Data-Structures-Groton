import math
def is_prime(n):
    x = 2
    while x < (n):
        if n % x == 0:
            return (False)
        x = x + 1
    return(True)

def is_int(val):
    if type(val) == int:
        return True
    else:
        if val.is_integer():
            return True
        else:
            return False

#TODO Problem 1
x = 1
y = 0
while x < 1000:
    if x % 3 == 0:
        y = y + x
    elif x % 5 == 0:
        y = y + x
    x = x + 1
print("The sum of all the multiples of 3 or 5 below 1000 is",y)

#TODO Problem 3
x = 600851475143
while x > 0:
    if 600851475143 % x == 0:
        check = print(is_prime(x))
        if check == "True":
            print("The largest prime factor of 600851475143 is",x)

    else:
        x = x - 1

#TODO Problem 7
def sum_of_squares(n):
    y = 0
    while n >= 1:
        y = y + n**2
        n = n - 1
    return(y)
def square_of_sum(n):
    y = 0
    while n >= 1:
        y = y + n
        n = n - 1
    y = y**2
    return(y)
difference = int(square_of_sum(100)) - int(sum_of_squares(100))
print("The difference is",difference)
