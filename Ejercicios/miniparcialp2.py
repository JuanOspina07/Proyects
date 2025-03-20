a=[3,6,2,1,9,4]
s=int(input("ingrese el numero:"))
for i in range (0,len(a)):
    x=a[i]
    for j in range (i+1,len(a)):
        y=a[j]
        z=x+y
        if(z==s):
            print(f"en la posicion {i} y {j}")

        

