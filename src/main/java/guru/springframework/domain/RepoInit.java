package guru.springframework.domain;

import guru.springframework.repositories.LibraryRepository;
import guru.springframework.repositories.RepoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Copyright (c)2019 DFS Services LLC
 * All rights reserved.
 *
 * @author rwaldro
 */
@Component
@Slf4j
public class RepoInit implements ApplicationRunner {
  private RepoRepository repoRepository;
  private LibraryRepository libraryRepository;

  @Autowired
  public RepoInit(RepoRepository repoRepository, LibraryRepository libraryRepository) {
    this.libraryRepository = libraryRepository;
    this.repoRepository = repoRepository;
  }

  @Override
  public void run(ApplicationArguments args) {
    repoRepository.deleteAll();
    InitialRepos.createRepos().forEach(repo -> {
      repoRepository.save(repo);
      log.info("Repo saved : " + repo.getName());
    });

    libraryRepository.deleteAll();
    InitialLibraries.createLibraries().forEach(library -> {
      libraryRepository.save(library);
      log.info("Library saved : " + library.getName());
    });

    repoRepository.getRepos(25).stream().forEach(repo -> log.info("Found :" + repo.getName()));

    repoRepository.createRelationship("partyProfiles", "extendedProfiles");
    repoRepository.createRelationship("jwt", "finIpc");

    repoRepository.getLinkedRepo("partyProfiles").forEach(product -> log.info("Found mccLoader match:" + product.getName()));

    repoRepository.getLinkedRepo("jwt").forEach(product -> log.info("Found jwt match:" + product.getName()));
  }

}
