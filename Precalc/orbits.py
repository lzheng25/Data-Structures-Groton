x = float(input("Real part of seed: "))
y = float(input("Complex part of seed: "))
print("Iteration       Orbit")
print("1              ",x,"+",y,"i")
n = 2
while n < 10:
    x1 = x * x - y * y
    y1 = 2 * x * y
    print(n,"             ",x1,"+",y1,"i")
    x = x1
    y = y1
    n = n + 1
