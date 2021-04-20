docker build -f Dockerfile -t customers:v1 .
docker run -p 8081:8081 --name customers customers:v1
