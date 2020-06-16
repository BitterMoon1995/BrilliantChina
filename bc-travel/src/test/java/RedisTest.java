import com.zh.RunMini;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Random;


@SpringBootTest(classes = RunMini.class)
public class RedisTest {
    @Autowired
    StringRedisTemplate stringTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test1(){
        ValueOperations<String, String> op = stringTemplate.opsForValue();
        op.append("blackNigger","黑奴大尼哥儿");
    }

    public static void main(String[] args) {
        Random random = new Random();

        StringBuffer valSb = new StringBuffer();
        String charStr = "0123456789abcdefghijklmnopqrstuvwxyz";
        int charLength = charStr.length();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(charLength);
            valSb.append(charStr.charAt(index));
        }

        System.out.println(valSb);
    }
}
