# Number Search Service

Number Search Service is a simple REST API built with Java and Spring Boot that reads numbers from an Excel file (.xlsx) and returns the N-th largest number.

## **Features**
- Exposes a single REST endpoint available in Swagger UI.
- Accepts a file path and a number **N** as input.
- Reads numbers from an `.xlsx` file.
- Returns the **N-th largest number** without using built-in sorting functions.
- Provides a **custom efficient algorithm** for finding the N-th maximum.

---

## **Technologies Used**
- Java 21
- Spring Boot 3.4.2
- Spring Web
- Springdoc OpenAPI (Swagger)
- Apache POI (for reading Excel files)
- Maven (for dependency management)
- Docker (for containerization)

---

## **API Documentation**
Once the application is running, you can access the **Swagger UI** at:

📌 [Swagger UI](http://localhost:8080/swagger-ui.html)

---

## **Build and Run Locally**
### **Prerequisites**
- Java 21+
- Maven 3.9+
- Ensure the `.xlsx` file exists with a single column of integers.

### **Clone Repository**
```
git clone https://github.com/your-repo/number-search.git
```

### **Build the Project**
```
mvn clean package
```

### **Run the Application**
```
java -jar target/number-search-0.0.1-SNAPSHOT.jar
```

### **Run with Docker**
1. Build the Docker Image
```
   docker build -t number-search .
```
2. Run the Container
```
   docker run -p 8080:8080 number-search
```

Now the service is accessible at http://localhost:8080/swagger-ui.html.