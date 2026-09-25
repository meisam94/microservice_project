package ir.javapro.order.feign_client;

import ir.javapro.order.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/api/products/get-by-id/{id}")
    ProductDto findById(@PathVariable long id);

    @PostMapping("/api/products/save")
    ProductDto save(@RequestBody ProductDto productDto);
}
