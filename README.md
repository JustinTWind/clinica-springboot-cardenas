# clinica-springboot-cardenas

# 🏥 Sistema de Gestión Clínica Médica - ClinicaSpringBootCardenas

## 📋 Descripción del Proyecto

Proyecto desarrollado como parcial práctico de **Programación Backend I** utilizando **Spring Boot**, **JPA/Hibernate** y **MySQL**. El sistema modela el dominio de una clínica médica implementando relaciones uno a uno (1:1) entre entidades para la gestión de pacientes, médicos, historias clínicas y tarjetas profesionales.

---

## 🎯 Objetivos del Proyecto

- ✅ Implementar conexión a base de datos MySQL
- ✅ Crear entidades JPA con mínimo 10 atributos cada una
- ✅ Establecer relaciones 1:1 entre entidades
- ✅ Aplicar correctamente anotaciones JPA
- ✅ Configurar tipos de datos apropiados para cada campo

---

## 🛠️ Tecnologías Utilizadas

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| Java | 25 | Lenguaje de programación |
| Spring Boot | 3.5.6 | Framework principal |
| Spring Data JPA | 3.5.6 | Persistencia de datos |
| Hibernate | (incluido) | Implementación JPA |
| MySQL | 8.0+ | Base de datos relacional |
| Maven | 3.8+ | Gestión de dependencias |
| Lombok | 1.18.42 | Reducción de código boilerplate |
| H2 Database | (runtime) | Base de datos en memoria para pruebas |

---

## 📁 Estructura del Proyecto

```
ClinicaSpringBootCardenas/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── ClinicaSpringBootCardenas/
│   │   │               ├── modelos/
│   │   │               │   ├── Paciente.java
│   │   │               │   ├── HistoriaClinica.java
│   │   │               │   ├── Medico.java
│   │   │               │   └── TarjetaProfesional.java
│   │   │               │
│   │   │               └── ClinicaSpringBootCardenasApplication.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   │
│   └── test/
│
├── target/
├── pom.xml
├── .gitignore
└── README.md
```

---

## 🗄️ Modelo de Datos

### Diagrama de Relaciones

```
┌─────────────┐               ┌──────────────────┐
│  Paciente   │──────1:1──────│ HistoriaClinica  │
└─────────────┘               └──────────────────┘
       
┌─────────────┐               ┌──────────────────────┐
│   Medico    │──────1:1──────│ TarjetaProfesional   │
└─────────────┘               └──────────────────────┘
```

---

### 📋 Entidades Implementadas

#### 1️⃣ **Paciente** (`pacientes`)
Representa a los pacientes de la clínica con información personal y de contacto.

**Atributos (10):**
- `id` - Integer (PK, Auto-increment)
- `nombreCompleto` - String(100) NOT NULL
- `documentoIdentidad` - Integer UNIQUE NOT NULL
- `fechaNacimiento` - LocalDate NOT NULL
- `sexo` - String(20) NOT NULL
- `direccion` - String(100) NOT NULL
- `telefono` - String(15) NOT NULL
- `correoElectronico` - String(100) UNIQUE NULL
- `estadoCivil` - String(30) NULL
- `ocupacion` - String(100) NULL

**Relaciones:**
- `@OneToOne` con `HistoriaClinica` (Dueño de la relación)
- FK: `historia_clinica_id`

---

#### 2️⃣ **HistoriaClinica** (`historiasClinicas`)
Almacena el historial médico completo de cada paciente.

**Atributos (10):**
- `id` - Integer (PK, Auto-increment)
- `grupoSanguineo` - String(10) NOT NULL
- `alergias` - TEXT NULL
- `enfermedadesPrevias` - TEXT NULL
- `medicamentosActuales` - TEXT NULL
- `antecedentesFamiliares` - TEXT NULL
- `fechaUltimaConsulta` - LocalDate NULL
- `estadoActual` - String(50) NOT NULL
- `motivoConsulta` - TEXT NOT NULL
- `recomendaciones` - TEXT NULL

**Relaciones:**
- `@OneToOne(mappedBy = "historiaClinica")` con `Paciente`

---

#### 3️⃣ **Medico** (`medicos`)
Representa a los médicos que trabajan en la clínica.

**Atributos (10):**
- `id` - Integer (PK, Auto-increment)
- `nombreCompleto` - String(100) NOT NULL
- `especialidad` - String(50) NOT NULL
- `correo` - String(100) UNIQUE NOT NULL
- `telefono` - String(15) NOT NULL
- `antiguedad` - Integer NOT NULL
- `clinica` - String(100) NOT NULL
- `fechaNacimiento` - LocalDate NOT NULL
- `direccion` - String(200) NOT NULL
- `idiomaPrincipal` - String(30) NOT NULL

