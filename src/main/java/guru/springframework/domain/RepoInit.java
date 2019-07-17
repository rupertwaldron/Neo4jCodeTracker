package guru.springframework.domain;

import guru.springframework.repositories.CodeOwnersRepository;
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
  private CodeOwnersRepository codeOwnersRepository;

  @Autowired
  public RepoInit(RepoRepository repoRepository, LibraryRepository libraryRepository, CodeOwnersRepository codeOwnersRepository) {
    this.libraryRepository = libraryRepository;
    this.repoRepository = repoRepository;
    this.codeOwnersRepository = codeOwnersRepository;
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

    codeOwnersRepository.deleteAll();
    InitialCodeOwners.createCodeOwners().forEach(owner -> {
      codeOwnersRepository.save(owner);
      log.info("CodeOwner saved : " + owner.getName());
    });

    repoRepository.getRepos(25).stream().forEach(repo -> log.info("Found :" + repo.getName()));

    repoRepository.createRelationship("partyProfiles", "extendedProfiles");
    repoRepository.createRelationship("jwt", "finIpc");
    repoRepository.createLibraryRelationship("mccLoader", "springBoot2");
    repoRepository.createLibraryRelationship("mccLoader", "neo4j");

    repoRepository.getLinkedRepo("partyProfiles").forEach(product -> log.info("Found partyProfiles match : " + product.getName()));
    repoRepository.getLinkedRepo("jwt").forEach(product -> log.info("Found jwt match : " + product.getName()));
    repoRepository.getLinkedLibrary("mccLoader").forEach(library -> log.info("Library match for mccLoader : " + library.getName()));

    libraryRepository.createRepoRelationship("partyProfiles", "neo4j");
    libraryRepository.createRepoRelationship("transMap", "springBoot2");
    libraryRepository.createRepoRelationship("finIpc", "springBoot2");
    libraryRepository.createRepoRelationship("transSearch", "springBoot2");
    libraryRepository.createRepoRelationship("partyProfiles", "springBoot2");
    libraryRepository.createLibraryRelationship("neo4j", "jackson");
    libraryRepository.getLinkedLibrary("jackson").forEach((library -> log.info("Lib match for jackson : " + library.getName())));

    codeOwnersRepository.createRepoRelationship("partyProfiles", "Seagulls");
    codeOwnersRepository.createRepoRelationship("extendedProfiles", "Seagulls");
    codeOwnersRepository.createLibraryRelationship("neo4j", "Hussars");
    codeOwnersRepository.createRepoRelationship("transMap", "Falcon");
    codeOwnersRepository.createRepoRelationship("finIpc", "Falcon");
    codeOwnersRepository.createRepoRelationship("transSearch", "Ultravox");
    codeOwnersRepository.getLinkedLibrary("Hussars").forEach((library -> log.info("Library match for Hussars : " + library.getName())));
    codeOwnersRepository.getLinkedRepo("Seagulls").forEach((repo -> log.info("Repo match for Seagulls : " + repo.getName())));
  }

}