package LLD.FileSystemWithCompositeDP;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystem {
	String dName;
	List<FileSystem> fileSystemList;
	
	public Directory(String dName) {
		super();
		this.dName = dName;
		this.fileSystemList = new ArrayList<FileSystem>();
	}
	
	public void add(FileSystem fileSystem) {this.fileSystemList.add(fileSystem);}
	
	@Override
	public void ls() {
		System.out.println(dName);
		for(FileSystem fs:fileSystemList)
			fs.ls();
	}

}
