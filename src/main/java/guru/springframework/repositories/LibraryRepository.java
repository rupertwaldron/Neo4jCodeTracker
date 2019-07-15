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

    @Query("MATCH (:Library {name: {linkedLib}})-[:LINKS_TO]-(l:Library) RETURN l")
    Collection<Library> getLinkedLibrary(@Param("linkedLib") String linkedLib);

    @Query("MATCH (a:Repo),(b:Library) WHERE a.name = {repo1} AND b.name = {lib2} CREATE (a)-[r:USES]->(b)")
    void createRepoRelationship(@Param("repo1") String repo1, @Param("lib2") String lib2);

    @Query("MATCH (a:Library {name: {lib1}}), (l:Library {name: {lib2}}) CREATE (a)-[r:LINKS_TO]->(l)")
    void createLibraryRelationship(@Param("lib1") String lib1, @Param("lib2") String lib2);

}
