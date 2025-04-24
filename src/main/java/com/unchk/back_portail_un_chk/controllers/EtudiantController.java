package com.unchk.back_portail_un_chk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.unchk.back_portail_un_chk.models.Etudiant;
import com.unchk.back_portail_un_chk.repositories.EtudiantRepository;

@RestController
@RequestMapping("/api")
public class EtudiantController {
    @Autowired
    private EtudiantRepository etudiantRepository;

    // Récupérer tous les étudiants
    @GetMapping("/etudiants")
    public ResponseEntity<Map<String, Object>> getAllEtudiants() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Liste des étudiants récupérée avec succès.");
        response.put("data", etudiants);
        return ResponseEntity.ok(response);
    }

    // Récupérer un étudiant par ID
    @GetMapping("/etudiants/{id}")
    public ResponseEntity<Map<String, Object>> getEtudiantById(@PathVariable Long id) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (etudiant.isPresent()) {
            response.put("message", "Étudiant trouvé avec succès.");
            response.put("data", etudiant.get());
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Étudiant non trouvé.");
            return ResponseEntity.status(404).body(response);
        }
    }

    // Créer un nouvel étudiant
    @PostMapping("/etudiants")
    public ResponseEntity<Map<String, Object>> createEtudiant(@RequestBody Etudiant etudiant) {
        Etudiant savedEtudiant = etudiantRepository.save(etudiant);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Étudiant créé avec succès.");
        response.put("data", savedEtudiant);
        return ResponseEntity.status(201).body(response);
    }

    // Mettre à jour un étudiant existant
    @PutMapping("/etudiants/{id}")
    public ResponseEntity<Map<String, Object>> updateEtudiant(@PathVariable Long id, @RequestBody Etudiant etudiantDetails) {
        System.out.println("Données reçues pour mise à jour : " + etudiantDetails);
        Optional<Etudiant> etudiantOptional = etudiantRepository.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (etudiantOptional.isPresent()) {
            Etudiant etudiant = etudiantOptional.get();
            etudiant.setNom(etudiantDetails.getNom());
            etudiant.setPrenom(etudiantDetails.getPrenom());
            // Ajoutez les autres champs ici
            Etudiant updatedEtudiant = etudiantRepository.save(etudiant);
            response.put("message", "Étudiant mis à jour avec succès.");
            response.put("data", updatedEtudiant);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Étudiant non trouvé.");
            return ResponseEntity.status(404).body(response);
        }
    }

    // Supprimer un étudiant
    @DeleteMapping("/etudiants/{id}")
    public ResponseEntity<Map<String, Object>> deleteEtudiant(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        if (etudiantRepository.existsById(id)) {
            etudiantRepository.deleteById(id);
            response.put("message", "Étudiant supprimé avec succès.");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Étudiant non trouvé.");
            return ResponseEntity.status(404).body(response);
        }
    }
}