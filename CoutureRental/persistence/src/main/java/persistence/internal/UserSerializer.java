package persistence.internal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.User;
import java.io.IOException;

public class UserSerializer extends JsonSerializer<User> {
    /**
     * Serialize a User object to JSON.
     */
    @Override
    public void serialize(final User user, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
        gen.writeStartObject();  // Start writing the User object as a JSON object.
        gen.writeStringField("username", user.getUsername());
        gen.writeStringField("password", user.getPassword());
        gen.writeEndObject();  // End writing the JSON object.
    }
}
