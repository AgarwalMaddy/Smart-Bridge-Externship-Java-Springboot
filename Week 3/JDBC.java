import java.sql.*;
import java.util.Scanner;

public class CRUD1 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Javaclass";
        String user = "root";
        String password = "123456";

        try{
            Connection con = DriverManager.getConnection(url , user , password);
            Statement stmt = con.createStatement();
            Scanner sc = new Scanner(System.in);

            while(true){

                System.out.println("Choose an operation");
                System.out.println("1. Create Operation");
                System.out.println("2. Read Operation");
                System.out.println("3. Update Operation");
                System.out.println("4. Delete Operation");
                System.out.println("0. Exit");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> {
                        String createQuery = "Create Table termend (Movie Varchar(255) , Genre Varchar(50))";
                        System.out.println("Enter Movie Name: ");
                        String mName = sc.next();
                        System.out.println("Enter Movie Genre: ");
                        String mGenre = sc.next();
                        String insertQuery = "Insert Into termend(Movie , Genre) Values ('" + mName + "','" + mGenre + "')";
                        stmt.addBatch(createQuery);
                        stmt.addBatch(insertQuery);
                        stmt.executeBatch();
                    }
                    case 2 ->{
                        String selectQuery = "Select * from termend";
                        ResultSet rs = stmt.executeQuery(selectQuery);
                        while(rs.next()){
                            String mName = rs.getString("Movie");
                            String mGenre = rs.getString("Genre");
                            System.out.println("Movie : " + mName + " Genre : " + mGenre);
                            break;
                        }
                    }
                    case 3 -> {
                        System.out.println("Enter the movie name: ");
                        String mName = sc.next();
                        System.out.println("Enter the new Genre: ");
                        String mGenre = sc.next();
                        String update1 = "Update termend Set Genre = " + mGenre + " Where Movie = " + mName + " ;";
//                        String updateQuery = "Update termend Set Genre = '" + mGenre + ' Where Movie = '" + mName + "";
                        stmt.addBatch(update1);
                        stmt.executeBatch();
                    }
                    case 4 ->{
                        System.out.println("Enter the movie name: ");
                        String mName = sc.next();
                        String deleteQuery = "Delete from termend where Movie = '" + mName + "'";
                        stmt.addBatch(deleteQuery);
                        stmt.executeBatch();
                    }
                    case 0 -> System.exit(0);
                    default -> System.out.println("Invalid Choice of Operation.");
                }
                System.out.println();
            }

        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
