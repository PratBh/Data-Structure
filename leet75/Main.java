package leet75;

import java.time.LocalTime;

public class Main {

	public static void main(String[] args) {
		DocumentStore st = new DocumentStore();
        st.save("foo","Hello World");
      st.save("foo","Welcome");
      System.out.println(st.load("foo"));
      st.save("foo","Hi There");
      System.out.println(st.loadFromTime("foo", LocalTime.now()));
	}

}
