# api-movies-data
Database of movie searches API

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup and Installation](#setup-and-installation)
* [How to use with Swagger UI](#how-to-use-with-Swagger-UI)
* [How to use with Postman](#how-to-use-with-Postman)
* [Contact](#contact)

## General info
This Rest API is developed using Java language and Spring boot framework. In this project API is programed to make a search which will look in the database for existing data and throw results for a movie which matches the name with search term. Each movie entry has a way of displaying movie details. 

If a movie is not found locally, it searches for the movie online using one of the available online resources. When a new movie is found, the data of the movie is stored in a local database. Search results can show listings of all movies that match the search criteria with details about the movie. 
While running jar file for first time whole database structure is being initialized using JPA and Hibernate. It also uses external PostgreSQL database to fetch some prerecorded values. 

## Technologies
* Java - version 11.0.7
* Spring Boot - version 2.3.2
* Spring Data JPA - version 2.4.0
* PostgreSQL - version 13.0.1
* Apache Maven - version 3.6.0
* Hibernate ORM - version 5.4.18
* Springfox Swagger 2- version 2.9.2
* Jackson datatype jsr310

## Setup and Installation

1. #### Download or clone the repository from GitHub

```
git clone https://github.com/vgrljusic/api-movies-data.git
cd api-movies-data
```

2. #### Install required programs

In order to follow along user needs to have PostgreSQL and Postman (you can use Swagger UI insted of Postman). Bellow are short terminal lines for easy installation.
```
sudo apt update
sudo snap install postman
sudo apt install postgresql postgresql-contrib
```

3. #### Setup database project

In the root application directory (api-movies-data), SQL script file (project_setup.sql) is present for creating database. Note: if you already have other user you can use that insted of postgres user referenced bellow

##### Run the script using psql client: 

```
cd Documents/api-movies-data
psql -U postgres --file project_setup.sql
# promt to enter postgres user password (usuall password is postgres)
```

4. #### (Optional) Update database configurations in application.properties

If you have changed defualt user for creating database with some different username and password, update the src/main/resources/application.properties file accordingly:

```
spring.jpa.hibernate.ddl-auto=create #for first time running MUST be set to create, for every consecutive time set to update (if you care to have permanent database, otherwise it is deleted after every consecutive jar run)
spring.datasource.url=jdbc:postgresql://localhost:5432/project_db
spring.datasource.username=postgres
spring.datasource.password=postgres

```

5. #### Run the spring boot application

If you download/clone repo elsewhere, change path update accordingly

```
cd Documents/api-movies-data
mvn clean install
java -jar target/api-movies-data-0.0.1-SNAPSHOT.jar
```

this runs at port 8080 and hence all endpoints can be accessed starting from http://localhost:8080

6. #### Create database objects (If you want some prerecorded values in local database)

In the root application directory (api-movies-data), SQL script file (project_db.sql) is present for populating database with some records

##### Run the script using psql client: 

```
cd Documents/api-movies-data
psql -U postgres --file project_db.sql
```

## How to use with Swagger UI

You can use Swagger UI from http://localhost:8080/swagger-ui.html

![image](https://user-images.githubusercontent.com/52451893/104134890-bf9a0100-538c-11eb-9ba4-0ed29cf273ce.png)


It has inbuilt functionalities to try out the API without having to install and use Postman 

![image](https://user-images.githubusercontent.com/52451893/104135183-b316a800-538e-11eb-9f34-7b46ac37f550.png)



## How to use with Postman


1. #### Provide Headers

In Headers tab set KEY to Content-Type and VALUE application/json

![image](https://user-images.githubusercontent.com/52451893/104134226-9aa38f00-5388-11eb-8786-b5ade1411f51.png)


2. #### POST items

In order to insert custom new movie in local database use JSON object (provide name and summary)

![image](https://user-images.githubusercontent.com/52451893/104134388-78f6d780-5389-11eb-9a03-22ed46dfa7f7.png)


3. #### GET all movies online

Example of fetching all similarly named movies on http://api.tvmaze.com and find out more about their plot

![image](https://user-images.githubusercontent.com/52451893/104134474-fd495a80-5389-11eb-832e-2424d37778e6.png)


4. #### GET single movie online

Searches for single movie on http://api.tvmaze.com and saves in local database

![image](https://user-images.githubusercontent.com/52451893/104134555-a001d900-538a-11eb-9d35-98d0adab750f.png)

5. #### GET single movies in local database

Example of searching for single movie on http://api.tvmaze.com, afterwards it saves it in local database

![image](https://user-images.githubusercontent.com/52451893/104134588-d8091c00-538a-11eb-8c2a-0cf0bbaae3ce.png)


6. #### GET all movies in local database

Example of fetching all movies in local database

![image](https://user-images.githubusercontent.com/52451893/104134833-5f0ac400-538c-11eb-8172-0537a2eef8ce.png)



## Contact
Created by [@vgrljusic](https://www.linkedin.com/in/vgrljusic/) - feel free to contact me!
