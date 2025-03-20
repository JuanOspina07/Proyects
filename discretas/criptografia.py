from Crypto.Cipher import AES
from Crypto.Util.Padding import pad, unpad
import base64



# Función para cifrar un mensaje con AES
def encrypt_message(message, key):
    cipher = AES.new(key, AES.MODE_CBC)
    ct_bytes = cipher.encrypt(pad(message.encode(), AES.block_size))
    iv = base64.b64encode(cipher.iv).decode('utf-8')
    ct = base64.b64encode(ct_bytes).decode('utf-8')
    return iv, ct

# Función para descifrar un mensaje cifrado con AES
def decrypt_message(iv, ciphertext, key):
    iv = base64.b64decode(iv)
    ciphertext = base64.b64decode(ciphertext)
    cipher = AES.new(key, AES.MODE_CBC, iv)
    pt = unpad(cipher.decrypt(ciphertext), AES.block_size)
    return pt.decode('utf-8')

# Mensaje a cifrar
original_message = str(input("Write the message to be crypted:"))

# Generar una clave
key=  b'Ronaldo07Thebest' 

# Cifrar el mensaje
iv, encrypted = encrypt_message(original_message, key)
print("Encrypted message:", encrypted)

# Descifrar el mensaje

decipher=str(input("Write the message you want to decrypt:"))
decrypted_message= decrypt_message(iv, decipher, key)
print("Decrypted message:", decrypted_message)








