package io.plumery.eventstore;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.setup.Environment;
import io.plumery.core.infrastructure.EventStore;
import io.plumery.eventstore.kafka.KafkaEventStore;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by veniamin on 10/02/2017.
 */
public class EventStoreFactory {
    @NotEmpty
    @JsonProperty
    private String zookeeper;

    public void setZookeeper(String zookeeper) {
        this.zookeeper = zookeeper;
    }

    public String getZookeeper() {
        return zookeeper;
    }

    public EventStore build(Environment enviroment, String eventsPackage) {
        return new KafkaEventStore.Builder()
                .withZookeeper(zookeeper)
                .withGroupId(enviroment.getName())
                .withObjectMapper(enviroment.getObjectMapper())
                .withEventsPackage(eventsPackage)
                .build();
    }
}
