# S01E02 流式输出与上下文记忆

## 视频链接
- [抖音](链接待更新)

## 本期内容
- 流式输出：`call()` 换成 `stream()`，实现打字机效果
- 上下文记忆：`ChatMemory` 让AI记住多轮对话
- SSE（Server-Sent Events）原理

## 环境要求
- JDK 17+
- Maven 3.8+
- iFlow API Key（免费：https://iflow.cn）

## 快速开始

### 1. 进入代码目录
```bash
cd S01E02
```

### 2. 设置API Key

**方式一：使用 .env 文件（推荐）**
```bash
# 复制配置文件
cp .env.example .env
# 编辑 .env 文件，填入 IFLOW_API_KEY=你的密钥
```

**方式二：使用环境变量**
```bash
export IFLOW_API_KEY=你的密钥
```

### 3. 运行
```bash
mvn spring-boot:run
```

### 4. 测试

**测试流式输出（浏览器）**
```
打开浏览器访问 http://localhost:8080
输入消息，点发送，观察打字机效果
```

**测试流式输出（curl）**
```bash
curl "http://localhost:8080/stream?message=讲个笑话"
```

**测试多轮对话**
```
在页面中：
1. 发送"我叫张三"
2. 再发送"我叫什么"
AI会回答"你叫张三"
```

## 核心代码

### 流式输出
```java
@GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public Flux<String> stream(@RequestParam String message) {
    return chatClient.prompt()
            .user(message)
            .stream()  // 关键：call() 换成 stream()
            .content();
}
```

### 上下文记忆
```java
// 配置 ChatMemory
@Bean
public ChatMemory chatMemory() {
    return MessageWindowChatMemory.builder()
            .maxMessages(20)
            .build();
}

// 使用时传入 sessionId
chatClient.prompt()
        .user(message)
        .advisors(a -> a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, sessionId))
        .call()
        .content();
```

## 课后练习
1. 试试不同的 `maxMessages` 值，观察记忆效果
2. 用浏览器直接访问 `/stream` 接口，观察流式效果
3. 用不同的 `sessionId` 测试，验证会话隔离

## 常见问题
见 [FAQ.md](../FAQ.md)

## 下期预告
S01E03：Web界面+本地部署 - 做个真正能用的网页聊天应用，打包成jar一键运行
