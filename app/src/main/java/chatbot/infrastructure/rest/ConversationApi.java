package chatbot.infrastructure.rest;

import chatbot.infrastructure.ApiDeclaration;
import spark.Request;
import spark.Response;
import spark.Service;

public class ConversationApi implements ApiDeclaration {
    @Override
    public void declare(Service service) {
        service.post("/conversations/initialization", this::receive);
        service.post("/conversations/receive", this::receive);
    }

    private String receive(Request request, Response response) {
        return null;
    }
}