**Relaciones:**
- `@OneToOne` con `TarjetaProfesional` (Dueño de la relación)
- FK: `tarjeta_profesional_id`

---

#### 4️⃣ **TarjetaProfesional** (`tarjetaProfesional`)
Registra la información de la tarjeta profesional de cada médico.

**Atributos (10):**
- `id` - Integer (PK, Auto-increment)
- `numeroTarjeta` - String(30) UNIQUE NOT NULL
- `nombreTitular` - String(100) NOT NULL
- `fechaExpedicion` - LocalDate NOT NULL
- `fechaVencimiento` - LocalDate NULL
- `documentoIdentidad` - Integer NOT NULL
- `profesion` - String(100) NOT NULL
- `especialidad` - String(100) NULL
- `entidad` - String(150) NOT NULL
- `estado` - Boolean NOT NULL

**Relaciones:**
- `@OneToOne(mappedBy = "tarjetaProfesional")` con `Medico`

---

## ⚙️ Configuración

### Base de Datos (application.properties)

```properties
spring.application.name=ClinicaSpringBootCardenas
spring.datasource.url=jdbc:mysql://localhost:3306/clinicaspringbootacardenas
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create
```

**Nota:** `ddl-auto=create` recreará las tablas cada vez que se inicie la aplicación. Para producción usar `update` o `validate`.

---

## 🔧 Dependencias Maven (pom.xml)

```xml
<dependencies>
    <!-- Spring Boot Starters -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Base de Datos -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    
    <!-- Herramientas -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.42</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    
    <!-- Testing -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

---

## 🚀 Instalación y Ejecución

### Prerrequisitos
- ☕ JDK 17 o superior
- 🗄️ MySQL 8.0+ instalado y corriendo
- 📦 Maven 3.8+
- 💻 IDE (IntelliJ IDEA, Eclipse, VS Code)

### Pasos de Instalación

1. **Clonar el repositorio**
```bash
git clone https://github.com/tu-usuario/ClinicaSpringBootCardenas.git
cd ClinicaSpringBootCardenas
```

2. **Crear la base de datos en MySQL o en XAMPP**
```sql
CREATE DATABASE clinicaspringbootacardenas;
```

3. **Configurar credenciales**
Editar `src/main/resources/application.properties` con tus credenciales de MySQL:
```properties
spring.datasource.username=root
spring.datasource.password=
```

4. **Compilar el proyecto desde el IDE**
`ClinicaSpringBootCardenasApplication.java`


O desde tu IDE: Run 

6. **Verificar**
La aplicación se iniciará en: `http://localhost:8080`

---

## 📊 Características Técnicas Implementadas

### ✅ Anotaciones JPA Utilizadas

- `@Entity` - Marca las clases como entidades JPA
- `@Table` - Define el nombre de la tabla en BD
- `@Id` - Marca el identificador único
- `@GeneratedValue(strategy = GenerationType.IDENTITY)` - Auto-incremento
- `@Column` - Configuración de columnas (length, nullable, unique)
- `@OneToOne` - Relación uno a uno
- `@JoinColumn` - Define la columna de llave foránea
- `mappedBy` - Lado inverso de la relación

### ✅ Lombok Annotations

- `@Data` - Genera getters, setters, toString, equals, hashCode
- `@AllArgsConstructor` - Constructor con todos los parámetros
- `@NoArgsConstructor` - Constructor sin parámetros

### ✅ Tipos de Datos Utilizados

- `Integer` - IDs y números enteros
- `String` - Textos con longitud variable
- `LocalDate` - Fechas (Java 8+)
- `Boolean` - Valores verdadero/falso
- `TEXT` - Textos largos sin límite específico

---

## 📝 Decisiones de Diseño

### Relaciones 1:1

**Paciente ↔ HistoriaClinica**
- **Dueño:** `Paciente` (tiene la FK)
- **Justificación:** Un paciente tiene exactamente una historia clínica y viceversa

**Medico ↔ TarjetaProfesional**
- **Dueño:** `Medico` (tiene la FK)
- **Justificación:** Un médico tiene exactamente una tarjeta profesional y viceversa

---

## 🎓 Conceptos Aprendidos

- ✅ Modelado de entidades con JPA
- ✅ Relaciones uno a uno bidireccionales
- ✅ Configuración de Spring Boot con MySQL
- ✅ Uso de Lombok para código limpio
- ✅ Anotaciones de validación y restricciones
- ✅ Gestión de dependencias con Maven

