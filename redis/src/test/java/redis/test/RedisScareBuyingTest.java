package redis.test;

import org.apache.log4j.PropertyConfigurator;
import redis.clients.jedis.Jedis;
import redis.jedis.RedisScareBuyingRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Created by zhyi(997295009@qq.com) on 2017/3/3.
 */
public class RedisScareBuyingTest {

    public static void main(String[] args){
        PropertyConfigurator.configure("D:\\User\\zhyiC\\Workspaces\\JetBrains\\redis\\src\\resources\\log4j.properties");
        Logger Log = Logger.getLogger(RedisScareBuyingTest.class.toString());

        final String watchKeys = "watchKeys";
        ExecutorService executorService = Executors.newFixedThreadPool(30);

        final Jedis jedis = new Jedis("localhost");
        jedis.set(watchKeys,"0");//重置watchKeys
        jedis.del("successSet", "failSet");//清空抢购信息
        jedis.close();

        Log.info("=================================================================");

        for (int i = 0; i < 100; i++) { //模拟用户同时访问测试
            executorService.execute(new RedisScareBuyingRunnable());
        }
        executorService.shutdown();
    }
}
