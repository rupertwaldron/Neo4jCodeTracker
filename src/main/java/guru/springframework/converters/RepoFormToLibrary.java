package guru.springframework.converters;

import guru.springframework.commands.RepoForm;
import guru.springframework.domain.Library;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by jt on 1/10/17.
 */
@Component
public class RepoFormToLibrary implements Converter<RepoForm, Library> {

    @Override
    public Library convert(RepoForm repoForm) {
        Library library = new Library();
        if (repoForm.getId() != null  && !StringUtils.isEmpty(repoForm.getId())) {
            library.setId(repoForm.getId());
        }
        library.setName(repoForm.getName());
        library.setRepoUrl(repoForm.getRepoUrl());
        return library;
    }
}
