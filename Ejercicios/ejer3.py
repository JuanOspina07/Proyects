def contar_repeticiones(arreglo, buscar):
    contador = 0
    for elemento in arreglo:
        if elemento == buscar:
            contador += 1
    return contador
arreglo = [3, 2, 4, 1, 2, 5, 6, 1, 7]
buscar = int(input("Ingrese el valor a buscar: "))
repeticiones = contar_repeticiones(arreglo, buscar)
print("El valor", buscar, "se repite", repeticiones, "veces.")
