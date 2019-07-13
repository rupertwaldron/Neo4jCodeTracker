package guru.springframework.repositories;

import guru.springframework.domain.InitialProducts;
import guru.springframework.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
        productRepository.deleteAll();
        InitialProducts.createProducts().forEach(product -> {
            productRepository.save(product);
            log.info("Product saved : " + product.getDescription());
        });
    }

    @Test
    public void testPersistence() {

        productRepository.findAll().forEach(product -> {
            assertNotNull(product.getId());
        });

        Product newProduct = productRepository.findByDescription("bucket");
        assertEquals("bucket", newProduct.getDescription());
        assertEquals(BigDecimal.valueOf(1.18).compareTo(newProduct.getPrice()), 0);
        assertEquals("empty", newProduct.getImageUrl());
    }

    @Test
    public void testRelationships() {
        Collection<Product> newProduct = productRepository.getComplimentProducts("bucket");
        assertEquals(newProduct.stream().findFirst().get().getDescription(), "spade");
    }
}