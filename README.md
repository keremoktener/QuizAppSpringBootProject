# Quiz Spring Boot Project

This is a quiz application developed using Spring Boot and Java. It can be used to create a set of questions and answers for a quiz.

## Technologies Used

- Java
- Spring Boot
- Gradle

## Features

- Create, update, and delete questions
- Create, update, and delete answers for a specific question
- Fetch all questions and answers

## Setup and Installation

1. Clone the repository to your local machine.
2. Run the `./QuizSpringBootProjectApplication.class`.
3. The application will start on `http://localhost:9090`.
4. Use the endpoints mentioned below to interact with the application.
5. The application uses the `Lombok` library to reduce boilerplate code. Make sure to install the Lombok plugin in your IDE.
6. The application uses the `MapStruct` library to map entities to DTOs. Make sure to install the MapStruct plugin in your IDE. 
7. The application uses the `Swagger` library to document the REST API. You can access the Swagger UI by visiting `http://localhost:9090/swagger-ui.html`.

## Usage

### Question Controller

- `POST /question/save`: Create a new question with answers.
- `PUT /question/update`: Update a question with answers.
- `DELETE /question/delete`: Delete a question by ID.
- `GET /question/findAll`: Fetch all questions.
- `GET /question/findById`: Fetch a question by ID.

### Answer Controller

- `DELETE /answer/deleteByQuestionId`: Delete answers by question ID.
- `POST /answer/save`: Save answers for a specific question.
- `PUT /answer/update`: Update answers for a specific question.
- `GET /answer/findAll`: Fetch all answers.
- `GET /answer/findByQuestionId`: Fetch answers by question ID.
