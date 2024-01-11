package com.sp.prj;
import java.sql.*;
import java.util.Scanner;

public class SP_JDBC_4_HotelManagementSystem {
    private static final String url="jdbc:mysql://localhost:3306/hotel_db";
    private static final String username="root";
    private static final String password="aryan123!@#";

    public static void main(String[] args) throws SQLException,ClassNotFoundException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("CONNECTION SUCCESSFUL");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try {
            Connection connection= DriverManager.getConnection(url,username,password);
            Statement statement= connection.createStatement();

            while (true){
                System.out.println("HOTEL MANAGEMENT SYSTEM");
                Scanner sc = new Scanner(System.in);
                System.out.println("1:Reserve a room");
                System.out.println("2:View Reservation");
                System.out.println("3:Get Room Number");
                System.out.println("4:Update Reservation");
                System.out.println("5:Delete Reservation");
                System.out.println("6:EXIT");
                int choice=sc.nextInt();
                switch (choice){
                    case 1:
                        reserveRoom(connection,sc,statement);
                        break;
                    case 2:
                        viewReservation(connection,statement);
                        break;
                    case 3:
                        getRoomNum(connection,sc,statement);
                        break;
                    case 4:
                        updateReservation(connection,sc,statement);
                        break;
                    case 5:
                        deleteReservation(connection,sc,statement);
                        break;
                    case 6:
                        exit();
                        sc.close();
                        return;
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void reserveRoom(Connection connection,Scanner sc,Statement statement) {
        System.out.println("Enter guest name");
        String guest_name=sc.next();
        sc.nextLine();
        System.out.println("Enter room number");
        int room_number=sc.nextInt();
        System.out.println("Enter contact number");
        String contact_number=sc.next();
        String sql="INSERT into reservation (guest_name,room_number,contact_number) "+
                "values ('" +guest_name+ "',"+room_number+ ",'"+contact_number+"');";
        try{
            int affected_rows= statement.executeUpdate(sql);
            if (affected_rows>0){
                System.out.println("Reservation Successful");
            }
            else {
                System.out.println("Reservation failed");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    private static void viewReservation(Connection connection,Statement statement){
        String sql="SELECT * FROM reservation;";
        System.out.println("current reservation");
        System.out.println("+----------------+-----------------+---------------+----------------------+-----------------------+");
        System.out.println("| Reservation ID |Name             |Room Number    | Contact Num          | Date                  |");
        System.out.println("+----------------+-----------------+---------------+----------------------+-----------------------+");
        try(ResultSet rs= statement.executeQuery(sql);) {

            while (rs.next()){
                int id=rs.getInt("reservation_id");
                String guest_name=rs.getString("guest_name");
                int room_number=rs.getInt("room_number");
                String contact_number=rs.getString("contact_number");
                String reservation_date=rs.getTimestamp("reservation_date").toString();
                System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-1s |\n",
                        id, guest_name, room_number, contact_number, reservation_date);
            }
            System.out.println("+------------------------------------------------------------------------------------------------+");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void getRoomNum(Connection connection,Scanner sc,Statement statement){
        System.out.println("Enter guest name ");
        String guest_name= sc.next();
        sc.nextLine();
        System.out.println("Enter Reservation id");
        int rid=sc.nextInt();
        String sql="SELECT room_number FROM reservation"+
                " WHERE reservation_id="+rid+" AND guest_name='"+guest_name+"';";
        try(ResultSet rs=statement.executeQuery(sql)){
            if (rs.next()){
                int room_num=rs.getInt("room_number");
                System.out.println("the room number for guest "+guest_name+" is"+room_num);

            }
            else {
                System.out.println("Incorrect data");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private static void updateReservation(Connection connection,Scanner sc,Statement statement){
        System.out.println("Enter reservation id you want to update");
        int id=sc.nextInt();
        sc.nextLine();
       if (!reservationExists(connection,id)){
           System.out.println("invalid reservation id");
       }
        System.out.println("enter new guest name");
       String guest_name=sc.next();
       sc.nextLine();
        System.out.println("enter new contact number");
        String contact_number=sc.next();
        sc.nextLine();
        System.out.println("enter new room number");
        int room_number=sc.nextInt();
        String sql="UPDATE reservation SET guest_name = '" + guest_name + "', " +
                "room_number = " + room_number + ", " +
                "contact_number = '" + contact_number + "' " +
                "WHERE reservation_id = " + id;
        try {
            int r=statement.executeUpdate(sql);
            if (r>0){
                System.out.println("sucessfully updated");
            }
            else {
                System.out.println("updation failed");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    private static void deleteReservation(Connection connection,Scanner sc,Statement statement){
        try {
            System.out.print("Enter reservation ID to delete: ");
            int reservationId = sc.nextInt();

            if (!reservationExists(connection, reservationId)) {
                System.out.println("Reservation not found for the given ID.");
                return;
            }

            String sql = "DELETE FROM reservation WHERE reservation_id = " + reservationId;

            try {
                int affectedRows = statement.executeUpdate(sql);

                if (affectedRows > 0) {
                    System.out.println("Reservation deleted successfully!");
                } else {
                    System.out.println("Reservation deletion failed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private static void exit() throws InterruptedException {
        System.out.println("exiting system");
        int i=0;
        while (i>5){
            System.out.println(".");
            Thread.sleep(1000);
            i++;
        }
        System.out.println();
        System.out.println("THANKYOU FOR USING HOTEL MANAGEMENT SYSTEM");
    }
    private static boolean reservationExists(Connection connection, int reservationId) {
        try {
            String sql = "SELECT reservation_id FROM reservation WHERE reservation_id = " + reservationId;

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                return resultSet.next(); // If there's a result, the reservation exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Handle database errors as needed
        }
    }
}
