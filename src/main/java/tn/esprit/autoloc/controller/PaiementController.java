package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/paiements")
@Tag(name = "Paiements", description = "Gestion des paiements liés aux contrats")
public class PaiementController {
    private final IPaiementService paiementService;

    @Operation(
            summary = "Lister tous les paiements",
            description = "Retourne la liste de tous les paiements enregistrés"
    )
    @ApiResponse(responseCode = "200", description = "Liste des paiements retournée avec succès")
    @GetMapping("/afficherToutesPaiements")
    public List<Paiement> getAllPaiements() {
        return paiementService.afficherToutesPaiements();
    }

    @Operation(
            summary = "Récupérer un paiement par id",
            description = "Retourne un paiement correspondant à l'identifiant fourni"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paiement trouvé"),
            @ApiResponse(responseCode = "404", description = "Paiement introuvable")
    })
    @GetMapping("/afficherPaiementById/{id}")
    public Paiement getPaiementById(
            @Parameter(description = "Identifiant du paiement") @PathVariable Long id) {
        return paiementService.afficherPaiementById(id);
    }

    @Operation(
            summary = "Ajouter un paiement",
            description = "Crée un paiement et le retourne avec son identifiant généré"
    )
    @ApiResponse(responseCode = "200", description = "Paiement créé avec succès")
    @PostMapping("/ajouterPaiement")
    public Paiement ajouterPaiement(@RequestBody Paiement paiement) {
        return paiementService.ajouterPaiement(paiement);
    }

    @Operation(
            summary = "Modifier un paiement",
            description = "Met à jour un paiement existant (l'identifiant doit être présent dans le corps de la requête)"
    )
    @ApiResponse(responseCode = "200", description = "Paiement modifié avec succès")
    @PutMapping("/modifierPaiement")
    public Paiement modifierPaiement(@RequestBody Paiement paiement) {
        return paiementService.modifierPaiement(paiement);
    }

    @Operation(
            summary = "Supprimer un paiement",
            description = "Supprime un paiement correspondant à l'identifiant fourni"
    )
    @ApiResponse(responseCode = "200", description = "Paiement supprimé avec succès")
    @DeleteMapping("/supprimerPaiement/{idPaiement}")
    public void supprimerPaiement(
            @Parameter(description = "Identifiant du paiement à supprimer") @PathVariable Long idPaiement) {
        paiementService.supprimerPaiement(idPaiement);
    }
}
