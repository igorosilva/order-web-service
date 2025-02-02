package com.educandoweb.course.configuration;

import com.educandoweb.course.domain.entity.Category;
import com.educandoweb.course.domain.entity.Order;
import com.educandoweb.course.domain.entity.OrderItem;
import com.educandoweb.course.domain.entity.Payment;
import com.educandoweb.course.domain.entity.Product;
import com.educandoweb.course.domain.entity.User;
import com.educandoweb.course.domain.enums.OrderStatus;
import com.educandoweb.course.repository.CategoryRepository;
import com.educandoweb.course.repository.OrderItemRepository;
import com.educandoweb.course.repository.OrderRepository;
import com.educandoweb.course.repository.PaymentRepository;
import com.educandoweb.course.repository.ProductRepository;
import com.educandoweb.course.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

import static com.educandoweb.course.domain.enums.OrderStatus.PAID;
import static com.educandoweb.course.domain.enums.OrderStatus.WAITING_PAYMENT;
import static com.educandoweb.course.util.Constants.PROFILE_TEST;
import static java.time.LocalDateTime.now;
import static java.util.Arrays.asList;

@Configuration
@Profile(PROFILE_TEST)
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private static final LocalDateTime now = now();

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public void run(String... args) throws Exception {
        Category category1 = createCategory("Electronics");
        Category category2 = createCategory("Books");
        Category category3 = createCategory("Computers");

        Product product1 = createProduct("The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5);
        Product product2 = createProduct("Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0);
        Product product3 = createProduct("Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0);
        Product product4 = createProduct("PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0);
        Product product5 = createProduct("Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99);

        categoryRepository.saveAll(asList(category1, category2, category3));
        productRepository.saveAll(asList(product1, product2, product3, product4, product5));

        product1.getCategoryList().add(category2);
        product2.getCategoryList().add(category1);
        product2.getCategoryList().add(category3);
        product3.getCategoryList().add(category3);
        product4.getCategoryList().add(category3);
        product5.getCategoryList().add(category2);

        productRepository.saveAll(asList(product1, product2, product3, product4, product5));

        User user1 = createUser("Maria Brown", "maria@gmail.com", "988888888");
        User user2 = createUser("Alex Green", "alex@gmail.com", "977777777");

        Order order1 = createOrder(PAID);
        Order order2 = createOrder(WAITING_PAYMENT);
        Order order3 = createOrder(WAITING_PAYMENT);

        userRepository.saveAll(asList(user1, user2));

        order1.setClient(user1);
        order2.setClient(user1);
        order3.setClient(user2);

        orderRepository.saveAll(asList(order1, order2, order3));

        OrderItem orderItem1 = createOrderItem(order1, product1, 2, product1.getVlPrice());
        OrderItem orderItem2 = createOrderItem(order1, product3, 1, product3.getVlPrice());
        OrderItem orderItem3 = createOrderItem(order2, product3, 2, product3.getVlPrice());
        OrderItem orderItem4 = createOrderItem(order3, product5, 2, product5.getVlPrice());

        order1.getProductList().add(orderItem1);
        order1.getProductList().add(orderItem2);
        order2.getProductList().add(orderItem3);
        order3.getProductList().add(orderItem4);

        orderItemRepository.saveAll(asList(orderItem1, orderItem2, orderItem3, orderItem4));
        orderRepository.saveAll(asList(order1, order2, order3));

        Payment payment1 = createPayment(now.plusHours(22), order1);
        order1.setPayment(payment1);

        orderRepository.saveAll(asList(order1));
    }

    private User createUser(String userName, String email, String phone) {
        return new User(null, userName, email, phone, "123456", now, now);
    }

    private Order createOrder(OrderStatus status) {
        return new Order(null, now, status, null);
    }

    private Category createCategory(String name) {
        return new Category(null, name);
    }

    private Product createProduct(String name, String description, Double price) {
        return new Product(null, name, description, price, "");
    }

    private OrderItem createOrderItem(Order order, Product product, int quantity, double price) {
        return new OrderItem(order, product, quantity, price);
    }

    private Payment createPayment(LocalDateTime time, Order order) {
        return new Payment(null, time, order);
    }
}
