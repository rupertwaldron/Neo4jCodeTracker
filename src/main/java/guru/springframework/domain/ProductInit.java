package guru.springframework.domain;

import guru.springframework.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Copyright (c)2019 DFS Services LLC
 * All rights reserved.
 *
 * @author rwaldro
 */
@Component
@Slf4j
public class ProductInit implements ApplicationRunner {
  private ProductRepository productRepository;

  @Autowired
  public ProductInit(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public void run(ApplicationArguments args) {
    productRepository.deleteAll();
    InitialProducts.createProducts().forEach(product -> {
      productRepository.save(product);
      log.info("Product saved : " + product.getDescription());
    });

    productRepository.findByDescription("lemon").compliments(productRepository.findByDescription("gin"));

    productRepository.getProducts(25).stream().forEach(product -> log.info("Found :" + product.getDescription()));

    productRepository.createRelationship("holiday", "spade");

    productRepository.getComplimentProducts("tonic").forEach(product -> log.info("Found tonic match:" + product.getDescription()));

    productRepository.getComplimentProducts("holiday").forEach(product -> log.info("Found holiday match:" + product.getDescription()));
  }

}
