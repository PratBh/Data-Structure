package Cloning;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Employee implements Externalizable{
	private String name;
	private transient int age;
	private static String dept;
	public Employee() {
		 
	 }
	public Employee(String name, int age,String dept) {
		super();
		this.name = name;
		this.age = age;
		this.dept=dept;
	}
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("serializeion using custom....");
		out.writeUTF(name);
		out.writeInt(age);
		out.writeUTF(dept);
		
	}
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("Custom deserialization...");
		name=in.readUTF();
		age=in.readInt();
		dept=in.readUTF();
	}
	
	
}
