package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV0 {
    private final OrderRepositoryVO orderRepository;

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
