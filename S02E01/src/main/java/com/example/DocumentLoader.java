package com.example;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentLoader implements CommandLineRunner {

    private final VectorStore vectorStore;

    @Value("classpath:knowledge.txt")
    private Resource knowledgeFile;

    public DocumentLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Override
    public void run(String... args) {
        System.out.println("开始加载知识库...");
        
        TextReader reader = new TextReader(knowledgeFile);
        reader.setCharset(StandardCharsets.UTF_8);
        List<Document> docs = reader.get();
        
        // 按行切分
        List<Document> splitDocs = new ArrayList<>();
        for (Document doc : docs) {
            String[] lines = doc.getText().split("\\r?\\n");
            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    splitDocs.add(new Document(line.trim()));
                }
            }
        }
        
        vectorStore.add(splitDocs);
        System.out.println("文档加载完成，共 " + splitDocs.size() + " 条");
    }
}
