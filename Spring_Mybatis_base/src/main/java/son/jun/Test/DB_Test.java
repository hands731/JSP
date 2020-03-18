package son.jun.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.Test;

public class DB_Test {
@Test
public void test() throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver"); 

   Connection con =     DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test2","root","1111"); 
    System.out.println(con);
    }
}