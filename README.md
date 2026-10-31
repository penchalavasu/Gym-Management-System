# 🏋️ Fitness Care — Gym Management System

A full-featured **Gym Management System** web application built as a BCA Final Year Project.

> Built by **Vasu** — BCA Final Year Project, IGNOU

---

## 👩‍💻 Developer

| Field           | Details                                             |
|-----------------|-----------------------------------------------------|
| **Name**        | Vasu                                             |
| **University**  | Indira Gandhi National Open University (IGNOU)      |
| **Programme**   | Bachelor of Computer Applications (BCA)             |
| **Year**        | Final Year                                          |
| **Project Title** | Gym Management System                            |

---

## 📖 About

Fitness Care is a role-based web application designed to streamline the management of a gym/fitness center. It provides separate dashboards and functionalities for **Admins**, **Managers**, and **Members**, enabling efficient management of memberships, diet plans, progress tracking, complaints, and reporting.

---

## ✨ Features

### 🔐 Authentication & Authorization
- Role-based login system (Admin, Manager, Member)
- New member registration
- Session-based authentication with servlet filters
- Password hashing for security

### 👑 Admin Panel
- Admin dashboard with overview statistics
- Manage Managers (add, view, delete)
- Manage Members (view, update, delete)
- Create & manage Diet Plans
- View member progress records
- Handle & resolve complaints
- Generate reports

### 🧑‍💼 Manager Panel
- Manager dashboard
- View & manage assigned members
- Create & assign diet plans to members
- Monitor member progress
- Handle complaints
- Generate reports

### 🏃 Member Panel
- Member dashboard
- View & update personal profile
- View assigned diet plan
- Log & track fitness progress (weight, height, BMI)
- Submit & track complaints

---

## 🛠️ Tech Stack

| Layer        | Technology                                  |
|--------------|---------------------------------------------|
| **Language** | Java 21                                     |
| **Web**      | Jakarta Servlet 6.0, JSP 3.1, JSTL 3.0     |
| **Server**   | Apache Tomcat 10.1.x                        |
| **Database** | MySQL 8.x                                   |
| **Build**    | Apache Maven                                |
| **Frontend** | HTML, CSS, JavaScript                       |
| **JDBC**     | MySQL Connector/J 8.3.0                     |

---

## 📁 Project Structure

```
Fitness Care/
├── database/
│   ├── schema.sql              # Database schema & table definitions
│   └── seed_data.sql           # Sample data for testing
├── src/main/java/com/fitnesscare/
│   ├── controller/
│   │   ├── admin/              # Admin servlets (dashboard, members, managers, etc.)
│   │   ├── auth/               # Login, logout, registration servlets
│   │   ├── manager/            # Manager servlets (dashboard, members, diet plans, etc.)
│   │   └── member/             # Member servlets (dashboard, profile, progress, etc.)
│   ├── dao/                    # Data Access Objects (JDBC operations)
│   ├── filter/                 # Authentication & authorization filters
│   ├── model/                  # Java model/entity classes
│   ├── service/                # Business logic / service layer
│   └── util/                   # Utility classes (DB connection, validation, password hashing)
├── src/main/webapp/
│   ├── assets/
│   │   ├── css/                # Stylesheets
│   │   └── js/                 # JavaScript files
│   ├── WEB-INF/
│   │   ├── views/              # JSP view pages (admin, manager, member, auth, error)
│   │   └── web.xml             # Deployment descriptor
│   └── index.jsp               # Landing page
└── pom.xml                     # Maven project configuration
```

---

## ⚙️ Prerequisites

- **Java JDK 21** or higher
- **Apache Tomcat 10.1.x**
- **MySQL 8.x**
- **Apache Maven 3.9+**

---

## 🔑 Default Credentials (Seed Data)

If you use the provided `seed_data.sql`, you can log in with the following default accounts. 
**The password for all demo accounts is:** `password123`

| Role | Username (ID) | Name |
|---|---|---|
| **Admin** | `admin` | Vasu |
| **Manager** | `MGR001` | Priya |
| **Manager** | `MGR002` | Dixita |
| **Member** | `MEM001` | Narendra Modi |
| **Member** | `MEM002` | Amit Shah |
| **Member** | `MEM003` | Yogi Adityanath |
| **Member** | `MEM004` | Rahul Gandhi |
| **Member** | `MEM005` | Arvind Kejriwal |

---

## 🚀 Setup & Installation

### 1. Clone the Repository

```bash
git clone https://github.com/<your-username>/fitness-care.git
cd fitness-care
```

### 2. Set Up the Database

Open MySQL and run the provided SQL scripts:

```sql
source database/schema.sql;
source database/seed_data.sql;
```

This will create the `fitnesscare` database with the following tables:
- `admin_gym` — Admin accounts
- `managerlogintable_gym` — Manager accounts
- `user_gym` — Member accounts
- `diet_plan` — Diet plan templates
- `member_diet_plan` — Diet plan assignments
- `progress_record` — Member fitness progress logs
- `complaint_gym` — Member complaints

### 3. Configure Database Connection

The application reads database credentials from environment variables. Set the following:

| Variable       | Default Value                             |
|----------------|-------------------------------------------|
| `DB_URL`       | `jdbc:mysql://localhost:3306/fitnesscare`  |
| `DB_USERNAME`  | `root`                                    |
| `DB_PASSWORD`  | *(empty)*                                 |

If you don't set these, the default values above will be used automatically.

### 4. Build the Project

```bash
mvn clean package
```

### 5. Deploy to Tomcat

Copy the generated WAR file to Tomcat's `webapps` directory:

```bash
cp target/fitnesscare.war /path/to/tomcat/webapps/
```

Or deploy directly through the Tomcat Manager.

### 6. Access the Application

Open your browser and navigate to:

```
http://localhost:8080/fitnesscare
```

---

## 🗄️ Database Schema (ER Overview)

```
admin_gym
  └── id (PK), password, name

managerlogintable_gym
  └── managerid (PK), password, name, gender

user_gym (Members)
  └── id (PK), name, password, gender, program, fitness_goal, age
        │
        ├──► progress_record (member_id → user_gym.id)
        │      └── weight_kg, height_cm, bmi, bmi_status, record_date
        │
        ├──► complaint_gym (member_id → user_gym.id)
        │      └── subject, complaint, status
        │
        └──► member_diet_plan (member_id → user_gym.id)
               └── diet_plan_id → diet_plan.id

diet_plan
  └── id (PK), plan_name, goal, breakfast, lunch, dinner, ...
```

---

## 🏗️ Architecture

The project follows a **layered MVC architecture**:

```
[Browser] → [Servlet Filters] → [Controller Servlets] → [Service Layer] → [DAO Layer] → [MySQL]
                                        ↓
                                   [JSP Views]
```

- **Model** — Java POJOs representing database entities
- **View** — JSP pages with JSTL for dynamic content rendering
- **Controller** — Jakarta Servlets handling HTTP requests and routing
- **Service** — Business logic and validation
- **DAO** — Data access using JDBC prepared statements
- **Filter** — Authentication and role-based access control

---

## 📝 License

This project is developed as an academic project for educational purposes.

---

<p align="center">Made with ❤️ by Vasu | BCA Final Year Project — IGNOU</p>
