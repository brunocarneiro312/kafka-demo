![](https://kafka.apache.org/images/logo.png)

### Apache Kafka Demo

Demonstração simples de Producer e Consumer utilizando o Apache Kafka

**Instalação**
Download: https://kafka.apache.org/downloads

1. Após o download do Apache Kafka, extraia a pasta e mova o diretório extraído para a pasta raiz (ex: **c:/kafka**)

2. Crie a pasta data (**c:/kafka/data**)

3.  Dentro da pasta data, crie os diretórios **kafka** e **zookeeper**

4. Edite o arquivo server.properties (**c:/kafka/config/server.properties**), alterando a chave log.dirs apontando para a pasta **c:/kafka/data/kafka**

5. Edite o arquivo zookeeper.properties (**c:/kafka/config/zookeeper.config**), alterando a chave dataDir apontando para a pasta **c:/kafka/data/zookeeper**

6.  Crie uma variável de ambiente no sistema com o nome **KAFKA_HOME** atribuindo o valor da pasta raiz do kafka (no nosso caso a pasta **c:/kafka**)

7.  Altere a variável de ambiente **Path** adicionando o valor **%KAFKA_HOME%\bin\windows** (a partir desse momento, os comandos do kafka deverão ser reconhecidos pelo prompt de comandos)

8. Abra o terminal e inicialize o zookeeper através do comando
`zookeeper-server-start.bat`

9.  Abra um segundo terminal e inicialize o kafka
`zookeeper-server-start.bat`

10. Abra um terceiro terminal e crie um tópico com o comando
`kafka-topics --bootstrap-server localhost:9092 --create --topic testTopic`

A partir desse momento as classes Producer e Consumer podem ser executadas para produzir e consumir mensagens pelo Java.
