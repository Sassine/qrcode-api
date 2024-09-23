# QRCode Spring Boot Application
#### A Spring Boot application designed to decode QR Codes from uploaded images and generate QR Codes based on provided content. This application provides a simple RESTful API to handle QR Code operations, making it easy to integrate into your projects or use as a standalone service.

## Features
Decode QR Codes: Upload an image containing a QR Code and retrieve its content.
Generate QR Codes: Create a QR Code image based on provided content, width, and height.
RESTful API: Easy-to-use endpoints for integration with other services or frontend applications.
Spring Boot: Built with Spring Boot for rapid development and ease of deployment.

## Technology Stack
- Java 21
- Spring Boot
- Spring Web
- Maven
- ZXing (for QR Code processing)

## Getting Started
  Prerequisites
- Java Development Kit (JDK) 17 or higher (alter in pom.xml)
- Maven 3.6+
- Git (optional, for cloning the repository)
- Installation
- Clone the Repository


`git clone https://github.com/seu-usuario/qrcode-spring-app.git`

`cd qrcode-spring-app`

## Build the Project
Use Maven to build the project:
`mvn clean install`

## Running the Application
You can run the application using Maven or by executing the generated JAR file.

**Using Maven**
`mvn spring-boot:run`

**Using the JAR File**
After building the project, run:

`java -jar target/qrcode-spring-app-0.0.1-SNAPSHOT.jar`

The application will start on port 8080 with the base URL http://localhost:8080/api.

## API Endpoints
### Decode QR Code
- URL: /api/qrcode/decode
- Method: POST
- Consumes: multipart/form-data
- Description: Upload an image containing a QR Code to decode its content.
- Parameters:
- file (Multipart File): The image file containing the QR Code.
- Response: 200 OK: JSON object with the decoded content.

Example Response
`{
"content": "https://example.com"
}`
### Generate QR Code
- URL: /api/qrcode/generate
- Method: POST
- Consumes: application/json
- Produces: image/png
- Description: Generate a QR Code image based on provided content, width, and height.
Request Body:
`{
"content": "https://example.com",
"width": 300,
"height": 300
}`
Response: 200 OK: PNG image of the generated QR Code.



##### License
Este projeto está licenciado sob a Licença MIT. Veja o arquivo LICENSE para mais detalhes.