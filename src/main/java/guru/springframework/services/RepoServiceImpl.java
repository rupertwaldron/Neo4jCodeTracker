package guru.springframework.services;

import guru.springframework.commands.RepoForm;
import guru.springframework.converters.RepoFormToRepo;
import guru.springframework.domain.Library;
import guru.springframework.domain.Repo;
import guru.springframework.repositories.RepoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
@Service
public class RepoServiceImpl implements RepoService {

    private RepoRepository repoRepository;
    private RepoFormToRepo repoFormToRepo;

    @Autowired
    public RepoServiceImpl(
            RepoRepository repoRepository,
            RepoFormToRepo repoFormToRepo) {
        this.repoRepository = repoRepository;
        this.repoFormToRepo = repoFormToRepo;
    }


    @Override
    public List<Repo> listAll() {
        List<Repo> repos = new ArrayList<>();
        repoRepository.findAll().forEach(repos::add); //fun with Java 8
        return repos;
    }

    @Override
    public Repo getById(Long id) {
        return repoRepository.findById(id).orElse(null);
    }

    @Override
    public Repo saveOrUpdate(Repo repo) {
        repoRepository.save(repo);
        return repo;
    }

    @Override
    public void delete(Long id) {
        repoRepository.deleteById(id);

    }

    @Override
    public Repo saveOrUpdateProductForm(RepoForm repoForm) {
        Repo savedRepo = saveOrUpdate(repoFormToRepo.convert(repoForm));

        System.out.println("Saved Repo Id: " + savedRepo.getId());
        return savedRepo;
    }

    @Override
    public Repo createLibraryLink(Repo repo, Library library) {
        repoRepository.createLibraryRelationship(repo.getName(), library.getName());
        return repo;
    }
}
