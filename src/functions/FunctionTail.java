package functions;

import java.util.List;

import expressions.Identifier;
import kaskell.Block;
import kaskell.Definition;
import kaskell.SymbolTable;
import types.Type;

public class FunctionTail implements Definition {
	private List<Identifier> variables;
	private List<Type> arguments;
	private Block body;
	private Type type;

	public FunctionTail(List<Identifier> variables, Block body) {
		this.variables = variables;
		this.body = body;
		this.arguments = null;
		this.type = null;
	}

	public boolean checkIdentifiers(SymbolTable symbolTable) {
		boolean wellIdentified = true;
		/* Insert the new indentifiers */
		if (variables != null) {
			for (int i = 0; i < variables.size(); i++) {
				wellIdentified = wellIdentified && symbolTable.insertIdentifier(variables.get(i), this);
			}
		}
		/* Check the block itself (it could be a return block, no problem) */
		if (wellIdentified) {
			wellIdentified = body.checkIdentifiers(symbolTable);
		}
		return wellIdentified;
	}

	public List<Identifier> getVariables() {
		return variables;
	}

	public Block getBlock() {
		return body;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public Type getDefinitionType() {
		return type;
	}

	public void setArguments(List<Type> arguments) {
		this.arguments = arguments;
	}

	public List<Type> getArguments() {
		return this.arguments;
	}

}
