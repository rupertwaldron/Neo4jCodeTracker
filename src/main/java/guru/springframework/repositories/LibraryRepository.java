package guru.springframework.repositories;

import guru.springframework.domain.Library;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * Created by jt on 1/10/17.
 */
public interface LibraryRepository extends Neo4jRepository<Library, Long> {

    Library findByName(String name);

    @Query("MATCH (p:Library) RETURN p LIMIT {limit}")
    Collection<Library> getLibraries(@Param("limit") int limit);

    @Query("MATCH (:Repo {name: {linkedRepo}})-[:USES]-(p:Repo) RETURN p")
    Collection<Library> getLinkedLibraries(@Param("linkedRepo") String linkedRepo);

    @Query("MATCH (a:Repo),(b:Repo) WHERE a.name = {repo1} AND b.name = {repo2} CREATE (a)-[r:USES]->(b)")
    void createRelationship(@Param("repo1") String repo1, @Param("repo2") String repo2);

}
