package guru.springframework.repositories;

import guru.springframework.domain.Product;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * Created by jt on 1/10/17.
 */
public interface ProductRepository extends Neo4jRepository<Product, Long> {

    Product findByDescription(String description);

    @Query("MATCH (p:Product) RETURN p LIMIT {limit}")
    Collection<Product> getProducts(@Param("limit") int limit);

    @Query("MATCH (:Product {description: {compProduct}})-[:COMPLIMENTS]-(p:Product) RETURN p")
    Collection<Product> getComplimentProducts(@Param("compProduct") String compProduct);

    @Query("MATCH (a:Product),(b:Product) WHERE a.description = {product1} AND b.description = {product2} CREATE (a)-[r:COMPLIMENTS]->(b)")
    void createRelationship(@Param("product1") String product1, @Param("product2") String product2);

}
