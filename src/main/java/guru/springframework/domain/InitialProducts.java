package guru.springframework.domain;

import java.math.BigDecimal;
import java.util.stream.Stream;

public final class InitialProducts {
    public final static Stream<Product> createProducts() {
        Product bucket = new Product("bucket", BigDecimal.valueOf(1.18));
        Product spade = new Product("spade", BigDecimal.valueOf(4.39));
        Product iceCream = new Product("ice-cream", BigDecimal.valueOf(2.50));
        Product lemon = new Product("lemon", BigDecimal.valueOf(0.29));
        Product gin = new Product("gin", BigDecimal.valueOf(4.12));
        Product tonic = new Product("tonic", BigDecimal.valueOf(2.76));
        Product holiday = new Product("holiday", BigDecimal.valueOf(4600.00));

        gin.compliments(tonic);
        bucket.compliments(spade);

        return Stream.of(bucket, spade,iceCream, lemon, gin, tonic, holiday);
    }
}
