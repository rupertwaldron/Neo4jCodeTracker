package guru.springframework.commands;


/**
 * Created by jt on 1/10/17.
 */
public class LinkForm {
    private String repoName;
    private String libraryName;

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }
}
