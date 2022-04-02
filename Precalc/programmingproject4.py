import math
mode=input("Normal Mode/Extra Credit Mode? ")
if mode == "Normal Mode":
    n=1
    d3=0
    while n < 101:
      dy=(((1-(math.cos(n*(2*math.pi/100))))-(1-(math.cos((n-1)*(2*math.pi/100)))))**2)
      dx=((((n*(2*math.pi/100))-math.sin(n*(2*math.pi/100)))-(((n-1)*(2*math.pi/100))-math.sin((n-1)*(2*math.pi/100))))**2)
      d3=d3+math.sqrt(dy+dx)
      n=n+1
    print("The length is", d3)
    n=1
    area = 0
    while n < 101:
        ax = (((n*(2*math.pi/100))-math.sin(n*(2*math.pi/100)))-(((n-1)*(2*math.pi/100))-math.sin((n-1)*(2*math.pi/100))))
        ay = (1-(math.cos(n*(2*math.pi/100))))
        area = area + (ax*ay)
        n=n+1
    print("The area under the arc is", area)

elif mode == "Extra Credit Mode":
    y=float(input("Number of intervals: "))
    z=2*math.pi/y
    n=1
    length=0
    while n < y+1:
      dy2=(((1-(math.cos(n*(z))))-(1-(math.cos((n-1)*(z)))))**2)
      dx2=((((n*(z))-math.sin(n*(z)))-(((n-1)*(z))-math.sin((n-1)*(z))))**2)
      length=length+math.sqrt(dy2+dx2)
      n=n+1
    print("The length is", length)
    a = input("Left Rectangles/Right Rectangles/Trapezoids: ")
    if a == "Left Rectangles":
        n=1
        area1 = 0
        while n < y+1:
            ax = (((n*(z))-math.sin(n*(z)))-(((n-1)*(z))-math.sin((n-1)*(z))))
            ay = (1-(math.cos((n-1)*(z))))
            area1 = area1 + (ax*ay)
            n=n+1
        print("The area under the arc is", area1)
    elif a == "Right Rectangles":
        n=1
        area2 = 0
        while n < y+1:
            ax = (((n*(z))-math.sin(n*(z)))-(((n-1)*(z))-math.sin((n-1)*(z))))
            ay = (1-(math.cos(n*(z))))
            area2 = area2 + (ax*ay)
            n=n+1
        print("The area under the arc is", area2)
    elif a == "Trapezoids":
        n=1
        area3 = 0
        while n < y+1:
            ax = (((n*(z))-math.sin(n*(z)))-(((n-1)*(z))-math.sin((n-1)*(z))))
            ay = (1-(math.cos((n-1)*(z))))+(1-(math.cos(n*(z))))
            area3 = area3 + ((ax*ay)/2)
            n=n+1
        print("The area under the arc is", area3)
print("")
print("For the arbitrary parametric equations, follow these steps: ")
domain1=eval(input("Input Lowest Domain: "))
domain2=eval(input("Input Highest Domain: "))
interval=float(input("How many intervals? "))
z=(domain2-domain1)/interval
n=1
t=n*z
print("Type in a set of parametric equations below: ")
x=(input("x = "))
y=(input("y = "))
distance=0
while n < interval+1:
    t=n*z
    dx1=eval(x)
    dy1=eval(y)
    n=n-1
    t=n*z
    dx0=eval(x)
    dy0=eval(y)
    dx=dx1-dx0
    dy=dy1-dy0
    distance=distance+math.sqrt((dx**2)+(dy**2))
    n=n+2
print("The length is", distance)
a = input("Left Rectangles/Right Rectangles/Trapezoids: ")
if a == "Left Rectangles":
    n=1
    area1 = 0
    while n < interval+1:
        t=n*z
        ax1=eval(x)
        n=n-1
        t=n*z
        ax0=eval(x)
        ay0=eval(y)
        area1 = area1 + (ax1-ax0)*(ay0)
        n=n+2
    print("The area under the arc is", area1)
elif a == "Right Rectangles":
    n=1
    area2 = 0
    while n < interval+1:
        t=n*z
        ax1=eval(x)
        ay1=eval(y)
        n=n-1
        t=n*z
        ax0=eval(x)
        area2 = area2 + (ax1-ax0)*(ay1)
        n=n+2
    print("The area under the arc is", area2)
elif a == "Trapezoids":
    n=1
    area3 = 0
    while n < interval+1:
        t=n*z
        ax1=eval(x)
        ay1=eval(y)
        n=n-1
        t=n*z
        ax0=eval(x)
        ay0=eval(y)
        area3 = area3 + (((ax1-ax0)*(ay1+ay0))/2)
        n=n+2
    print("The area under the arc is", area3)
