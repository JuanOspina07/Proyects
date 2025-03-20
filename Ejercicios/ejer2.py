def calcular_serie(n):
    resultado = 0.0
    for i in range(1, n + 1):
        if i % 2 == 0:  # Si el índice es par, se resta
            resultado -= 1 / i
        else:  # Si el índice es impar, se suma
            resultado += 1 / i
    return resultado

n = int(input("Ingrese el valor de N: "))
resultado = calcular_serie(n)
print("El resultado de la serie es:", resultado)
