import pygame
import sys
import random

# Inicializar Pygame
pygame.init()

# Configuración de la pantalla
WIDTH, HEIGHT = 1000, 700
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption('Hangman game')

# Colores
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
RED = (255, 0, 0)


# Fuentes
font = pygame.font.Font(None, 48)
small_font = pygame.font.Font(None, 36)

# Palabras para adivinar
WORDS = ['PYTHON', 'PROGRAMACION', 'VIDEOJUEGOS', 'DESARROLLO','CRISTIANO','FUTBOL','CRIPTOGRAFIA','DISCRETAS']

# Función para seleccionar una palabra aleatoria
def select_palabra():
    return random.choice(WORDS)

# Función para mostrar la palabra con letras adivinadas
def show_word(word, guessed_letters):
    result = ""
    for letter in word:
        if letter in guessed_letters:
            result += letter + ' '
        else:
            result += '_ '
    return result

# Variables del juego
secret_word= select_palabra()
guessed_letters = set()
attempts = 6

# Loop principal
running = True

while running:
    screen.fill(WHITE)
    mouse_pos = pygame.mouse.get_pos()

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
            pygame.quit()
            sys.exit()

        if event.type == pygame.KEYDOWN:
            if event.unicode.isalpha() and event.unicode.upper() not in guessed_letters :
                guessed_letters .add(event.unicode.upper())
                if event.unicode.upper() not in secret_word:
                    attempts -= 1

    word_shown = show_word(secret_word, guessed_letters )

    text_word = font.render(word_shown , True, BLACK)
    screen.blit(text_word, (400, 500))

    text_attempts = small_font.render(f"Remaining attempts: {attempts}", True, RED)
    screen.blit(text_attempts, (400, 600))

    # Dibujar la estructura del ahorcado
    if attempts < 6:
        pygame.draw.line(screen, BLACK, (400, 100), (400, 50), 4)
    if attempts < 5:
        pygame.draw.line(screen, BLACK, (400, 50), (250, 50), 4)
    if attempts < 4:
        pygame.draw.line(screen, BLACK, (250, 50), (250, 500), 4)
    if attempts < 3:
        pygame.draw.line(screen, BLACK, (220, 500), (280, 500), 4)
    if attempts < 2:
        pygame.draw.circle(screen, BLACK, (400, 140), 40, 4)
    if attempts < 1:
        pygame.draw.line(screen, BLACK, (400, 350), (400, 180), 4)
        pygame.draw.line(screen, BLACK, (400, 230), (320, 180), 4)
        pygame.draw.line(screen, BLACK, (400, 230), (480, 180), 4)
        pygame.draw.line(screen, BLACK, (400, 350), (320, 500), 4)
        pygame.draw.line(screen, BLACK, (400, 350), (490, 500), 4)

    # Verificar si se ganó o perdió
    if all(letter in guessed_letters  for letter in secret_word):
        texto_ganador = font.render("¡Won!", True, BLACK)
        screen.blit(texto_ganador, (500, 100))
    elif attempts == 0:
        texto_perdedor = font.render("¡You lost!", True, BLACK)
        screen.blit(texto_perdedor, (500, 100))
        texto_respuesta = font.render(f"The word was: {secret_word}", True, BLACK)
        screen.blit(texto_respuesta, (500, 150))

    pygame.display.flip()
