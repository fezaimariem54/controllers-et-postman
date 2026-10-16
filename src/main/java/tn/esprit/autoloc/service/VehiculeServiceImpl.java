package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceRepository agenceRepository;

    @Override
    @Transactional
    public Vehicule addVehicule(Vehicule vehicule) {
        rattacherAgence(vehicule);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    public List<Vehicule> addVehicules(List<Vehicule> vehicules) {
        vehicules.forEach(this::rattacherAgence);
        return vehiculeRepository.saveAll(vehicules);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> retrieveAllVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Vehicule retrieveVehicule(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicule introuvable avec l'id " + id));
    }

    @Override
    @Transactional
    public Vehicule updateVehicule(Long id, Vehicule vehicule) {
        if (!vehiculeRepository.existsById(id)) {
            throw new EntityNotFoundException("Vehicule introuvable avec l'id " + id);
        }
        rattacherAgence(vehicule);
        vehicule.setIdVehicule(id);
        return vehiculeRepository.saveAndFlush(vehicule);
    }

    @Override
    @Transactional
    public void removeVehicule(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new EntityNotFoundException("Vehicule introuvable avec l'id " + id);
        }
        vehiculeRepository.deleteById(id);
        vehiculeRepository.flush();
    }

    // Remplace l'agence reçue en JSON ({"idAgence": 1}) par l'agence réelle de la base
    private void rattacherAgence(Vehicule vehicule) {
        Agence agence = vehicule.getAgence();
        if (agence == null) {
            return;
        }
        Long idAgence = agence.getIdAgence();
        if (idAgence == null) {
            throw new IllegalArgumentException("L'agence du véhicule doit contenir un idAgence");
        }
        vehicule.setAgence(agenceRepository.findById(idAgence)
                .orElseThrow(() -> new EntityNotFoundException("Agence introuvable avec l'id " + idAgence)));
    }
}
