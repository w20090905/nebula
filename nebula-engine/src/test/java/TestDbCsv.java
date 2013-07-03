import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;

public class TestDbCsv {
	public static void main(String[] args) {
		String filename = "d://hhtord.csv";
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			String s = null;

			List<Multiset<String>> valuesMultisetArray = Lists.newCopyOnWriteArrayList();

			final int MAX_FIELD = 180;
			for (int i = 0; i < MAX_FIELD; i++) {
				Multiset<String> o = HashMultiset.create();
				valuesMultisetArray.add(o);
			}
			
			s = reader.readLine();
			final int MAX = 1000 * 1000;
			for(int i=0;i<MAX && s != null;i++,s = reader.readLine()) {
				String[] sd = s.split(",");

				for (int j=0;j<sd.length;j++){
					valuesMultisetArray.get(j).add(sd[j]);
				}
				
				if (i % 1000 == 0) {
					System.out.println(i);
				}
			}
			System.out.println("=============");

			for (int i = 0; i < MAX_FIELD; i++) {
				Multiset<String> o = valuesMultisetArray.get(i);
				System.out.println(o.entrySet().size());				
			}

		} catch (FileNotFoundException e) {

			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
