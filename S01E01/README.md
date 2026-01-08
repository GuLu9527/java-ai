# S01E01 5分钟跑通AI对话

## 视频链接
- [抖音](待更新)

## 本期内容
- 系列介绍：6季31期，学完能独立开发AI应用项目
- Spring AI + iFlow 快速配置
- 一行代码调用大模型

## 环境要求
- JDK 17+
- Maven 3.8+
- iFlow API Key（免费：https://iflow.cn）

## 快速开始

### 1. 设置API Key

复制 `.env.example` 为 `.env`，填入你的 API Key：

```bash
# 复制配置文件
cp .env.example .env
```

编辑 `.env` 文件：
```properties
IFLOW_API_KEY=你的密钥
```

> 💡 项目使用 [spring-dotenv](https://github.com/paulschwarz/spring-dotenv) 自动加载 `.env` 文件，无需手动设置环境变量

### 2. 运行
```bash
mvn spring-boot:run
```

### 3. 测试
```bash
curl "http://localhost:8080/chat?message=你好"
```

## 核心代码

```java
@GetMapping("/chat")
public String chat(@RequestParam String message) {
    return chatClient.prompt()
            .user(message)
            .call()
            .content();
}
```

## 课后练习

1. **换模型**：修改`application.yml`中的`model`为`deepseek-v3-671b`，观察效果
2. **调参数**：修改`temperature`为0.1和1.0，对比输出差异
3. **加系统提示**：在代码中添加`.system("你是一个Java专家")`，观察回答风格变化

## 常见问题

见 [FAQ.md](../FAQ.md)

常见问题速查：
- 连接超时：检查网络，iFlow国内直连
- 401错误：API Key错了或.env没配置
- 模型不存在：检查model名称拼写
- 404错误：base-url多加了/v1

## 下期预告

**S01E02 流式输出+上下文记忆**
- 打字机效果（像ChatGPT那样一个字一个字出来）
- 多轮对话（记住之前说过什么）
