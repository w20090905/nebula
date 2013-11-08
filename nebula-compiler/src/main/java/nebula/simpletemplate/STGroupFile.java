package nebula.simpletemplate;

public class STGroupFile extends STGroup {

	public STGroupFile(String string) {
		super();
		this.parseGroupFile(string);
	}

	public STGroupFile(String string, char delimiterStartChar, char delimiterStopChar) {
		super(delimiterStartChar, delimiterStopChar);
		this.parseGroupFile(string);
	}

}
