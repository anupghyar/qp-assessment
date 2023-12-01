How to run application on local
 // build the application using maven
/.mvnw install
// build docker image using below command
docker build -t qp-assessment/groceryapp .
// run docker image 
docker run -p 8080:8080 qp-assessment/groceryapp
