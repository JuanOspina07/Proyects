
states = ['q0', 'q1', 'q2', 'q3', 'q4', 'q5', 'q6']#Aqui definimos los estados
entries = ['Encender', 'L', 'S', 'XL', 'Preparar']#aqui definimos las entradas
initialState = 'q0' #Definimos en que estado va a estar
currentState = initialState #Definimos una variable que nos mostrar el estado actual en el que nos encontremos

""" Definición de las transiciones que tendremos de un estado a otro
 dependiendo de lo que ponga el usuario"""
transitions = {
    ('q0', 'Encender'): 'q1',
    ('q0', 'L'): 'q6',
    ('q0', 'S'): 'q6',
    ('q0', 'XL'): 'q6',
    ('q0', 'Preparar'): 'q6',
    ('q1', 'S'): 'q2',
    ('q1', 'L'): 'q3',
    ('q1', 'XL'): 'q4',
    ('q1', 'Preparar'): 'q6',
    ('q1', 'Encender'): 'q6',
    ('q2', 'Preparar'): 'q5',
    ('q2', 'L'): 'q6',
    ('q2', 'XL'): 'q6',
    ('q2', 'S'): 'q6',
    ('q2', 'Encender'): 'q6',
    ('q3', 'Preparar'): 'q5',
    ('q3', 'L'): 'q6',
    ('q3', 'XL'): 'q6',
    ('q3', 'S'): 'q6',
    ('q3', 'Encender'): 'q6',
    ('q4', 'Preparar'): 'q5',
    ('q4', 'L'): 'q6',
    ('q4', 'XL'): 'q6',
    ('q4', 'S'): 'q6',
    ('q4', 'Encender'): 'q6'
}

"""Función con el parametro entry que es lo que digita el usuario"""
def changeState(entry):
    global currentState #Definnimos que esta es una variable global ya que esta por fuera de las funciones y asi nos ayuda a poder acceder y modificarla
    i = (currentState, entry) #Aqui le damos los parametros a utilizar que seran el currentSatet y el entry para poder verificarlos en las transitions
    if i in transitions: #Aqui verificamos que lo guardado en la variable i si este guardado en la lista de transitions
        currentState = transitions[i] # Para luego actualizar currentState dependiendo a cual estado pase
        return f"Transición a {currentState}" #aqui muestra un mensaje de a que transicion hicimos y se retorna la variable currentState
    else:
        return f"Entrada no válida mira las opciones.\nIngrese los comandos para la máquina de café {entries}:"#Si lo guardado en la variable i no se encuentra en transicions mostrara un mensaje diciendo que no es valido y que vuelva a digitar la entries que si son aceptadas
       

# Función para verificar si el currentState es el estado de aceptación
def accepteState(currentState):
    return currentState == 'q5'

# Función para verificar si el estado actual es el estado de pozo
def pitState(currentState):
    return currentState == 'q6'

# Función principal
def main():
    global currentState
    print(f"Estado actual: {currentState}") #Nos muestra en que estado estamos
    print(f"Ingrese los comandos para la máquina de café {entries}:")#Nos muestra los comandos que acepta la maquina

    while not accepteState(currentState): #Aqui mientras no este en el accepteState entonces nos dejara ingresar un comando para la cafetera
        entry = input("Ingrese un comando: ").strip() #El comando ingresado se guardara en entry
        result = changeState(entry) # para luego llamar la funcion de changeState y darle el comando que acabamos de digitar y cuando se ejecute la funcion se guarde en result
        print(result) # Para luego mostrarla
        
        
        if pitState(currentState): # Verificar si la cafetera está en el pitState
            print("Se Fue al Infierno.\nEl programa se cerrará.") #si lo esta entonces nos mostrar un mensaje donde diga que nos fuimos al infierno 
            exit()# Y luego cerrara el programa

    
    if accepteState(currentState):# Verificar si la cafetera está en un estado de aceptación
        print("Café preparado.")# si lo esta entonces nos mostrara un mensaje diciendo que el cafe se preparo
        currentState="q0" # Luego la variable CurrentState vuelve al estado q0
        main() # y se vuelve a ejecutar el programa

if __name__ == "__main__":
    main() # aqui es donde desde un comienzo ejecutamos el programa
