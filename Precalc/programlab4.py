import math
answers = {}
def sin(x):
    if x in answers.keys():
        return answers[x]
    elif x == 1:
        return 0.017452406
    else:
        answer = sin(x-1)*cos(1) + cos(x-1)*sin(1)
        answers[x] = answer
        return answer

def cos(x):
    return math.sqrt(1-sin(x)*sin(x))

x = float(input("Input x:"))
while x < 91:
    print("sin (",x,"°) =",sin(x))
    print("cos (",x,"°) =",cos(x))
    x = x + 1
