package guru.springframework.domain;

import java.util.stream.Stream;

public class InitialLibraries {
    public final static Stream<Library> createLibraries() {
        Library springBoot2 = new Library("springBoot2", "spring.com");
        Library jackson = new Library("jackson", "jackson.com");
        Library neo4j = new Library("neo4j", "neo4j.com");

        return Stream.of(springBoot2, jackson, neo4j);
    }
}
