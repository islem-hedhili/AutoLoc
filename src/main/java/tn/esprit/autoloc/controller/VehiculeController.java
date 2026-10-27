package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vehicules")
@Tag(name = "Véhicules", description = "Gestion du parc de véhicules")
public class VehiculeController {
    private final IVehiculeService vehiculeService;

    @Operation(
            summary = "Lister tous les véhicules",
            description = "Retourne la liste de tous les véhicules enregistrés"
    )
    @ApiResponse(responseCode = "200", description = "Liste des véhicules retournée avec succès")
    @GetMapping("/afficherToutesVehicules")
    public List<Vehicule> getAllVehicules() {
        return vehiculeService.afficherToutesVehicules();
    }

    @Operation(
            summary = "Récupérer un véhicule par id",
            description = "Retourne un véhicule correspondant à l'identifiant fourni"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Véhicule trouvé"),
            @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    })
    @GetMapping("/afficherVehiculeById/{id}")
    public Vehicule getVehiculeById(
            @Parameter(description = "Identifiant du véhicule") @PathVariable Long id) {
        return vehiculeService.afficherVehiculeById(id);
    }

    @Operation(
            summary = "Ajouter un véhicule",
            description = "Crée un véhicule et le retourne avec son identifiant généré"
    )
    @ApiResponse(responseCode = "200", description = "Véhicule créé avec succès")
    @PostMapping("/ajouterVehicule")
    public Vehicule ajouterVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.ajouterVehicule(vehicule);
    }

    @Operation(
            summary = "Modifier un véhicule",
            description = "Met à jour un véhicule existant (l'identifiant doit être présent dans le corps de la requête)"
    )
    @ApiResponse(responseCode = "200", description = "Véhicule modifié avec succès")
    @PutMapping("/modifierVehicule")
    public Vehicule modifierVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.modifierVehicule(vehicule);
    }

    @Operation(
            summary = "Supprimer un véhicule",
            description = "Supprime un véhicule correspondant à l'identifiant fourni"
    )
    @ApiResponse(responseCode = "200", description = "Véhicule supprimé avec succès")
    @DeleteMapping("/supprimerVehicule/{idVehicule}")
    public void supprimerVehicule(
            @Parameter(description = "Identifiant du véhicule à supprimer") @PathVariable Long idVehicule) {
        vehiculeService.supprimerVehicule(idVehicule);
    }
}
