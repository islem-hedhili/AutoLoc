package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/agences")
@Tag(name = "Agences", description = "Gestion des agences de location")
public class AgenceController {
    private final IAgenceService agenceService;

    @Operation(
            summary = "Lister tous les agences",
            description = "Retourne la liste de tous les agences enregistrés"
    )
    @ApiResponse(responseCode = "200", description = "Liste des agences retournée avec succès")
    @GetMapping("/afficherToutesAgences")
    public List<Agence> getAllAgences() {
        return agenceService.afficherToutesAgences();
    }

    @Operation(
            summary = "Récupérer une agence par id",
            description = "Retourne une agence correspondant à l'identifiant fourni"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agence trouvée"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    @GetMapping("/afficherAgenceById/{id}")
    public Agence getAgenceById(
            @Parameter(description = "Identifiant de l'agence") @PathVariable Long id) {
        return agenceService.afficherAgenceById(id);
    }

    @Operation(
            summary = "Ajouter une agence",
            description = "Crée une agence et la retourne avec son identifiant généré"
    )
    @ApiResponse(responseCode = "200", description = "Agence créée avec succès")
    @PostMapping("/ajouterAgence")
    public Agence ajouterAgence(@RequestBody Agence agence) {
        return agenceService.ajouterAgence(agence);
    }

    @Operation(
            summary = "Modifier une agence",
            description = "Met à jour une agence existante (l'identifiant doit être présent dans le corps de la requête)"
    )
    @ApiResponse(responseCode = "200", description = "Agence modifiée avec succès")
    @PutMapping("/modifierAgence")
    public Agence modifierAgence(@RequestBody Agence agence) {
        return agenceService.modifierAgence(agence);
    }

    @Operation(
            summary = "Supprimer une agence",
            description = "Supprime une agence correspondant à l'identifiant fourni"
    )
    @ApiResponse(responseCode = "200", description = "Agence supprimée avec succès")
    @DeleteMapping("/supprimerAgence/{idAgence}")
    public void supprimerAgence(
            @Parameter(description = "Identifiant de l'agence à supprimer") @PathVariable Long idAgence) {
        agenceService.supprimerAgence(idAgence);
    }
}
