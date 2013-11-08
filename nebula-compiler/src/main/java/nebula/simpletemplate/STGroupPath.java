package nebula.simpletemplate;

public class STGroupPath extends STGroup {
	String root;

	public STGroupPath(String root) {
		this.root = root;
	}
	public STGroupPath(String root,char delimiterStartChar, char delimiterStopChar) {
		super(delimiterStartChar, delimiterStopChar);
		this.root = root;
	}

	@Override
	public TemplateImpl getTemplate(String name) {
		TemplateImpl tmpl = super.getTemplate(name);
		if(tmpl!=null) return tmpl;
		
		super.defineFileTemplate(name, this.root + "/" + name + ".st");
		return super.getTemplate(name);
	}

}
