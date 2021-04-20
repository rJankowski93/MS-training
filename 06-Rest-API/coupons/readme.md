docker build -f Dockerfile -t coupon:v1 .
docker run -p 8081:8081 --name coupon coupon:v1
