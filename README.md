# 🌾 Agriculture Management System (AGMS)
### *Advanced Microservices-based Precision Farming Solution*

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.x-brightgreen)](https://spring.io/projects/spring-boot)
[![Microservices](https://img.shields.io/badge/Architecture-Microservices-blue)](https://microservices.io/)
[![Docker](https://img.shields.io/badge/Container-Docker-blue)](https://www.docker.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

---

## 🏛️ System Architecture & Workflow

The AGMS is engineered with a decentralized microservices architecture to ensure high availability, scalability, and independent service deployment.

[Image of Spring Boot microservices architecture diagram showing Eureka server, API Gateway, and multiple domain services]

### Core Infrastructure Components:
* **Service Discovery (Netflix Eureka):** Provides a dynamic service registry, allowing microservices to discover and communicate with each other without hardcoded IP addresses.
* **API Gateway (Spring Cloud Gateway):** Acts as the unified entry point for all client requests, handling routing, cross-cutting concerns, and load balancing.
* **Centralized Security Layer:** Implements a global authentication filter at the gateway level to ensure all internal traffic is verified.

---

## 🔒 Enterprise Security & Authentication

This project utilizes a **Zero-Trust Security Model** where the perimeter is strictly guarded at the API Gateway.

* **Global JWT Filter:** A custom `AuthenticationFilter` intercepts every incoming request to validate the `Authorization: Bearer <token>` header.
* **RBAC & Path Protection:** * **Public Access:** Authentication and registration endpoints are excluded from the filter.
    * **Secured Access:** All domain logic (Zone, Sensor, Staff, Crop) requires valid JWT credentials.
* **Stateless Security:** Leverages JSON Web Tokens for secure, stateless communication between the frontend and microservices.

[Image of API Gateway security filter chain for JWT authentication and authorization]

---

## 🔄 Intelligent Data Flow & Automation

The system demonstrates complex inter-service orchestration through the following workflow:

1.  **IoT Integration:** The `sensor-telemetry-service` utilizes **OpenFeign** clients to fetch real-time environmental metrics from an external IoT provider.
2.  **Telemetry Propagation:** Raw telemetry is asynchronously processed and pushed to the `automation-service`.
3.  **Threshold Analysis:** The `automation-service` synchronously communicates with the `zone-service` to validate incoming data against predefined zone safety thresholds.
4.  **Automated Response:** Based on the threshold logic, the system triggers hardware-level simulations (irrigation systems, fans, or lighting).

---

## 🛠️ Deployment & Infrastructure Setup

### 🚦 Recommended Startup Sequence:

To ensure proper service registration and heartbeat monitoring with Eureka, follow this order:

1.  **Discovery Server (8761):** Initialize the Eureka Registry.
2.  **API Gateway (8080):** Ensure the gateway connects to Eureka.
3.  **Core Services:** Launch Zone, Sensor, Automation, Crop, and Staff services.

### Containerization with Docker:
Deploy the entire ecosystem using the pre-configured Docker Compose orchestration:

```bash
# Build images and start services in detached mode
docker-compose up --build -d