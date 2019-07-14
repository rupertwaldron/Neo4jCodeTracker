package guru.springframework.domain;

import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

public class CodeOwner extends Entity {
    String name;

    public CodeOwner(String name) {
        this.name = name;
    }

    public CodeOwner() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Relationship(type = "OWNS", direction = Relationship.OUTGOING)
    public Set<Entity> entities;
}
