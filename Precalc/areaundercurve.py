import math
x = 0
sum = 0
w = 1/1000
while x < 1000:
    sum = sum + w * (1 / (w * x + 1))
    x = x + 1
print(sum)
print("ln(2) =",math.log(2))

x = 0
sum = 0
w = 2/1000
while x < 1000:
    sum = sum + w * (1 / (w * x + 1))
    x = x + 1
print(sum)
print("ln(3) =",math.log(3))

x = 0
sum = 0
w = 5/1000
while x < 1000:
    sum = sum + w * (1 / (w * x + 1))
    x = x + 1
print(sum)
print("ln(6) =",math.log(6))
