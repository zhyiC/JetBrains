package redis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        //连接redis
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        //查看服务是否运行
        System.out.println("Server is running: " + jedis.ping());
    }
}
