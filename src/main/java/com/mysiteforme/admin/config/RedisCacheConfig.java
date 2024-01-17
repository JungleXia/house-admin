package com.mysiteforme.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by wangl on 2017/11/25.
 * todo:
 */
@EnableCaching
@Configuration
public class RedisCacheConfig {

    @Value("${spring.redis.host}")
    private String jedisHost;

    @Value("${spring.redis.port}")
    private Integer jedisPort;

    @Value("${spring.redis.password}")
    private String jedisPassword;
    
    @Value("${spring.redis.database}")
    private Integer database;
    
	@Value("${spring.redis.timeout}")
	private int timeout;

	@Value("${spring.redis.pool.max-active}")
	private int redisPoolMaxActive;

	@Value("${spring.redis.pool.max-wait}")
	private int redisPoolMaxWait;

	@Value("${spring.redis.pool.max-idle}")
	private int redisPoolMaxIdle;

	@Value("${spring.redis.pool.min-idle}")
	private int redisPoolMinIdle;
	
    /**
	 * 创建redis连接工厂
	 *
	 * @param dbIndex
	 * @param host
	 * @param port
	 * @param password
	 * @param timeout
	 * @return
	 */
	public JedisConnectionFactory createJedisConnectionFactory(int dbIndex, String host, int port, String password,
			int timeout) {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setDatabase(dbIndex);
		jedisConnectionFactory.setHostName(host);
		jedisConnectionFactory.setPort(port);
		jedisConnectionFactory.setPassword(password);
		jedisConnectionFactory.setTimeout(timeout);
		jedisConnectionFactory.setPoolConfig(
				setPoolConfig(redisPoolMaxIdle, redisPoolMinIdle, redisPoolMaxActive, redisPoolMaxWait, true));
		return jedisConnectionFactory;

	}
	
	@Primary
	@Bean
	public RedisConnectionFactory defaultConnectionFactory() {
		return createJedisConnectionFactory(database, jedisHost, jedisPort, jedisPassword, timeout);
	}
	
	/**
	 * 设置连接池属性
	 *
	 * @param maxIdle
	 * @param minIdle
	 * @param maxActive
	 * @param maxWait
	 * @param testOnBorrow
	 * @return
	 */
	public JedisPoolConfig setPoolConfig(int maxIdle, int minIdle, int maxActive, int maxWait, boolean testOnBorrow) {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMinIdle(minIdle);
		poolConfig.setMaxTotal(maxActive);
		poolConfig.setMaxWaitMillis(maxWait);
		poolConfig.setTestOnBorrow(testOnBorrow);
		return poolConfig;
	}
	
    @Bean(name = "redisTemplate")
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(factory);
        redisTemplate.setConnectionFactory(defaultConnectionFactory());
//        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        //这里如果启用fastjson序列化对象到redis的话 启动必须加参数 -Dfastjson.parser.autoTypeSupport=true
//        RedisSerializer fastJson = fastJson2JsonRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Primary
    @Bean
    public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }

}
