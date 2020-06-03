package com.mustafa.patterns;

import com.mustafa.patterns.pojo.Serializer;
import com.mustafa.patterns.pojo.TestFactory;
import com.mustafa.patterns.pojo.orders.Order;
import com.mustafa.patterns.pojo.orders.OrderSerializer;
import com.mustafa.patterns.pojo.products.Product;
import com.mustafa.patterns.pojo.products.ProductSerializer;
import com.mustafa.patterns.pojo.products.ProductsFlyweight;
import com.mustafa.patterns.pojo.products.TestProductFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Context {

    @Bean
    public TestFactory<Product> productTestFactory() {
        return new TestProductFactory();
    }

    @Bean
    public Serializer<Order> orderSerializer(Serializer<Product> productSerializer) {
        return new OrderSerializer(productSerializer);
    }

    @Bean
    public Serializer<Product> productSerializer() {
        return new ProductSerializer();
    }

    @Bean
    public ProductsFlyweight productsFlyweight(TestFactory<Product> productTestFactory) {
        return new ProductsFlyweight(productTestFactory.getData());
    }
}
