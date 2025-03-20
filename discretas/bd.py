from faker import Faker
import random
equipo=0
fake = Faker('es_ES')


for i in range(1,276):
    primer_nombre = fake.first_name()
    segundo_nombre = fake.first_name() if random.choice([True, False]) else None
    primer_apellido = fake.last_name()
    segundo_apellido = fake.last_name() if random.choice([True, False]) else None
    telefono = random.randint(1000000, 9999999)
    fecha_nacimiento = fake.date_of_birth()
    id_documento = random.randint(1, 4)  # Suponiendo que tienes 3 tipos de documentos
    id_ciudad = random.randint(1, 85)  # Suponiendo que tienes 89 ciudades
    numero_documento = random.randint(1000000, 9999999)
    cargo=random.randint(1,5)
    sueldo=random.randint(1000000,2000000)
    horari=random.randint(1,3)
    emple= (i%30)+1
    talla=random.randint(1,7)
    cliente=(i%198)+1
    precio = random.choice([30000, 40000, 50000])
    mp=random.randint(1,4)
    d=random.randint(1,15)
    valor = random.choice([500000, 700000, 1000000])
    cantidad = random.choice([25, 35, 50])
    uniforme=(i%800)+1
    factura=(i%1300)+1

    
    

    





    print(f"INSERT INTO FacturaDetalle (IdFacturaDetalle, IdUniforme,IdFactura) VALUES ({i},{uniforme},{factura});")
