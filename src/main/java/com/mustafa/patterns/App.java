/*
Task:
Разработайте и реализуйте объектно-ориентированную модель , рассчитывающую общую стоимость и вес покупки в магазине.
Сама покупка собирается из различных товаров и продуктов, которые покупает отдельный человек. Известно, что все товары
и продукты обладают своей стоимостью и весом. Предусмотрите возможность добавления новых общих свойств
 */

package com.mustafa.patterns;

import com.mustafa.patterns.pojo.Serializer;
import com.mustafa.patterns.pojo.orders.Order;
import com.mustafa.patterns.pojo.products.Product;
import com.mustafa.patterns.pojo.products.ProductsFlyweight;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Collectors;

@SpringBootApplication
public class App implements CommandLineRunner {

    private final ProductsFlyweight products;
    private final Serializer<Order> orderSerializer;
    private final Serializer<Product> productSerializer;

    public App(ProductsFlyweight products, Serializer<Order> orderSerializer, Serializer<Product> productSerializer) {
        this.products = products;
        this.orderSerializer = orderSerializer;
        this.productSerializer = productSerializer;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.printf(
                "All products:\n%s",
                products.values().stream()
                        .map(productSerializer::serialize)
                        .collect(Collectors.joining("\n"))
        );

        final Order order = Order.newBuilder()
                .addProduct(products.get("Milk"))
                .addProduct(products.get("Cheese"))
                .addProduct(products.get("Tea"))
                .build();

        System.out.println("\n\n" + orderSerializer.serialize(order));

        order.getProducts().get(2).cancel();
        System.out.println(orderSerializer.serialize(order));
    }
}
