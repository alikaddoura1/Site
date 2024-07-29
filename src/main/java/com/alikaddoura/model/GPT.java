package com.alikaddoura.model;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GPT {

    private static final String URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "sk-None-TW9TqivGQOpez6J4996JT3BlbkFJBPLBkB6DyuHyjCwn8gze";
    private static final String MODEL = "gpt-3.5-turbo";
    private ArrayList<String> conversationHistory;

    public GPT() {
        this.conversationHistory = new ArrayList<>();
    }

    public String chatGPT(String prompt) {
        conversationHistory.add("{\"role\": \"user\", \"content\": \"" + prompt + "\"}");

        try {
            URL obj = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setRequestProperty("Content-Type", "application/json");

            // Build the conversation history into the request body
            StringBuilder messages = new StringBuilder();
            for (String message : conversationHistory) {
                messages.append(message).append(",");
            }

            // Remove the trailing comma
            if (messages.length() > 0) {
                messages.setLength(messages.length() - 1);
            }

            String body = "{\"model\": \"" + MODEL + "\", \"messages\": [" + messages.toString() + "]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // Extract the message and add it to the history
            String responseMessage = extractMessageFromJSONResponse(response.toString());
            conversationHistory.add("{\"role\": \"assistant\", \"content\": \"" + responseMessage + "\"}");

            return responseMessage;

        } catch (IOException e) {
            return "shi didnt work";
        }
    }

    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content") + 11;
        int end = response.indexOf("\"", start);
        return response.substring(start, end);
    }

    public String getOutput(String question) {
        return chatGPT(question);
    }

    public static void main(String[] args) {
        GPT gpt = new GPT();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("What is your question? (Type 'exit' to quit)");
            String question = input.nextLine();

            if (question.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println(gpt.getOutput(question));
        }

        input.close();
    }
}

