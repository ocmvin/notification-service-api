package com.test.notification.config;

import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class RedisConfig {

	@Value("${spring.redis.host}")

	private String REDIS_HOSTNAME;

	@Value("${spring.redis.port}")

	private int REDIS_PORT;

	@Bean
	protected JedisConnectionFactory jedisConnectionFactory() {
		log.info("REDIS HOST "+REDIS_HOSTNAME+" PORT "+REDIS_PORT);
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(REDIS_HOSTNAME, REDIS_PORT);

		JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build();

		JedisConnectionFactory factory = new JedisConnectionFactory(configuration, jedisClientConfiguration);

		factory.afterPropertiesSet();
		log.info("REDIS factory############ "+factory);
		return factory;

	}

	@Bean
	public <T> RedisTemplate<String, Object> redisTemplate() {

		final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

		redisTemplate.setKeySerializer(new StringRedisSerializer());

		redisTemplate.setHashKeySerializer(new GenericToStringSerializer<Object>(Object.class));

		redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());

		redisTemplate.setValueSerializer(new StringRedisSerializer());

		redisTemplate.setConnectionFactory(jedisConnectionFactory());

		return redisTemplate;

	}

	@Bean
	public RedisMessageListenerContainer redisContainer() {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(jedisConnectionFactory());
		container.setTaskExecutor(Executors.newFixedThreadPool(10));// Executors.newCachedThreadPool()
		return container;
	}

}
