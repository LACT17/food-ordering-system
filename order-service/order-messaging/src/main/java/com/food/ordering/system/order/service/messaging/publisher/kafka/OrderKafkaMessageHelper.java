package com.food.ordering.system.order.service.messaging.publisher.kafka;

import com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Slf4j
@Component
public class OrderKafkaMessageHelper {



    public <T> BiConsumer<SendResult<String, T>, Throwable> getKafkaCallback(
            String responsetTopicName,
            T requestAvroModel,
            String orderId,
            String requestAvroModelName) {

                return (result, ex) -> {
                    if (ex != null) {
                        // --- FAILURE CASE ---
                        log.error("Error occurred while sending"+ requestAvroModelName+" message {} to topic {}",
                                requestAvroModel.toString(),
                                responsetTopicName,
                                ex);
                    } else {
                        // --- SUCCESS CASE ---
                        // You must extract metadata from the result object
                        var metadata = result.getRecordMetadata();

                        log.info("Received successful response from kafka for order id: {} Topic: {} Partition: {} Offset: {} Timestamp: {}",
                                orderId,
                                metadata.topic(),
                                metadata.partition(),
                                metadata.offset(),
                                metadata.timestamp());
                    }
                };
    }
}
