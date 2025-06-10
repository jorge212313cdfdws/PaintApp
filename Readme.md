# 🖌️ PaintApp – Java MVC Drawing Application

Aplicación de escritorio hecha en Java que permite dibujar figuras geométricas básicas sobre un lienzo, guardar y recuperar figuras desde una base de datos SQLite, y exportar el dibujo completo como un archivo SVG.

---

## 🎯 Características

- Dibujar: punto, línea, circunferencia, polígono regular, polígono irregular
- Selector de color de borde y relleno
- Guardado de figuras en base de datos SQLite
- Recuperación de figuras desde la base de datos
- Exportación a archivo SVG
- Patrón de diseño Modelo–Vista–Controlador (MVC)
- Código estructurado, reutilizable y bien comentado

---

## 🧱 Estructura del proyecto

PaintApp
├── controller/
│   └── PaintController.java
├── db/
│   ├── ConexionBD.java
│   ├── FiguraDAO.java
│   └── SVGDAO.java
├── model/
│   ├── Figura.java
│   ├── Punto.java
│   ├── Linea.java
│   ├── Circulo.java
│   ├── PoligonoRegular.java
│   └── PoligonoIrregular.java
├── view/
│   ├── Lienzo.java
│   └── VentanaPrincipal.java
├── paintapp/
│   └── PaintApp.java
├── paint.db
└── README.md

---

## 📷 Capturas de pantalla

(Añade tus imágenes en una carpeta llamada `capturas/` y enlázalas así)

![Pantalla inicial](capturas/pantalla-inicial.png)
![Figuras dibujadas](capturas/figuras.png)
![SVG generado](capturas/svg.png)

---

## 🔌 Requisitos

- Java 17 (recomendado)
- SQLite JDBC Driver (por ejemplo: `sqlite-jdbc-3.36.0.3.jar`)
- NetBeans, IntelliJ o Visual Studio Code
- Git (para clonarlo o subirlo)

---

## 🚀 Ejecución

Compilar y ejecutar desde la clase principal:

SwingUtilities.invokeLater(() -> new VentanaPrincipal());

Puedes ejecutar `PaintApp.java` directamente si es tu clase con `main`.

---

## 🗃️ Base de datos

- Archivo: `paint.db`
- Tabla: `figura`
- Campos:
  - id (INTEGER, autoincremental)
  - tipo (TEXT)
  - datos (TEXT, serialización)
  - colorBorde (TEXT en formato RGB)
  - colorRelleno (TEXT en formato RGB o "none")

Las figuras se guardan y recuperan usando `FiguraDAO`.

---

## 📤 Exportación SVG

La aplicación exporta automáticamente un archivo llamado `exportado.svg` al pulsar el botón correspondiente.

Ejemplo de contenido SVG generado:

<svg xmlns="http://www.w3.org/2000/svg" width="600" height="400">
  <circle cx="100" cy="100" r="50" stroke="rgb(0,0,0)" fill="rgb(255,0,0)" />
</svg>

---

## 🧠 Aprendizajes

Este proyecto fue desarrollado como parte de una actividad práctica de programación estructurada en Java.  
Se trabajó con:
- Programación orientada a objetos
- Interfaces gráficas (Swing)
- Acceso a base de datos con JDBC
- Exportación de gráficos a SVG
- Estructura de software con el patrón MVC

---

## 👨‍💻 Autor

**Jorge de la Parra Donaire**  
📅 2024 – Proyecto académico MVC Java + SQLite + SVG
