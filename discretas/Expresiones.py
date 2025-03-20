import re
#Utilizamos una funcion para abrir el archivo txt guardado en la variable archive y luego lo leemos
def UploadFile(archive):
    try:
        with open(archive, 'r', encoding='utf-8') as file:
            reading = file.read()#aqui guardamos lo que se leyo en una variable llamada reading
        return reading           #para luego retornarla y pasarsela a la variable stories
    except FileNotFoundError:
        print(f"El archivo {archive} no se encontró.")
        return None
    
"""Creamos una funcion con el parametro stories donde contiene lo leido del txt
para luego con la ayuda de la biblioteca importada re creamos variables de las preguntas,
exclamaciones y abreviaciones, donde utilizamos el findall para encontrar las concidencias
donde armamos las expresiones y utilizamos el .+ para que tome los espacios en blanco y utilizamos
la variable stories que es donde tenemos guardado los textos en ingles y español
"""
def expressions(stories):
    questions=re.findall(r'([¿]*[a-zA-Z]*.+[?])',stories)
    exclamations = re.findall(r'([¡]*[A-Za-z].+[!])',stories)
    abbreviations = re.findall(r'([a-zA-Z]*\'[a-z]*)',stories)
    return questions, exclamations, abbreviations  #Despues lo retornamos para utilizarlos en otra funcion

#Esta funcion es para mostrar las pregunta, exclamaciones y abreviaciones encontradas
"""Utilizamos de parametros las variables retornadas de la otra funcion
luego separamos las preguntas,exclamaciones y abreviaciones con los print para luego utilizar un
for donde vamos a recorrer todo lo guardado en la variable questions para ir guardando
en la variable question e irla mostrando una por una y lo mismo hacemos con las exclamaciones y abreviaciones"""
def solution(questions, exclamations, abbreviations):
    print("Preguntas encontradas:")
    for question in questions:
        print(question)

    print("\nExclamaciones encontradas:")
    for exclamation in exclamations:
        print(exclamation)

    print("\nAbreviaciones encontradas:")
    for abbreviation in abbreviations:
        print(abbreviation)   
if __name__ == "__main__":
    archive = 'CuentosDiscretas.txt'
    stories = UploadFile(archive)
    #Verifica que stories no este vacio para luego proceder a ejecutar lo otro
    if stories:
        questions, exclamations, abbreviations = expressions(stories) #Guarda lo retornado de la funcion expressions en las variables questions,exclamations,abbreviations
        solution(questions, exclamations, abbreviations)#Para luego llamar a la funcion solution y darle de parametros las variables donde estan guardado lo de la funcion de expressions