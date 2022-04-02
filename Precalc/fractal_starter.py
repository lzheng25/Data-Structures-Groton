import math
from graphics import *

def square(point, length, fillColor):
    p = Point(point.getX() + length, point.getY()-length)
    r = Rectangle(point, p)
    r.setFill(fillColor)
    r.setOutline("")
    r.draw(win)

def cross(point,length, fillColor):
    offsets = [(0,0), (2,0), (0, -2), (2,-2)]
    for o in offsets:
        p = Point(point.getX() + o[0]*(length), point.getY() + o[1]*(length))
        square(p, length, fillColor)
    offsets = [(1,0), (0,-1), (1, -1), (2, -1), (1, -2)]
    nextIter = []
    for o in offsets:
        p = Point(point.getX() + o[0]*(length), point.getY() + o[1]*(length))
        nextIter.append(p)
    return nextIter

def printIterationPoints(listOfPoints):
  for p in listOfPoints:
      print("(%0.1f,%0.1f)"%(p.getX(), p.getY()))

def draw_cross():
    p1 = Point(0,600)
    length = 600
    square(p1, length, "yellow")
    length = length / 3.0
    nextIterationPoints = cross(p1, length, "dark green")
    newIterationPoints = nextIterationPoints
    #win.getMouse()
    for count in range(4):
        nextIterationPoints = newIterationPoints
        newIterationPoints = []
        for ip in nextIterationPoints:
            newIterationPoints.extend(cross(ip, length/3, "dark green"))
        length = length / 3
        #win.getMouse()

def midpoint(p0, p1):
    return Point( (p0.getX() + p1.getX())/2.0, (p0.getY() + p1.getY())/2.0)

def get_midpoints(endpoints):
    p0, p1, p2 = endpoints
    return [midpoint(p0, p1), midpoint(p0, p2), midpoint(p1, p2)]

def triangle(endpoints, fillColor):
    p0, p1, p2  = endpoints
    t = Polygon(p0, p1, p2)
    t.setFill(fillColor)
    t.setOutline("black")
    t.draw(win)

def draw_sierpinski():
    p1 = Point(0,600)
    length = 600
    p0 = Point(0,0)
    p1 = Point(p0.getX() + length, p0.getY())
    p2 = Point(p0.getX() + length/2.0, p0.getY() + length * math.sqrt(3.0) / 2)
    endpoints =  [p0, p1, p2]
    triangle(endpoints, "yellow")
    triangles = [endpoints]
    #win.getMouse()
    for i in range(7):
        new_triangles = []
        for endpoints in triangles:
            midpoints = get_midpoints(endpoints)
            triangle(midpoints, "light blue")
            new_triangles.append([endpoints[0], midpoints[0], midpoints[1]])
            new_triangles.append([endpoints[1], midpoints[0], midpoints[2]])
            new_triangles.append([endpoints[2], midpoints[1], midpoints[2]])
        #win.getMouse()
        triangles = new_triangles

def carpet(point,length,fillColor):
    pass

def draw_carpet():
    pass

def main():
    global win
    win = GraphWin("Cross Fractal", 600, 600)
    win.setCoords(0,0,600,600)
    win.setBackground("green")
    draw_cross()

    win = GraphWin("Sierpinski Triangle", 600, 520)
    win.setCoords(0,0,600,520)
    win.setBackground("blue")
    draw_sierpinski()
    
    win = GraphWin("Sierpinski Carpet", 600, 600)
    win.setCoords(0,0,600,600)
    win.setBackground("black")
    draw_carpet()

    win.getMouse()

main()
