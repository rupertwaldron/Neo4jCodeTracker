package guru.springframework.services;

import guru.springframework.commands.RepoForm;
import guru.springframework.domain.Repo;

import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
public interface RepoService {

    List<Repo> listAll();

    Repo getById(Long id);

    Repo saveOrUpdate(Repo repo);

    void delete(Long id);

    Repo saveOrUpdateProductForm(RepoForm repoForm);
}
