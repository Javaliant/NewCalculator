/* Author: Luigi Vincent

*/

public enum Operator implements Equation {
	DIVIDE("\u00F7", (x, y) -> x / y),
	MULTIPLY("x", (x, y) -> x * y),
	SUBTRACT("-", (x, y) -> x - y),
	ADD("+", (x, y) -> x + y);

	private final String symbol;
	private final Equation equation;
	

	Operator(String symbol, Equation equation) {
		this.symbol = symbol;
		this.equation = equation;
	}

	@Override
	public double compute(double alpha, double beta) {
		return equation.compute(alpha, beta);
	}

	@Override
	public String toString() {
		return symbol;
	}
}