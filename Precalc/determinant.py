def minor(m, c):
    result = []
    for row in range(1,len(m)):
        l = []
        for col in range(0,len(m)):
            v = m[row][col]
            if col != c:
                l.append(v)
        result.append(l)
    return result

def determinant(m):
    if len(m) == 2:
        return m[0][0]*m[1][1]-m[0][1]*m[1][0]
    elif len(m) == 1:
        return m
    else:
        d = 0
        for c in range(0,len(m)):
            sign = (-1)**c
            mi = minor(m,c)
            d = d + sign * m[0][c]*determinant(mi)
        return d

x = int(input("Square matrix size: "))

m = []
for row in range(0,x):
    l = []
    for col in range(0,x):
        v = int(input("Input a value for row "+str(row)+" and col "+str(col)+":"))
        l.append(v)
    m.append(l)

print("The matrix's determinant is", determinant(m))
