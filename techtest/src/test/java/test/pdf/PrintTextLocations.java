/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test.pdf;


/**
 * This is an example on how to get some x/y coordinates of text.
 * 
 * Usage: java org.apache.pdfbox.examples.util.PrintTextLocations
 * &lt;input-pdf&gt;
 * 
 * @author <a href="mailto:ben@benlitchfield.com">Ben Litchfield</a>
 * @version $Revision: 1.7 $
 */
public class PrintTextLocations{// extends PDFTextStripper {
	/**
//	 * Default constructor.
//	 * 
//	 * @throws IOException
//	 *             If there is an error loading text stripper properties.
//	 */
//	public PrintTextLocations() throws IOException {
//		super.setSortByPosition(true);
//	}
//
//	Writer out;
//
//	class Line {
//		long fromX;
//		long fromY;
//		long toX;
//		long toY;
//		boolean empty = false;
//		boolean newParagraph = true;
//		StringBuilder text = new StringBuilder(100);
//
//		@Override
//		public String toString() {
//			return "Line [fromX=" + fromX + ", fromY=" + fromY + ", toX=" + toX + ", toY=" + toY + ", empty=" + empty
//					+ ", newParagraph=" + newParagraph + ", text=" + text + "]";
//		}
//
//	}
//
//	class Page {
//		ArrayList<Line> lines = new ArrayList<>(100);
//	}
//
//	/**
//	 * This will print the documents data.
//	 * 
//	 * @param args
//	 *            The command line arguments.
//	 * 
//	 * @throws Exception
//	 *             If there is an error parsing the document.
//	 */
//	public static void main(String[] args) throws Exception {
//		if (args.length != 1) {
//			usage();
//		} else {
//			String from = args[0];
//			String to = null;
//			if (args.length > 1) {
//				to = args[1];
//			} else {
//				to = from.replace(".pdf", "." + System.currentTimeMillis() + ".txt");
//			}
//			new PrintTextLocations().deal(from, to);
//		}
//	}
//
//	public void deal(String fromFile, String toFile) {
//
//		PDDocument document = null;
//		try {
//			
//			this.out = new FileWriter(toFile);
//			
//			document = PDDocument.load(fromFile);
//			if (document.isEncrypted()) {
//				try {
//					document.decrypt("");
//				} catch (InvalidPasswordException e) {
//					System.err.println("Error: Document is encrypted with a password.");
//					System.exit(1);
//				}
//			}
//			List<?> allPages = document.getDocumentCatalog().getAllPages();
//			for (int i = 0; i < allPages.size(); i++) {
//				this.page = new Page();
//				this.lastX = 0;
//				this.lastY = 0;
//				this.lastText = null;
//
//				PDPage page = (PDPage) allPages.get(i);
//				System.out.println("Processing page: " + i);
//				PDStream contents = page.getContents();
//
//				if (contents != null) {
//					this.processStream(page, page.findResources(), page.getContents().getStream());
//				}
//
//				if (this.page.lines.size() > 0) {
//					this.analyze(this.page);
//				}
//
//			}
//
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		} finally {
//			try {
//			if (document != null) {
//					document.close();
//			}
//			if (out != null) {
//				out.close();
//		}
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}
//		}
//	}
//
//	long lastY = 0;
//	long lastX = -1;
//
//	TextPosition lastText;
//	Line line;
//	Page page;
//
//	protected void analyze(Page page) {
//
//		List<Line> lines = page.lines;
//
//		Line lastLine = lines.get(0);
//		Line line;
//		int pStartLine = 0;
//		long pMaxX = lastLine.toX;
//		int canBeParagraph = 1;
//
//		for (int i = 1; i < lines.size(); i++) {
//			line = lines.get(i);
//			pMaxX = pMaxX > line.toX ? pMaxX : line.toX;
//
//			if (lastLine.empty) {
//				line.newParagraph = true;
//				pStartLine = i;
//				pMaxX = line.toX;
//				canBeParagraph = 1;
//			} else if (line.empty) {
//				for (int k = pStartLine + 1; k < i; k++) {
//					StringBuilder sb = lines.get(k - 1).text;
//					if (sb.charAt(sb.length() - 1) == '-') {
//						sb.setLength(sb.length() - 1);
//					} else if (sb.charAt(sb.length() - 1) == ' ') {
//					} else {
//						sb.append(' ');
//					}
//				}
//				line.newParagraph = true;
//			} else if (canBeParagraph == 1) {
//				if (line.fromX == lastLine.fromX && pMaxX - lastLine.toX >= 0
//						&& pMaxX - lastLine.toX < lastLine.toX / 8) {
//					line.newParagraph = false;
//					canBeParagraph = 1;
//				} else {
//					for (int k = pStartLine; k < i; k++) {
//						lines.get(i).newParagraph = true;
//					}
//					canBeParagraph = -1;
//				}
//				// } else if(canBeParagraph == 0) {
//				// canBeParagraph = 1;
//				// line.newParagraph = true;
//				// pStartLine = i;
//				// pMaxX = line.toX;
//			} else {
//				// canBeParagraph = 1;
//				line.newParagraph = true;
//				pStartLine = i;
//				pMaxX = line.toX;
//			}
//			lastLine = line;
//		}
//
//		boolean newline = true;
//
//		for (int i = 0; i < lines.size(); i++) {
//			line = lines.get(i);
//			if (line.newParagraph) {
//				this.write("\r\n");
//				newline = true;
//			}
//
//			// System.out.print(String.format(" %03d  -<< %03d : %s  : %03d",
//			// line.fromY, line.fromX,
//			// line.text.toString(), line.toX));
//			this.write(line.text.toString());
//			newline = false;
//		}
//		if (newline) this.write("\r\n");
//
//	}
//
//	public void write(String s) {
//		try {
//			out.write(s);
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	protected void processTextPosition(TextPosition text) {
//		if (lastY == 0 || (long) text.getYDirAdj() - line.fromY > lastText.getHeight()) {
//
//			if (lastY > 0) {
//				line.toX = lastX;
//				line.toY = lastY;
//
//				long ls = (long) (text.getYDirAdj() - lastText.getYDirAdj() - lastText.getHeightDir());
//				if (ls > lastText.getHeightDir() * 1.2 || ls > text.getHeightDir() * 1.2) {
//					line = new Line();
//					line.empty = true;
//					page.lines.add(line);
//				}
//
//			}
//			lastText = text;
//
//			line = new Line();
//			line.fromX = (long) text.getXDirAdj();
//			line.fromY = (long) text.getYDirAdj();
//
//			// New Line
//			lastX = (long) text.getXDirAdj() + (long) text.getWidthDirAdj();
//			lastY = (long) text.getYDirAdj();
//
//			page.lines.add(line);
//
//		}
//
//		if (text.getXDirAdj() - lastX >= 0.7 * text.getWidthOfSpace()) {
//			line.text.append(' ');
//		}
//
//		lastY = (long) text.getYDirAdj();
//		lastX = (long) text.getXDirAdj() + (long) text.getWidthDirAdj();
//
//		line.text.append(text.getCharacter());
//	}
//
//	/**
//	 * This will print the usage for this document.
//	 */
//	private static void usage() {
//		System.err.println("Usage: java org.apache.pdfbox.examples.pdmodel.PrintTextLocations <input-pdf>");
//	}

}
