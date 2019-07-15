package guru.springframework.services;

import guru.springframework.commands.RepoForm;
import guru.springframework.domain.Library;

import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
public interface LibraryService {

    List<Library> listAll();

    Library getById(Long id);

    Library saveOrUpdate(Library library);

    void delete(Long id);

    Library saveOrUpdateProductForm(RepoForm repoForm);
}
