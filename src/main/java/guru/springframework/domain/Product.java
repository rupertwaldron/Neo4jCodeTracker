package guru.springframework.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jt on 1/10/17.
 */
@NodeEntity
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private BigDecimal price;
    private String imageUrl;

    public Product() {}

    public Product(String description, BigDecimal price) {
        this.description = description;
        this.price = price;
        this.imageUrl = "empty";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Relationship(type = "COMPLIMENTS", direction = Relationship.UNDIRECTED)
    public Set<Product> partners;

    public void compliments(Product product) {
        if (partners == null) {
            partners = new HashSet<>();
        }
        partners.add(product);
    }

    public String toString() {

        return this.description + "'s partners => "
                + Optional.ofNullable(this.partners).orElse(
                Collections.emptySet()).stream()
                .map(Product::getDescription)
                .collect(Collectors.toList());
    }
}
