package guru.springframework.domain;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

/**
 * Created by jt on 1/10/17.
 */
@NodeEntity
public class Repo extends Entity {
    private String name;
    private String repoUrl;

    public Repo() {}

    public Repo(String name, String repoUrl) {
        this.name = name;
        this.repoUrl = repoUrl;
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

    @Relationship(type = "USES", direction = Relationship.INCOMING)
    public Set<Repo> partners;

}
