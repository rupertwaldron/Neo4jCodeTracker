package guru.springframework.repositories;

import guru.springframework.domain.CodeOwner;
import guru.springframework.domain.Library;
import guru.springframework.domain.Repo;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface CodeOwnersRepository extends Neo4jRepository<CodeOwner, Long> {

    CodeOwner findByName(String name);

    @Query("MATCH (p:CodeOwner) RETURN p LIMIT {limit}")
    Collection<CodeOwner> getLibraries(@Param("limit") int limit);

    @Query("MATCH (:CodeOwner {name: {owner}})-[:OWNS]-(l:Library) RETURN l")
    Collection<Library> getLinkedLibrary(@Param("owner") String owner);

    @Query("MATCH (:CodeOwner {name: {owner}})-[:OWNS]-(a:Repo) RETURN a")
    Collection<Repo> getLinkedRepo(@Param("owner") String owner);

    @Query("MATCH (a:Repo),(b:CodeOwner) WHERE a.name = {repo1} AND b.name = {owner2} CREATE (b)-[r:OWNS]->(a)")
    void createRepoRelationship(@Param("repo1") String repo1, @Param("owner2") String owner2);

    @Query("MATCH (a:Library),(b:CodeOwner) WHERE a.name = {lib1} AND b.name = {owner2} CREATE (b)-[r:OWNS]->(a)")
    void createLibraryRelationship(@Param("lib1") String lib1, @Param("owner2") String owner2);
}
