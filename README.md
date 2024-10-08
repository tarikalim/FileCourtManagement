# File-Court Management Application for Bakirkoy Courthouse

The File-Court Management Application 
for Bakirkoy Courthouse automates the distribution
of case files among 24 Heavy Penal Courts. 
Each week, one court is designated as the on-duty court, 
with the authority to input case files into the system. 
Once a case file is entered by the on-duty court, 
the application ensures that the files are evenly distributed across all courts, 
maintaining an equal workload. 
The duty court rotates automatically every week, 
and each court has the ability to view and process the cases assigned to them. 
This system replaces the previous manual process, 
streamlining operations by requiring only file input from the courts, 
while the assignment process is handled by the application.

## Technologies Used

- **Spring Boot**: Used for building the backend services and APIs of the application, ensuring high performance and reliability.
- **MySQL**: The relational database used to store and manage case files and court data.
- **Spring Security**: Implements role-based access control, ensuring that only the designated on-duty court can input new case files. It also manages authentication via JWT tokens to secure the application.
- **React**: Utilized for the frontend, providing an interactive user interface where courts can view and manage their assigned case files.


## Prerequisites

To run this application, you will need to have the following software installed:

- [Docker Desktop](https://www.docker.com/products/docker-desktop) - Required to build and run the application using Docker.

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/tarikalim/FileCourtManagement.git
    ```

2. Navigate to the project directory:
    ```bash
    cd FileCourtManagement
    ```

3. Set the environment variables for MySQL credentials in docker-compose.yml file :


4. Create a `secrets.properties` file in `backend/src/main/resources` and provide the following configuration:
    ```properties
    spring.datasource.url=jdbc:mysql://mysql:3306/filemanagement
    spring.datasource.username=root
    spring.datasource.password=give your docker-compose.yml MYSQL_ROOT_PASSWORD here too
    secret.key=your-secret-key
    cors.allowed.origin=http://localhost:3000
    ```
5. Make sure you have Docker and Docker Compose installed.


6. Run the application using Docker Compose:
    ```bash
    docker-compose up --build
    ```

7. The backend will be accessible at `http://localhost:8080` and the frontend at `http://localhost:3000`.


8.  You may need to give execute permission for "mvnw" file located in Backend directory:
      ```bash
      cd Backend/
      chmod +x mvnw
      ```
## Port Conflicts

If you encounter port conflicts, 
ensure that ports 3306 (MySQL), 8080 (Backend), and 3000 (Frontend)
are not already in use by other applications. 
You can modify the docker-compose.yml file to change the port mappings if needed.



