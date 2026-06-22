package com.sena.actividadandroidstudio.domain;

import com.sena.actividadandroidstudio.data.UserRepository;
import com.sena.actividadandroidstudio.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String name, String email, String password) {
        String cleanName = clean(name);
        String cleanEmail = clean(email).toLowerCase(Locale.ROOT);
        String cleanPassword = clean(password);

        if (cleanName.isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (!cleanEmail.contains("@")) {
            throw new IllegalArgumentException("Ingresa un correo valido.");
        }
        if (cleanPassword.length() < 4) {
            throw new IllegalArgumentException("La contrasena debe tener minimo 4 caracteres.");
        }
        if (userRepository.existsByEmail(cleanEmail)) {
            throw new IllegalArgumentException("Ya existe un usuario con ese correo.");
        }

        String createdAt = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
        long id = userRepository.save(cleanName, cleanEmail, cleanPassword, createdAt);
        if (id == -1) {
            throw new IllegalArgumentException("No fue posible registrar el usuario.");
        }
    }

    public User login(String email, String password) {
        String cleanEmail = clean(email).toLowerCase(Locale.ROOT);
        String cleanPassword = clean(password);

        if (cleanEmail.isEmpty() || cleanPassword.isEmpty()) {
            throw new IllegalArgumentException("Correo y contrasena son obligatorios.");
        }

        User user = userRepository.findByEmailAndPassword(cleanEmail, cleanPassword);
        if (user == null) {
            throw new IllegalArgumentException("Usuario o contrasena incorrectos.");
        }
        return user;
    }

    private String clean(String value) {
        return value == null ? "" : value.trim();
    }
}
