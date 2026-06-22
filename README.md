# Actividad Android Studio

Evidencia: GA8-220501096-AA2-EV02 - APK, desarrollar modulos movil segun requerimientos del proyecto.

Este directorio contiene una base organizada para la actividad:

- `proyecto-android/`: proyecto Android nativo para abrir en Android Studio.
- `documentacion/`: requisitos, arquitectura, herramientas y guia de configuracion.
- `entrega/`: carpeta reservada para guardar el APK, PDF y ZIP/RAR final.

## Herramientas solicitadas

- Java: necesario para ejecutar Android Studio y compilar proyectos Java/Kotlin.
- Android Studio: IDE oficial para crear aplicaciones Android, con SDK, emuladores y herramientas de depuracion.
- Firebase: servicio web de Google que se configura desde consola; puede usarse para autenticacion, base de datos, hosting u otros servicios.
- SQLite: base de datos local usada dentro del dispositivo para persistencia sin internet.

## Proyecto propuesto

La app de ejemplo se llama `ActividadAndroidStudio` y funciona como una plataforma academica basica:

- Registro de usuarios.
- Inicio de sesion.
- Usuarios guardados en SQLite.
- Dashboard principal para el aprendiz.
- Visualizacion de curso, actividad actual y modulos del sistema.

Incluye:

- Capa de presentacion: `MainActivity`.
- Capa de negocio: `AuthService`.
- Capa de acceso a datos/persistencia: `DatabaseHelper` y `UserRepository`.
- Modelo: `User`.
- Persistencia local con SQLite.

Abre la carpeta `proyecto-android` desde Android Studio y sincroniza Gradle. Si Android Studio solicita actualizar Gradle o el Android Gradle Plugin, acepta la version recomendada por el IDE.
