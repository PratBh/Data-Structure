package Cloning;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Per implements Serializable{
	public String name;
	private int age;

	public Per(String name) {
		super();
		this.name = name;
	}
	
	
	private void writeObject(ObjectOutputStream ops) throws IOException {
		System.out.println("serializing");
		ops.defaultWriteObject();
	}


	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(!(obj instanceof Per))
			return false;
		if(obj==this)
			return true;
		Per p=(Per)obj;
		return this.name.equals(p.name);
	}


	@Override
	public int hashCode() {
		return this.name.length();
	}
	
	
	
	
	
	
	
}
