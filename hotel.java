import java.util.*;
import java.sql.*;
  
public class hotel {
       //class for different function 
    public static class Innerhotel {
          void reserveroom( Connection con ,  Scanner sc){
            System.out.println("Insert data  for reservation :");
            System.out.println("Enter guest id :");
            int id =sc.nextInt();
            System.out.println("Enter guest Name :");
            String name = sc.next();
            System.out.println("Enter guest ph_no :");
            String ph_no=sc.next();
            System.out.println("Enter room no for guest :");
            int room_no=sc.nextInt();
            
            String sql="INSERT INTO customer(id,name,ph_no,room_no)"+"VALUES('"+id+"','"+name+"','"+ph_no+"','"+room_no+"')";
            try {
                Statement st=con.createStatement();
                int i=st.executeUpdate(sql);
                if (i>0) {
                    System.out.println("reservation succesful....");
                }else{
                    System.out.println("unsuccessful..try again");
                }

                } catch (SQLException e) {
                e.getMessage();
                }
            }
              //for view...
                void  view(Connection con){
                  String sql="select*from customer";
                  try {
                    Statement st=con.createStatement();
                    ResultSet rs=st.executeQuery(sql);
                    while (rs.next()) {
                        int id=rs.getInt("id");
                        String name= rs.getString("name");
                        String ph_no=rs.getString("ph_no");
                        int room_no=rs.getInt("room_no");
                        System.out.println("....................................");
                        System.out.println("id :"+id);
                        System.out.println("name:"+name);
                        System.out.println("phy_no :"+ph_no);
                        System.out.println("room_no :"+room_no);
                        System.out.println("++++++++++++++++++++++++++++++++++++");
                    }

                    } catch (SQLException e) {
                    e.getMessage();
                 }}
                //to get room number..... 

                 void getroom( Connection con ,  Scanner sc){
                   System.out.println("Enter guest id:");
                   int id =sc.nextInt();
                   System.out.println("Enter guest name");
                   String name =sc.next();
                   String sql ="select room_no from customer where id='"+id+"'";
                   try {
                      Statement st =con.createStatement();
                      ResultSet rs = st.executeQuery(sql);
                      while (rs.next()) {
                        int room_no=rs.getInt("room_no");
                        System.out.println("Room number is :"+room_no);
                      }
                   } catch (SQLException e) {
                    e.getMessage();
                   }
                 }
                 //for update....

                 void update_res ( Connection con ,  Scanner sc){
                   
                   System.out.println("New name :");
                   String new_name=sc.next();
                   
                   System.out.println("Enter guest new ph_no :");
                   String newph_no=sc.next();
                   System.out.println("Enter  new room no for guest :");
                   int newroom_no=sc.nextInt();
                   System.out.println("Enter id for guest update :");
                   int id = sc.nextInt();
                   
                   try {
                    String sql="update customer set name=?,ph_no=?,room_no=? WHERE id=?";
                    PreparedStatement st= con.prepareStatement(sql);
                   
                     st.setString(1,new_name);
                     st.setString(2, newph_no);
                     st.setInt(3, newroom_no);
                     st.setInt(4, id);
                     
                     int i = st.executeUpdate();

                     
                   } catch (Exception e) {
                    e.getMessage();
                   }
                  }

                  //delete any data..

                 void delete_res( Connection con ,  Scanner sc){
                      System.out.println("Enter the id to Delete : ");
                      int id = sc.nextInt();
                      try {
                        String sql="delete from customer where id=?";
                        PreparedStatement ps= con.prepareStatement(sql);
                        ps.setInt(1, id);
                        int i = ps.executeUpdate();
                      } catch (Exception e) {
                       e.getMessage();
                      }
                }
                 // return / exit...
                void exit( Connection con ,  Scanner sc){
                      System.out.println("exiting system....");
                    return;
                }
            

          }
          //main class..............
        public static void main(String[] args) throws Exception{
        String url= "jdbc:mysql://localhost:3306/hotel_database";
        String user = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
          e.printStackTrace();
        }
        
            try {
                Connection con = DriverManager.getConnection(url, user, password);
                while (true) {
                Scanner sc = new Scanner(System.in);
                System.out.println(".......................................");
                System.out.println("Welcome to the Hotel management system:");
                System.out.println(".......................................");
            
                System.out.println("1. reserve a room :");
                System.out.println("2. View reservation :");
                System.out.println("3. get room number :");
                System.out.println("4. Update reservation : ");
                System.out.println("5. Delete reservation :");
                System.out.println("6. EXIT...");
                System.out.println("Enter your choise :");
                int choise=sc.nextInt();

                Innerhotel info=new Innerhotel();

                switch (choise) {
                    case 1: info.reserveroom(con,sc);
                           break;
                    case 2: info.view(con);
                           break;
                    case 3: info.getroom(con,sc);
                           break;
                    case 4:info.update_res(con,sc);
                           break;
                    case 5: info.delete_res(con,sc);
                           break;
                    case 6:info.exit(con,sc);
                        break;                        
                    default:
                     System.out.println("invalid choise.....");
                        break;
                    }
                }
            }catch (SQLException e) {
                e.getMessage(); 
            }
            
        }
        
    }