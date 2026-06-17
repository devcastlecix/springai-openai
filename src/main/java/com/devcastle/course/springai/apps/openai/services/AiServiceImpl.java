package com.devcastle.course.springai.apps.openai.services;

import com.devcastle.course.springai.apps.openai.models.*;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AiServiceImpl implements AiService {
    private final ChatClient chatClient;
    public AiServiceImpl(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public String greeting(String name) {
        return this.chatClient
                .prompt()
                .system("Responde siempre en ingles y en una sola linea.")
                .user("Dime Hola Mundo con mi nombre: %s".formatted(name))
                .call()
                .content();
    }

    @Override
    public String chat(String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    @Override
    public String chatExpertSpring(String prompt) {
        return chatClient.prompt()
                .system("Eres un experto en Java y Spring Boot. Responde de forma clara y simple.")
                .user(prompt)
                .call()
                .content();
    }

    @Override
    public CodeDto generateCode(Requirement requirement) {
        String code = chatClient.prompt()
                .system("""
                        Eres un desarrollador senior experto en java, jakarta y spring boot 4.
                        Con buenas practicas, responde solo preguntas o requerimientos relacionado a java y spring boot.
                        Nada mas, ningun otro lenguaje ni contexto, solo programacion java.
                        """)
                .user(requirement.requirement())
                .call()
                .content();
        return new CodeDto(code);
    }

    @Override
    public String explain(String code) {
        PromptTemplate promptTemplate = new PromptTemplate("Explica el codigo linea por linea: {code}");
        String userPrompt = promptTemplate.render(Map.of("code", code));

        return chatClient.prompt()
                .system("""
                Eres un profesor experto en programacion. 
                Explica paso a paso de forma sencilla.
                """)
                .user(userPrompt)
                .call()
                .content();
    }

    @Override
    public String chatFormat(String topic) {
        return chatClient.prompt()
                .system("""
                        Eres un experto en tecnologia.
                        Responde resumido, no mas de 2 lineas por puntos, usando:
                        - titulo
                        - lista de 3 puntos importantes
                        - un ejemplo practico
                        """)
                .user(topic)
                .call().content();
    }

    @Override
    public String analyze(String text) {
        return chatClient.prompt()
                .system("""
                        Eres un experto en analisis de texto.
                        Resume el siguiente texto en 3 puntos claves:
                        Devuelve solo json valido.
                        Formato exacto:
                        {
                            "summary": "string",
                            "key_points": ["string", "string", "string"],
                            "sentiment": "positive|neutral|negative"
                        }
                        """)
                .user(text)
                .call().content();
    }

    @Override
    public CityInfo cityInfo(String city) {
        return chatClient.prompt()
                .system("""
                        Eres un asistente experto en geografia.
                        Responde solo con la informacion correcta y en formato json.
                        """)
                .user("""
                        Devuelveme la informacion en espanol de la ciudad %s con este formato:
                        {
                            "city": "string",
                            "country": "string",
                            "population": number,
                            "description": "string"
                        }
                        """.formatted(city))
                .call().entity(CityInfo.class);
    }

    @Override
    public TicketClassification classifyTyped(String text) {
        return chatClient.prompt()
                .system("""
                        Eres un sistema de clasificacion de tickets.
                        Responde solo en JSON valido.
                        """)
                .user("""
                        Clasifica el siguiente texto en una categoria y prioridad.
                        Categorias:
                        - Soporte
                        - Ventas
                        - Reclamo
                        
                        Formato:
                        {
                            "category": "string",
                            "reason": "string",
                            "priority": number
                        }
                        
                        Texto:
                        %s
                        """.formatted(text))
                .call().entity(TicketClassification.class);
    }

    @Override
    public CodeGeneration generateCodeTyped(String requirement) {
        return chatClient.prompt().system("""
                Eres un desarrollador experto y senior en java y spring boot.
                Genera una solucion limpia con buenas practicas.
                Devuelve solo JSON valido.
                No uses markdown.
                Ni pongas ```java ni ```json.
                Con el formato exacto del json:
                {
                    "requirement": "string",
                    "summary": "string",
                    "code": "string",
                    "notes": ["string", "string"]
                }
                
                """)
                .user(requirement)
                .call().entity(CodeGeneration.class);
    }

    @Override
    public CodeExplanation explainCodeTyped(String code) {
        return chatClient.prompt()
                .system("""
                        Eres un profesor experto en programacion.
                        Explica el codigo en español de forma simple paso a paso y linea por linea.
                        Devuelve solo el JSON valido.
                        No uses markdown.
                        Formato exacto:
                        {
                            "language": "string",
                            "summary": "string",
                            "line_by_line": [
                                {
                                    "line": 1,
                                    "explanation": "string"
                                }
                            ],
                            "final_explanation": "string"
                        }
                        """)
                .user(code)
                .call().entity(CodeExplanation.class);
    }

    @Override
    public ArchitectEntity expertArchitectTyped(String prompt) {
        return chatClient.prompt().system("""
                Eres un arquitecto de software experto en microservicios, spring boot y arquitectura de sistemas.
                Devuelve solo JSON valido, no uses markdown.
                Formato exacto:
                {
                    "question": "string",
                    "recommendation": "string",
                    "pros": ["string", "string", "string"],
                    "cons": ["string", "string"],
                    "final_decision": "string"
                }
                """)
                .user(prompt)
                .call().entity(ArchitectEntity.class);

    }

    @Override
    public Map<String, Object> chatMetadata(String prompt) {
        ChatResponse response = chatClient.prompt()
                .user(prompt)
                .call()
                .chatResponse();

        return Map.of("answer", response.getResult().getOutput().getText(),
                "metadata", response.getMetadata());
    }
}
