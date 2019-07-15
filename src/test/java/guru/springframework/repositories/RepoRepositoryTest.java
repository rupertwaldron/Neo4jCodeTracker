package guru.springframework.repositories;

import guru.springframework.domain.InitialRepos;
import guru.springframework.domain.Repo;
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
public class RepoRepositoryTest {

    @Autowired
    private RepoRepository repoRepository;

    @Before
    public void setUp() throws Exception {
        repoRepository.deleteAll();
        InitialRepos.createRepos().forEach(product -> {
            repoRepository.save(product);
            log.info("Repo saved : " + product.getName());
        });
    }

    @Test
    public void testPersistence() {

        repoRepository.findAll().forEach(repo -> {
            assertNotNull(repo.getId());
        });

        Repo newRepo = repoRepository.findByName("mccLoader");
        assertEquals("mccLoader", newRepo.getName());
        assertEquals("dfs.com", newRepo.getRepoUrl());
    }

    @Test
    public void testRelationships() {
        repoRepository.createRelationship("partyProfiles", "extendedProfiles");
        repoRepository.createRelationship("jwt", "finIpc");
        Collection<Repo> newRepo = repoRepository.getLinkedRepo("jwt");
        assertEquals(newRepo.stream().findFirst().get().getName(), "finIpc");
    }
}