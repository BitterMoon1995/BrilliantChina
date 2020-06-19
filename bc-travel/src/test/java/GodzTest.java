import com.zh.RunMini;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Calendar;
import java.util.Date;


@SpringBootTest(classes = RunMini.class)
public class GodzTest {
    @Autowired
    StringRedisTemplate stringTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test1(){
        ValueOperations<String, String> op = stringTemplate.opsForValue();
        op.append("blackNigger","黑奴大尼哥儿");
    }

    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
        //随机数
//        Random random = new Random();
//
//        StringBuffer valSb = new StringBuffer();
//        String charStr = "0123456789abcdefghijklmnopqrstuvwxyz";
//        int charLength = charStr.length();
//
//        for (int i = 0; i < 6; i++) {
//            int index = random.nextInt(charLength);
//            valSb.append(charStr.charAt(index));
//        }
//
//        System.out.println(valSb);

        //拼音首字母
//        String[] strings = PinyinHelper.toHanyuPinyinStringArray('系', new HanyuPinyinOutputFormat());
//        for (String s:strings
//             ) {
//            System.out.println(s);
//        }

//        String s = GodzSUtils.getFirstPinYin("孙笑川");
//        System.out.println(s);


        //指定年月日
        Calendar calendar = Calendar.getInstance();
        calendar.set(1995,6-1,1);
        System.out.println(calendar.getTime());
    }
}
