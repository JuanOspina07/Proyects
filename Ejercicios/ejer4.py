def sumar_arreglos(arr1, arr2):
    if len(arr1) != len(arr2):
        raise ValueError("Los arreglos deben tener el mismo tamaño")
    resultado = []
    for i in range(len(arr1)):
        resultado.append(arr1[i] + arr2[i])
    return resultado
arr1 = [1, 2, 3]
arr2 = [4, 5, 6]
print("La suma de los arreglos es:", sumar_arreglos(arr1, arr2))