# food-ordering-system
Food ordering system application in Udemy course: Microservices: Clean Architecture, DDD, SAGA, Outbox &amp; Kafka


docker-compose -f common.yml -f zookeeper.yml up

echo ruok | nc localhost 2181 

docker-compose -f common.yml -f kafka_cluster.yml up
docker-compose -f common.yml -f init_kafka.yml up



docker compose -f common.yml -f zookeeper.yml -f kafka_cluster.yml -f init_kafka.yml -f create-cluster.yml up -d



./mvnw clean package -DskipTests
./mvnw spring-boot:run


mvn clean install is the same as ./mvnw clean install

./mvnw com.github.ferstl:depgraph-maven-plugin:aggregate -DcreateImage=true -DreduceEdges=false -Dscope=compile "-Dincludes=com.food.ordering.system*:*"

dot -Tpng dependency-graph.dot -o dependency-graph.png