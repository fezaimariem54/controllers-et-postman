package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;

import java.util.List;

public interface IEmployeService {

    Employe addEmploye(Employe employe);

    List<Employe> addEmployes(List<Employe> employes);

    List<Employe> retrieveAllEmployes();

    Employe retrieveEmploye(Long id);

    Employe updateEmploye(Long id, Employe employe);

    void removeEmploye(Long id);
}
