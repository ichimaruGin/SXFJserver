package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class testProperties {

	public testProperties() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		File f = new File("./banci.properties");
		System.out.println(f.exists());
		Properties properties = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(f);
			properties.load(is);
			//Enumeration<String> keys = (Enumeration<String>)properties.propertyNames();
			Set<String> keys = properties.stringPropertyNames();
			Iterator<?> i = keys.iterator();
			System.out.println("----------banci.properties-------------");
			while(i.hasNext()){
				String keyName = (String)i.next();
				System.out.println(keyName+" = "+properties.getProperty( keyName));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found exception");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
