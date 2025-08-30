# Naukri.com Clone

A microservices-based job portal application inspired by Naukri.com, built with Spring Boot and modern Java technologies.

## 🚀 Features

### User Management
- ✅ User registration for job seekers
- ✅ JWT-based authentication
- ✅ User profile management (CRUD operations)
- ✅ Role-based access control (Recruiter/Job Seeker)

### Job Management
- ✅ Job posting and management
- ✅ Basic job search functionality
- ✅ Job application submission

### Application Process
- ✅ Online application forms
- ✅ Application status tracking
- ✅ Form submissions management

### Notifications
- ✅ Email notifications for recruiter invitations
- ✅ Application status update notifications

### Security
- ✅ JWT token-based authentication
- ✅ Role-based authorization
- ✅ Secure password handling

## 🛠 Tech Stack

- **Backend**: Java 17, Spring Boot 3.5.x
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Key Libraries**: 
  - Spring Data JPA
  - Lombok
  - ModelMapper
  - Spring Mail

## 🏗 Project Structure

```
naukri.com/
├── central-api/          # API Gateway & Orchestration
├── database-api/        # Core Data Management
└── notification-api/    # Email Notifications
```

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.8+
- PostgreSQL 13+

### Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```

2. Set up the database:
   - Create a PostgreSQL database
   - Update application properties with your database credentials

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the services:
   ```bash
   # In separate terminals
   cd central-api && mvn spring-boot:run
   cd database-api && mvn spring-boot:run
   cd notification-api && mvn spring-boot:run
   ```

## 📝 API Documentation

API documentation is available at:
- Central API: `http://localhost:8080/swagger-ui.html`
- Database API: `http://localhost:8081/swagger-ui.html`
- Notification API: `http://localhost:8082/swagger-ui.html`

## 🤝 Contributing

Contributions are welcome! Please follow these steps:
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Built with ❤️ using Spring Boot
- Inspired by Naukri.com
