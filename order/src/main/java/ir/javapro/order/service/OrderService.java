package ir.javapro.order.service;

import ir.javapro.order.dto.ProductDto;
import ir.javapro.order.dto.UserDto;
import ir.javapro.order.enums.OrderStatus;
import ir.javapro.order.feign_client.ProductClient;
import ir.javapro.order.feign_client.UserClient;
import ir.javapro.order.model.Order;
import ir.javapro.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    public final UserClient userClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductClient productClient, UserClient userClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
        this.userClient = userClient;
    }

    public Order create(Order order) {

        UserDto userDto = userClient.findById(order.getUserId());

        if (userDto == null) {
            throw new RuntimeException("user not found");
        }

        ProductDto productDto = productClient.findById(order.getProductId());

        if (productDto == null) {
            throw new RuntimeException("product not found");
        }

        //بررسی موجودی
        if (productDto.getStock() < order.getQuantity()) {
            throw new RuntimeException("stock not enough");
        }

        int newStock = productDto.getStock() - order.getQuantity();
        productDto.setStock(newStock);

        productClient.save(productDto);

        //محاسبه مبلغ سفارش
        BigDecimal totalPrice = productDto.getPrice()
                .multiply(BigDecimal.valueOf(order.getQuantity()));

        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.CREATED);

        return orderRepository.save(order);

    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}