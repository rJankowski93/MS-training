docker build -f Dockerfile -t config-server:v1 .
docker run -p 8888:8888 --name config-server config-server:v1
