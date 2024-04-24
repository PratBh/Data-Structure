package LLD.FileSystem;

public class File {
	String fileName;
	
	public File(String fileName) {
		super();
		this.fileName = fileName;
	}

	public void ls() {
		System.out.println(this.fileName);
	}
}
