package guru.springframework.converters;

import guru.springframework.commands.RepoForm;
import guru.springframework.domain.Repo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 1/10/17.
 */
@Component
public class RepoToRepoForm implements Converter<Repo, RepoForm> {
    @Override
    public RepoForm convert(Repo repo) {
        RepoForm repoForm = new RepoForm();
        repoForm.setId(repo.getId());
        repoForm.setName(repo.getName());
        repoForm.setRepoUrl(repo.getRepoUrl());
        return repoForm;
    }
}
