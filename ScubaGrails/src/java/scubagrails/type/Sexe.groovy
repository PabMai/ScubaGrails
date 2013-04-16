package scubagrails.type

public enum Sexe {

	MASCULIN("M"), FEMININ("F")
	public final String value
	Sexe(String value) { this.value = value}
	public String value() { return value }
}
