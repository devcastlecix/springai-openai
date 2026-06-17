package com.devcastle.course.springai.apps.openai.controllers;

import com.devcastle.course.springai.apps.openai.models.*;
import com.devcastle.course.springai.apps.openai.services.AiService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AiController {

    private final AiService aiService;
    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(defaultValue = "Luis") String name) {
        return  aiService.greeting(name);
    }

    @PostMapping("/chat")
    public String chat(@RequestBody String prompt) {
        return aiService.chat(prompt);
    }

    @PostMapping("/chat-expert")
    public String chatExpert(@RequestBody String prompt) {
        return aiService.chatExpertSpring(prompt);
    }

    @PostMapping("/generate-code")
    public CodeDto generateCode(@RequestBody Requirement requirement) {
        return aiService.generateCode(requirement);
    }

    @PostMapping("/explain-code")
    public String explainCode(@RequestBody String code) {
        return aiService.explain(code);
    }

    @PostMapping("/chat-format")
    public String chatFormat(@RequestBody String prompt) {
        return aiService.chatFormat(prompt);
    }

    @PostMapping("/analyze")
    public String analyze(@RequestBody String text) {
        return aiService.analyze(text);
    }

    @GetMapping("/city-info")
    public CityInfo cityInfo(@RequestParam String city) {
        return aiService.cityInfo(city);
    }

    @PostMapping("/classify-typed")
    public TicketClassification classifyTyped(@RequestBody String text) {
        return aiService.classifyTyped(text);
    }
    
    @PostMapping("/generate-code-json")
    public CodeGeneration generateCodeJson(@RequestBody String requirement) {
        return aiService.generateCodeTyped(requirement);
    }

    @PostMapping("/explain-code-json")
    public CodeExplanation explainCodeJson(@RequestBody String code) {
        return aiService.explainCodeTyped(code);
    }

    @PostMapping("/expert-architect-json")
    public ArchitectEntity expertArchitectJson(@RequestBody String prompt) {
        return aiService.expertArchitectTyped(prompt);
    }

    @GetMapping("/chat-metadata")
    public Map<String, Object> chatMetadata(@RequestParam String prompt) {
        return aiService.chatMetadata(prompt);
    }
}
