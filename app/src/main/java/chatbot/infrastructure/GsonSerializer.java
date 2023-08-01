package chatbot.infrastructure;

import com.google.gson.Gson;

public class GsonSerializer implements Serializer{

    private final Gson gson = new Gson();

    @Override
    public String serialize(Object toSerialize) {
        return gson.toJson(toSerialize);
    }

    @Override
    public <T> T deserialize(String toDeserialize, Class<T> clazz) {
        return gson.fromJson(toDeserialize, clazz);
    }
}
