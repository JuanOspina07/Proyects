import re

texto = "El Dr. Pérez y el Sr. López trabajan no en la misma empresa. Están en la av. principal. Por favor, envíe los documentos a la dir. general."

# Expresión regular para encontrar abreviaciones
regex = r"\b[A-Za-z]{1,3}\."

# Compilar la expresión regular
pattern = re.compile(regex)

# Encontrar todas las abreviaciones en el texto
abreviaciones = pattern.findall(texto)

# Mostrar las abreviaciones encontradas
for abreviacion in abreviaciones:
    print("Abreviación encontrada:", abreviacion)
