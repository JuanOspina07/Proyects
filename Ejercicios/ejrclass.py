n = int(input("Ingrese el numero de giros hacia la izquierda: "))
m = int(input("Ingrese el numero de giros hacia la derecha: "))
a = [1, 2, 3, 4, 5]
def sum():
    for i in range(len(a)):
        a[i] += 1
        if a[i] > 5:
            a[i] = 1
def res():
    for u in range(len(a)):
        a[u] -= 1
        if a[u] < 1:
            a[u] = 5

def giros(n, m):
    for i in range(n):
        sum()
    for e in range(m):
        res()
giros(n, m)
print(a)
#Hecho por Juan Diego Ospina y Felipe Ruiz