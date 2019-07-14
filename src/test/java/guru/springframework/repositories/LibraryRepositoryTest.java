package guru.springframework.repositories;

import guru.springframework.domain.InitialLibraries;
import guru.springframework.domain.InitialRepos;
import guru.springframework.domain.Library;
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
public class LibraryRepositoryTest {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private RepoRepository repoRepository;

    @Before
    public void setUp() throws Exception {
        libraryRepository.deleteAll();
        repoRepository.deleteAll();

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

        libraryRepository.findAll().forEach(repo -> {
            assertNotNull(repo.getId());
        });

        Library newLibrary = libraryRepository.findByName("springBoot2");
        assertEquals("springBoot2", newLibrary.getName());
        assertEquals("spring.com", newLibrary.getRepoUrl());
    }

    @Test
    public void testLibraryRelationships() {
        libraryRepository.createRepoRelationship("partyProfiles", "neo4j");
        libraryRepository.createLibraryRelationship("neo4j", "jackson");
        Collection<Library> newLibrary = libraryRepository.getLinkedLibrary("jackson");
        assertEquals(newLibrary.stream().findFirst().get().getName(), "neo4j");
    }
}