docker build -f Dockerfile -t registration:v1 .
docker run -p 8082:8082 --name registration registration:v1
