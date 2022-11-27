# SpringBootUsingRedis
## Requirements

 + JDK
 + Redis

# Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method of the class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:
- mvn spring-boot:run

# Redis installation

follow this link https://redis.io/docs/getting-started/

# Dependency
````
 <dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-redis</artifactId>
 </dependency>
````
````
<dependency>
		<groupId>redis.clients</groupId>
		<artifactId>jedis</artifactId>
		<version>4.3.1</version>
		<type>jar</type>
</dependency>
````

# About the project

This is small and simple CRUD operation spring boot application using Redis as a database.
It's a small library management system.

# Book Endpoints
  #### Get All Books
    curl --location --request GET 'http://localhost:8080/api/v1/book/GetAllBooks'
#### Add Book
     curl --location --request POST 'http://localhost:8080/api/v1/book/AddNewBook' \
    --header 'Content-Type: application/json' \
    --data-raw '{
     "id": 4,
    "name": "Data Structure and Algorithm",
    "totalCopies": 10,
    "availableCopies": 6
    }'
#### Remove Book
    curl --location --request DELETE 'http://localhost:8080/api/v1/book/id'

#### Get Book by id
     curl --location --request GET 'http://localhost:8080/api/v1/book/id'

# Student Endpoints
#### Get All Students
      curl --location --request GET 'http://localhost:8080/api/v1/student/GetAllStudents'
#### Add Student
     curl --location --request POST 'http://localhost:8080/api/v1/student/AddNewStudent' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "id":4,
    "name":"Nishant",
    "allocatedBook": 1
}'
#### Delete Student
     curl --location --request DELETE 'http://localhost:8080/api/v1/student/id'
#### Get Student by id
     curl --location --request GET 'http://localhost:8080/api/v1/student/id'

#### Allocate Book to Student
    curl --location --request PUT 'http://localhost:8080/api/v1/AllocateBookToStudent?studentId=1&bookId=1'

#### Unallocated Book to Student
    curl --location --request PUT 'http://localhost:8080/api/v1/UnAllocateBookToStudent?studentId=1&bookId=1'

