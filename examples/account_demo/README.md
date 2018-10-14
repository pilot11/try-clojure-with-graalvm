# 账号注册服务的一个demo

这个demo简单示范一个web服务的构建.包括:
* 加载配置文件
* 启动http服务
* 路由http请求
* 业务逻辑
* 集成业务流程
* 数据库存取
* 日志输出
* 单元测试

## 使用命令
* 测试
    ```
    lein test
    ```
* 打jar包
    ```
    lein uberjar
    ```
* 运行jar包
    ```
    java -cp ./etc:./target/account-demo-1.0.0-SNAPSHOT-standalone.jar clojure.main -e "(use 'account.main)(-main \"etc/config.edn\")"
    ```

## 作者

陈建业
pilot11@163.com

## License
Copyright 风林火山 2018
