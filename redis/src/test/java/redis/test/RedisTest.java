package redis.test;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.jedis.RedisDemo;
import redis.util.RedisUtil;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.List;

/**
 * Created by zhyi(997295009@qq.com) on 2017/3/2.
 */
public class RedisTest {
    //连接redis
    private Jedis jedis;

    @Before
    public void setup(){
        jedis = new Jedis("localhost");
        //查看服务是否运行
        System.out.println("Server is running: " + jedis.ping());
    }

    @Test
    public void batchDelTest(){
        RedisUtil.batchDel(jedis);
        System.out.println("batch delete redis key success");
    }

    @Test
    public void redisKeys(){
        Set<String> set = RedisDemo.redisKeys(jedis);
        for (String str: set) {
            System.out.println("Stored string in redis: " + str);
        }
    }

    @Test
    public void redisStringTest(){
        System.out.println(RedisDemo.redisString(jedis));
    }

    @Test
    public void redisListTest(){
        List<String> list = RedisDemo.redisList(jedis);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("study-list[" + i + "] = " + list.get(i));
        }
    }

    @Test
    public void redisMapTest(){
        Map<String, String> map = RedisDemo.redisMap(jedis);
        System.out.println(map);
        Iterator<String> iterator = map.keySet().iterator();
        String key, value;
        while (iterator.hasNext()){
            key = iterator.next();
            value = map.get(key);
            System.out.println(key + " : " + value);
        }
    }

    @Test
    public void redisSetTest(){
        Set<String> set = RedisDemo.redisSet(jedis);
        System.out.println(set);
        for (String str: set) {
            System.out.println("lesson in redis : " + str);
        }
    }

    @Test
    public void redisSortedSetTest(){
        Set<String> set = RedisDemo.redisSortedSet(jedis);
        System.out.println(set);
        for (String str: set) {
            System.out.println("letter in redis : " + str);
        }
    }
}
