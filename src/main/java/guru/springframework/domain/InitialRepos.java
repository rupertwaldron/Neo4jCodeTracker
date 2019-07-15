package guru.springframework.domain;

import java.util.stream.Stream;

public final class InitialRepos {
    public final static Stream<Repo> createRepos() {
        Repo transMap = new Repo("transMap", "dfs.com");
        Repo transSearch = new Repo("transSearch", "dfs.com");
        Repo partyProfiles = new Repo("partyProfiles", "dfs.com");
        Repo extendedProfiles = new Repo("extendedProfiles", "dfs.com");
        Repo mccLoader = new Repo("mccLoader", "dfs.com");
        Repo finIpc = new Repo("finIpc", "dfs.com");
        Repo jwt = new Repo("jwt", "dfs.com");

        return Stream.of(transMap, transSearch, partyProfiles, extendedProfiles, mccLoader, finIpc, jwt);
    }
}
