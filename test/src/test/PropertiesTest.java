package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args) throws IOException {
		
		// saveProperties(System.getProperties());
		System.getProperties().list(System.out);
	}
	
	public static Properties readProperties() throws IOException {
		Properties defaultProps = new Properties();
		FileInputStream in = new FileInputStream("defaultProperties");
		defaultProps.load(in);
		in.close();
		return defaultProps;
	}
	
	public static void saveProperties(Properties applicationProps) throws IOException {
		FileOutputStream out = new FileOutputStream("appProperties");
		applicationProps.store(out, "---No Comment---");
		out.close();
	}

}
