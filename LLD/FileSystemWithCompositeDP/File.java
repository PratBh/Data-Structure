package LLD.FileSystemWithCompositeDP;

public class File  implements FileSystem{
	String fileName;
	
	public File(String fileName) {
		super();
		this.fileName = fileName;
	}
	@Override
	public void ls() {
		System.out.println(fileName);
	}

}
