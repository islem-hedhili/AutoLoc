package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;

public interface IMaintenanceService {
    Maintenance ajouterMaintenance(Maintenance maintenance);
    Maintenance modifierMaintenance(Maintenance maintenance);
    List<Maintenance> afficherToutesMaintenances();
    Maintenance afficherMaintenanceById(Long id);
    void supprimerMaintenance(Long id);
}
