package son.jun.Test;
import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

 

public class mysqlTest1 {

 private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
 private static final String URL = "jdbc:mysql://127.0.0.1:3306/test2?characterEncoding=UTF-8&serverTimezone=UTC"; // jdbc:mysql://127.0.0.1:3306/�������� ����� ��Ű���̸�
 private static final String USER = "root"; //DB ����ڸ�
 private static final String PW = "1111";   //DB ����� ��й�ȣ
 
 @Test
 public void testConnection() throws Exception{
  Class.forName(DRIVER);
  
  try(Connection con = DriverManager.getConnection(URL, USER, PW)){
   System.out.println("����");
   System.out.println(con);
  }catch (Exception e) {
   System.out.println("�����߻�");
   e.printStackTrace();
  }
 }

}
