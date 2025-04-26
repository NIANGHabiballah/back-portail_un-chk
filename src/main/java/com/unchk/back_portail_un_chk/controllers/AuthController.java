package com.unchk.back_portail_un_chk.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

     @PostMapping("/login")
     public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        // Rechercher l'utilisateur par email
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        
        // Logique pour authentifier l'utilisateur
        if (user.isPresent() && user.get().getPassword().equals(loginRequest.getPassword())) {
        
        // Si l'utilisateur existe et que le mot de passe correspond
        Map<String, String> response = new HashMap<>();
        response.put("message", "Connexion réussie");
        return ResponseEntity.ok(response);
    } else {
        // Si l'utilisateur n'existe pas ou que le mot de passe est incorrect
        Map<String, String> response = new HashMap<>();
        response.put("message", "Email ou mot de passe incorrect.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
}