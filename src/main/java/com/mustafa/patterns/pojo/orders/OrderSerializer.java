package com.mustafa.patterns.pojo.orders;

import com.mustafa.patterns.pojo.Serializer;
import com.mustafa.patterns.pojo.money.Rubble;
import com.mustafa.patterns.pojo.products.Product;
import com.mustafa.patterns.pojo.products.ProductSerializer;
import com.mustafa.patterns.pojo.weights.Gramme;

import java.util.List;
import java.util.stream.Collectors;

public class OrderSerializer implements Serializer<Order> {

    private final Serializer<Product> productSerializer;

    public OrderSerializer(Serializer<Product> productSerializer) {
        this.productSerializer = productSerializer;
    }

    @Override
    public String serialize(Order order) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Order:\n");

        final List<Product> products = order.getProducts().stream()
                .filter(entry -> !entry.isCancelled())
                .map(Order.Entry::getProduct)
                .collect(Collectors.toList());

        if (!products.isEmpty()) {
            products.forEach(product -> builder.append(productSerializer.serialize(product)).append('\n'));
            final Rubble cost = products.stream().map(Product::getCost).reduce(Rubble::plus).get();
            final Gramme weight = products.stream().map(Product::getWeight).reduce(Gramme::plus).get();
            builder.append("Summary:\n\tCost: ").append(cost.toString()).append("\n\tWeight: ").append(weight);
        }

        return builder.toString();
    }
}
