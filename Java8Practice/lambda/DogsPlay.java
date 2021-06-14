package lambda;

public class DogsPlay {
	private DogQuerier dogQ;

	public DogsPlay(DogQuerier dogQ) {
		super();
		this.dogQ = dogQ;
	}
	
	public boolean doQuerry(Dog d) {
		return dogQ.test(d);
	}
}
