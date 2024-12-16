package com.educandoweb.course.configuration;

import com.educandoweb.course.domain.entity.Category;
import com.educandoweb.course.domain.entity.Order;
import com.educandoweb.course.domain.entity.Product;
import com.educandoweb.course.domain.entity.User;
import com.educandoweb.course.repository.CategoryRepository;
import com.educandoweb.course.repository.OrderRepository;
import com.educandoweb.course.repository.ProductRepository;
import com.educandoweb.course.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.List;

import static com.educandoweb.course.domain.enums.OrderStatus.WAITING_PAYMENT;
import static com.educandoweb.course.util.Constants.PROFILE_TEST;
import static java.util.Arrays.asList;

@Configuration
@Profile(PROFILE_TEST)
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private static final LocalDateTime now = LocalDateTime.now();

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = createUser("Maria Brown", "maria@gmail.com", "988888888");
        User user2 = createUser("Alex Green", "alex@gmail.com", "977777777");

        Order order1 = createOrder(user1);
        Order order2 = createOrder(user2);
        Order order3 = createOrder(user1);

        Product product1 = new Product(null, "Smartphone", "Latest smartphone", 599.99, "url1");
        Product product2 = new Product(null, "Laptop", "Gaming laptop", 1199.99, "url2");

        Category category1 = createCategory("Electronics");
        Category category2 = createCategory("Books");
        Category category3 = createCategory("Computers");

        product1.getCategoryList().add(category1);
        product2.getCategoryList().add(category1);
        product2.getCategoryList().add(category3);

        userRepository.saveAll(asList(user1, user2));
        orderRepository.saveAll(asList(order1, order2, order3));
        categoryRepository.saveAll(asList(category1, category2, category3));
        productRepository.saveAll(asList(product1, product2));
    }

    private User createUser(String userName, String email, String phone) {
        return new User(null, userName, email, phone, "123456", now, now, List.of());
    }

    private Order createOrder(User user) {
        return new Order(null, now, WAITING_PAYMENT, List.of(), user, null);
    }

    private Category createCategory(String name) {
        return new Category(null, name);
    }
}
