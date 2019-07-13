package guru.springframework.domain;

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
}
