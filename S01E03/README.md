# S01E03 System Prompt与角色扮演

## 视频链接
- [抖音](链接待更新)

## 本期内容
- 完整聊天界面（消息气泡、流式显示、角色切换）
- Thinking模型概念介绍
- System Prompt与角色扮演（代码审查、SQL生成、周报助手）
- 多模型切换（运行时动态切换）
- PromptTemplate（动态变量）

## 环境要求
- JDK 17+
- Maven 3.8+
- iFlow API Key（免费：https://iflow.cn）

## 快速开始

### 1. 进入代码目录
```bash
cd S01E03
```

### 2. 设置API Key
```bash
cp .env.example .env
# 编辑 .env 文件，填入 IFLOW_API_KEY=你的密钥
```

### 3. 运行
```bash
mvn spring-boot:run
```

### 4. 访问
打开浏览器访问 http://localhost:8080

## 接口说明

| 接口 | 说明 |
|-----|------|
| `/chat?message=xxx&role=xxx&model=xxx` | 普通对话（支持角色和模型切换） |
| `/stream?message=xxx&role=xxx&model=xxx` | 流式对话 |
| `/support?message=xxx&company=xxx&name=xxx` | 客服模式（PromptTemplate演示） |

## 角色类型
- `default` - 通用助手
- `reviewer` - 代码审查
- `sql` - SQL生成
- `weekly` - 周报助手
- `support` - 客服模式（前端切换，调用 /support 接口）

## 课后练习
1. 添加一个"英语翻译"角色
2. 修改客服模式的公司名和客服名，测试效果
3. 尝试不同模型，对比回复质量和速度

## 常见问题
见 [FAQ.md](../FAQ.md)

## 第一季完结
恭喜你完成了第一季的学习！下一季我们将学习RAG企业知识库。
