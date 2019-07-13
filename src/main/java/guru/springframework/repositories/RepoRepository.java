package guru.springframework.repositories;

import guru.springframework.domain.Repo;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * Created by jt on 1/10/17.
 */
public interface RepoRepository extends Neo4jRepository<Repo, Long> {

    Repo findByName(String name);

    @Query("MATCH (p:Repo) RETURN p LIMIT {limit}")
    Collection<Repo> getRepos(@Param("limit") int limit);

    @Query("MATCH (:Repo {name: {linkedRepo}})-[:USES]-(p:Repo) RETURN p")
    Collection<Repo> getLinkedRepo(@Param("linkedRepo") String linkedRepo);

    @Query("MATCH (a:Repo),(b:Repo) WHERE a.name = {repo1} AND b.name = {repo2} CREATE (a)-[r:USES]->(b)")
    void createRelationship(@Param("repo1") String repo1, @Param("repo2") String repo2);

}
