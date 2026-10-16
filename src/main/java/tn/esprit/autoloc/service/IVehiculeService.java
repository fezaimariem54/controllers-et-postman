package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;

public interface IVehiculeService {

    Vehicule addVehicule(Vehicule vehicule);

    List<Vehicule> addVehicules(List<Vehicule> vehicules);

    List<Vehicule> retrieveAllVehicules();

    Vehicule retrieveVehicule(Long id);

    Vehicule updateVehicule(Long id, Vehicule vehicule);

    void removeVehicule(Long id);
}
