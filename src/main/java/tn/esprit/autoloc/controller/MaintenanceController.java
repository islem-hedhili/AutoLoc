package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/maintenances")
@Tag(name = "Maintenances", description = "Gestion des maintenances des véhicules")
public class MaintenanceController {
    private final IMaintenanceService maintenanceService;

    @Operation(
            summary = "Lister tous les maintenances",
            description = "Retourne la liste de tous les maintenances enregistrés"
    )
    @ApiResponse(responseCode = "200", description = "Liste des maintenances retournée avec succès")
    @GetMapping("/afficherToutesMaintenances")
    public List<Maintenance> getAllMaintenances() {
        return maintenanceService.afficherToutesMaintenances();
    }

    @Operation(
            summary = "Récupérer une maintenance par id",
            description = "Retourne une maintenance correspondant à l'identifiant fourni"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maintenance trouvée"),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable")
    })
    @GetMapping("/afficherMaintenanceById/{id}")
    public Maintenance getMaintenanceById(
            @Parameter(description = "Identifiant de la maintenance") @PathVariable Long id) {
        return maintenanceService.afficherMaintenanceById(id);
    }

    @Operation(
            summary = "Ajouter une maintenance",
            description = "Crée une maintenance et la retourne avec son identifiant généré"
    )
    @ApiResponse(responseCode = "200", description = "Maintenance créée avec succès")
    @PostMapping("/ajouterMaintenance")
    public Maintenance ajouterMaintenance(@RequestBody Maintenance maintenance) {
        return maintenanceService.ajouterMaintenance(maintenance);
    }

    @Operation(
            summary = "Modifier une maintenance",
            description = "Met à jour une maintenance existante (l'identifiant doit être présent dans le corps de la requête)"
    )
    @ApiResponse(responseCode = "200", description = "Maintenance modifiée avec succès")
    @PutMapping("/modifierMaintenance")
    public Maintenance modifierMaintenance(@RequestBody Maintenance maintenance) {
        return maintenanceService.modifierMaintenance(maintenance);
    }

    @Operation(
            summary = "Supprimer une maintenance",
            description = "Supprime une maintenance correspondant à l'identifiant fourni"
    )
    @ApiResponse(responseCode = "200", description = "Maintenance supprimée avec succès")
    @DeleteMapping("/supprimerMaintenance/{idMaintenance}")
    public void supprimerMaintenance(
            @Parameter(description = "Identifiant de la maintenance à supprimer") @PathVariable Long idMaintenance) {
        maintenanceService.supprimerMaintenance(idMaintenance);
    }
}
