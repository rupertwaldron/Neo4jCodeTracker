package guru.springframework.domain;

import java.util.stream.Stream;

public class InitialCodeOwners {
    public final static Stream<CodeOwner> createCodeOwners() {
        CodeOwner falcon = new CodeOwner("Falcon");
        CodeOwner seagulls = new CodeOwner("Seagulls");
        CodeOwner hussars = new CodeOwner("Hussars");
        CodeOwner ultravox = new CodeOwner(("Ultravox"));

        return Stream.of(falcon, seagulls, hussars);
    }
}
