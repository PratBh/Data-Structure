package LLD.FileSystemWithCompositeDP;

public class Main {

	public static void main(String[] args) {
		Directory movieDirectory = new Directory("Movies");
		FileSystem border = new File("Border");
		Directory comedyMovieDirectory = new Directory("Comedy");
		FileSystem hulchul = new File("Hulchul");
		comedyMovieDirectory.add(hulchul);
		movieDirectory.add(border);
		movieDirectory.add(comedyMovieDirectory);
		movieDirectory.ls();
	}

}
