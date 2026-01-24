🏥 Hospital Appointment Booking Management System (HABMS)

A Spring Boot Microservices–based backend system designed to manage hospital operations such as Doctor availability, Patient management, and Appointment booking with slot-conflict detection.

This project is built with a real-world enterprise architecture, focusing on clean design, validation, exception handling, and non-CRUD business logic.

🚀 Tech Stack

Java 17

Spring Boot

Spring MVC

Spring Data JPA

Hibernate

Microservices Architecture

Spring Cloud Eureka (Service Discovery)

Spring Cloud Config Server

MySQL

Postman (API Testing)

Git & GitHub

Maven

🧩 Microservices Overview
Service Name	Description
Doctor-Service	Manages doctor details and availability timings
Patient-Service	Manages patient registration and details
Appointment-Service	Handles appointment booking, availability checks, and slot conflicts
HABMS-EurekaServer	Service discovery for all microservices
HABMS-ConfigServer	Centralized configuration management
📂 Project Structure (Mono-Repo)
scalive_project/
│
├── Appointment-Service/
├── Doctor-Service/
├── Patient-Service/
├── HABMS-EurekaServer/
├── HABMS-ConfigServer/
├── README.md
└── .gitignore

✨ Key Features
✅ CRUD Operations

Doctor registration & management

Patient registration & management

✅ Non-CRUD Business Logic (Core Highlight)

Doctor availability time validation

Slot conflict detection

Past date restriction for appointments

Optimistic locking using @Version

EXISTS-based JPQL queries for performance

🔐 Validation & Exception Handling

Bean Validation (@NotNull, @NotBlank, @Positive, etc.)

Centralized Global Exception Handling using @ControllerAdvice

Custom exceptions:

InvalidInputException

ResourceNotFoundException

DoctorNotAvailableException

⚙️ Important Concepts Implemented

DTO / VO Pattern

Layered Architecture

Separation of Concerns

Optimistic Locking

JPQL EXISTS queries

Microservice-to-Database mapping

Metadata fields (createdOn, updatedOn, updateCount)

🧠 Why EXISTS Queries?

Faster than fetching full records

Stops execution once a match is found

Ideal for availability & slot conflict checks

Reduces memory and DB load

🔍 Sample API Flow (Appointment Booking)

Validate appointment date (not past)

Check doctor existence

Validate doctor availability time

Check slot conflict (JPQL EXISTS)

Save appointment if all checks pass

🧪 API Testing

All APIs were tested using Postman with:

Valid payloads

Invalid inputs

Edge cases

Validation & exception scenarios

📌 Future Enhancements

JWT-based authentication & role-based authorization

API Gateway

Centralized logging

Docker & Kubernetes

Frontend integration (React / Angular)

👨‍💻 Author

Sai Lokesh Sammengi
Java Backend Developer | Spring Boot | Microservices
Linkedin : https://www.linkedin.com/in/sammengi-sai-lokesh/
