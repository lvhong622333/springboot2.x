package com.lvhong;

//@Configuration
//@ConditionalOnClass({JedisCluster.class})
public class RedisManager {
	
//	@Value("${spring.redis.cluster.nodes}")
//    private String clusterNodes;
//	
//    @Value("${spring.redis.cluster.timeout}")
//    private Long timeout;
//    
//    @Value("${spring.redis.jedis.pool.max-idle}")
//    private Integer maxIdle;
//    
//    @Value("${spring.redis.jedis.pool.max-wait}")
//    private Long maxWaitMillis;
//    
//    @Value("${spring.redis.jedis.pool.min-idle}")
//    private Integer minIdle;
//    
//    @Bean
//    public JedisCluster jedisCluster() {
//        Set<HostAndPort> nodeSet = new HashSet<>();
//        String[] cluster = clusterNodes.split(",");
//        for(String node :cluster) {
//            String[] split = node.split(":");
//            nodeSet.add(new HostAndPort(split[0],Integer.valueOf(split[1])));
//        }
//        
//        JedisPoolConfig jedisPoolConfig =new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//        jedisPoolConfig.setMinIdle(minIdle);
//        return new JedisCluster(nodeSet,jedisPoolConfig);
//    }
}
