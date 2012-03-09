package test.javabytecode;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

public class BlockStruct {
	public static void main(String[] args) {
		new BlockStruct().load("HelloWorld.class");
	}

	public void load(String filename) {
		try {
			FileInputStream in = new FileInputStream(filename);

			magic = readU4(in);
			minor_version = readU2(in);
			major_version = readU2(in);
			CONSTANT_pool_count = readU2(in);
			constant_pool = new cp_info[CONSTANT_pool_count];
			for (int i = 0; i < CONSTANT_pool_count - 1; i++)
				constant_pool[i + 1] = readInfo(in);

			// Class Info
			access_flags = readU2(in);
			this_class = readU2(in);

			// Extends
			super_class = readU2(in);

			// Implements
			interfaces_count = readU2(in);
			int[] interfaces = new int[interfaces_count];
			for (int i = 0; i < interfaces_count; i++)
				interfaces[i] = readU2(in);

			// Fields
			fields_count = readU2(in);
			field_info fields[] = new field_info[fields_count];
			for (int i = 0; i < fields_count; i++)
				fields[i] = new field_info().load(in, constant_pool);

			// Method
			methods_count = readU2(in);
			MethodInfo methods[] = new MethodInfo[methods_count];
			for (int i = 0; i < methods_count; i++)
				methods[i] = new MethodInfo().load(in, constant_pool);

			// Attribute
			int attributes_count = readU2(in);
			attribute_info attributes[] = new attribute_info[attributes_count];
			for (int i = 0; i < attributes_count; i++)
				attributes[i] = readAttr(in, constant_pool);

			System.out.println("End");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public final static long readU4(InputStream in) {
		try {
			long a = in.read() << 0X18;
			a += in.read() << 0X10;
			a += in.read() << 0X08;
			a += in.read();
			return a;
			// return (long) in.read() << 0X18 + (long) in.read() << 0X10 +
			// in.read() << 0X08 + in.read();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public final static int readU2(InputStream in) {
		try {
			int a = in.read() << 0X08;
			a += in.read();
			return a;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public final static byte readU1(InputStream in) {
		try {
			return (byte) in.read();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public final static byte[] readBytes(InputStream in, int length) {
		try {
			byte[] bytes = new byte[length];
			in.read(bytes, 0, length);
			return bytes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public final static String readChars(InputStream in, int length) {
		try {
			char[] cs = new char[length];

			int cntChar = 0;
			int b0 = 0;
			int b1 = 0;
			int b2 = 0;
			for (int i = 0; i < length; i++) {
				b0 = in.read();
				if (b0 < 0x80) {
					cs[cntChar++] = (char) b0;
				} else if ((b0 & 0xE0) == 0xC0 && (b0 & 0x1E) != 0) {
					i++;
					b1 = in.read();
					cs[cntChar++] = (char) (((b0 & 0x1f) << 6) | (b1 & 0x3f));
				} else if ((b0 & 0xF0) == 0xE0) {
					i++;
					b1 = in.read();
					i++;
					b2 = in.read();
					cs[cntChar++] = (char) (((b0 & 0xf) << 12) | ((b1 & 0x3f) << 6) | (b2 & 0x3f));

				} else if ((b0 & 0xF8) == 0xF0) {
					throw new RuntimeException();
				}

				// c = ((x & 0x1f) << 6) + (y & 0x3f);
			}

			return new String(cs, 0, cntChar);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	long magic;
	int minor_version;
	int major_version;
	int CONSTANT_pool_count;
	cp_info[] constant_pool;
	int access_flags;
	int this_class;
	int super_class;
	int interfaces_count;
	int[] interfaces;
	int fields_count;
	field_info[] fields;
	int methods_count;
	MethodInfo[] methods;
	int attributes_count;
	attribute_info[] attributes;

	public static final byte CONSTANT_Class = 7;
	public static final byte CONSTANT_Fieldref = 9;
	public static final byte CONSTANT_Methodref = 10;
	public static final byte CONSTANT_InterfaceMethodref = 11;
	public static final byte CONSTANT_String = 8;
	public static final byte CONSTANT_Integer = 3;
	public static final byte CONSTANT_Float = 4;
	public static final byte CONSTANT_Long = 5;
	public static final byte CONSTANT_Double = 6;
	public static final byte CONSTANT_NameAndType = 12;
	public static final byte CONSTANT_Utf8 = 1;

	public static final int ACC_PUBLIC = 0x0001;
	public static final int ACC_PRIVATE = 0x0002;
	public static final int ACC_PROTECTED = 0x0004;
	public static final int ACC_STATIC = 0x0008;
	public static final int ACC_FINAL = 0x0010;
	public static final int ACC_SYNCHRONIZED = 0x0020;
	public static final int ACC_NATIVE = 0x0100;
	public static final int ACC_ABSTRACT = 0x0400;
	public static final int ACC_STRICT = 0x0800;

	cp_info readInfo(InputStream in) {
		byte tag;
		cp_info info = null;
		tag = readU1(in);
		switch (tag) {
		case CONSTANT_Class: // 7;
			info = new CONSTANT_Class_info();
			break;
		case CONSTANT_Fieldref: // 9;
			info = new CONSTANT_Fieldref_info();
			break;
		case CONSTANT_Methodref: // 10;
			info = new CONSTANT_Methodref_info();
			break;
		case CONSTANT_InterfaceMethodref: // 11;
			info = new CONSTANT_Integer_info();
			break;
		case CONSTANT_String: // 8;
			info = new CONSTANT_String_info();
			break;
		case CONSTANT_Integer: // 3;
			info = new CONSTANT_Integer_info();
			break;
		case CONSTANT_Float: // 4;
			info = new CONSTANT_Float_info();
			break;
		case CONSTANT_Long: // 5;
			info = new CONSTANT_Long_info();
			break;
		case CONSTANT_Double: // 6;
			info = new CONSTANT_Double_info();
			break;
		case CONSTANT_NameAndType: // 12;
			info = new CONSTANT_NameAndType_info();
			break;
		case CONSTANT_Utf8: // 1;
			info = new CONSTANT_Utf8_info();
			break;
		default:
			throw new RuntimeException("Unknown tag");
		}

		info.load(in);
		return info;
	}

	attribute_info readAttr(InputStream in, cp_info[] CONSTANT_pool) {
		String tag;
		attribute_info info = null;

		int attribute_name_index = readU2(in);
		long attribute_length = readU4(in);

		tag = ((CONSTANT_Utf8_info) CONSTANT_pool[attribute_name_index]).text;
		switch (tag) {
		case "LocalVariableTable": // 7;
			info = new LocalVariableTable_attribute();
			break;
		case "LineNumberTable": // 9;
			info = new LineNumberTable_attribute();
			break;
		case "Code": // 10;
			info = new Code_attribute();
			break;
		case "SourceFile": // 11;
			info = new SourceFile_attribute();
			break;
		default:
			info = new attribute_info_unknown();
		}

		info.attribute_name_index = attribute_name_index;
		info.attribute_length = attribute_length;

		info.load(in);
		return info;
	}

	public static abstract class cp_info {
		byte tag;

		public abstract cp_info load(InputStream in);
	}

	public class CONSTANT_Class_info extends cp_info {
		int name_index;

		@Override
		public CONSTANT_Class_info load(InputStream in) {
			name_index = readU2(in);
			System.out.println(name_index);
			return this;
		}
	}

	public class CONSTANT_Fieldref_info extends cp_info {
		int class_index;
		int name_and_type_index;

		@Override
		public CONSTANT_Fieldref_info load(InputStream in) {
			class_index = readU2(in);
			name_and_type_index = readU2(in);
			return this;
		}
	}

	public class CONSTANT_Methodref_info extends cp_info {
		int class_index;
		int name_and_type_index;

		@Override
		public CONSTANT_Methodref_info load(InputStream in) {
			class_index = readU2(in);
			name_and_type_index = readU2(in);
			return this;
		}
	}

	public class CONSTANT_InterfaceMethodref_info extends cp_info {
		int class_index;
		int name_and_type_index;

		@Override
		public CONSTANT_InterfaceMethodref_info load(InputStream in) {
			class_index = readU2(in);
			name_and_type_index = readU2(in);
			return this;
		}
	}

	public class CONSTANT_String_info extends cp_info {
		int string_index;

		// String string;

		@Override
		public CONSTANT_String_info load(InputStream in) {
			string_index = readU2(in);
			return this;
		}

		public String getString() {
			return ((CONSTANT_Utf8_info) constant_pool[string_index]).text;
		}
	}

	public class CONSTANT_Integer_info extends cp_info {
		long bytes;

		@Override
		public CONSTANT_Integer_info load(InputStream in) {
			bytes = readU4(in);
			return this;

		}
	}

	public class CONSTANT_Float_info extends cp_info {
		long bytes;

		@Override
		public CONSTANT_Float_info load(InputStream in) {
			bytes = readU4(in);
			return this;
		}
	}

	public class CONSTANT_Long_info extends cp_info {
		long high_bytes;
		long low_bytes;

		@Override
		public CONSTANT_Long_info load(InputStream in) {
			high_bytes = readU4(in);
			low_bytes = readU4(in);
			return this;
		}
	}

	public class CONSTANT_Double_info extends cp_info {
		long high_bytes;
		long low_bytes;

		@Override
		public CONSTANT_Double_info load(InputStream in) {
			high_bytes = readU4(in);
			low_bytes = readU4(in);
			return this;
		}
	}

	public class CONSTANT_NameAndType_info extends cp_info {
		int name_index;
		int descriptor_index;

		@Override
		public CONSTANT_NameAndType_info load(InputStream in) {
			name_index = readU2(in);
			descriptor_index = readU2(in);
			return this;
		}
	}

	public class CONSTANT_Utf8_info extends cp_info {
		int length;
		String text;

		@Override
		public CONSTANT_Utf8_info load(InputStream in) {
			length = readU2(in);
			text = readChars(in, length);
			return this;
		}
	}

	public class field_info {
		int access_flags;
		int name_index;
		// String name;

		int descriptor_index;
		String descriptor;

		int attributes_count;
		attribute_info attributes[];

		public field_info load(InputStream in, cp_info[] constant_pool) {
			access_flags = readU2(in);
			name_index = readU2(in);
			descriptor_index = readU2(in);

			attributes_count = readU2(in);
			attributes = new attribute_info[attributes_count];
			for (int i = 0; i < attributes_count; i++)
				attributes[i] = readAttr(in, constant_pool);
			return this;
		}

		public String getName() {
			return ((CONSTANT_Utf8_info) constant_pool[name_index]).text;
		}

		public String getDescriptor() {
			return ((CONSTANT_Utf8_info) constant_pool[descriptor_index]).text;
		}

		@Override
		public String toString() {
			return "field_info [access_flags=" + access_flags + ", name=" + this.getName() + ", descriptor="
					+ this.getDescriptor() + ", attributes=" + Arrays.toString(attributes) + "]";
		}

	}

	public class ConstantValue_attribute extends attribute_info {
		int constantvalue_index;

		public ConstantValue_attribute load(InputStream in) {
			constantvalue_index = readU2(in);
			return this;
		}
	}

	// method_info {
	// u2 access_flags;
	// u2 name_index;
	// u2 descriptor_index;
	// u2 attributes_count;
	// attribute_info attributes[attributes_count];
	// }
	public class MethodInfo {
		int access_flags;
		int name_index;
		int descriptor_index;

		int attributes_count;
		attribute_info attributes[];

		public MethodInfo load(InputStream in, cp_info[] constant_pool) {
			access_flags = readU2(in);
			name_index = readU2(in);
			descriptor_index = readU2(in);

			attributes_count = readU2(in);
			attributes = new attribute_info[attributes_count];
			for (int i = 0; i < attributes_count; i++)
				attributes[i] = readAttr(in, constant_pool);

			return this;
		}
	}

	//
	// Code_attribute {
	// u2 attribute_name_index;
	// u4 attribute_length;
	// u2 max_stack;
	// u2 max_locals;
	// u4 code_length;
	// u1 code[code_length];
	// u2 exception_table_length;
	// { u2 start_pc;
	// u2 end_pc;
	// u2 handler_pc;
	// u2 catch_type;
	// } exception_table[exception_table_length];
	// u2 attributes_count;
	// attribute_info attributes[attributes_count];
	// }
	public class Code_attribute extends attribute_info {
		int max_stack;
		int max_locals;
		long code_length;
		byte code[];
		int exception_table_length;
		exception_table_class exception_table[];

		int attributes_count;
		attribute_info attributes[];

		public Code_attribute load(InputStream in) {

			max_stack = readU2(in);
			max_locals = readU2(in);
			code_length = readU4(in);
			code = readBytes(in, (int) code_length);
			exception_table_length = readU2(in);
			exception_table = new exception_table_class[attributes_count];
			for (int i = 0; i < exception_table_length; i++)
				exception_table[i] = new exception_table_class().load(in);

			attributes_count = readU2(in);
			attributes = new attribute_info[attributes_count];
			for (int i = 0; i < attributes_count; i++)
				attributes[i] = readAttr(in, constant_pool);

			return this;
		}

		class exception_table_class {
			int start_pc;
			int end_pc;
			int handler_pc;
			int catch_type;

			public exception_table_class load(InputStream in) {
				start_pc = readU2(in);
				end_pc = readU2(in);
				handler_pc = readU2(in);
				catch_type = readU2(in);
				return this;
			}
		}
	}

	public abstract class attribute_info {
		int attribute_name_index;
		long attribute_length;

		public abstract attribute_info load(InputStream in);

		@Override
		public String toString() {
			return this.getClass().getName() + " [attribute_name" + constant_pool[attribute_name_index]
					+ ", attribute_length=" + attribute_length + "]";
		}

	}

	public class attribute_info_unknown extends attribute_info {
		byte info[];

		public attribute_info load(InputStream in) {
			info = readBytes(in, (int) super.attribute_length);
			return this;
		}
	}

	public class Exceptions_attribute extends attribute_info {
		int number_of_exceptions;
		int exception_index_table[];

		public Exceptions_attribute load(InputStream in) {
			number_of_exceptions = readU2(in);
			exception_index_table = new int[number_of_exceptions];

			for (int i = 0; i < number_of_exceptions; i++)
				exception_index_table[i] = readU2(in);
			return this;
		}

	}

	public class InnerClasses_attribute extends attribute_info {
		int number_of_classes;
		classes_class classes[];

		public InnerClasses_attribute load(InputStream in) {
			number_of_classes = readU2(in);
			classes = new classes_class[number_of_classes];
			for (int i = 0; i < number_of_classes; i++)
				classes[i] = new classes_class().load(in);
			return this;
		}
	}

	public class classes_class extends attribute_info {
		int inner_class_info_index;
		int outer_class_info_index;

		int inner_name_index;
		int inner_class_access_flags;

		public classes_class load(InputStream in) {
			inner_class_info_index = readU2(in);
			outer_class_info_index = readU2(in);
			inner_name_index = readU2(in);
			inner_class_access_flags = readU2(in);
			return this;
		}
	}

	public class Synthetic_attribute extends attribute_info {

		public Synthetic_attribute load(InputStream in) {
			return this;
		}
	}

	public class SourceFile_attribute extends attribute_info {
		int sourcefile_index;

		public SourceFile_attribute load(InputStream in) {
			sourcefile_index = readU2(in);
			return this;
		}

	}

	public class LineNumberTable_attribute extends attribute_info {
		int line_number_table_length;
		line_number_table_class line_number_table[];

		public LineNumberTable_attribute load(InputStream in) {

			line_number_table_length = readU2(in);
			line_number_table = new line_number_table_class[line_number_table_length];
			for (int i = 0; i < line_number_table_length; i++)
				line_number_table[i] = new line_number_table_class().load(in);
			return this;
		}
	}

	public class line_number_table_class {
		int start_pc;
		int line_number;

		public line_number_table_class load(InputStream in) {
			start_pc = readU2(in);
			line_number = readU2(in);
			return this;
		}
	}

	public class LocalVariableTable_attribute extends attribute_info {
		int local_variable_table_length;
		local_variable_table_class local_variable_table[];

		public LocalVariableTable_attribute load(InputStream in) {

			local_variable_table_length = readU2(in);
			local_variable_table = new local_variable_table_class[local_variable_table_length];
			for (int i = 0; i < local_variable_table_length; i++)
				local_variable_table[i] = new local_variable_table_class().load(in);
			return this;
		}
	}

	public class local_variable_table_class {
		int start_pc;
		int length;
		int name_index;
		int descriptor_index;
		int index;

		public local_variable_table_class load(InputStream in) {
			start_pc = readU2(in);
			length = readU2(in);
			name_index = readU2(in);
			descriptor_index = readU2(in);
			index = readU2(in);
			return this;
		}
	}

	public class Deprecated_attribute extends attribute_info {
		public Deprecated_attribute load(InputStream in) {
			return this;
		}
	}

}