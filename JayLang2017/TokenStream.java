// ConcreteSyntax.java

// Implementation of the Scanner for JAY

// This code DOES NOT implement a scanner for JAY. You have to complete
// the code and also make sure it implements a scanner for JAY - not something
// else.

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TokenStream {

	private boolean isEof = false;

	private char nextChar = ' '; // next character in input stream

	private BufferedReader input;

	// This function was added to make the main.
	public boolean isEoFile() {
		return isEof;
	}

	// Pass a filename for the program text as a source for the TokenStream.
	public TokenStream(String fileName) {
		try {
			input = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
			// System.exit(1); // Removed to allow ScannerDemo to continue
			// running after the input file is not found.
			isEof = true;
		}
	}

	public Token nextToken() { // Return next token type and value.
		Token t = new Token();
		t.setType("Other");
		t.setValue("");

		// First check for whitespace and bypass it.
		skipWhiteSpace();

		// Then check for a comment, and bypass it
		// but remember that / is also a division operator.
		while (nextChar == '/') {
			// Changed if to while to avoid the 2nd line being printed when
			// there
			// are two comment lines in a row.
			nextChar = readChar();
			if (nextChar == '/') { // If / is followed by another /
				// skip rest of line - it's a comment.
				// look for <cr>, <lf>, <ff>
				t.setType("Comment");
				t.setValue(t.getValue() + nextChar + nextChar);
				nextChar = readChar();
				while(!isEndOfLine(nextChar)) {
					t.setValue(t.getValue() + nextChar);
					nextChar = readChar();
				}
			} else {
				t.setValue("/");
				t.setType("Operator");
				return t;
			}
		}


		skipWhiteSpace();
		// Then check for an operator; recover 2-character operators
		// as well as 1-character ones.
		if (isOperator(nextChar)) {
				t.setType("Operator");
				while(!isEndOfLine(nextChar) && isOperator(nextChar)) {
						t.setValue(t.getValue() + nextChar);
						nextChar = readChar();
				}
				if(!isValidOperator(t.getValue())) {
						t.setType("Other");
				}
				return t;
		}

		// Then check for a separator.
		if (isSeparator(nextChar)) {
			t.setType("Separator");
			t.setValue(t.getValue() + nextChar);
			nextChar = readChar();
			return t;
		}

		// Then check for an identifier, keyword, or literal.
		if (isLetter(nextChar)) {
			// get an identifier
			t.setType("Identifier");
			while ((isLetter(nextChar) || isDigit(nextChar))) {
				t.setValue(t.getValue() + nextChar);
				nextChar = readChar();
			}
			// now see if this is a keyword
			if (isKeyword(t.getValue()))
				t.setType("Keyword");
            if(t.getValue().equals("true") || t.getValue().equals("false"))
                t.setType("Boolean");
			if (isEndOfToken(nextChar)) // If token is valid, returns.
				return t;
		}

		if (isDigit(nextChar)) { // check for integers
			t.setType("Integer-Literal");
            //System.out.println(t.getValue() + " value");
            while(!isEndOfToken(nextChar)) {
                if(!isDigit(nextChar) && !t.getType().equals("Other")) {
                    t.setType("Other");
                }
                t.setValue(t.getValue() + nextChar);
                nextChar = readChar();
            }
            // An Integer-Literal is to be only followed by a space,
			// an operator, or a separator.
			if (isEndOfToken(nextChar))
            return t;
		}

		if (isEof)
			return t;

		// Makes sure that the whole unknown token (Type: Other) is printed.
		while (!isEndOfToken(nextChar)) {
			if (nextChar == '!') {
				nextChar = readChar();
				if (nextChar == '=') { // looks for = after !
					nextChar = 7; // means next token is !=
					break;
				} else
					t.setValue(t.getValue() + "!");
			} else {
				t.setValue(t.getValue() + nextChar);
				nextChar = readChar();
			}
		}

		while(t.getType().equals("Comment")) {
			t = nextToken();
		}

		return t;
	}

	private char readChar() {
		int i = 0;
		if (isEof)
			return (char) 0;
		System.out.flush();
		try {
			i = input.read();
		} catch (IOException e) {
			System.exit(-1);
		}
		if (i == -1) {
			isEof = true;
			return (char) 0;
		}
		return (char) i;
	}

	private long skipChar() {
        long ret = 0;
        try {
            ret = input.skip(1);
        } catch (IOException e) {
            System.exit(-1);
        }
        System.out.println("Skipped " + ret + " characters");
        return ret;
    }

	private boolean isKeyword(String s) {
		return (s.equals("boolean") || s.equals("else") || s.equals("if") ||
				s.equals("main") 	|| s.equals("void") || s.equals("while"));
	}

	private boolean isWhiteSpace(char c) {
		return (c == ' ' || c == '\t' || c == '\r' ||
				c == '\n' || c == '\f');
	}

	private boolean isEndOfLine(char c) {
		return (c == '\r' || c == '\n' || c == '\f');
	}

	private boolean isEndOfToken(char c) { // Is the value a seperate token?
		return (isWhiteSpace(nextChar) || isOperator(nextChar)
				|| isSeparator(nextChar) || isEof);
	}

	private void skipWhiteSpace() {
		while (!isEof && isWhiteSpace(nextChar)) {
			nextChar = readChar();
		}
	}

	private boolean isSeparator(char c) {
		return (c == '(' || c == ')' || c == '{' ||
				c == '}' || c == ';' || c == ',');
	}

	private boolean isOperator(char c) {
		return (c == '=' || c == '+' || c == '-' ||
				c == '*' || c == '/' || c == '<' ||
				c == '>' || c == '!' || c == '|' ||
				c == '&');
	}

	private boolean isValidOperator(String s) {
        return (s.equals("=") || s.equals("+") || s.equals("-") ||
                s.equals("*") || s.equals("/") || s.equals("<") ||
                s.equals("<=") || s.equals(">") || s.equals(">=") ||
                s.equals("==") || s.equals("!=") || s.equals("&&") ||
                s.equals("||") || s.equals("!"));
    }

	private boolean isLetter(char c) {
		return (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z');
	}

	private boolean isDigit(char c) {
		return (c >= '0' && c <= '9');
	}

	public boolean isEndofFile() {
		return isEof;
	}
}
