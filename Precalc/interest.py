print("         4.5%          5%            5.5%          6%            6.5%")
x = 1
A1 = 0
A2 = 0
A3 = 0
A4 = 0
A5 = 0
while x <= 10:
    A1 = round(1000*(1+(0.045/4))**(4*x),8)
    A2 = round(1000*(1+(0.05/4))**(4*x),8)
    A3 = round(1000*(1+(0.055/4))**(4*x),8)
    A4 = round(1000*(1+(0.06/4))**(4*x),8)
    A5 = round(1000*(1+(0.065/4))**(4*x),8)
    print("Year",x,":",A1,A2,A3,A4,A5)
    x = x + 1

import math
print("")
print("Now Extra Credit.")
initial = float(input(("Initial Value of Investment: ")))
print("Compounded annually(1), semiannually(2), quarterly(4), monthly(12), daily(365), or continuously(6)?")
interest = int(input("Type in the number without parentheses: "))
year = input("Compound for how many years? ")
whatrate = input("Table Rates / Own Rate? ")
if whatrate == "Own Rate":
    rate = int(input("What % interest rate? "))
    x = 1
    if interest == 6:
        Ay = 0
        while x <= int(year):
            Ay = initial*(math.exp((rate/100)*(x)))
            print("Year",x,":",Ay)
            x = x + 1
    elif interest == 1 or interest == 2 or interest == 4 or interest == 12 or interest == 365:
        A1 = 0
        while x <= int(year):
            A1 = round(initial*(1+((rate/100)/(int(interest))))**((int(interest))*x),8)
            print("Year",x,":",A1)
            x = x + 1
elif whatrate == "Table Rates":
    print("         4.5%          5%            5.5%          6%            6.5%")
    x = 1
    if interest == 6:
        A1 = 0
        A2 = 0
        A3 = 0
        A4 = 0
        A5 = 0
        while x <= int(year):
            A1 = initial*(math.exp((0.045)*(x)))
            A2 = initial*(math.exp((0.05)*(x)))
            A3 = initial*(math.exp((0.055)*(x)))
            A4 = initial*(math.exp((0.06)*(x)))
            A5 = initial*(math.exp((0.065)*(x)))
            print("Year",x,":",A1,A2,A3,A4,A5)
            x = x + 1
    elif interest == 1 or interest == 2 or interest == 4 or interest == 12 or interest == 365:
        A1 = 0
        A2 = 0
        A3 = 0
        A4 = 0
        A5 = 0
        while x <= int(year):
            A1 = round(initial*(1+(0.045/4))**(interest*x),8)
            A2 = round(initial*(1+(0.05/4))**(interest*x),8)
            A3 = round(initial*(1+(0.055/4))**(interest*x),8)
            A4 = round(initial*(1+(0.06/4))**(interest*x),8)
            A5 = round(initial*(1+(0.065/4))**(interest*x),8)
            print("Year",x,":",A1,A2,A3,A4,A5)
            x = x + 1
