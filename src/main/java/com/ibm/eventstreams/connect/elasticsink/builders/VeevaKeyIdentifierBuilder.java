package com.ibm.eventstreams.connect.elasticsink.builders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.connect.sink.SinkRecord;

/**
 * Builds document identifiers from Kafka Connect SinkRecords
 */
public class VeevaKeyIdentifierBuilder implements IdentifierBuilder {

    private static final ObjectMapper om = new ObjectMapper();
    /**
     * Convert a Kafka Connect SinkRecord into a document identifier.
     *
     * @param record             the Kafka Connect SinkRecord
     *
     * @return the document identifier
     */
    @Override
    public String fromSinkRecord(SinkRecord record) {
        try {
            return om.writeValueAsString(record.key());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Whether document identifiers generated by this builder are unique.
     *
     * @return true if unique, false if not
     */
    @Override
    public boolean isUnique() {
        return false;
    }
}