package evm.dmc.core.api;

public enum AttributeType {
	NOMINAL("nominal"),
	DATE("data"),
	INT("int"),
	FLOAT("float"),
    // TODO hierarchy :(
	NUMERIC("numeric"),
	STRING("string");
	private String name;

	AttributeType(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
