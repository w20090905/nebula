import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class Rdl {
	public static void main(String[] args) {
		try {
			int from = 0;
			int to = 0;
			if (args.length == 3) {
				from = Integer.parseInt(args[1]);
				to = Integer.parseInt(args[2]);
			} else {
				to = Integer.parseInt(args[1]);
			}

			String filename = args[0];

			File file = new File(filename);
			BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "utf-8"), 5 * 1024 * 1024);

			if (from == 0 && to > 0) {
				String line = "";
				for (int i = 0; (line = reader.readLine()) != null && i < to; i++) {
					System.out.println(line);
				}
			} else if (from == 0 && to < 0) {
				String[] lines = new String[-to];

				int i = 0;
				String line = "";
				while ((line = reader.readLine()) != null) {
					lines[i] = line;
					i++;
					if (i >= lines.length) {
						i = 0;
					}
				}

				int j = i + 1;
				if (j >= lines.length) {
					j = 0;
				}

				for (; j < lines.length; j++) {
					System.out.println(lines[j]);
				}

				for (j = 0; j < i; j++) {
					System.out.println(lines[j]);
				}

			} else if (from > 0 && to > 0) {
				int cnt = to;

				String line = "";
				for (int i = 0; (line = reader.readLine()) != null && i < from; i++)
					;
				for (int i = from; (line = reader.readLine()) != null && i < from + cnt; i++) {
					System.out.println(line);
				}
			}

		} catch (NumberFormatException e) {
			throw new RuntimeException(e);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
