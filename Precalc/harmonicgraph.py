from graphics import *
global win
x_start = -2
x_end = 1000
y_start = 0
y_end = 1
win = GraphWin("Harmonic", 600, 600)
win.setCoords(x_start, y_start, x_end, y_end)

xaxis = Line(Point(x_start,0), Point(x_end,0))
xaxis.draw(win)
yaxis = Line(Point(0,y_start), Point(0,y_end))
yaxis.draw(win)

def plot(x,y):
    c = Circle(Point(x,y),0.001)
    c.setFill("lime")
    c.draw(win)
    c.setOutline("blue")
#Increase x_end for a more accurate Limit
#Wait for window to finish for answer

print("Part 2")
n = 0
s = 0

while n < x_end:
    plot(n,s)
    n = n + 1
    s = s + (1/n * (-1)**(n-1))
print("The Limit of the alternating harmonic series is about",s)

print("Part 1")

n = 0
s = 0
while s < 3:
    n = n + 1
    s = s + 1/n
print("The number of terms it takes to exceed 3:",n)

while s < 4:
    n = n + 1
    s = s + 1/n
print("The number of terms it takes to exceed 4:",n)

while s < 10:
    n = n + 1
    s = s + 1/n
print("The number of terms it takes to exceed 10:",n)
