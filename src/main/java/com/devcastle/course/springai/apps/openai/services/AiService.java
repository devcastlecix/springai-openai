package com.devcastle.course.springai.apps.openai.services;

import com.devcastle.course.springai.apps.openai.models.*;

import java.util.Map;

public interface AiService {
    String greeting(String name);
    String chat(String prompt);
    String chatExpertSpring(String prompt);

    CodeDto generateCode(Requirement requirement);
    String explain(String code);
    String chatFormat(String topic);
    String analyze(String text);
    CityInfo cityInfo(String city);
    TicketClassification classifyTyped(String text);
    CodeGeneration generateCodeTyped(String requirement);
    CodeExplanation explainCodeTyped(String code);
    ArchitectEntity expertArchitectTyped(String prompt);
    Map<String, Object> chatMetadata(String prompt);
}
