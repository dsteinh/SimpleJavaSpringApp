package hr.dsteinh.edukacijskizadatak.service;

import hr.dsteinh.edukacijskizadatak.model.Rent;
import hr.dsteinh.edukacijskizadatak.repos.RentRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RentService {
    private final RentRepo rentRepo;

    public RentService(RentRepo rentRepo) {
        this.rentRepo = rentRepo;
    }

    public List<Rent> findAll() {
        return rentRepo.findAll();
    }

    public Rent save(Rent rent) {
        return rentRepo.save(rent);
    }

    public Optional<Rent> findById(Long id) {
        return rentRepo.findById(id);
    }

    public void deleteById(Long id) {
        rentRepo.deleteById(id);
    }
}
