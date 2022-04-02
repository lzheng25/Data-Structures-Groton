from graphics import *
global win
import time

def Julia(cx,cy,m,n,s,l):
    x_start = 0
    x_end = l
    y_start = 0
    y_end = l
    colors = ["spring green","lawn green","green yellow","lime green","pale green","sea green","deep pink","red","cyan","turquoise","steel blue","dodger blue","hot pink","violet red","medium orchid","deep pink","maroon","magenta","yellow","firebrick","indianred"]
    win = GraphWin("Julia Set", l, l, autoflush=False)
    win.setCoords(x_start, y_start, x_end, y_end)
    zoom = 0.5
    zooming = []
    zooming.append([m,n,s])
    color = input("  Enhancement 2: What color gradient do you want? Select Lucas for my personal colors. Lucas / Blue / Red / Purple / Green / Magenta: ")
    while True:
        for a in range(0,l+1):
            for b in range(0,l+1):
                x0 = m + (s*a / l)
                y0 = n - (s*b / l)
                for i in range(0,21):
                    x1 = x0 * x0 - y0 * y0 + cx
                    y1 = 2 * x0 * y0 + cy
                    if x1 * x1 + y1 * y1 > 4:
                        if color == "Lucas":
                            win.plot(a,b,colors[i])
                            break
                        elif color == "Blue":
                            win.plot(a,b,color_rgb(0,25+5*i,2*(25+5*i)))
                            break
                        elif color == "Red":
                            win.plot(a,b,color_rgb(110+7*i,0,0))
                            break
                        elif color == "Purple":
                            win.plot(a,b,color_rgb(25+5*i,0,2*(25+5*i)))
                            break
                        elif color == "Green":
                            win.plot(a,b,color_rgb(0,110+7*i,0))
                            break
                        elif color == "Magenta":
                            win.plot(a,b,color_rgb(2*(25+5*i),0,25+5*i))
                            break
                    else:
                        x0 = x1
                        y0 = y1
                        win.plot(a,b)
        win.update()
        print("Click to zoom in. Hit the space bar to zoom out. Type 'S' to move to next fractal or end if no more fractals in line. ")
        clickPoint = None
        key = None
        while not clickPoint and not key:
            time.sleep(0.01)
            clickPoint = win.checkMouse()
            key = win.checkKey()
        if key == "space":
            zooming.pop(len(zooming)-1)
            m,n,s = zooming[len(zooming)-1]
        elif clickPoint:
            a = clickPoint.getX()
            b = clickPoint.getY()
            m = m + (s * a / l)
            n = n - (s * b / l)
            s = s * zoom
            m = m - (s * zoom)
            n = n + (s * zoom)
            zooming.append([m,n,s])
        elif key == "S":
            break
def Mandelbrot(m,n,s,l):
    x_start = 0
    x_end = l
    y_start = 0
    y_end = l
    colors = ["spring green","lawn green","green yellow","lime green","pale green","sea green","deep pink","red","steel blue","turquoise","cyan","dodger blue","hot pink","violet red","medium orchid","deep pink","maroon","magenta","yellow","firebrick","indianred"]
    win = GraphWin("Julia Set", l, l, autoflush=False)
    win.setCoords(x_start, y_start, x_end, y_end)
    zoom = 0.5
    zooming = []
    zooming.append([m,n,s])
    color = input("  Enhancement 2: What color gradient do you want? Select Lucas for my persoanl colors. Lucas / Blue / Red / Purple / Green / Magenta: ")
    while True:
        for a in range(0,l+1):
            cx = m + (s*a / l)
            for b in range(0,l+1):
                cy = n - (s*b / l)
                x0 = 0
                y0 = 0
                for i in range(0,21):
                    x1 = x0 * x0 - y0 * y0 + cx
                    y1 = 2 * x0 * y0 + cy
                    if x1 * x1 + y1 * y1 > 4:
                        if color == "Lucas":
                            win.plot(a,b,colors[i])
                            break
                        elif color == "Blue":
                            win.plot(a,b,color_rgb(0,25+5*i,2*(25+5*i)))
                            break
                        elif color == "Red":
                            win.plot(a,b,color_rgb(110+7*i,0,0))
                            break
                        elif color == "Purple":
                            win.plot(a,b,color_rgb(25+5*i,0,2*(25+5*i)))
                            break
                        elif color == "Green":
                            win.plot(a,b,color_rgb(0,110+7*i,0))
                            break
                        elif color == "Magenta":
                            win.plot(a,b,color_rgb(2*(25+5*i),0,25+5*i))
                            break
                    else:
                        x0 = x1
                        y0 = y1
                        win.plot(a,b)
        win.update()
        print("Click to zoom in. Hit the space bar to zoom out. Type 'S' to move to next fractal or end if no more fractals in line. ")
        clickPoint = None
        key = None
        while not clickPoint and not key:
            time.sleep(0.1)
            clickPoint = win.checkMouse()
            key = win.checkKey()
        if key == "space":
            zooming.pop(len(zooming)-1)
            m,n,s = zooming[len(zooming)-1]
        elif clickPoint:
            a = clickPoint.getX()
            b = clickPoint.getY()
            m = m + (s * a / l)
            n = n - (s * b / l)
            s = s * zoom
            m = m - (s * zoom)
            n = n + (s * zoom)
            zooming.append([m,n,s])
        elif key == "S":
            break

times = int(input("How many fractals do you want? "))

t = 1
while t < times + 1:
    choice = input("For Julia Set enter J; for Mandelbrot Set enter M: ")
    if choice == "J":
        print("Fractal",t)
        print("If you want full fractal, put m = -2, n + s = 2, then s = 4.")
        m = float(input("  Lowest bound of x-axis on complex plane: m = "))
        n = float(input("  Highest bound of y-axis on complex plane: n + s = "))
        s = float(input("  The width or height (the same bc square plane) of the complex plane: s = "))
        cx = float(input("  Real part of complex constant: "))
        cy = float(input("  Complex part of complex constant: "))
        l = int(input("  Enhancement 1: How large do you want the window to be? Between 100 and 400 pixels: "))
        Julia(cx,cy,m,n,s,l)
        t = t + 1
    elif choice == "M":
        print("Fractal",t)
        m = float(input("  Lowest bound of x-axis on complex plane: m = "))
        n = float(input("  Highest bound of y-axis on complex plane: n + s = "))
        s = float(input("  The width or height (the same bc square plane) of the complex plane: s = "))
        l = int(input("  Enhancement 1: How large do you want the window to be? Between 100 and 400 pixels: "))
        Mandelbrot(m,n,s,l)
        t = t + 1
