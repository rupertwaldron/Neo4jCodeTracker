package guru.springframework.converters;

import guru.springframework.commands.RepoForm;
import guru.springframework.domain.Repo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by jt on 1/10/17.
 */
@Component
public class RepoFormToRepo implements Converter<RepoForm, Repo> {

    @Override
    public Repo convert(RepoForm repoForm) {
        Repo repo = new Repo();
        if (repoForm.getId() != null  && !StringUtils.isEmpty(repoForm.getId())) {
            repo.setId(new Long(repoForm.getId()));
        }
        repo.setName(repoForm.getName());
        repo.setRepoUrl(repoForm.getRepoUrl());
        return repo;
    }
}
