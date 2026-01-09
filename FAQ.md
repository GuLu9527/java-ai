# 常见问题 FAQ

## 环境问题

### Q: JDK版本要求？
A: JDK 17+，推荐使用JDK 21。

### Q: Maven版本要求？
A: Maven 3.8+。

### Q: IDE推荐？
A: IntelliJ IDEA（社区版即可）。

---

## API问题

### Q: iFlow API Key怎么获取？
A: 
1. 访问 https://iflow.cn
2. 注册登录
3. 在用户设置页面生成API Key
4. 免费使用

### Q: API Key怎么配置？
A: 推荐使用 `.env` 文件（项目使用 spring-dotenv 自动加载）：

1. 复制 `.env.example` 为 `.env`
2. 编辑 `.env` 文件：
```properties
IFLOW_API_KEY=你的密钥
```

也可以使用环境变量：
```bash
# Linux/Mac
export IFLOW_API_KEY=你的密钥

# Windows PowerShell
$env:IFLOW_API_KEY="你的密钥"

# Windows CMD
set IFLOW_API_KEY=你的密钥
```

### Q: 可以用其他模型API吗？
A: 可以，只要兼容OpenAI接口格式。修改`application.yml`中的`base-url`和`model`即可。

---

## 运行问题

### Q: 连接超时怎么办？
A: 
1. 检查网络连接
2. iFlow是国内服务，不需要梯子
3. 检查API Key是否正确

### Q: 401错误？
A: API Key错误或未设置。检查 `.env` 文件是否正确配置，或环境变量是否生效。

### Q: IDEA启动后API调用返回空内容？
A: **最常见原因：`.env` 文件没被加载。**

`spring-dotenv` 从工作目录读取 `.env` 文件。如果你在 IDEA 里打开的是父目录（如 `java-ai`），但 `.env` 在子目录（如 `S01E02/.env`），就会找不到。

**解决方案**（任选一种）：

1. **修改 IDEA 工作目录**：
   - Edit Configurations → 选择你的应用
   - Working directory 改成 `$MODULE_DIR$`（或项目子目录的绝对路径）

2. **在 IDEA 里直接设置环境变量**：
   - Edit Configurations → Environment variables
   - 添加 `IFLOW_API_KEY=你的密钥`

3. **用命令行启动**：在项目目录下运行 `mvn spring-boot:run`

### Q: 404错误？
A: `base-url` 配置多加了 `/v1`。Spring AI 会自动拼接 `/v1/chat/completions`，所以 `base-url` 应该是 `https://apis.iflow.cn`，不要加 `/v1`。

### Q: 模型不存在？
A: 检查`application.yml`中的`model`名称拼写，参考iFlow文档支持的模型列表。

### Q: 端口被占用？
A: 修改`application.yml`中的`server.port`，或关闭占用端口的程序。

---

## 代码问题

### Q: Spring AI版本？
A: 当前使用1.1.2版本。

### Q: Spring AI 依赖名称？
A: Spring AI 1.1.x 版本使用新的 artifact 名称：
- `spring-ai-starter-model-openai`（原 `spring-ai-openai-spring-boot-starter`）
- `spring-ai-client-chat`（ChatClient 所在模块）

### Q: 依赖下载失败？
A: 
1. 检查 pom.xml 是否添加了 Spring 仓库配置（repositories）
2. 检查Maven配置
3. 尝试使用阿里云镜像
4. 清理本地仓库重新下载

---

## 其他问题

### Q: 后续会出Python版本吗？
A: 会的，Java系列完结后会单独开Python系列，深入微调、训练等内容。

### Q: 怎么联系作者？
A: 
- 抖音评论区留言
- GitHub Issues

---

## 更新日志

| 日期 | 更新内容 |
|-----|---------|
| 2026-01-08 | 创建FAQ |
