# CodeQuest

**CodeQuest** is a distributed, full-stack web application built using React for the frontend and Java for the backend. The project is structured to follow scalable and maintainable architecture principles, enabling robust development and easy collaboration.

## Project Structure

CodeQuest/ ├── .idea/ # IDE configuration files (specific to IntelliJ IDEA) ├── .vscode/ # VS Code workspace settings ├── Backend/ # Backend Java codebase ├── Frontend2/ # Frontend React codebase ├── README.md # Project documentation

### **1. Backend**
The `Backend` folder contains the server-side code written in Java. It includes APIs and services for handling data, business logic, and communication with the database.

- **Tech Stack**: Java, Spring Boot, Hibernate, PostgreSQL (or database in use)
- **Setup**:
  1. Navigate to the `Backend` directory.
  2. Run the following commands to build and start the server:
     ```bash
     ./mvnw clean install
     ./mvnw spring-boot:run
     ```
  3. The server will run on `http://localhost:8080` by default.

- **Key Features**:
  - RESTful APIs for interacting with the frontend.
  - Secure authentication and authorization.
  - Modular and layered architecture for maintainability.

---

### **2. Frontend**
The `Frontend2` folder houses the React frontend application responsible for providing the user interface (UI) and communicating with the backend APIs.

- **Tech Stack**: React, Redux (for state management), TailwindCSS (or any styling framework in use)
- **Setup**:
  1. Navigate to the `Frontend2` directory.
  2. Install dependencies:
     ```bash
     npm install
     ```
  3. Start the development server:
     ```bash
     npm start
     ```
  4. The application will run on `http://localhost:3000` by default.

- **Key Features**:
  - Responsive and dynamic user interface.
  - Integration with backend APIs for data fetching.
  - Component-based architecture for reusability.

---

## How to Run the Application

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd CodeQuest
2. Setup the Backend: Follow the instructions in the Backend section to run the Java backend server.

3. Setup the Frontend: Follow the instructions in the Frontend section to run the React application.

4. Verify the Application: Open a browser and navigate to http://localhost:3000 to interact with the frontend, which communicates with the backend server.