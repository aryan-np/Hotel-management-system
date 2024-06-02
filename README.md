 <h1>Hotel Management System - Aryan Neupane</h1>
    <p>This Java application is a simple Hotel Management System that interacts with a MySQL database to perform CRUD operations. It allows users to reserve rooms, view reservations, retrieve room numbers, update reservations, and delete reservations.</p>
    
    <h2>Prerequisites</h2>
    <ul>
        <li>Java Development Kit (JDK) 8 or higher</li>
        <li>MySQL Database</li>
        <li>MySQL Connector/J</li>
    </ul>
    
    <h2>Setup Instructions</h2>
    <ol>
        <li>Ensure MySQL is installed and running on your system.</li>
        <li>Create a database named <code>hotel_db</code> in MySQL.</li>
        <li>Create a table named <code>reservation</code> with the following structure:
            <pre>
CREATE TABLE reservation (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_name VARCHAR(50) NOT NULL,
    room_number INT NOT NULL,
    contact_number VARCHAR(15) NOT NULL,
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
            </pre>
        </li>
        <li>Clone or download the project repository.</li>
        <li>Update the database connection details in the <code>SP_JDBC_4_HotelManagementSystem</code> class:
            <pre>
private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
private static final String username = "root";
private static final String password = "your_password";
            </pre>
        </li>
        <li>Compile and run the application.</li>
    </ol>
    
    <h2>Usage</h2>
    <p>When you run the application, you will see the following menu options:</p>
    <ol>
        <li>Reserve a room</li>
        <li>View Reservation</li>
        <li>Get Room Number</li>
        <li>Update Reservation</li>
        <li>Delete Reservation</li>
        <li>EXIT</li>
    </ol>
    <p>Select an option by entering the corresponding number.</p>
    
    <h3>Reserve a Room</h3>
    <p>Select option 1 and provide the required details (guest name, room number, and contact number) to reserve a room.</p>
    
    <h3>View Reservation</h3>
    <p>Select option 2 to view all current reservations.</p>
    
    <h3>Get Room Number</h3>
    <p>Select option 3 and provide the guest name and reservation ID to retrieve the room number.</p>
    
    <h3>Update Reservation</h3>
    <p>Select option 4 and provide the reservation ID you want to update along with the new guest name, contact number, and room number.</p>
    
    <h3>Delete Reservation</h3>
    <p>Select option 5 and provide the reservation ID to delete the reservation.</p>
    
    <h3>EXIT</h3>
    <p>Select option 6 to exit the application.</p>
    
    <h2>Code Structure</h2>
    <p>The main class <code>SP_JDBC_4_HotelManagementSystem</code> contains the following methods:</p>
    <ul>
        <li><code>reserveRoom</code>: Handles room reservation logic.</li>
        <li><code>viewReservation</code>: Displays all current reservations.</li>
        <li><code>getRoomNum</code>: Retrieves the room number based on guest name and reservation ID.</li>
        <li><code>updateReservation</code>: Updates reservation details.</li>
        <li><code>deleteReservation</code>: Deletes a reservation based on reservation ID.</li>
        <li><code>exit</code>: Exits the application.</li>
        <li><code>reservationExists</code>: Checks if a reservation exists based on reservation ID.</li>
    </ul>
    
    <h2>Database Connection</h2>
    <p>The application connects to the MySQL database using JDBC. Make sure to include the MySQL Connector/J library in your classpath.</p>
    
    <h2>Error Handling</h2>
    <p>Basic error handling is implemented to catch and display SQL exceptions.</p>
    
    <h2>Contributing</h2>
    <p>Feel free to fork the repository and submit pull requests for improvements or bug fixes.</p>
    
    <h2>License</h2>
    <p>This project is licensed under the MIT License.</p>
