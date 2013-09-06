package test.httpd;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

class ChannelState {
	public ChannelState(FileChannel fchannel, long position, long total, ByteBuffer buffer) {
		this.fchannel = fchannel;
		this.position = position;
		this.total_file_size = total;
		this.buffer = buffer;
	}

	public FileChannel fchannel;
	public ByteBuffer buffer;
	public long position;
	public long total_file_size;
}

public class webserver {
	public static void main(String[] args) {
		// File wwwBase=new File("h:/workspace/web server/");
		// File wwwBase=new File("/home/jacasey/workspace/web server/");

		File wwwBase = new File("d:/Temp/");

		try {
			ServerSocketChannel channel = ServerSocketChannel.open();

			InetSocketAddress address = new InetSocketAddress(8080);
			channel.socket().bind(address);
			channel.configureBlocking(false);

			Selector selector = Selector.open();
			channel.register(selector, SelectionKey.OP_ACCEPT);

			while (true) {
				int n = selector.select();

				if (n == 0) {
					continue;
				}
				Set<SelectionKey> readyKeys = selector.selectedKeys();
				Iterator<SelectionKey> ready = readyKeys.iterator();

				while (ready.hasNext()) {
					SelectionKey selkey = ready.next();
					ready.remove();
					try {
						if (selkey.isValid() == false) {
							continue;
						} else if (selkey.isAcceptable()) {
							ServerSocketChannel server = (ServerSocketChannel) selkey.channel();
							SocketChannel client = server.accept();

							if (client == null) continue;

							client.configureBlocking(false);
							client.register(selector, SelectionKey.OP_READ);
							ByteBuffer buffer = ByteBuffer.allocate(3276);

							SelectionKey key = client.keyFor(selector);
							key.attach(buffer);
						} else if (selkey.isReadable()) {
							handle_ready_read(wwwBase, selkey);
						} else if (selkey.isWritable()) {
							handle_write_ready(selector, selkey);
						}
					} catch (IOException err) {
						selkey.channel().close();
						err.printStackTrace();
					}

				}

			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void handle_write_ready(Selector selector, SelectionKey selkey) throws IOException,
			ClosedChannelException {
		SocketChannel client = (SocketChannel) selkey.channel();

		ChannelState state = (ChannelState) selkey.attachment();

		if (state.buffer != null) {
			client.write(state.buffer);

			if (state.buffer.hasRemaining()) {
				state.buffer.compact();
				client.register(selkey.selector(), SelectionKey.OP_WRITE);
				selkey.attach(state);
			} else {
				transfer_file(selkey, client, state);
			}
		} else {
			transfer_file(selkey, client, state);
		}
	}

	@SuppressWarnings("unused")
	public static void handle_ready_read(File wwwBase, SelectionKey selkey) throws IOException, MalformedURLException,
			FileNotFoundException {
		ByteBuffer buffer = (ByteBuffer) selkey.attachment();
		SocketChannel client = (SocketChannel) selkey.channel();
		long BYTES_READ = client.read(buffer);
		int position = buffer.position();
		buffer.flip();

		if (BYTES_READ >= 4) {
			// String tmp=new String(buffer.array());
			// System.err.println(tmp);

			boolean short_read = process_request(buffer);
			if (short_read == false) {
				// determine what sort of request it is
				String requestMethod = "";

				String queryString = process_query_string(buffer, requestMethod);

//				File data = new File(wwwBase + queryString);
//				FileInputStream fis = new FileInputStream(data);
//				FileChannel fchannel = fis.getChannel();

				// use the inbuilt class to guess the type of data we are going
				// towrite back to the client
				String contentType = "text/html";
//				System.out.println("request: " + data.toString());
//				contentType = URLConnection.guessContentTypeFromName(data.toString());

//				if (data.isFile()) {
					StringBuffer output = new StringBuffer();
					output.append("HTTP/1.0 200 OK\r\n");
					output.append("Server: proxy\r\n");
					// output.append("Connection: close\r\n"); // eventually I
					// will have to detect whether a client is capable of
					// persistance OK for now.
					output.append("Connection: keep-alive\r\n");
					output.append("Content-Type: " + contentType + "\r\n");
					output.append(("Content-Length: 21\r\n"));
					output.append("\r\n");

					buffer.clear();
					buffer.put(output.toString().getBytes());
					buffer.flip();

					client.write(buffer);
					


					ByteBuffer body = ByteBuffer.allocate(1024);
					body.put("<h1>Hello world!</h1>".getBytes());
					body.flip();

					client.write(body);
					

					selkey.cancel();
					client.close();

//					if (buffer.hasRemaining()) {
//						// short header write
//						buffer.compact();
//
//						client.register(selkey.selector(), SelectionKey.OP_WRITE);
//						selkey.attach(buffer);
//					} else {
//						transfer_file(selkey, client, new ChannelState(fchannel, 0, data.length(), buffer));
//					}
//				} else {
//					// file does not exist
//				}
			} else {
				// short read
				client.register(selkey.selector(), SelectionKey.OP_READ);

				// reset the buffer so that we can read more data in
				buffer.position(position);
				buffer.limit(buffer.capacity());
				selkey.attach(buffer);
			}
		} else if (BYTES_READ > 0) {
			// short read
			client.register(selkey.selector(), SelectionKey.OP_READ);

			// reset the buffer so that we can read more data in
			buffer.position(position);
			buffer.limit(buffer.capacity());
			selkey.attach(buffer);
		} else if (BYTES_READ == -1) {
			System.err.println("closed");
			client.close();
		}
	}

	public static String process_query_string(ByteBuffer buffer, String requestMethod) {
		byte[] request = new byte[buffer.position()];
		buffer.flip();
		buffer.get(request);
		buffer.clear();
		int i = 0;
		for (; i < request.length; i++) {
			if (request[i] == ' ') {
				i++;
				break;
			}
			requestMethod += (char) request[i];
		}

		// extract the query_string if we are dealing with a get request
		StringBuffer queryString = new StringBuffer(100);
		if (requestMethod.equalsIgnoreCase("GET")) {
			for (; i < request.length; i++) {
				if (request[i] == ' ') break;

				queryString.append((char) request[i]);
			}
		} else {
			System.err.println("unhandled method" + requestMethod);
			// unhandled method
		}
		return queryString.toString();
	}

	public static boolean process_request(ByteBuffer buffer) {
		boolean short_read = false;
		byte END_HTTP[] = "\r\n\r\n".getBytes();
		byte END_OF_REQUEST[] = new byte[4];

		buffer.position(buffer.limit() - 4);
		buffer.get(END_OF_REQUEST, 0, 4);

		for (int j = 0; j < END_OF_REQUEST.length; j++) {
			if (END_HTTP[j] != END_OF_REQUEST[j]) {
				short_read = true;
				break;
			}
		}
		return short_read;
	}

	public static void transfer_file(SelectionKey selkey, SocketChannel client, ChannelState cs) throws IOException,
			ClosedChannelException {

		ByteBuffer buffer = cs.buffer;
		// pipe the file channel data back to our socket channel
		long write = cs.fchannel.transferTo(cs.position, 32768, client);

		// if we have a short write select OP_WRITE
		if (cs.position + write < cs.total_file_size) {
			client.register(selkey.selector(), SelectionKey.OP_WRITE);
			cs.position += write;
			selkey.attach(cs);
		} else {
			cs.fchannel.close();
			client.register(selkey.selector(), SelectionKey.OP_READ);
			buffer.clear();
			selkey.attach(buffer);
		}
	}
}
