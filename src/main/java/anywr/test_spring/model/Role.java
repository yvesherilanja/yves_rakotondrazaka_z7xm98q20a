package anywr.test_spring.model;

public class Role {

	private ERole name;
	public Role() {
	}
	public ERole getName() {
		return name;
	}
	public void setName(ERole name) {
		this.name = name;
	}
	public Role(ERole name) {
		super();
		this.name = name;
	}
	
}
