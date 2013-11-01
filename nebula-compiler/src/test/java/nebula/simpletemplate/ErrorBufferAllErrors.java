package nebula.simpletemplate;

import org.stringtemplate.v4.misc.ErrorBuffer;
import org.stringtemplate.v4.misc.STMessage;

public class ErrorBufferAllErrors extends ErrorBuffer {
	@Override
	public void runTimeError(STMessage msg) {
		errors.add(msg);
	}
}
