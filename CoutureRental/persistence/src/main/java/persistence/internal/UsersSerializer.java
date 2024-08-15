package persistence.internal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.User;
import core.Users;

import java.io.IOException;

public class UsersSerializer extends JsonSerializer<Users> {
    /**
     * Serialize a Users object to JSON.
     */
    @Override
    public void serialize(final Users users, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
        gen.writeStartObject();  // Start writing the Users object as a JSON object.

        // Serialize the "users" field.
        gen.writeArrayFieldStart("users");
        for (User user : users.getUsers()) {
            gen.writeObject(user);
        }
        gen.writeEndArray();  // End the "users" array.

        gen.writeEndObject();  // End writing the JSON object.
    }
}
