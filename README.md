# Microservices Online Shopping Backend

A production-oriented **microservices-based online shopping backend** built with Java and Spring Boot.

The project demonstrates how multiple independent microservices communicate with each other, discover services dynamically, and run together in a containerized environment using Docker and Docker Compose.

The complete application has also been deployed and executed on a **Linux VPS**.

---

## 🏗️ Architecture

The system consists of the following services:

* **Eureka Server** — Service discovery
* **API Gateway** — Single entry point for client requests
* **User Service** — User management
* **Product Service** — Product and inventory management
* **Order Service** — Order management and business logic

Each business service has its own database.

```text
                         Client
                           │
                           ▼
                    ┌──────────────┐
                    │ API Gateway  │
                    └──────┬───────┘
                           │
              ┌────────────┼────────────┐
              │            │            │
              ▼            ▼            ▼
       ┌────────────┐ ┌────────────┐ ┌────────────┐
       │   User     │ │  Product   │ │   Order    │
       │  Service   │ │  Service   │ │  Service   │
       └─────┬──────┘ └─────┬──────┘ └─────┬──────┘
             │              │              │
             ▼              ▼              ▼
         User DB        Product DB       Order DB

                    ┌────────────────┐
                    │ Eureka Server  │
                    │ Service        │
                    │ Discovery      │
                    └────────────────┘

                    Docker Network
                           │
                           ▼
                      Linux VPS
```

---

## 🚀 Features

* Microservices architecture
* Service discovery with Eureka
* Centralized API Gateway
* Inter-service communication with OpenFeign
* RESTful APIs
* User management
* Product management
* Inventory/stock management
* Order management
* Independent databases for services
* Docker containerization
* Docker Compose orchestration
* Custom Docker network
* Persistent MySQL volumes
* Linux VPS deployment

---

## 🛠️ Technologies

### Backend

* Java
* Spring Boot
* Spring Cloud
* Spring Cloud Netflix Eureka
* Spring Cloud Gateway
* Spring Cloud OpenFeign
* Spring Data JPA
* REST API
* Maven

### Database

* MySQL

### DevOps

* Docker
* Docker Compose
* Linux
* VPS
* Nginx
* Domain
* HTTPS

### Development Tools

* IntelliJ IDEA
* Postman
* Git
* GitHub

---

## 📦 Microservices

### Eureka Server

Responsible for service discovery.

All microservices register themselves with Eureka and use service names instead of hard-coded service IP addresses.

---

### API Gateway

Acts as the entry point for external clients.

The Gateway receives incoming requests and routes them to the appropriate microservice.

```text
Client
   │
   ▼
API Gateway
   │
   ├──► User Service
   ├──► Product Service
   └──► Order Service
```

---

### User Service

Responsible for user-related operations.

Main responsibilities:

* Create users
* Retrieve users
* Manage user information

---

### Product Service

Responsible for product and inventory management.

Main responsibilities:

* Create products
* Retrieve products
* Manage product quantity
* Update stock

---

### Order Service

Responsible for order management.

The Order Service communicates with other services using **OpenFeign**.

For example:

```text
Order Service
      │
      ├──► User Service
      │
      └──► Product Service
```

Before creating an order, the service can communicate with the required services to validate the user and product information and handle product stock.

---

## 🔗 Service Communication

Inter-service communication is implemented using **Spring Cloud OpenFeign**.

Instead of directly using IP addresses and ports, services communicate through service names registered in Eureka.

Example:

```text
Order Service
      │
      ▼
OpenFeign
      │
      ▼
Product Service
```

This allows services to communicate dynamically inside the Docker environment.

---

## 🐳 Docker Architecture

All services run as Docker containers and communicate through a dedicated Docker network.

