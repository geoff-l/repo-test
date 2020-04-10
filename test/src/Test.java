import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Test {
	static final String LINE_SEPARATOR_PATTERN = "\r\n|[\n\r\u2028\u2029\u0085]";
	public static void main(String[] args) throws InterruptedException {
		test7();
	}
	
	private static void test8() {
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
	}
	
	private static void test7() {
		Collection<Integer> c = new ArrayList<Integer>();
		c.add(Integer.valueOf(20));
		c.add(Integer.valueOf(31));
		c.add(Integer.valueOf(43));
		c.add(Integer.valueOf(35));
		c.add(Integer.valueOf(81));
		c.stream().filter(t-> t > 40 ? true : false).forEach(t-> System.out.println(t));
	}
	
	private static void test6() {
		Collection<Integer> list = new ArrayList<Integer>(Arrays.asList(20, 31, 43, 35, 81));
		list.addAll(Arrays.asList(new Integer[] {101, 102, 203, 403}));
		for(Integer i: list) {
			System.out.println(i + " ");
		}
	}
	
	private static void test5() {
		Collection<Integer> c = new ArrayList<Integer>();
		c.add(Integer.valueOf(20));
		c.add(Integer.valueOf(31));
		c.add(Integer.valueOf(43));
		c.add(Integer.valueOf(35));
		c.add(Integer.valueOf(81));
		for(Integer i: c) {
			System.out.println(i + " ");
		}
	}
	
	public <T extends Comparable<? super T>> void sort(List<T> list) {
		
	}
	
	private static void test4() {
		
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("( )*,( )*|( )*"+ LINE_SEPARATOR_PATTERN);
		System.out.print("NAME: ");
		String name;
		if(scanner.hasNext()) {
			name = scanner.next();
			System.out.printf("NAME [%s]\n", name);
		}
		
		System.out.print("Score: ");
		float score;
		if(scanner.hasNextFloat()) {
			score = scanner.nextFloat();
			System.out.printf("Score [%f]\n", score);
		}
		
		scanner.close();
		System.out.println("END");
	}

	private static void test() {
		String name;
		String sex = "F"; 
		int age = 20;
		//System.out.printf("%s, %d", sex, age);
		LineNumberReader reader = new LineNumberReader(new InputStreamReader(System.in));
		try {
			do {
				System.out.print("NAME:");
				name = reader.readLine();
				System.out.printf("LINE NO. %d - %s\n", new Object[] {Integer.valueOf(reader.getLineNumber()), name});
			} while (!name.equals("exit"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("END");
	    return;
	}
	
	private static void test2() {
		try {
			// LineNumberReader reader = new LineNumberReader(new FileReader(new File("D:/test.txt")));
			Path path = FileSystems.getDefault().getPath("D:/test.txt");
			BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
			LineNumberReader lr = new LineNumberReader(reader);
			String line;
			while((line = reader.readLine())!= null) {
				System.out.printf("LINE NO. %d - %s\n", new Object[] {Integer.valueOf(lr.getLineNumber()), line});
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void test3() throws InterruptedException {
		final PipedReader preader = new PipedReader();
		PipedWriter pwriter = null;
		try {
			pwriter = new PipedWriter(preader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Thread t1 = new Thread(new ReaderRunner(preader));
		Thread t2 = new Thread(new WriterRunner(pwriter));
		t1.start();
		t2.start();
		
		TimeUnit.SECONDS.sleep(5);
		System.out.println("Main thread END.");
		
	}
}

class ReaderRunner implements Runnable {
	PipedReader preader;
	ReaderRunner(PipedReader pr) {
		preader = pr;
	}
	public void run() {
		try {
			char[] cbuf = new char[100];
			if (preader.read(cbuf) > 0) {
				System.out.print("Received: ");
				System.out.print(cbuf);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ReaderRunner END.");
	}
	
}

class WriterRunner implements Runnable{
	PipedWriter pwriter;
	WriterRunner(PipedWriter pw) {
		pwriter = pw;
	}
	
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter please: ");
			String line = br.readLine();
			if (null != pwriter) {
				pwriter.write(line);
				pwriter.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("WriterRunner END.");
		
	}
}
class Student{
	private String sid;
	private String name;
	private Integer age;
	public Student(String sid, String name, Integer age) {
		this.sid = sid;
		this.name = name;
		this.age = age;
	}
	public String sid() { return this.sid; }
	public String name() { return this.name; }
	public Integer age() { return this.age; }	
}

class IDToStudent implements Function<String, Student> {
	Map<String, Student> students = new HashMap<String, Student>();
	IDToStudent() {
		students.put("s001", new Student("001", "Liu Jianfeng", 30));
		students.put("s002", new Student("002", "Zhu Fen", 35));
	}
	public Student apply(String t) {
		return students.get(t);
	}	
}

class Students{
	
}

@FunctionalInterface
interface InterfaceWithException {
	void apply(int i);
}
