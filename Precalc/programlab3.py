x=float(input("Input a value for x: "))
import math
sum=0
old_sum=1
n=1
a=1
b=1
print("x=",x)
print("cos(x)=",math.cos(x))
print("sin(x)=",math.sin(x))
while (math.fabs(sum-old_sum))>0.0000000000000001:
    old_sum=sum
    sum=sum+((x**a)*((-1)**(b-1))/(math.factorial(n)))
    b=b+1
    n=n+2
    a=a+2
print("y=",sum)
#y=sin(x)
a=0
c=0
n=0
b=1
new_sum=0
new_old_sum=1
while (math.fabs(new_sum-new_old_sum))>0.0000000000000001:
    new_old_sum=new_sum
    new_sum=new_sum+((x**a)*((-1)**(b-1))/(math.factorial(n**c)))
    b=b+1
    n=n+2
    a=a+2
    c=1
print("z=",new_sum)
#z=cos(x)
print("Conclusion: y=sin(x) and z=cos(x)")
