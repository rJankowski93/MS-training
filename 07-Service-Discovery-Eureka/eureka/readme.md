docker build -f Dockerfile -t eureka:v1 .
docker run -p 8761:8761 --name eureka eureka:v1
