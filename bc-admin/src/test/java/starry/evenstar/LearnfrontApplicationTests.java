package starry.evenstar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class LearnfrontApplicationTests {

    @Test
    void contextLoads() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }

}
