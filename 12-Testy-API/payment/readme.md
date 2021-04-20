docker build -f Dockerfile -t payment:v1 .
docker run -p 8083:8083 --name payment payment:v1
