package hr.dsteinh.edukacijskizadatak.service;

import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.Writer;
import hr.dsteinh.edukacijskizadatak.repos.WriterRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WriterService{
    private final WriterRepo writerRepo;

    public WriterService(WriterRepo writerRepo) {
        this.writerRepo = writerRepo;
    }
    public List<Writer> findAll() {
        return writerRepo.findAll();
    }

    public Writer save(Writer writer) {
        return writerRepo.save(writer);
    }
    public Optional<Writer> findById(Long id) {
        return writerRepo.findById(id);
    }
    public void deleteById(long id){
        writerRepo.deleteById(id);
    }
}
