import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPay;
import com.mysql.cj.protocol.x.Notice;
import com.zh.RunMini;
import com.zh.admin.utils.HttpsUtils;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import netscape.javascript.JSObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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

    public static void main(String[] args) throws Exception {
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
//        String s = "510904198906041211";
//        Calendar calendar = Calendar.getInstance();
//        String year = s.substring(6, 10);
//        String month = s.substring(11, 12);
//        String day = s.substring(13, 14);
//        calendar.set(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
//        System.out.println(calendar.getTime());

        //调用第三方接口
//        String url = "https://api.weixin.qq.com/sns/jscode2session";
//
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("appid","wxa9e6f1fa364a24ab");
//        params.put("secret","7db719b2cead5dab3af40032612f71d0");
//        params.put("js_code","001TLLyT0vf9212FYVwT0yaRyT0TLLy8");
//        params.put("grant_type","authorization_code");
//
//        String s = HttpsUtils.get(url, params, null);
//        Object zxtsima = JSONObject.parse(s);

        //文件流读resources文件
//        File file = new File("/apiclient_cert.p12");
//        System.out.println(file.getAbsolutePath());
//
//        FileInputStream fileInputStream = new FileInputStream(file);
//
//        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

//        //充值成功，续一年
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+1);
//        System.out.println(calendar.getTime());

        //比天数差

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2020-07-04");
        long expDateTime = date.getTime();
        System.out.println(expDateTime);

        long currentDateTime = new Date().getTime();
        System.out.println(currentDateTime);

        System.out.println(Math.toIntExact((expDateTime - currentDateTime) / 86400000));
    }
}
