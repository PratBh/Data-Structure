package Cloning;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

import javax.swing.tree.FixedHeightLayoutCache;

public class Test  {

	public static void main(String[] args) throws IOException {
		//Per per=new Per("abc");
		String fileName="file.docx";
		
//		FileOutputStream file=new FileOutputStream(fileName);
//		ObjectOutputStream ob=new ObjectOutputStream(file);
//		
//		try {
//			ob.writeObject(per);
//			ob.close();
//			file.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		Per per2=null;
//		FileInputStream ip=new FileInputStream(fileName);
//		ObjectInputStream ops=new ObjectInputStream(ip);
//		try {
//			per2=(Per) ops.readObject();
//			ops.close();
//			ip.close();
//		} catch (ClassNotFoundException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(per2.name);
		
		Employee empObj = new Employee("Shanti", 25, "IT");
		FileOutputStream file=new FileOutputStream(fileName);
		ObjectOutputStream ob=new ObjectOutputStream(file);
		
		ob.writeObject(empObj);
		Employee empObj1=null;
		FileInputStream ip=new FileInputStream(fileName);
		ObjectInputStream ops=new ObjectInputStream(ip);
		try {
			empObj1=(Employee) ops.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Rectangle rec=new Rectangle(20, 10);
		try {
			Rectangle rec2=rec.clone();
			System.out.println(rec2==rec);
			System.out.println(rec2.getClass()==rec.getClass());
			System.out.println(rec.equals(rec2));
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		HashSet<Object> hs=new HashSet<Object>();
		Per k1=new Per("abc");
		Per k2=new Per("abc");
		System.out.println(k1.equals(k2));
		String s1=new String("abc");
		String s2="abc";
		hs.add(s1);hs.add(k1);hs.add(k2);hs.add(s2);
		System.out.println(hs.size());
//		Test t=new Test();
//		t.start();
		ImplementMarker imp=new ImplementMarker();
		System.out.println(isMarker(imp));
		
	}
	
	public static boolean isMarker(Object obj) {
		return (obj instanceof Marker);
	}
}
