# 注意这是个多分支项目

queryDSL 的使用需要
1. 使用JPA工具根据数据库生成 `Entity` 对象
2. 然后使用 `mvn clean install -DskipsTest=true` 这样就会在 `target` 目录的 `generated-sources` 生成代码
3. 
