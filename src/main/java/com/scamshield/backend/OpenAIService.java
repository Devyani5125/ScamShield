package com.scamshield.backend;

import org.springframework.stereotype.Service;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

@Service
public class OpenAIService {

    private final OpenAIClient client;

    public OpenAIService() {
        client = OpenAIOkHttpClient.fromEnv();
    }

    public String analyzeWithAI(String message) {

        ResponseCreateParams params =
                ResponseCreateParams.builder()
                        .input(
                            "You are ScamShield, a cybersecurity scam detection assistant. " +
                            "Analyze the following message for possible scam or phishing behavior. " +
                            "Explain the suspicious elements clearly and briefly. " +
                            "Mention social engineering tactics if present. " +
                            "Do not claim certainty. Treat the result as a risk assessment.\n\n" +
                            "Message:\n" + message
                        )
                        .model(ChatModel.GPT_5_2)
                        .build();

        Response response =
                client.responses().create(params);

        return response.output()
                .stream()
                .flatMap(item -> item.message().stream())
                .flatMap(messageItem -> messageItem.content().stream())
                .flatMap(content -> content.outputText().stream())
                .map(outputText -> outputText.text())
                .findFirst()
                .orElse("No AI analysis was returned.");
    }
}