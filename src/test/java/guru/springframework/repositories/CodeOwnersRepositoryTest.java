package guru.springframework.repositories;

import guru.springframework.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CodeOwnersRepositoryTest {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private RepoRepository repoRepository;

    @Autowired
    private CodeOwnersRepository codeOwnersRepository;

    @Before
    public void setUp() throws Exception {
        libraryRepository.deleteAll();
        repoRepository.deleteAll();
        codeOwnersRepository.deleteAll();

        InitialCodeOwners.createCodeOwners().forEach(owner -> {
            codeOwnersRepository.save(owner);
            log.info("CodeOwner saved : " + owner.getName());
        });

        InitialLibraries.createLibraries().forEach(library -> {
            libraryRepository.save(library);
            log.info("Library saved : " + library.getName());
        });

        InitialRepos.createRepos().forEach(product -> {
            repoRepository.save(product);
            log.info("Repo saved : " + product.getName());
        });

    }

    @Test
    public void testLibraryPersistence() {

        codeOwnersRepository.findAll().forEach(owner -> {
            assertNotNull(owner.getId());
        });

        CodeOwner newCodeOwner = codeOwnersRepository.findByName("Falcon");
        assertEquals("Falcon", newCodeOwner.getName());
    }

    @Test
    public void testLibraryRelationships() {
        codeOwnersRepository.createRepoRelationship("partyProfiles", "Seagulls");
        codeOwnersRepository.createLibraryRelationship("neo4j", "Hussars");
        Collection<Library> newLibrary = codeOwnersRepository.getLinkedLibrary("Hussars");
        assertEquals(newLibrary.stream().findFirst().get().getName(), "neo4j");
    }
}