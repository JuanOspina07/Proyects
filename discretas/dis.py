def sumandos(numeros,suma):
    conjuntos=set()
    for a in numeros:
        diferencia=suma-a
        if diferencia in conjuntos:
            return (a,diferencia)
        else: 
            conjuntos.add (a)
    return (0,0)    
sumandos    