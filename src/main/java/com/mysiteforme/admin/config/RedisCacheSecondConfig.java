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
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class RedisCacheSecondConfig {
	@Value("${spring.redis2.database}")
	private int dbIndex;

	@Value("${spring.redis2.host}")
	private String host;

	@Value("${spring.redis2.port}")
	private int port;

	@Value("${spring.redis2.password}")
	private String password;

	@Value("${spring.redis2.timeout}")
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

	/**
	 * 设置RedisTemplate的序列化方式
	 *
	 * @param redisTemplate
	 */
	public void setSerializer(RedisTemplate redisTemplate) {
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		// 设置键（key）的序列化方式
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		// 设置值（value）的序列化方式
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.afterPropertiesSet();
	}
	
	/**
	 * 设置RedisTemplate的序列化方式 String
	 * 
	 * @param redisTemplate
	 * 2019年11月21日上午11:06:47
	 */
	public void setStringSerializer(RedisTemplate redisTemplate) {
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		// 设置键（key）的序列化方式
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		// 设置值（value）的序列化方式
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		redisTemplate.afterPropertiesSet();
	}

	/**
	 * 配置redis连接工厂
	 *
	 * @return
	 */

	@Bean(name = "redis2ConnectionFactory")
	public RedisConnectionFactory cacheSecondConnectionFactory() {
		return createJedisConnectionFactory(dbIndex, host, port, password, timeout);
	}

	/**
	 * 配置redisTemplate 注入方式使用@Resource(name="") 方式注入
	 *
	 * @return
	 */
	@Bean(name = "redis2Template")
	public RedisTemplate cacheRedisTemplate() {
		RedisTemplate template = new RedisTemplate();
		template.setConnectionFactory(cacheSecondConnectionFactory());
		setSerializer(template);
		template.afterPropertiesSet();
		return template;
	}
	
	/**
	 * redis3Template 是redis2Template变异，使用string 序列化value
	 * @return
	 * 2019年11月21日上午11:07:13
	 */
	@Bean(name = "redis3Template")
	public RedisTemplate cacheRedis3Template() {
		RedisTemplate template = new RedisTemplate();
		template.setConnectionFactory(cacheSecondConnectionFactory());
		setStringSerializer(template);
		template.afterPropertiesSet();
		return template;
	}
	
  
    @Bean(name = "cacheSecondManager")
    public CacheManager cacheSecondManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }
}
