package com.comitfy.kidefy.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final String productionLogQueue = "production_log_queue";
    private final String workOrderQueue = "work_order_queue";


    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();

        // RabbitMQ bağlantısı için factory'yi yapılandırıyoruz
        factory.setConnectionFactory(connectionFactory);

        // Prefetch count: Aynı anda yalnızca bir mesaj alınacak
        factory.setPrefetchCount(1);
        factory.setConcurrentConsumers(1);
        // Concurrency: Aynı anda yalnızca bir iş parçacığı çalışacak
        // Jackson2JsonMessageConverter kullanarak mesajları otomatik olarak POJO'ya (JSON) dönüştürme
        factory.setMessageConverter(jackson2JsonMessageConverter());

        return factory;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.findAndRegisterModules(); // Registers any other available modules
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public Queue productionLogQueue() {
        return new Queue(productionLogQueue, Boolean.TRUE); // durable = true
    }

    @Bean
    public Queue workOrderQueue() {
        return new Queue(workOrderQueue, Boolean.TRUE); // durable = true
    }

}
