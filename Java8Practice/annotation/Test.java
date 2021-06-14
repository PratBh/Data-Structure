package annotation;


public class Test {
	public static void main(String []args) throws Exception {
		Person person=new Person("Ram","1");
		ObjectToJsonConverter converter=new ObjectToJsonConverter();
		System.out.println(converter.convertToJson(person));
//		Test ts=new Test();
//		System.out.println(ts.hello());
//		System.out.println(Test.hello());
	}
	static boolean hello() {
		return true;
	}
}
