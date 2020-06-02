package com.test.notification.config;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AppConfig {

	@Bean
	public TaskExecutor getAsyncTaskExecutor() {
		ThreadPoolTaskExecutor pooledExecutor = new ThreadPoolTaskExecutor();
		pooledExecutor.initialize();
		return pooledExecutor;
	}


	@Bean(name = "TopicFactory")
	public Function<String, ChannelTopic> topicFactory() {
		return (String x) -> new ChannelTopic(x);
	}

}
