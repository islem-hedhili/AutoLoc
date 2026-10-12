package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Equipement;

import java.util.List;

public interface IEquipementService {
    Equipement ajouterEquipement(Equipement equipement);
    Equipement modifierEquipement(Equipement equipement);
    List<Equipement> afficherToutesEquipements();
    Equipement afficherEquipementById(Long id);
    void supprimerEquipement(Long id);
}
