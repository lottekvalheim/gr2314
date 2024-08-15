package persistence.internal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import core.Dress;
import core.Dresses;

import java.io.IOException;

class DressesSerializer extends JsonSerializer<Dresses> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(final Dresses dresses, final JsonGenerator jsonGen, final SerializerProvider serializerProvider)
            throws IOException {
        jsonGen.writeStartObject();
        jsonGen.writeArrayFieldStart("dresses");
        for (Dress dress : dresses.getDresses()) {
            jsonGen.writeStartObject();
            jsonGen.writeStringField("id", dress.getId());
            jsonGen.writeBooleanField("isAvailable", dress.getIsAvailable());
            jsonGen.writeEndObject();
        }
        jsonGen.writeEndArray();
        jsonGen.writeEndObject();
    }
}

