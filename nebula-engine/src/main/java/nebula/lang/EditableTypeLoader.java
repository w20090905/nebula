package nebula.lang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import util.FileUtil;

public class EditableTypeLoader extends TypeLoader {

	final File root;
	public EditableTypeLoader(TypeLoader parent, File root){
		super(parent);
		this.root = root;
	}
	
	public Type update(Type oldType) {
		try {
			List<Type> typeList = tryDefineNebula(new StringReader(oldType.code));

			Type oldInnerType = this.findType(oldType.name);
			if(!oldInnerType.mutable){
				throw new RuntimeException("Cannot edit");
			}
			
			if(oldInnerType.getTypeLoader() != this){
				return oldInnerType.getTypeLoader().update(oldType);
			}
			
			File newFile = null;
			URL url =  oldInnerType.url;
			if ("file".equals(url.getProtocol())) {
				newFile = FileUtil.replace(new File(url.getFile()), oldType.code);
			} else {
				throw new RuntimeException("Cannot edit");
			}
			
			typeList =  super.defineNebula(new FileInputStream(newFile));

			return typeList.get(0);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected URL loadClassData(String name) {
		try {
			File file = new File(root,name.replace('.', '/') + ".nebula");
			return file.toURI().toURL();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}

interface Tracable{
	Object getSource();
}