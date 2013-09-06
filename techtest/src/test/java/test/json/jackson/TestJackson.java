package test.json.jackson;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class TestJackson {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		User user = mapper.readValue(new File("User.json"), User.class);

		oos.writeObject(user);
		// deserialize object, get new object newpair
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		User newpair = (User) ois.readObject();

		readByObjectMapper();
	}

	public static void readByObjectMapper() throws ClassNotFoundException {
		final int timeOuter = 10;
		final int timeIn = 1000;
		final int times = timeOuter * timeIn;
		long start = 0, end = 0;
		try {
			ObjectMapper mapper = new ObjectMapper(); // can reuse, share
														// globally
			User user = mapper.readValue(new File("User.json"), User.class);

			long costUser, costMap, costJava,costJavaRaw;
			long costUserW, costMapW, costRawW, costJavaW,costJavaRawW;

			{
				// -- JAVA START --
				ByteArrayOutputStream bos = new ByteArrayOutputStream(2048);
				ObjectOutputStream oos = new ObjectOutputStream(bos);
				oos.writeObject(user);
				ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
				ObjectInputStream ois = new ObjectInputStream(bis);
				ois.readObject();
				start = System.nanoTime();
				for (int i = 0; i < timeOuter; i++) {
					bos.reset();
					oos = new ObjectOutputStream(bos);
					for (int j = 0; j < timeIn; j++) {
						oos.writeObject(user);
					}
				}
				end = System.nanoTime();
				costJavaW = timeEverySecond(times, start, end);

				bis = new ByteArrayInputStream(bos.toByteArray());

				start = System.nanoTime();
				for (int i = 0; i < timeOuter; i++) {
					bis.reset();
					ois = new ObjectInputStream(bis);
					for (int j = 0; j < timeIn; j++) {
						user = (User) ois.readObject();
					}
				}
				end = System.nanoTime();
				costJava = timeEverySecond(times, start, end);
				// -- JAVA END --
				
				
			}
			{
				// -- JAVA START --
				user = mapper.readValue(new File("User.json"), User.class);
				
				ByteArrayOutputStream bos = new ByteArrayOutputStream(2048);
				ObjectOutputStream oos = new ObjectOutputStream(bos);
				user.writeExternal(oos);
				oos.writeObject(user);
				ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
				ObjectInputStream ois = new ObjectInputStream(bis);
				user.readExternal(ois);
				start = System.nanoTime();
				for (int i = 0; i < timeOuter; i++) {
					bos.reset();
					oos = new ObjectOutputStream(bos);
					for (int j = 0; j < timeIn; j++) {
						user.writeExternal(oos);
					}
				}
				end = System.nanoTime();
				costJavaRawW = timeEverySecond(times, start, end);

				bis = new ByteArrayInputStream(bos.toByteArray());

				start = System.nanoTime();
				for (int i = 0; i < timeOuter; i++) {
					bis.reset();
					ois = new ObjectInputStream(bis);
					for (int j = 0; j < timeIn; j++) {
						user.readExternal(ois);
					}
				}
				end = System.nanoTime();
				costJavaRaw = timeEverySecond(times, start, end);
				// -- JAVA END --
				
				
			}
			{

				ByteArrayOutputStream os = new ByteArrayOutputStream(2048);
				mapper.writeValue(os, user);

				ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());

				Map<?, ?> mp = mapper.readValue(is, Map.class);

				start = System.nanoTime();
				for (int i = 0; i < timeOuter; i++) {
					os.reset();
					for (int j = 0; j < timeIn; j++) {
						mapper.writeValue(os, user);
					}
				}
				end = System.nanoTime();

				costUserW = timeEverySecond(times, start, end);

				is = new ByteArrayInputStream(os.toByteArray());

				start = System.nanoTime();
				for (int i = 0; i < timeOuter; i++) {
					for (int j = 0; j < timeIn; j++) {
						is.reset();
						user = mapper.readValue(is, User.class);
					}
				}
				end = System.nanoTime();
				costUser = timeEverySecond(times, start, end);

				start = System.nanoTime();
				for (int i = 0; i < timeOuter; i++) {
					os.reset();
					for (int j = 0; j < timeIn; j++) {
						mapper.writeValue(os, mp);
					}
				}
				end = System.nanoTime();
				costMapW = timeEverySecond(times, start, end);

				is = new ByteArrayInputStream(os.toByteArray());

				start = System.nanoTime();
				for (int i = 0; i < timeOuter; i++) {
					for (int j = 0; j < timeIn; j++) {
						is.reset();
						mp = mapper.readValue(is, Map.class);
					}
				}
				end = System.nanoTime();
				costMap = timeEverySecond(times, start, end);

				UserSerialize us = new UserSerialize();
				JsonFactory f = new JsonFactory();
				JsonGenerator g = f.createJsonGenerator(os);

				start = System.nanoTime();
				for (int i = 0; i < timeOuter; i++) {
					os.reset();
					for (int j = 0; j < timeIn; j++) {
						us.writeMedia(g, user);
					}
				}
				end = System.nanoTime();
				costRawW = timeEverySecond(times, start, end);

			}

			System.out.println("costUser : " + costUser + " costMap : " + costMap + " costJava : " + costJava + " costJavaRaw : " + costJavaRaw);
			System.out.println("costUserW: " + costUserW + " costMapW: " + costMapW + " costJavaW: " + costJavaW
					+ " costRawW: " + costRawW+ " costJavaRawW: " + costJavaRawW);

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static long timeEverySecond(long times, long start, long end) {
		return 1000 * 1000 * 1000 * times / (end - start);
	}
}
