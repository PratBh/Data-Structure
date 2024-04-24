package LLD.FileSystem;

import java.util.ArrayList;
import java.util.List;

public class Directory {
	String dName;
	List<Object> objList;
	public Directory(String dName) {
		super();
		this.dName = dName;
		this.objList = new ArrayList<Object>();
	}
	
	public void addObj(Object obj) {this.objList.add(obj);}
	
	public void ls() {
		System.out.println(dName);
		for(Object obj:objList) {
			if(obj instanceof File)
				((File) obj).ls();
			else if (obj instanceof Directory)
				((Directory) obj).ls();
		}
	}
}
