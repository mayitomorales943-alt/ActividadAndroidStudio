package com.sena.actividadandroidstudio.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sena.actividadandroidstudio.R;
import com.sena.actividadandroidstudio.data.DatabaseHelper;
import com.sena.actividadandroidstudio.data.UserRepository;
import com.sena.actividadandroidstudio.domain.AuthService;
import com.sena.actividadandroidstudio.model.User;

public class MainActivity extends Activity {
    private LinearLayout loginView;
    private LinearLayout registerView;
    private LinearLayout dashboardView;
    private EditText loginEmailInput;
    private EditText loginPasswordInput;
    private EditText registerNameInput;
    private EditText registerEmailInput;
    private EditText registerPasswordInput;
    private TextView loginStatusText;
    private TextView registerStatusText;
    private TextView welcomeText;
    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        UserRepository userRepository = new UserRepository(databaseHelper);
        authService = new AuthService(userRepository);

        loginView = findViewById(R.id.loginView);
        registerView = findViewById(R.id.registerView);
        dashboardView = findViewById(R.id.dashboardView);
        loginEmailInput = findViewById(R.id.loginEmailInput);
        loginPasswordInput = findViewById(R.id.loginPasswordInput);
        registerNameInput = findViewById(R.id.registerNameInput);
        registerEmailInput = findViewById(R.id.registerEmailInput);
        registerPasswordInput = findViewById(R.id.registerPasswordInput);
        loginStatusText = findViewById(R.id.loginStatusText);
        registerStatusText = findViewById(R.id.registerStatusText);
        welcomeText = findViewById(R.id.welcomeText);

        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);
        Button showRegisterButton = findViewById(R.id.showRegisterButton);
        Button showLoginButton = findViewById(R.id.showLoginButton);
        Button logoutButton = findViewById(R.id.logoutButton);

        loginButton.setOnClickListener(view -> login());
        registerButton.setOnClickListener(view -> register());
        showRegisterButton.setOnClickListener(view -> showRegister());
        showLoginButton.setOnClickListener(view -> showLogin());
        logoutButton.setOnClickListener(view -> showLogin());

        showLogin();
    }

    private void register() {
        try {
            authService.register(
                    registerNameInput.getText().toString(),
                    registerEmailInput.getText().toString(),
                    registerPasswordInput.getText().toString()
            );
            loginEmailInput.setText(registerEmailInput.getText().toString());
            registerNameInput.setText("");
            registerEmailInput.setText("");
            registerPasswordInput.setText("");
            showLogin();
            loginStatusText.setText("Usuario registrado. Ya puedes iniciar sesion.");
        } catch (IllegalArgumentException exception) {
            registerStatusText.setText(exception.getMessage());
        }
    }

    private void login() {
        try {
            User user = authService.login(
                    loginEmailInput.getText().toString(),
                    loginPasswordInput.getText().toString()
            );
            loginPasswordInput.setText("");
            showDashboard(user);
        } catch (IllegalArgumentException exception) {
            loginStatusText.setText(exception.getMessage());
        }
    }

    private void showLogin() {
        loginView.setVisibility(View.VISIBLE);
        registerView.setVisibility(View.GONE);
        dashboardView.setVisibility(View.GONE);
        registerStatusText.setText("");
    }

    private void showRegister() {
        loginView.setVisibility(View.GONE);
        registerView.setVisibility(View.VISIBLE);
        dashboardView.setVisibility(View.GONE);
        loginStatusText.setText("");
    }

    private void showDashboard(User user) {
        loginView.setVisibility(View.GONE);
        registerView.setVisibility(View.GONE);
        dashboardView.setVisibility(View.VISIBLE);
        welcomeText.setText("Bienvenido, " + user.getName());
    }
}
