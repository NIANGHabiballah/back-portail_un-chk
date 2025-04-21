package com.unchk.back_portail_un_chk.repositories;

import com.unchk.back_portail_un_chk.models.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire
}