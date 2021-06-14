package Cloning;

import java.util.ArrayList;
import java.util.List;

public class Programmer implements Cloneable{
	private String name;
	private int age;
	private List certifications;
	public Programmer(String name, int age) {
		super();
		this.name = name;
		this.age = age;
		this.certifications=new ArrayList<>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public void addCerts(String certs) {
		this.certifications.add(certs);
	}
	
	@Override
	protected Programmer clone() {
		Programmer clone=null;
		try {
			clone=(Programmer) super.clone();
			clone.certifications=new ArrayList(this.certifications);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}
}
