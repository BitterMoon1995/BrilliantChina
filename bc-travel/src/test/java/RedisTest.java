import com.zh.RunMini;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;


@SpringBootTest(classes = RunMini.class)
public class RedisTest {
    @Autowired
    StringRedisTemplate stringTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test1(){
        ValueOperations<String, String> op = stringTemplate.opsForValue();
        op.append("blackSlave","黑奴大尼哥儿");
    }
}
