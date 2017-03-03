package redis.jedis;

import org.apache.log4j.PropertyConfigurator;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * redis 使用watch实现抢购
 * Created by zhyi(997295009@qq.com) on 2017/3/3.
 */
public class RedisScareBuyingRunnable implements Runnable{

    String watchKeys = "watchKeys";//监视key
    Jedis jedis = new Jedis("localhost");

    public RedisScareBuyingRunnable(){}

    public void run(){
        try {
            PropertyConfigurator.configure("D:\\User\\zhyiC\\Workspaces\\JetBrains\\redis\\src\\resources\\log4j.properties");
            Logger Log = Logger.getLogger(RedisScareBuyingRunnable.class.toString());

            jedis.watch(watchKeys);//WATCH 命令用于在事务开始之前监视任意数量的键

            String value = jedis.get(watchKeys);
            int valueInt = Integer.valueOf(value);
            String userInfo = UUID.randomUUID().toString();//使用UUID随机生成模拟用户信息

            if (valueInt < 20){
                Transaction transaction = jedis.multi();//开启事务

                transaction.incr(watchKeys);

                List<Object> list = transaction.exec();//提交事务，如果此时watchKeys被改动了，则返回null
                if (null != list){
                    //抢购成功
                    Log.info("用户" + userInfo + "抢购成功，目前抢购成功人数： " + (valueInt + 1));
                    jedis.sadd("successSet", userInfo);
                }else{
                    //抢购失败
                    Log.info("用户" + userInfo + "抢购失败");
                    jedis.sadd("failSet", userInfo);
                }
            }else{
                //抢购失败
                Log.info("--用户" + userInfo + "抢购失败");
                jedis.sadd("failSet", userInfo);
            }
        }catch (Exception e){
            e.getStackTrace();
        }finally {
            jedis.close();
        }

    }

}
