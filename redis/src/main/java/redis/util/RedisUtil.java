package redis.util;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Created by zhyi(997295009@qq.com) on 2017/3/2.
 */
public class RedisUtil {

    /**
     * 批量删除redis key
     * @param jedis
     */
    public static void batchDel(Jedis jedis){
        Set<String> set = jedis.keys("*");
        for (String str: set) {
            jedis.del(str);
        }
    }
}
