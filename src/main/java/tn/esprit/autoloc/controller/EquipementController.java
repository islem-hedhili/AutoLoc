package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/equipements")
@Tag(name = "Équipements", description = "Gestion des équipements des véhicules")
public class EquipementController {
    private final IEquipementService equipementService;

    @Operation(
            summary = "Lister tous les équipements",
            description = "Retourne la liste de tous les équipements enregistrés"
    )
    @ApiResponse(responseCode = "200", description = "Liste des équipements retournée avec succès")
    @GetMapping("/afficherToutesEquipements")
    public List<Equipement> getAllEquipements() {
        return equipementService.afficherToutesEquipements();
    }

    @Operation(
            summary = "Récupérer un équipement par id",
            description = "Retourne un équipement correspondant à l'identifiant fourni"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Équipement trouvé"),
            @ApiResponse(responseCode = "404", description = "Équipement introuvable")
    })
    @GetMapping("/afficherEquipementById/{id}")
    public Equipement getEquipementById(
            @Parameter(description = "Identifiant de l'équipement") @PathVariable Long id) {
        return equipementService.afficherEquipementById(id);
    }

    @Operation(
            summary = "Ajouter un équipement",
            description = "Crée un équipement et le retourne avec son identifiant généré"
    )
    @ApiResponse(responseCode = "200", description = "Équipement créé avec succès")
    @PostMapping("/ajouterEquipement")
    public Equipement ajouterEquipement(@RequestBody Equipement equipement) {
        return equipementService.ajouterEquipement(equipement);
    }

    @Operation(
            summary = "Modifier un équipement",
            description = "Met à jour un équipement existant (l'identifiant doit être présent dans le corps de la requête)"
    )
    @ApiResponse(responseCode = "200", description = "Équipement modifié avec succès")
    @PutMapping("/modifierEquipement")
    public Equipement modifierEquipement(@RequestBody Equipement equipement) {
        return equipementService.modifierEquipement(equipement);
    }

    @Operation(
            summary = "Supprimer un équipement",
            description = "Supprime un équipement correspondant à l'identifiant fourni"
    )
    @ApiResponse(responseCode = "200", description = "Équipement supprimé avec succès")
    @DeleteMapping("/supprimerEquipement/{idEquipement}")
    public void supprimerEquipement(
            @Parameter(description = "Identifiant de l'équipement à supprimer") @PathVariable Long idEquipement) {
        equipementService.supprimerEquipement(idEquipement);
    }
}
