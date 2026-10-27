package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.IReservationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reservations")
@Tag(name = "Réservations", description = "Gestion des réservations de véhicules")
public class ReservationController {
    private final IReservationService reservationService;

    @Operation(
            summary = "Lister tous les réservations",
            description = "Retourne la liste de tous les réservations enregistrés"
    )
    @ApiResponse(responseCode = "200", description = "Liste des réservations retournée avec succès")
    @GetMapping("/afficherToutesReservations")
    public List<Reservation> getAllReservations() {
        return reservationService.afficherToutesReservations();
    }

    @Operation(
            summary = "Récupérer une réservation par id",
            description = "Retourne une réservation correspondant à l'identifiant fourni"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Réservation trouvée"),
            @ApiResponse(responseCode = "404", description = "Réservation introuvable")
    })
    @GetMapping("/afficherReservationById/{id}")
    public Reservation getReservationById(
            @Parameter(description = "Identifiant de la réservation") @PathVariable Long id) {
        return reservationService.afficherReservationById(id);
    }

    @Operation(
            summary = "Ajouter une réservation",
            description = "Crée une réservation et la retourne avec son identifiant généré"
    )
    @ApiResponse(responseCode = "200", description = "Réservation créée avec succès")
    @PostMapping("/ajouterReservation")
    public Reservation ajouterReservation(@RequestBody Reservation reservation) {
        return reservationService.ajouterReservation(reservation);
    }

    @Operation(
            summary = "Modifier une réservation",
            description = "Met à jour une réservation existante (l'identifiant doit être présent dans le corps de la requête)"
    )
    @ApiResponse(responseCode = "200", description = "Réservation modifiée avec succès")
    @PutMapping("/modifierReservation")
    public Reservation modifierReservation(@RequestBody Reservation reservation) {
        return reservationService.modifierReservation(reservation);
    }

    @Operation(
            summary = "Supprimer une réservation",
            description = "Supprime une réservation correspondant à l'identifiant fourni"
    )
    @ApiResponse(responseCode = "200", description = "Réservation supprimée avec succès")
    @DeleteMapping("/supprimerReservation/{idReservation}")
    public void supprimerReservation(
            @Parameter(description = "Identifiant de la réservation à supprimer") @PathVariable Long idReservation) {
        reservationService.supprimerReservation(idReservation);
    }
}
