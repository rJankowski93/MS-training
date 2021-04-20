docker build -f Dockerfile -t zuul:v1 .
docker run -p 8080:8080 --name zuul zuul:v1
