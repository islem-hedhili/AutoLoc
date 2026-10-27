package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/employes")
@Tag(name = "Employés", description = "Gestion des employés")
public class EmployeController {
    private final IEmployeService employeService;

    @Operation(
            summary = "Lister tous les employés",
            description = "Retourne la liste de tous les employés enregistrés"
    )
    @ApiResponse(responseCode = "200", description = "Liste des employés retournée avec succès")
    @GetMapping("/afficherToutesEmployes")
    public List<Employe> getAllEmployes() {
        return employeService.afficherToutesEmployes();
    }

    @Operation(
            summary = "Récupérer un employé par id",
            description = "Retourne un employé correspondant à l'identifiant fourni"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employé trouvé"),
            @ApiResponse(responseCode = "404", description = "Employé introuvable")
    })
    @GetMapping("/afficherEmployeById/{id}")
    public Employe getEmployeById(
            @Parameter(description = "Identifiant de l'employé") @PathVariable Long id) {
        return employeService.afficherEmployeById(id);
    }

    @Operation(
            summary = "Ajouter un employé",
            description = "Crée un employé et le retourne avec son identifiant généré"
    )
    @ApiResponse(responseCode = "200", description = "Employé créé avec succès")
    @PostMapping("/ajouterEmploye")
    public Employe ajouterEmploye(@RequestBody Employe employe) {
        return employeService.ajouterEmploye(employe);
    }

    @Operation(
            summary = "Modifier un employé",
            description = "Met à jour un employé existant (l'identifiant doit être présent dans le corps de la requête)"
    )
    @ApiResponse(responseCode = "200", description = "Employé modifié avec succès")
    @PutMapping("/modifierEmploye")
    public Employe modifierEmploye(@RequestBody Employe employe) {
        return employeService.modifierEmploye(employe);
    }

    @Operation(
            summary = "Supprimer un employé",
            description = "Supprime un employé correspondant à l'identifiant fourni"
    )
    @ApiResponse(responseCode = "200", description = "Employé supprimé avec succès")
    @DeleteMapping("/supprimerEmploye/{idEmploye}")
    public void supprimerEmploye(
            @Parameter(description = "Identifiant de l'employé à supprimer") @PathVariable Long idEmploye) {
        employeService.supprimerEmploye(idEmploye);
    }
}
