package test.pdf;

import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.NumberFormatter;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdfwriter.ContentStreamWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.util.PDFOperator;

/**
 * This is an example on how to remove all text from PDF document.
 * 
 * Usage: java org.apache.pdfbox.examples.util.RemoveAllText &lt;input-pdf&gt;
 * &lt;output-pdf&gt;
 * 
 * @author <a href="mailto:ben@benlitchfield.com">Ben Litchfield</a>
 * @version $Revision: 1.2 $
 */
public class TestPDF {
	/**
	 * Default constructor.
	 */
	private TestPDF() {
		// example class should not be instantiated
	}

	public static void main(String[] args) throws Exception {
		new TestPDF().parse(args);
	}

	/**
	 * This will remove all text from a PDF document.
	 * 
	 * @param args
	 *            The command line arguments.
	 * 
	 * @throws Exception
	 *             If there is an error parsing the document.
	 */

	NumberFormat f = NumberFormat.getNumberInstance();

	public void parse(String[] args) throws Exception {
		f.setMaximumFractionDigits(0);
		f.setGroupingUsed(false);
		if (args.length != 2) {
			usage();
		} else {
			PDDocument document = null;
			try {
				document = PDDocument.load(args[0]);
				if (document.isEncrypted()) {
					System.err.println("Error: Encrypted documents are not supported for this example.");
					System.exit(1);
				}
				List<?> allPages = document.getDocumentCatalog().getAllPages();

				float fontsize = 0;
				float lineHeight = 0;

				float px = 0, py = 0;
				char pC = 0;

				COSNumber cosX, cosY;
				COSString cs;

				for (int i = 1; i < 4; i++) {
					PDPage page = (PDPage) allPages.get(i);
					PDFStreamParser parser = new PDFStreamParser(page.getContents());
					parser.parse();
					List<Object> tokens = parser.getTokens();

					boolean newline = true;
					int pStart = 0;
					for (int j = 0; j < tokens.size(); j++) {
						Object o = tokens.get(j);

						if (o instanceof PDFOperator) {
							String op = ((PDFOperator) o).getOperation();
							switch (op) {
							/* 文本直接关联命令 */
							case "Tj": // 顯示文字
								cs = (COSString) tokens.get(j - 1);

								px += fontsize;

								out(cs.getString());
								pC += cs.getString().length();
								break;

							case "TJ": // 顯示文字，數字物件用來表示文字顯示後接的文字應顯示之游標位置，需延基準線回減多少距離，單位是字形大

								out("[" + f.format(pC) + ":" + f.format(px) + "," + f.format(py) + "]");	
								COSArray array = (COSArray) tokens.get(j - 1);
								for (int k = 0; k < array.size(); k++) {
									COSBase io = array.get(k);
									if (io instanceof COSString) {
										cs = (COSString) io;

										px += fontsize;
										assert fontsize> 0;
										assert px > 0;

										out(cs.getString());
										pC += cs.getString().length();
									} else if (io instanceof COSFloat) {
										COSFloat f = (COSFloat) array.get(k);
										px += fontsize * f.floatValue();
										assert f.floatValue()> 0;
										assert fontsize> 0;
										assert px > 0;
										if (f.floatValue() > 100 || f.floatValue() < -100) {
											out(" ");
											
										}
									}
								}
								out("[" + f.format(pC) + ":" + f.format(px) + "," + f.format(py) + "]");	
								break;

							case "TD": // 文字行游標偏移(x,y)，並將行距設成-y
								lineHeight = -((COSNumber) tokens.get(j - 1)).floatValue();
							case "Td":// 文字行游標偏移(x,y)位置
								out("<TD>");
								out("[" + f.format(pC) + ":" + f.format(px) + "," + f.format(py) + "]");								
								out("->");
								cosX = (COSNumber) tokens.get(j - 2);
								cosY = (COSNumber) tokens.get(j - 1);
								px += -cosX.floatValue();
								py += -cosY.floatValue();
								out("[" + f.format(pC) + ":" + f.format(px) + "," + f.format(py) + "]");						
								out("</TD>");

								break;
							case "Tf":// 設定字形與大小
								fontsize = ((COSNumber) tokens.get(j - 1)).floatValue();
								break;

							case "Tm":// 設定文字座標轉換矩陣，並重設文字游標到原點
								
								// Before
								out("[" + f.format(pC) + ":" + f.format(px) + "," + f.format(py) + "]");
								pC = 0;
								newline = true;
								out("\n");

								out("<Tm>");
								COSNumber t1 = (COSNumber) tokens.get(j - 6);
								COSNumber t2 = (COSNumber) tokens.get(j - 5);
								COSNumber t3 = (COSNumber) tokens.get(j - 4);
								COSNumber t4 = (COSNumber) tokens.get(j - 3);
								if (t1.floatValue() != 1 || t2.floatValue() != 0 || t3.floatValue() != 0
										|| t4.floatValue() != 1) {
									outNumber(t1, t2, t3, t4);
									out(":");
								}
								
								cosX = (COSNumber) tokens.get(j - 2);
								cosY = (COSNumber) tokens.get(j - 1);
								px = cosX.floatValue();
								py = cosY.floatValue();
								out("[" + f.format(pC) + ":" + f.format(px) + "," + f.format(py) + "]");
								out("</Tm>");

								// out("[Tm]");
								break;
							case "cm":// 設定CTM座標轉換矩陣
								// Before
								out("[" + f.format(pC) + ":" + f.format(px) + "," + f.format(py) + "]");
								pC = 0;
								newline = true;
								out("\n");
								
								out("<cm>");
								outNumber((COSNumber) tokens.get(j - 6), (COSNumber) tokens.get(j - 5),
										(COSNumber) tokens.get(j - 4), (COSNumber) tokens.get(j - 3),
										(COSNumber) tokens.get(j - 2), (COSNumber) tokens.get(j - 1));
								out("</cm>");
								break;

							case "BT":// Begin Text 開始一個文字物件，並重設文字座標轉換矩陣與文字游標位置
								if (!newline) out("\n");
								out("\n");
								out("<p>");
								px = 0;
								newline = true;
								break;
							case "ET":// End Text 結束一個文字物件
								out("</p>");
								out("\n");
								out("\n");
								newline = true;
								break;

							/* 影响游标命令 */
							case "m":// 將游標移到指定位置
								cosX = (COSNumber) tokens.get(j - 2);
								cosY = (COSNumber) tokens.get(j - 1);
								px += -cosX.floatValue();
								py += -cosY.floatValue();

								break;
							case "T*":// 將文字行游標移到下一行
								// Before
								out("[" + f.format(pC) + ":" + f.format(px) + "," + f.format(py) + "]");
								pC = 0;
								newline = true;
								out("\n");
								
								out("\n[NEWLINE T*");				
								px = 0;
								out("[" + f.format(pC) + ":" + f.format(px) + "," + f.format(py) + "]");
								out("]");
								
								break;

							/* 绘图相关命令 */
							case "c":// 畫一條Bezier曲線
							case "Do":// 處理一個XObject資料可由額外資源中取得XObject資料
							case "f":// 對封閉區域塗網
							case "g":// 設定畫線時的灰度值
							case "G":// 設定斷線時的灰度值
							case "gs":// 屬性名稱（名稱物件），可由額外資源中取得一個詞典物件
							case "l":// 畫一直線
							case "q":// 儲存繪圖狀態
							case "rg":// 設定畫線時的色彩值
							case "RG":// DONE設定斷線時的色彩值color
							case "Q":// 回復繪圖狀態
							case "w":// 設定線粗
								break;

							case "TL": // DONE: 设定行距
								lineHeight = ((COSNumber) tokens.get(j - 1)).floatValue();
								break;

							default:
								if (!newline) out("\n");

								out("/" + op + ": ");
								for (int k = pStart; k < j; k++) {
									out(tokens.get(k).toString());
									out(" ");
								}
								out("\n");
								newline = true;
							}
							pStart = j + 1;
						}
					}
				}
			} finally {
				if (document != null) {
					document.close();
				}
			}
		}
	}

	void out(String s) {
		System.out.print(s);
	}

	void outNumber(COSNumber... ns) {
		out(f.format(ns[0].doubleValue()));
		for (int i = 1; i < ns.length; i++) {
			out(",");
			out(f.format(ns[i].doubleValue()));
		}
	}

	void outNumber(float... ns) {
		out(f.format(ns[0]));
		for (int i = 1; i < ns.length; i++) {
			out(",");
			out(f.format(ns[i]));
		}
	}

	/**
	 * This will print the usage for this document.
	 */
	private static void usage() {
		System.err.println("Usage: java org.apache.pdfbox.examples.pdmodel.RemoveAllText <input-pdf> <output-pdf>");
	}

}
