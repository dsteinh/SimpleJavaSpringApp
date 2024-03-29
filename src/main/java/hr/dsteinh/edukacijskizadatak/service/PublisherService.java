package hr.dsteinh.edukacijskizadatak.service;

import hr.dsteinh.edukacijskizadatak.model.Publisher;
import hr.dsteinh.edukacijskizadatak.repos.PublisherRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PublisherService {
    private final PublisherRepo publisherRepo;

    public PublisherService(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    public List<Publisher> findAll() {
        return publisherRepo.findAll();
    }

    public Publisher save(Publisher publisher) {
        return publisherRepo.save(publisher);
    }

    public Optional<Publisher> findById(Long id) {
        return publisherRepo.findById(id);
    }

    public void deleteById(long id) {
        publisherRepo.deleteById(id);
    }
}
