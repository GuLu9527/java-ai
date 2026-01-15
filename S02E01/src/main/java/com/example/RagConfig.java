package com.example;

import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RagConfig {

    @Value("${lmstudio.base-url}")
    private String lmstudioBaseUrl;

    @Value("${lmstudio.model}")
    private String lmstudioModel;

    @Bean
    OpenAiEmbeddingModel embeddingModel() {
        OpenAiApi api = OpenAiApi.builder()
                .baseUrl(lmstudioBaseUrl)
                .apiKey("lm-studio")
                .build();
        
        OpenAiEmbeddingOptions options = OpenAiEmbeddingOptions.builder()
                .model(lmstudioModel)
                .build();
        
        return new OpenAiEmbeddingModel(api, MetadataMode.EMBED, options);
    }

    @Bean
    SimpleVectorStore vectorStore(OpenAiEmbeddingModel embeddingModel) {
        return SimpleVectorStore.builder(embeddingModel).build();
    }
}
