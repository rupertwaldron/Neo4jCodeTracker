package guru.springframework.converters;

import guru.springframework.commands.RepoForm;
import guru.springframework.domain.Library;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 1/10/17.
 */
@Component
public class LibraryToRepoForm implements Converter<Library, RepoForm> {
    @Override
    public RepoForm convert(Library library) {
        RepoForm repoForm = new RepoForm();
        repoForm.setId(library.getId());
        repoForm.setName(library.getName());
        repoForm.setRepoUrl(library.getRepoUrl());
        return repoForm;
    }
}
