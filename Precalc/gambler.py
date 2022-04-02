import random

def roulette(x):
    y = 2*x
    while y > x > 0:
        n = random.randint(1,38)
        if 18 >= n >= 1:
             x = x + 1
        else:
            x = x - 1
    return x == y

success = 0
trials = 1000000
for i in range(0,trials):
    if roulette(10):
        success = success + 1
print("The probability of doubling $10 is",success*100/trials,"percent.")

success = 0
trials = 1000000
for i in range(0,trials):
    if roulette(1000):
        success = success + 1
print("The probability of doubling $1000 is",success*100/trials,"percent.")
