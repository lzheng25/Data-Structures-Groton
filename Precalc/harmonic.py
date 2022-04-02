import math
s = 0
n = 0
while s < 3:
    n = n + 1
    s = s + (1/n)
print("The # terms needed to exceed a sum of 3 is",n)

while s < 4:
    n = n + 1
    s = s + (1/n)
print("The # terms needed to exceed a sum of 4 is",n)

while s < 10:
    n = n + 1
    s = s + (1/n)
print("The # terms needed to exceed a sum of 10 is",n)


s = 0
n = 0
while n < 1000:
    n = n + 1
    s = s + (1/n*(-1)**(n-1))
print("The sum of the alternating harmonic series is",s)
print("The limit of the alternating harmonic series is ln(2):",math.log(2))
