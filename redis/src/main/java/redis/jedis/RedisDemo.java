package redis.jedis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhyi(997295009@qq.com) on 2017/3/2.
 */
public class RedisDemo {

    /**
     * Java Redis keys实例
     * @param jedis
     * @return
     */
    public static Set<String> redisKeys(Jedis jedis){
        Set<String> set = jedis.keys("*");
        return set;
    }

    /**
     *Java Redis String 实例
     * @param jedis
     */
    public static String redisString(Jedis jedis){
        //设置 redis 字符串数据
        jedis.set("name", "zhyi");
        //获取数据
       return jedis.get("name");
    }

    /**
     * Java Redis List 实例
     * @param jedis
     * @return
     */
    public static List<String> redisList(Jedis jedis){
        //存储数据到列表中
        jedis.lpush("study-list", "mongoDB");
        jedis.lpush("study-list", "nodejs");
        jedis.lpush("study-list", "java");
        jedis.lpush("study-list", "redis");
        //获取数据，第一个是key，第二个是起始位置，第三个是结束位置，结束位置-1表示取得所有
        List<String> list = jedis.lrange("study-list", 0, 2);
        return list;
    }

    /**
     * Java Redis Map 实例
     * @param jedis
     * @return
     */
    public static Map<String, String> redisMap(Jedis jedis){
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "zhyi");
        map.put("age", "21");
        map.put("qq", "123456");
        jedis.hmset("student", map);
        Map<String, String> rsMap = jedis.hgetAll("student");
        System.out.println(jedis.hlen("student"));//返回key为user的键中存放的值的个数
        System.out.println(jedis.exists("student"));//是否存在key为user的记录
        System.out.println(jedis.hkeys("student"));//返回map对象中的所有key
        System.out.println(jedis.hvals("student"));//返回map对象中的所有value
        return rsMap;
    }

    /**
     * Java Redis Set 实例
     * @param jedis
     * @return
     */
    public static Set<String> redisSet(Jedis jedis){
        jedis.sadd("lesson", "MongoDB");
        jedis.sadd("lesson", "Redis");
        jedis.sadd("lesson", "Nodejs");
        jedis.sadd("lesson", "Java");
        Set<String> set = jedis.smembers("lesson");
        System.out.println(jedis.sismember("lesson", "Redis"));//判断Redis是否是user集合的元素
        System.out.println(jedis.scard("lesson"));//返回集合的元素个数
        return set;
    }

    /**
     * Java Redis Sorted Set实例
     * @param jedis
     * @return
     */
    public static Set<String> redisSortedSet(Jedis jedis){
        jedis.zadd("letter", 0, "a");
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("b", 0.00);
        map.put("c", 2.00);
        map.put("d", 1.00);
        jedis.zadd("letter", map);
        Set<String> set = jedis.zrangeByScore("letter", 0, 100);
        return set;
    }
}
