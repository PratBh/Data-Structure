package Cloning;

public class Rectangle implements Cloneable {
	 private int length;
	 private int breadth;
	 
	 
	public Rectangle(int length, int breadth) {
		super();
		this.length = length;
		this.breadth = breadth;
	}
	 
	 @Override
	 protected Rectangle clone() throws CloneNotSupportedException {
		 return (Rectangle) super.clone();
	 }
	 
	 @Override
	 public boolean equals(Object obj) {
		 if(obj==null)
			 return false;
		 if(obj==this)
			 return true;
		 if(!(obj instanceof Rectangle))
			 return false;
		 Rectangle r=(Rectangle) obj;
		 return Integer.compare(r.length, this.length)==0 && Integer.compare(r.breadth, this.breadth)==0;
	 }
}
