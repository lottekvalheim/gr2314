package persistence.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import core.User;
import java.io.IOException;

/**
 * JSON deserializer for the User class.
 */
public class UserDeserializer extends JsonDeserializer<User> {
    /**
     * Deserialize a User from a JsonParser.
     * @param parser
     * @param ctxt
     * @return Deserialized User object.
     * @throws IOException if there is an error deserializing the User.
     */
    @Override
    public User deserialize(final JsonParser parser, final DeserializationContext ctxt)
            throws IOException {
        JsonNode jsonNode = parser.getCodec().readTree(parser);
        return deserializeUser(jsonNode);
    }

    /**
     * Deserialize a User from a JsonNode.
     * @param jsonNode
     * @return Deserialized User object.
     * @throws IOException if there is an error deserializing the User.
     */
    private User deserializeUser(final JsonNode jsonNode) throws IOException {
        if (jsonNode == null) {
            throw new IOException("JsonNode is null");
        }

        JsonNode usernameNode = jsonNode.get("username");
        JsonNode passwordNode = jsonNode.get("password");

        if (usernameNode == null || !usernameNode.isTextual()) {
            throw new IOException("Missing required 'username' field or it's not textual");
        }

        if (passwordNode == null || !passwordNode.isTextual()) {
            throw new IOException("Missing required 'password' field or it's not textual");
        }

        return new User(usernameNode.asText(), passwordNode.asText());
    }
}
