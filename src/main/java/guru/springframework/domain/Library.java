package guru.springframework.domain;

import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

public class Library extends Entity {
    String name;
    String repoUrl;

    public Library(String name, String repoUrl) {
        this.name = name;
        this.repoUrl = repoUrl;
    }

    public Library() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    @Relationship(type = "LINKS_TO", direction = Relationship.OUTGOING)
    public Set<Library> libraries;
}
