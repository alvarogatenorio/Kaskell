package kaskell;

import java.util.List;

import expressions.Expression;
import expressions.Identifier;
import functions.FunctionTail;
import statements.Assignment;
import statements.Declaration;
import statements.Statement;

public class ReturnBlock extends Block {
	private Expression returnStatement;
	private Identifier id;
	private Assignment aux;
	private int returnAddress;
	private Declaration decAux;
	private FunctionTail f;

	public ReturnBlock(List<Statement> statements, Expression returnStatement) {
		super(statements);
		this.returnStatement = returnStatement;
		this.id = new Identifier("1");
		id.setAddress(0);
		this.aux = new Assignment(id, returnStatement);
	}

	/* Checks the block itself and the expression (return statement) */
	public boolean checkIdentifiers(SymbolTable symbolTable) {
		boolean wellIdentified = super.checkIdentifiers(symbolTable);
		wellIdentified = wellIdentified && returnStatement.checkIdentifiers(symbolTable);
		this.decAux = new Declaration(returnStatement.getType(), id);
		decAux.setInsideFunction(true);
		decAux.setFunctionInside(f);
		this.returnAddress = id.getAddress();
		return wellIdentified;
	}

	/* Checks the block itself and the expression (return statement) */
	public boolean checkType() {
		return super.checkType() && returnStatement.checkType();
	}

	public int lengthStackExpressions() {
		return Math.max(super.lengthStackExpressions(), this.calculateExpSubTree(returnStatement));
	}

	public void setInsideFunction(FunctionTail f) {
		this.f = f;
		super.setInsideFunction(f);
		id.setFunctionInside(f);
		id.setInsideFunction(true);
		returnStatement.setFunctionInside(f);
		returnStatement.setInsideFunction(true);
	}

	public Expression getReturnExpression() {
		return returnStatement;
	}

	public void generateCode(Instructions instructions) {
		super.generateCode(instructions);
		aux.generateCode(instructions);
	}

	public int getAddress() {
		return this.returnAddress;
	}
}
