# car-tracker-IoT
Docker containerized Spring Boot Web application for tracking Car sensor readings over time. 
The application is RESTFul and runs on Amazon EC2 with MySQL RDS for the backend. 
The build and deploy processes has been automated using CI/CD pipeline in Jenkins.

API Endpoints supported - 

* POST - /vehicle/addReadings
* GET - /car/getCarInfo
* PUT - /car/loadCarList
* GET - /getCarAlerts
* Endpoint to fetch high alerts within last 2 hours for all the cars (sorted by time) - GET - /getRecentHighAlerts
* Endpoint to get geo information of a vehicle for last 30 mins - GET - /vehicle/getGeoInfoByVin/{vin}
* GET - /getAlertsByVin/{vin}

Supports email notifications with **AWS SNS** for high alert warnings - HIGH engine RPM alerts

Instructions to build and run -

* gradlew clean build
* docker build -t image_name .
* docker run --env-file prop.env -p 8085:8080 image_name

Jenkins CI/CD for automated build and run

* Create and run an AWS EC2 instance
* Install java8 on EC2 if not found
* Install jenkins [Jenkins documentation](https://www.jenkins.io/doc/book/installing/#debianubuntu)
* sudo service jenkins start
* Login using password from - cat /var/lib/jenkins/secrets/initialAdminPassword
* Create a pipeline with Git pull repo (webhook), Jenkinsfile and other configurations 
