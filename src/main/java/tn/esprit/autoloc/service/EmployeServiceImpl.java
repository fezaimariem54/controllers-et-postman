package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.EmployeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    private final EmployeRepository employeRepository;

    @Override
    @Transactional
    public Employe addEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    @Transactional
    public List<Employe> addEmployes(List<Employe> employes) {
        return employeRepository.saveAll(employes);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employe> retrieveAllEmployes() {
        return employeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Employe retrieveEmploye(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employe introuvable avec l'id " + id));
    }

    @Override
    @Transactional
    public Employe updateEmploye(Long id, Employe employe) {
        if (!employeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employe introuvable avec l'id " + id);
        }
        employe.setIdEmploye(id);
        return employeRepository.saveAndFlush(employe);
    }

    @Override
    @Transactional
    public void removeEmploye(Long id) {
        if (!employeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employe introuvable avec l'id " + id);
        }
        employeRepository.deleteById(id);
        employeRepository.flush();
    }
}
