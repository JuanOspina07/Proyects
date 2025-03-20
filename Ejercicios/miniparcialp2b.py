a=[1,2,3,4,5]
s=int(input("ingrese numero: "))
i=0
x=len(a)-1
while i<x:
    z=a[i]+a[x]
    if(z==s):
       print(f"{i} {x}")
       break
    elif(z>s):
        x-=1
    elif(s>z):
        i+=1
    else:
        print("no se encontro")
        break

        
