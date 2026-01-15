# S02E01 RAG原理与最简实现

## 视频链接
- [抖音](链接待补充)

## 本期内容
- RAG（检索增强生成）原理
- 向量检索与语义匹配
- Spring AI + LM Studio 本地 Embedding
- 员工手册问答系统实战

## 技术架构

| 组件 | 选择 | 说明 |
|-----|------|------|
| Chat模型 | iFlow云端 | qwen3-coder-plus |
| Embedding模型 | LM Studio本地 | nomic-embed-text-v1.5 |
| 向量库 | SimpleVectorStore | 内存存储，开发用 |

## 环境要求
- JDK 17+
- Maven 3.8+
- iFlow API Key（免费：https://iflow.cn）
- LM Studio（本地 Embedding）

## 快速开始

### 1. 配置 LM Studio

1. 下载安装 [LM Studio](https://lmstudio.ai/)
2. 搜索并下载 `nomic-embed-text-v1.5` (Q4_K_M 版本，84MB)
3. 进入 "Local Server" 标签页
4. 加载模型，点击 "Start Server"
5. 确认服务运行在 `http://127.0.0.1:1234`

### 2. 进入代码目录
```bash
cd S02E01
```

### 3. 设置 API Key

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

### 4. 运行
```bash
mvn spring-boot:run
```

### 5. 测试

**命令行测试**：
```bash
curl "http://localhost:8080/rag?question=请假需要提前多久"
curl "http://localhost:8080/rag?question=加班有补贴吗"
```

**浏览器测试**：
访问 http://localhost:8080 使用前端页面

## 代码结构

```
S02E01/
├── pom.xml
├── .env                    # API Key（不提交）
├── .env.example            # API Key 示例
└── src/main/
    ├── java/com/example/
    │   ├── RagApplication.java
    │   ├── RagConfig.java              # 配置 EmbeddingModel 和 VectorStore
    │   ├── DocumentLoader.java         # 加载知识库
    │   └── RagController.java          # RAG 接口
    └── resources/
        ├── application.yml
        ├── knowledge.txt               # 知识库文件
        └── static/index.html           # 前端页面
```

> LM Studio 支持 OpenAI 兼容接口，直接用 Spring AI 的 `OpenAiEmbeddingModel`，不需要自定义类

## 课后练习
1. 修改 knowledge.txt，添加你自己的知识内容
2. 调整 similarityThreshold 和 topK 参数，观察效果变化
3. 尝试问一些知识库里没有的问题，看 AI 如何回答

## 常见问题

### LM Studio 连接失败
- 确认 LM Studio 服务已启动
- 确认端口是 1234
- 确认模型已加载

### 检索不到相关内容
- 降低 similarityThreshold（如 0.3）
- 检查知识库内容是否正确加载

### 回答不准确
- 提高 similarityThreshold（如 0.7）
- 增加 topK 返回更多相关文档

见 [FAQ.md](../FAQ.md)

## 下期预告
S02E02：文档处理与切分 - PDF/Word 怎么解析
