# oracle-java-spring-dss

[Abishek Arumugam Thiruselvi](https://www.abishekarumugam.com)

# Running the Application in Eclipse
#### Prerequisites: Eclipse IDE plugins Buildship Gradle Integration 3.0, Spring Tools 3, Git, Java 17 or greater, Maven 3.9 or higher and Gradle 7.6 or higher.

1. Open Git Perspective in Eclipse and clone this [repository]([https://github.com/abishekat/oracle-spring-dss-assignment-1](https://github.com/abishekat/dss-assignment-1))

2. Import the project into project explorer.

3. Right-click on the project in the project explorer 

   ```shell
   run -> clean install
   ```
4. To run the application right-click on the project in the project explorer 

   ```shell
   run > java application / junit
   ```
5. To run the application on terminal 

   ```shell
   mvn spring-boot:run
   ```

# Running the Application in Oracle Cloud VM

####  Follow the steps from this [URL](https://github.com/youyinnn/distributed_system_jetty_helloworld/blob/main/Oracle%20Cloud%20VM%20Setup.md) and clone this project

To run the application on Cloud VM 

   ```shell
    mvn spring-boot:run
   ```
To test the application on Postman or in OpenAPI

   ```shell
   http://<public-ip>:8080/audio/api/
   ```
   
# OpenAPI
#### OpenAPI test works only for cloud deployment. Does not work for localhost. Because of firewall conflict on port 22.
#### For localhost openapi test to work run the spring application and got to [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)


## RESULTS

#### Client Vs. Oracle VM Time to complete request
![](src/main/resources/images/Oracle-Client.png)

#### Client Vs. Localhost Time to complete request
![](src/main/resources/images/localhost-client.png)

#### Create Audio Item - OpenAPI
![](src/main/resources/images/post-create.png)

####  Get: Copies Sold – OpenAPI
![](src/main/resources/images/copies-sold.png)

####  Get: Artist by ID – OpenAPI
![](src/main/resources/images/get_id.png)

####  Get: Artist by name and Property – OpenAPI
![](src/main/resources/images/name-property.png)

####  Get: Artist by Name – OpenAPI
![](src/main/resources/images/get-name.png)

####  Get: All Artist in JSON– OpenAPI
![](src/main/resources/images/all-artist-json.png)

####  Get: Copies sold before creating and audio item.
![](src/main/resources/images/before-copies-sold.png)

####  Get: Copies sold after creating and audio item.
![](src/main/resources/images/after-post-copies-sold.png)

#### Project Structure
![](src/main/resources/images/project-structure.png)

## Concurrent Clients Test logs file

```shell
   PATH : src/main/resources/console-oracle.logs
   PATH : src/main/resources/console-localhost.logs
   ```

### UNIVERSITY

 [CONCORDIA UNIVERSITY](https://www.concordia.ca/).
  
