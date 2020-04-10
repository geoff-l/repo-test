package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SingleThreadWebServer {
	private static final int NTHREAD = 100;
	private static final Executor exec = Executors.newFixedThreadPool(NTHREAD);

	public static void main(String[] args) throws IOException {
		ServerSocket socket = new ServerSocket(80);
		while(true) {
			Socket connection = socket.accept();
			Runnable task = new Runnable() {
				@Override
				public void run() {
					try {
						handleRequest(connection);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			};
			exec.execute(task);
		}
		// socket.close();
	}
	
	public static void handleRequest(Socket connection) throws IOException {
		System.out.println("BEGIN");
		InputStream in = connection.getInputStream();
		BufferedReader r = new BufferedReader(new InputStreamReader(in));
		while (r.ready()) {
			String line = r.readLine();
			System.out.println(line);
		}
		
		OutputStream out = connection.getOutputStream();
		BufferedWriter w = new BufferedWriter(new OutputStreamWriter(out));
		w.write("hahaha");
		w.flush();
		//r.close();
		//w.close();
		System.out.println("END");
	}
}
