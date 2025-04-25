package com.unchk.back_portail_un_chk.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unchk.back_portail_un_chk.models.LoginRequest;
import com.unchk.back_portail_un_chk.models.User;
import com.unchk.back_portail_un_chk.repositories.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

        @Autowired
    private UserRepository userRepository; // Repository pour interagir avec la base de données

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody User user) {
        // Logique pour enregistrer l'utilisateur
        System.out.println("Données reçues : " + user);
        System.out.println("Email: " + user.getEmail());
        System.out.println("Password: " + user.getPassword());

        // Vérifiez si l'utilisateur existe déjà
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "L'utilisateur existe déjà");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Enregistrez l'utilisateur dans la base de données
        userRepository.save(user);

        // Réponse de succès
        Map<String, String> response = new HashMap<>();
        response.put("message", "Utilisateur enregistré avec succès");
        return ResponseEntity.ok(response);
    }

     @PostMapping("/forgot-password")
     public ResponseEntity<Map<String, String>> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        // Logique pour gérer la réinitialisation du mot de passe
        System.out.println("Email: " + email);
        
        // Réponse de succès
        Map<String, String> response = new HashMap<>();
        response.put("message", "Email de réinitialisation envoyé avec succès");
        return ResponseEntity.ok(response);
    }

     @PostMapping("/login")
     public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        // Logique pour authentifier l'utilisateur
        System.out.println("Email: " + loginRequest.getEmail());
        System.out.println("Password: " + loginRequest.getPassword());
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Connexion réussie");
        return ResponseEntity.ok(response);
    }
}