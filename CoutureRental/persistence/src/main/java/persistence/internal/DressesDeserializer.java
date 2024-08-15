package persistence.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import core.Dress;
import core.Dresses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class DressesDeserializer extends JsonDeserializer<Dresses> {
    /**
     * {@inheritDoc}
     */
    @Override
    public Dresses deserialize(final JsonParser parser, final DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode treeNode = parser.getCodec().readTree(parser);
        return deserialize(treeNode);
    }

    /**
     * Deserialize a dresses object.
     * @param jsonNode
     * @return a dresses object.
     */
    Dresses deserialize(final JsonNode jsonNode) throws IOException {
        if (!jsonNode.isObject()) {
            throw new IOException("Expected JSON object for Dresses");
        }

        JsonNode dressesNode = jsonNode.get("dresses");
        if (dressesNode == null || !dressesNode.isArray()) {
            throw new IOException("Expected 'dresses' array in the JSON object");
        }

        List<Dress> dressList = new ArrayList<>();
        for (JsonNode dressNode : dressesNode) {
            String id = dressNode.get("id").asText();
            Boolean isAvailable = dressNode.has("isAvailable") ? dressNode.get("isAvailable").asBoolean() : false; // Assuming false as default
            dressList.add(new Dress(id, isAvailable));
        }
        return new Dresses(dressList);
    }
}
