package dev.fringe.model;

public class Test {

	private String name;
	private String id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("name");
		builder.append(",id");
		return builder.toString();
	}
	public static void main(String[] args) {
		System.out.println(new Test().toString());
	}
	
	
}
