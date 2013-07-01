package test.load.pdftxt;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.regex.Pattern;

public class PDFFormatFileScanToFormat {
	public static void main(String[] args) {
		try {

			String fromFile = "d:\\L.txt";
			new PDFFormatFileScanToFormat().deal(fromFile);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static final int PAGE_HEADER = 10;

	static final int BEGIN_PAGE_HEADER = 202;
	static final int BEGINE_LINE = 201;

	static final int PAGE_LINE = 15;
	static final int EMPTY_LINE = 100;
	LineNumberReader in = null;
	FileWriter out = null;

	public void deal(String fromFile) throws Exception {
		String toFile = fromFile.replaceAll("[.]", "." + System.currentTimeMillis() + ".");
		FileReader fr = new FileReader(fromFile);
		InputCharStream in = new InputCharStream(fr);

		out = new FileWriter(toFile, false);

		int status = 0;
		int lastIndent = 0;
		int indent = 0;

		status = BEGINE_LINE;

		String line = "";
		String lastLine = "";

		int from = 0, to = 0;

		String indentSpace = "";
		String leadNum = "";

//		int cntLine = 0;
		boolean newLined = false;
		try {
			OutterWhile: while (true) {
				if (in.LA(1) > 0X8000) {
					in.consume();
					continue;
				}
				indent = 0;
				line = "";
				indentSpace = "";
				leadNum = "";
				switch (in.LA(1)) {
				case '-':
					indent = lastIndent;
					from = in.index();

					line = toLineEnd(in);
					System.out.println("CLEAR   |" + line);
					from = in.index();
					toLineEnd(in);
					assert to - from < 1;
					System.out.println("CLEAR   |");

					status = PAGE_HEADER;
					line = lastLine;
					break;
				case '\r':
					in.consume();
				case '\n':
					in.consume();
					if (status == PAGE_LINE) {
						if (!newLined) this.newLine("case '\n' status == PAGE_LINE");
						newLined = true;
					}
					status = EMPTY_LINE;
					line = "";
					this.newLine("case '\n' status == EMPTY_LINE");
					newLined = true;
					break;
				case -1:
					break OutterWhile;
				case ' ':
					from = in.index();
					while ((c = in.LA(1)) == ' ')
						in.consume();
					indent = in.index() - from;
					if (in.index() > from) {
						indentSpace = in.substring(from, in.index() - 1);
					}

				default:
					from = in.index();
					consumeLeadingNumber(in);
					leadNum = "";
					indent += in.index() - from;
					if (in.index() > from) {
						leadNum = in.substring(from, in.index() - 1);
					}

					// Empty Line
					from = in.index();
					line = toLineEnd(in);
					if (line.length() == 0) {
						if (status == PAGE_LINE) {
							this.newLine("default: if (status == PAGE_LINE) {");
							newLined = true;
						}
						status = EMPTY_LINE;
						line = "";
						this.newLine("default: status = EMPTY_LINE;");
						newLined = true;
						indentSpace = "";
						leadNum = "";

						break;
					}

					if (status == PAGE_HEADER && indent > 30 && pPageBegin.matcher(line).find()) {
						status = PAGE_HEADER;

						System.out.println("CLEAR   |" + indentSpace + leadNum + line);
						line = lastLine;
						break;
					}

					if (!newLined) {
						if (Math.abs(lastIndent - indent) > 1) {
							this.newLine("Math.abs");
							this.write(indentSpace);
						} else if (lastIndent > 0 && line.length() > 0) {
							// System.out.println("[[" +
							// pLineNum.matcher(lastLine).find() + "]] --  |" +
							// lastLine);
							if (pLineNum.matcher(lastLine).find()) {
								this.newLine("lastIndent > 0");
								this.write(indentSpace);
							} else {
								this.write(" ");
							}
						} else {
							this.write(" ");
						}
					} else {
						this.write(indentSpace);
					}

					status = PAGE_LINE;
					if (line.length() > 20
							&& ((line.charAt(line.length() - 1) == '-'
									&& Character.isLetter(line.charAt(line.length() - 2))
							|| (line.charAt(line.length() - 2) == '-' && Character
									.isLetter(line.charAt(line.length() - 2)))))) {
						System.out.println("****[[" + pLineEndHalf.matcher(line).hitEnd() + "]] --  |" + line);
						// if (pLineEndHalf.matcher(line).hitEnd()) {
						line = line.substring(0, line.length() - 2);
						// }

					}
					this.write(leadNum + line);
					newLined = false;

					indentSpace = "";
					leadNum = "";

					break;
				}

				lastIndent = indent;
				lastLine = line;

				// cntLine++;
				// if ((cntLine >> 8) << 8 == cntLine) {
				// System.out.println(String.valueOf(cntLine));
				// }
			}

		} finally {
			out.close();
		}
	}

	static final Pattern pLineEndHalf = Pattern.compile("[A-Za-z\\.\\,\\;\\'\\\" ]+[-] ?");

	static final Pattern pPageBegin = Pattern.compile("[A-Z][A-Z][A-Z]*");// (\\([0-9a-kA-Z]*\\))?[A-Z]*
																			// *[0-9]*
																			// *");
	static final Pattern pLineNum = Pattern.compile("(\\. )(\\. )+[\\. ]+[0-9]+ *");

	int consumeLeadingSpace(InputCharStream in) {
		int from = in.index();
		int to = in.index();
		while ((c = in.LA(1)) == ' ')
			;
		assert to > from;

		return from - to;

	}

	String consumeLeadingNumber(InputCharStream in) {
		int from = in.index();
		int to = in.index();
		Outter: while ((c = in.LA(1)) > 0) {
			switch (c) {
			case '0' - '9':
				break;
			case '•':
				break;
			case '.':
				break;
			default:
				break Outter;
			}
			in.consume();
		}

		to = in.index();

		if (to > from) {
			return in.substring(from, to - 1);

		} else {
			return "";
		}

	}

	String toLineEnd(InputCharStream in) {
		int from = in.index();
		int to = in.index();
		Outter: while ((c = in.LA(1)) > 0) {
			switch (c) {
			case '\r':
				to = in.index() - 1;
				in.consume();
				break;
			case '\n':
				if (to <= from) to = in.index() - 1;
				in.consume();
				break Outter;
			default:
				in.consume();
				break;
			}
		}
		assert to >= from;

		if (to > from) {
			return in.substring(from, to - 1);
		} else {
			return "";
		}
	}

	int status = 0;
	int c = -1;

	void nextLine() {
		try {
			c = in.read();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	void write(String sline) {
		try {
			out.write(sline);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	void newLine(String location) {
		try {
			out.write("\r\n");// + "[["+ location +"]]");
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

}
