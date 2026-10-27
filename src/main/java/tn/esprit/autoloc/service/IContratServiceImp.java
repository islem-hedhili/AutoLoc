package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.ContratRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class IContratServiceImp implements IContratService {
    /*@Autowired
    private ContratRepository contratRepository;*/

    private final ContratRepository contratRepository;

    @Override
    public Contrat ajouterContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat modifierContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public List<Contrat> afficherToutesContrats() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat afficherContratById(Long id) {
        return contratRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerContrat(Long id) {
        contratRepository.deleteById(id);
    }
}
