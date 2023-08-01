package chatbot.infrastructure;

public interface Serializer {
    String serialize(Object toSerialize);

    <T> T deserialize(String toDeserialize, Class<T> clazz);
}
