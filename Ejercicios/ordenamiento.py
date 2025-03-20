A=[3,5,8,2,1,9,4,0]
for i in range(8):
    x=A[i]
    j=i-1
    while j>=0 and x<A[j]:
        A[j+1]=A[j]
        j=j-1
    A[j+1]=x
print(A)

