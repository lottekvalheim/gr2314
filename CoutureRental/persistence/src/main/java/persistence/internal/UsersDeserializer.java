package persistence.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import core.User;
import core.Users;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsersDeserializer extends JsonDeserializer<Users> {
    /**
     * Deserialize a Users object from a JsonParser.
     */
    @Override
    public Users deserialize(final JsonParser parser, final DeserializationContext ctxt) throws IOException {
        if (parser.getCodec() == null) {
            throw new IllegalStateException("JsonParser does not have an ObjectCodec.");
        }
        JsonNode rootNode = parser.getCodec().readTree(parser);
        return deserialize(rootNode, ctxt, parser.getCodec());
    }

    private Users deserialize(final JsonNode rootNode, final DeserializationContext ctxt, final ObjectCodec codec) throws IOException {
        if (rootNode == null) {
            throw new IOException("Root node is null");
        }

        JsonNode usersNode = rootNode.get("users");
        if (usersNode == null || !usersNode.isArray()) {
            throw new IOException("Expected 'users' key with an array of users");
        }

        List<User> userList = new ArrayList<>();
        for (JsonNode userNode : usersNode) {
            // Here we can use the codec directly from the parser
            User user = codec.treeToValue(userNode, User.class);
            userList.add(user);
        }
        return new Users(userList);
    }
}




