package net.jack.atlas.backend;

import redis.clients.jedis.Connection;
import redis.clients.jedis.Jedis;

public class RedisDB {


    private Connection connection;
    private final int port =  10591;
    private final String user = "root";
    private final String password = "gurGvJKHLuJ@H2y";

    public Connection connect() {
        try {
            Jedis jedis = new Jedis("redis-10591.c268.eu-west-1-2.ec2.cloud.redislabs.com", port);
            jedis.auth(user, password);
            System.out.println("RedisDB: Connected Successfully");
            connection = jedis.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
