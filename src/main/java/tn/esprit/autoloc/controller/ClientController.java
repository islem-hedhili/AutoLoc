package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/clients")
@Tag(name = "Clients", description = "Gestion des clients")
public class ClientController {
    private final IClientService clientService;

    @Operation(
            summary = "Lister tous les clients",
            description = "Retourne la liste de tous les clients enregistrés"
    )
    @ApiResponse(responseCode = "200", description = "Liste des clients retournée avec succès")
    @GetMapping("/afficherToutesClients")
    public List<Client> getAllClients() {
        return clientService.afficherToutesClients();
    }

    @Operation(
            summary = "Récupérer un client par id",
            description = "Retourne un client correspondant à l'identifiant fourni"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client trouvé"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    @GetMapping("/afficherClientById/{id}")
    public Client getClientById(
            @Parameter(description = "Identifiant du client") @PathVariable Long id) {
        return clientService.afficherClientById(id);
    }

    @Operation(
            summary = "Ajouter un client",
            description = "Crée un client et le retourne avec son identifiant généré"
    )
    @ApiResponse(responseCode = "200", description = "Client créé avec succès")
    @PostMapping("/ajouterClient")
    public Client ajouterClient(@RequestBody Client client) {
        return clientService.ajouterClient(client);
    }

    @Operation(
            summary = "Modifier un client",
            description = "Met à jour un client existant (l'identifiant doit être présent dans le corps de la requête)"
    )
    @ApiResponse(responseCode = "200", description = "Client modifié avec succès")
    @PutMapping("/modifierClient")
    public Client modifierClient(@RequestBody Client client) {
        return clientService.modifierClient(client);
    }

    @Operation(
            summary = "Supprimer un client",
            description = "Supprime un client correspondant à l'identifiant fourni"
    )
    @ApiResponse(responseCode = "200", description = "Client supprimé avec succès")
    @DeleteMapping("/supprimerClient/{idClient}")
    public void supprimerClient(
            @Parameter(description = "Identifiant du client à supprimer") @PathVariable Long idClient) {
        clientService.supprimerClient(idClient);
    }
}
