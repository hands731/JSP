package son.jun.Test.bean;
import java.sql.Date;



public class TestBean {

	private int num_INT;

	private String name_TEXT;

	private Date test_DATE;

	private double test_DOUBLE;

	public int getNum_INT() {
		return num_INT;
	}

	public void setNum_INT(int num_INT) {
		this.num_INT = num_INT;
	}

	public String getName_TEXT() {
		return name_TEXT;
	}

	public void setName_TEXT(String name_TEXT) {
		this.name_TEXT = name_TEXT;
	}

	public Date getTest_DATE() {
		return test_DATE;
	}

	public void setTest_DATE(Date test_DATE) {
		this.test_DATE = test_DATE;
	}

	public double getTest_DOUBLE() {
		return test_DOUBLE;
	}

	public void setTest_DOUBLE(double test_DOUBLE) {
		this.test_DOUBLE = test_DOUBLE;
	}

}