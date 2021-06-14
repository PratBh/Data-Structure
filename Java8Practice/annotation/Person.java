package annotation;

@JsonSerializeble
public class Person {
	@JsonElement(key="NAME")
	private String name;
	
	@JsonElement(key="ID")
	private String id;
	
	int age;
	
	
	
	public Person(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}



	@Init
	private void getName() {
		System.out.println(this.name);
	}
	
	
}
