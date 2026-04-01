# 🏢 HRMS — Human Resource Management System

> **A full-stack HR management web application built with Spring Boot, Thymeleaf, and MySQL — designed to streamline employee management, department organization, and leave tracking for organizations of any size.**

<br/>

![Java](https://img.shields.io/badge/Java-17-007396?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.x-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.x-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

---

## 🚀 Live Demo

> 🔗 **[Live Demo — Coming Soon](#)**
>

**Default Credentials (auto-seeded on first run):**

| Role  | Username | Password   |
|-------|----------|------------|
| Admin | `admin`  | `admin123` |

---

## 📌 Features

### 👨‍💼 Employee Management
- ✅ Add, edit, view, and delete employee records
- ✅ Track designation, salary, joining date, and employment status (Active/Inactive)
- ✅ Live keyword search — filter employees by name or department instantly
- ✅ Avatar initials auto-generated from employee name
- ✅ Each employee is linked to a department with a Many-to-One relationship

### 🏛️ Department Management
- ✅ Create and manage departments with name, description, and floor/location
- ✅ Department cards display live employee count
- ✅ Prevent deletion of departments that still have employees assigned

### 📋 Leave Management
- ✅ Apply for leave by selecting employee, leave type (Sick/Unpaid/etc.), date range, and reason
- ✅ Automatic calculation of the total number of leave days
- ✅ Admin can approve or reject leave requests with optional remarks
- ✅ Leave status tracked as `PENDING`, `APPROVED`, or `REJECTED` with color-coded badges
- ✅ Dashboard shows recent leave activity and pending leave count

### 📊 Dashboard
- ✅ Real-time summary cards: Total Employees, Total Departments, Pending Leaves, Recent Requests
- ✅ Quick Actions panel — Add Employee, Add Department, Apply Leave, Search Employees
- ✅ Recent leave requests table with status badges
- ✅ Personalized welcome message using Spring Security's authenticated user

### 🔐 Authentication & Authorization
- ✅ Role-based access control: `ROLE_ADMIN` and `ROLE_USER`
- ✅ Admins can manage employees and departments; Users can view dashboard and manage leaves
- ✅ BCrypt password encoding for secure credential storage
- ✅ Custom login & registration pages with show/hide password toggle
- ✅ Session management with JSESSIONID cookie cleanup on logout

### ⚙️ Developer Experience
- ✅ Automatic data seeding on startup (admin user + sample departments + employees)
- ✅ Spring Boot DevTools for hot reload during development
- ✅ Bean Validation (`@NotBlank`, `@Email`, `@Pattern`, `@DecimalMin`) with inline form errors
- ✅ Flash attributes for success/error feedback across redirects

---

## 🛠️ Tech Stack

| Layer      | Technology                                         |
|------------|----------------------------------------------------|
| **Language** | Java 17                                          |
| **Framework** | Spring Boot 4.0.3                               |
| **Security** | Spring Security 6 (BCrypt, Role-based access)   |
| **ORM** | Spring Data JPA / Hibernate                          |
| **Template Engine** | Thymeleaf 3 + Thymeleaf Spring Security Extras |
| **Validation** | Jakarta Bean Validation                       |
| **Database** | MySQL 8.x                                        |
| **Frontend** | Bootstrap 5.3, Bootstrap Icons, Custom CSS, Vanilla JS |
| **Build Tool** | Maven (with Maven Wrapper)                    |
| **Deployment** | WAR packaging (Tomcat-ready)                  |
| **Dev Tools** | Spring Boot DevTools                          |

---

## 📂 Project Structure

```
HRMSProject/
├── src/
│   └── main/
│       ├── java/com/example/college/
│       │   ├── config/
│       │   │   ├── DataInitializer.java        # Seeds default admin, departments & employees on startup
│       │   │   └── SecurityConfig.java         # Spring Security (roles, login, logout)
│       │   ├── controller/
│       │   │   ├── AuthController.java         # Login & registration
│       │   │   ├── DashboardController.java    # Dashboard stats and overview
│       │   │   ├── DepartmentController.java   # Department CRUD
│       │   │   ├── EmployeeController.java     # Employee CRUD + search
│       │   │   └── LeaveController.java        # Leave apply, approve, reject
│       │   ├── entity/
│       │   │   ├── Department.java             # Department JPA entity
│       │   │   ├── Employee.java               # Employee entity (linked to Department & Leaves)
│       │   │   ├── LeaveRequest.java           # Leave request entity
│       │   │   ├── LeaveStatus.java            # Enum: PENDING, APPROVED, REJECTED
│       │   │   └── User.java                   # User entity for authentication
│       │   ├── repository/                     # Spring Data JPA repositories
│       │   ├── security/
│       │   │   └── CustomUserDetailsService.java
│       │   ├── service/                        # Business logic layer
│       │   ├── HrmsProjectApplication.java     # Spring Boot entry point
│       │   └── ServletInitializer.java         # WAR deployment initializer
│       └── resources/
│           ├── static/
│           │   ├── css/style.css               # Custom styling
│           │   └── js/script.js                # Client-side interactivity
│           ├── templates/                      # Thymeleaf HTML views
│           │   ├── navbar.html
│           │   ├── dashboard.html
│           │   ├── login.html / register.html
│           │   ├── employee_list.html / add_employee.html / edit_employee.html
│           │   ├── department_list.html / add_department.html / edit_department.html
│           │   └── leave_requests.html / apply_leave.html
│           └── application.properties
└── pom.xml
```

---

## ⚙️ Installation & Setup

### Prerequisites

Make sure you have the following installed:

- ☕ **Java 17+** — [Download JDK](https://adoptium.net/)
- 🐬 **MySQL 8.x** — [Download MySQL](https://dev.mysql.com/downloads/)
- 📦 **Maven 3.8+** (or use the included `mvnw` wrapper)

---

### Step 1 — Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/HRMSProject.git
cd HRMSProject
```

> 🔁 Replace `YOUR_USERNAME` with your actual GitHub username.

---

### Step 2 — Create the MySQL Database

```sql
CREATE DATABASE hrms_db1;
```

> The schema (tables) will be auto-created by Hibernate on first run thanks to `spring.jpa.hibernate.ddl-auto=update`.

---

### Step 3 — Configure Database Credentials

Open `src/main/resources/application.properties` and update:

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/hrms_db1
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

> ⚠️ Change the port (`3307`) and password to match your local MySQL setup.

---

### Step 4 — Build the Project

```bash
# Using Maven Wrapper (no Maven installation needed)
./mvnw clean install

# Or if Maven is installed globally
mvn clean install
```

---

## ▶️ Usage

### Run the Application

```bash
./mvnw spring-boot:run
```

Then open your browser and navigate to:

```
http://localhost:8080/login
```

---

### Default Login

The application **auto-seeds** the database on first run:

| Username | Password   | Role  | Access                              |
|----------|------------|-------|-------------------------------------|
| `admin`  | `admin123` | Admin | Full access to all modules          |
| *(Register)* | *(your choice)* | User | Dashboard + Leave management |

---

### Navigation Guide

| Page | URL | Access |
|------|-----|--------|
| Login | `/login` | Public |
| Register | `/register` | Public |
| Dashboard | `/dashboard` | All authenticated users |
| Employees | `/employees` | Admin only |
| Add Employee | `/employees/add` | Admin only |
| Departments | `/departments` | Admin only |
| Leave Requests | `/leaves` | Admin + User |
| Apply Leave | `/leaves/apply` | Admin + User |

---

## 📸 Screenshots

### 🔐 Login Page
<img width="700" height="700" alt="login" src="https://github.com/user-attachments/assets/d663870b-d3b2-42a8-9c6b-9ebf82fce7ca" />

*Elegant login card with gradient header, show/hide password toggle, and a link to the registration page.*

---

### 📝 Register Page
<img width="700" height="700" alt="Screenshot 2026-04-01 212420" src="https://github.com/user-attachments/assets/fc18e948-971f-49da-848d-08d364414514" />

*Clean user registration page to create a new account and join the HRMS portal.*

---

### 📊 Dashboard
<img width="1917" height="906" alt="Screenshot 2026-04-01 212519 - Copy" src="https://github.com/user-attachments/assets/d0b452e2-c987-4d34-bedc-114e24152a6c" />

*Real-time overview with 4 stat cards (Total Employees, Departments, Pending Leaves, Recent Requests), a Quick Actions panel, and a recent leave activity table.*

---

### 👨‍💼 Employee Management
<img width="1918" height="902" alt="Screenshot 2026-04-01 212611 - Copy" src="https://github.com/user-attachments/assets/839b8d3e-0d70-4153-92a6-91cebc754f2c" />

*Full employee table with avatar initials, department badges, salary (₹), joining date, status indicator, and edit/delete actions.*

---

### 🔍 Employee Search
<img width="1919" height="907" alt="Screenshot 2026-04-01 212645" src="https://github.com/user-attachments/assets/8ea2f7ee-0a6e-4999-a187-31fbcea0fe3b" />

*Live keyword search filters the employee table instantly — shows result count badge and a clear button to reset.*

---

### ➕ Add New Employee
<img width="1912" height="905" alt="Screenshot 2026-04-01 212732" src="https://github.com/user-attachments/assets/90714a39-a8be-464f-9ec1-d09641c65ba7" />
*Validated form with fields for name, email, phone (10-digit pattern), department dropdown, designation, salary, joining date, and status.*

---

### 🏛️ Department Management
<img width="1915" height="911" alt="Screenshot 2026-04-01 212759" src="https://github.com/user-attachments/assets/1b8b44b8-6cea-4949-adff-17fc30d3a483" />
*Department cards display name, description, floor/location, and live employee count — with edit and delete actions per card.*

---

### ➕ Add Department
<img width="1916" height="862" alt="Screenshot 2026-04-01 212821" src="https://github.com/user-attachments/assets/422038f2-91e3-4de8-81c7-2eced84862fc" />
*Minimal form to create a new department with name, description, and location.*

---

### 📋 Leave Requests
<img width="1915" height="724" alt="Screenshot 2026-04-01 212846" src="https://github.com/user-attachments/assets/4872e6a7-ab62-40ef-a9c1-fc4d9b052b2c" />
*Comprehensive leave table showing employee, department, leave type (color-coded badge), date range, day count, reason, applied date, and approval status.*

---

### 📅 Apply for Leave
<img width="1915" height="917" alt="Screenshot 2026-04-01 212913" src="https://github.com/user-attachments/assets/a0c02d3b-c539-4f02-805b-9a75b8855b8d" />
*Leave application form with employee selector, leave type dropdown, start/end date pickers, and a reason textarea.*

---
## 🔮 Future Improvements

- [ ] 📧 **Email Notifications** — Automated emails on leave approval/rejection via Spring Mail
- [ ] 📅 **Leave Balance Tracker** — Set and track annual leave quotas per employee
- [ ] 📈 **Analytics Dashboard** — Charts for department headcount and leave trends using Chart.js
- [ ] 📄 **PDF Export** — Export employee lists and leave reports as downloadable PDFs
- [ ] 🌐 **REST API Layer** — Expose endpoints for mobile or third-party integration
- [ ] 🌙 **Dark Mode** — Toggle between light and dark UI themes
- [ ] 🔔 **In-App Notifications** — Real-time alerts for leave status changes
- [ ] 🏷️ **Employee Profile Photos** — Upload and display profile images
- [ ] 🐳 **Docker Support** — Containerize with Docker Compose for easy deployment
- [ ] 🧪 **Unit & Integration Tests** — Expand test coverage with JUnit & MockMvc

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

```bash
# 1. Fork the repository
# 2. Create your feature branch
git checkout -b feature/your-feature-name

# 3. Commit your changes
git commit -m "feat: add your feature description"

# 4. Push to your branch
git push origin feature/your-feature-name

# 5. Open a Pull Request
```

Please follow standard commit message conventions (`feat:`, `fix:`, `docs:`, `refactor:`, etc.).

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

<table>
  <tr>
    <td align="center">
      <strong>Mansi Bhagat</strong><br/>
      <em>Full Stack Java Developer</em><br/><br/>
      <a href="https://linkedin.com/in/YOUR_LINKEDIN">
        <img src="https://img.shields.io/badge/LinkedIn-Connect-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white" />
      </a>
      &nbsp;
      <a href="https://mansibhagat-dev">
        <img src="https://img.shields.io/badge/GitHub-Follow-181717?style=for-the-badge&logo=github&logoColor=white" />
      </a>
    </td>
  </tr>
</table>

---

<div align="center">

⭐ **If you found this project useful, please consider giving it a star!** ⭐

*Built with ❤️ using Spring Boot & Thymeleaf*

</div>
