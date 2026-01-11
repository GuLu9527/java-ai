package com.example;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
public class ChatController {
    
    private final ChatClient chatClient;
    
    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }
    
    @GetMapping("/chat")
    public String chat(@RequestParam String message,
                       @RequestParam(defaultValue = "default") String role,
                       @RequestParam(defaultValue = "qwen3-coder-plus") String model) {
        return chatClient.prompt()
                .system(getSystemPrompt(role))
                .user(message)
                .options(OpenAiChatOptions.builder().model(model).build())
                .call()
                .content();
    }
    
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream(@RequestParam String message,
                               @RequestParam(defaultValue = "default") String role,
                               @RequestParam(defaultValue = "qwen3-coder-plus") String model) {
        return chatClient.prompt()
                .system(getSystemPrompt(role))
                .user(message)
                .options(OpenAiChatOptions.builder().model(model).build())
                .stream()
                .content();
    }
    
    /**
     * 动态客服示例 - 使用PromptTemplate
     */
    @GetMapping("/support")
    public String support(@RequestParam String message,
                          @RequestParam(defaultValue = "京东") String company,
                          @RequestParam(defaultValue = "小京") String name) {
        PromptTemplate template = new PromptTemplate(Prompts.CUSTOMER_SERVICE);
        String systemPrompt = template.render(Map.of(
                "company", company,
                "name", name
        ));
        
        return chatClient.prompt()
                .system(systemPrompt)
                .user(message)
                .call()
                .content();
    }

    private String getSystemPrompt(String role) {
        return switch (role) {
            case "reviewer" -> Prompts.CODE_REVIEWER;
            case "sql" -> Prompts.SQL_GENERATOR;
            case "weekly" -> Prompts.WEEKLY_REPORT;
            default -> Prompts.DEFAULT;
        };
    }
}