```text
                 Docker Network
              microservice-network
                       │
       ┌───────────────┼────────────────┐
       │               │                │
       ▼               ▼                ▼
   Eureka          API Gateway      User Service
       │               │                │
       │               │                ▼
       │               │             User DB
       │               │
       │               ├──────────────► Product Service
       │               │                     │
       │               │                     ▼
       │               │                  Product DB
       │               │
       │               └────────────────► Order Service
       │                                     │
       │                                     ▼
       │                                  Order DB
```

---

## 🐳 Docker Compose

The complete environment can be started using Docker Compose.

The Compose environment includes:

* Eureka Server
* API Gateway
* User Service
* Product Service
* Order Service
* MySQL databases
* Docker network
* Persistent database volumes

Start the application with:

```bash
docker compose up -d
```

Check running containers:

```bash
docker compose ps
```

Stop the application:

```bash
docker compose down
```

---

## 💻 Local Development

### Prerequisites

Make sure the following are installed:

* Java
* Maven
* Docker
* Docker Compose
* Git

Clone the repository:

```bash
git clone <REPOSITORY_URL>
```

Navigate to the project:

```bash
cd online-shopping-microservices
```

Build and start the environment:

```bash
docker compose up -d --build
```

Check the running containers:

```bash
docker compose ps
```

---

## 🌐 VPS Deployment

The complete microservices environment has been deployed to a **Linux VPS**.

The deployment environment uses:

* Linux VPS
* Docker
* Docker Compose
* Containerized Spring Boot applications
* MySQL
* Nginx
* Domain
* HTTPS

The application can be built and started on the VPS using Docker Compose.

```bash
docker compose up -d --build
```

After deployment, the running containers can be verified with:

```bash
docker compose ps
```

---

## 🧪 API Testing

The APIs were tested using **Postman**.

The main application flows include:

### User

```http
POST /api/users
GET /api/users/{id}
```

### Product

```http
POST /api/products
GET /api/products/{id}
```

### Order

```http
POST /api/orders
GET /api/orders/{id}
```

The exact API endpoints and request/response examples are documented below.

---

## 📋 API Documentation

| Service | Method | Endpoint             | Description       |
| ------- | ------ | -------------------- | ----------------- |
| User    | POST   | `/api/users`         | Create a user     |
| User    | GET    | `/api/users/{id}`    | Get user by ID    |
| Product | POST   | `/api/products`      | Create a product  |
| Product | GET    | `/api/products/{id}` | Get product by ID |
| Order   | POST   | `/api/orders`        | Create an order   |
| Order   | GET    | `/api/orders/{id}`   | Get order by ID   |

> API endpoints may be expanded as the project evolves.

---

## 📸 Screenshots

Screenshots demonstrating the running application will be added here.

### Eureka Dashboard

*Add screenshot here.*

### Docker Containers

*Add screenshot here.*

### Postman — User Service

*Add screenshot here.*

### Postman — Product Service

*Add screenshot here.*

### Postman — Order Service

*Add screenshot here.*

### VPS Deployment

*Add screenshot here.*

---

## 📁 Project Structure

```text
online-shopping-microservices/
│
├── eureka-server/
│
├── user-service/
│
├── product-service/
│
├── order-service/
│
├── api-gateway/
│
├── docker-compose.yml
│
└── README.md
```

---

## 🎯 Project Goals

This project was developed to demonstrate practical experience with:

* Designing a microservices architecture
* Building RESTful backend services
* Implementing service discovery
* Implementing an API Gateway
* Implementing inter-service communication
* Managing independent databases
* Containerizing applications with Docker
* Orchestrating multiple containers with Docker Compose
* Deploying backend services to a Linux VPS

---

## 📌 Project Status

**Version:** 1.0.0

**Status:** Deployed and running

The current version includes the complete backend microservices environment and Docker-based deployment.

---

## 👨‍💻 Author

**Meisam Sabzi**

Java Backend Engineer

### Technologies

`Java` `Spring Boot` `Spring Cloud` `Microservices` `Eureka` `OpenFeign` `API Gateway` `MySQL` `Docker` `Docker Compose` `Linux`

---

## 📄 License

This project is intended for educational and portfolio purposes.
