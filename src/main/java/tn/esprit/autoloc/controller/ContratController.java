package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/contrats")
@Tag(name = "Contrats", description = "Gestion des contrats de location")
public class ContratController {
    private final IContratService contratService;

    @Operation(
            summary = "Lister tous les contrats",
            description = "Retourne la liste de tous les contrats de location enregistrés"
    )
    @ApiResponse(responseCode = "200", description = "Liste des contrats retournée avec succès")
    @GetMapping("/afficherToutesContrats")
    public List<Contrat> getAllContrats() {
        return contratService.afficherToutesContrats();
    }

    @Operation(
            summary = "Récupérer un contrat par id",
            description = "Retourne le contrat correspondant à l'identifiant fourni"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contrat trouvé"),
            @ApiResponse(responseCode = "404", description = "Contrat introuvable")
    })
    @GetMapping("/afficherContratById/{id}")
    public Contrat getContratById(
            @Parameter(description = "Identifiant du contrat") @PathVariable Long id) {
        return contratService.afficherContratById(id);
    }

    @Operation(
            summary = "Ajouter un contrat",
            description = "Crée un nouveau contrat de location et le retourne avec son identifiant généré"
    )
    @ApiResponse(responseCode = "200", description = "Contrat créé avec succès")
    @PostMapping("/ajouterContrat")
    public Contrat ajouterContrat(@RequestBody Contrat contrat) {
        return contratService.ajouterContrat(contrat);
    }

    @Operation(
            summary = "Modifier un contrat",
            description = "Met à jour un contrat existant (l'identifiant doit être présent dans le corps de la requête)"
    )
    @ApiResponse(responseCode = "200", description = "Contrat modifié avec succès")
    @PutMapping("/modifierContrat")
    public Contrat modifierContrat(@RequestBody Contrat contrat) {
        return contratService.modifierContrat(contrat);
    }

    @Operation(
            summary = "Supprimer un contrat",
            description = "Supprime le contrat correspondant à l'identifiant fourni"
    )
    @ApiResponse(responseCode = "200", description = "Contrat supprimé avec succès")
    @DeleteMapping("/supprimerContrat/{idContrat}")
    public void supprimerContrat(
            @Parameter(description = "Identifiant du contrat à supprimer") @PathVariable Long idContrat) {
        contratService.supprimerContrat(idContrat);
    }
}
