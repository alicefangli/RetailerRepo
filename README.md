﻿# RetailerRepo
A Retailer Customer Points Application

1. This spring boot microservice used spring MVC architecture to process a retailer reward to customers

2. Currently datasource is not set yet.
relational database has two tables as following:
![image](https://github.com/alicefangli/RetailerRepo/assets/130603197/6a75999f-cf7c-4dfc-950e-48336593f143)
![image](https://github.com/alicefangli/RetailerRepo/assets/130603197/472279fa-47ca-44f0-97dd-c1416762ac11)

3. Logging: slf4j is for logging.

4. Health check: spring actuator is used for health check. Please see the health check endpoint in /RetailerRepo/doc/postman-test.docx

5. JUnit 5 is for unit testing. Test cases running results are shown at /RetailerRepo/doc/JUnitTest-screenshot.docx

6. REST API endpoints are shown at /RetailerRepo/doc/swagger_ui_endpoints.png file. Those endpoints are demonstrated with testing data in /RetailerRepo/doc/postman-test.docx


