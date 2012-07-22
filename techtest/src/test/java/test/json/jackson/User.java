package test.json.jackson;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

public class User implements Serializable, Externalizable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6396718760677539514L;

	public enum Gender {
		MALE, FEMALE
	};

	public static class Name implements Serializable, Externalizable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -8337487907876073777L;
		private String _first, _last;

		public String getFirst() {
			return _first;
		}

		public String getLast() {
			return _last;
			
		}

		public void setFirst(String s) {
			_first = s;
		}

		public void setLast(String s) {
			_last = s;
		}

		public void writeExternal(ObjectOutput out) throws IOException {
			out.writeUTF(_first);
			out.writeUTF(_last);
		}

		public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
			_first = in.readUTF();
			_last = in.readUTF();
		}
	}

	private Gender _gender;
	private Name _name;
	private boolean _isVerified;
	private byte[] _userImage;

	public Name getName() {
		return _name;
	}

	public boolean isVerified() {
		return _isVerified;
	}

	public Gender getGender() {
		return _gender;
	}

	public byte[] getUserImage() {
		return _userImage;
	}

	public void setName(Name n) {
		_name = n;
	}

	public void setVerified(boolean b) {
		_isVerified = b;
	}

	public void setGender(Gender g) {
		_gender = g;
	}

	public void setUserImage(byte[] b) {
		_userImage = b;
	}
	
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(_gender.ordinal());
		_name.writeExternal(out);
		out.writeBoolean(_isVerified);
		out.writeInt(_userImage.length);
		out.write(_userImage);		
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//		_gender=Gender. in.readInt();
//		_name = new Name();
//		_name.readExternal(in);
//		_isVerified = in.readBoolean();
//		int length = in.readInt();
//		_userImage = new byte[length];
//		in.read(_userImage);		
	}
}
