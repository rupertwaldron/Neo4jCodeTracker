package guru.springframework.services;

import guru.springframework.commands.RepoForm;
import guru.springframework.converters.RepoFormToLibrary;
import guru.springframework.domain.Library;
import guru.springframework.repositories.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
@Service
public class LibraryServiceImpl implements LibraryService {

    private LibraryRepository libraryRepository;
    private RepoFormToLibrary repoFormToLibrary;

    @Autowired
    public LibraryServiceImpl(
            LibraryRepository libraryRepository,
            RepoFormToLibrary repoFormToLibrary) {
        this.libraryRepository = libraryRepository;
        this.repoFormToLibrary = repoFormToLibrary;
    }


    @Override
    public List<Library> listAll() {
        List<Library> libraries = new ArrayList<>();
        libraryRepository.findAll().forEach(libraries::add);
        return libraries;
    }

    @Override
    public Library getById(Long id) {
        return libraryRepository.findById(id).orElse(null);
    }

    @Override
    public Library saveOrUpdate(Library library) {
        libraryRepository.save(library);
        return library;
    }

    @Override
    public void delete(Long id) {
        libraryRepository.deleteById(id);

    }

    @Override
    public Library saveOrUpdateProductForm(RepoForm repoForm) {
        Library savedLibrary = saveOrUpdate(repoFormToLibrary.convert(repoForm));

        System.out.println("Saved Repo Id: " + savedLibrary.getId());
        return savedLibrary;
    }
}
