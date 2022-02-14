package check.speed;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.python.antlr.PythonParser.file_input_return;

public class ConsoleData {

	public static PrintStream printStream;
	public void searchConsoleData() throws IOException {
	File f1 = new File(System.getProperty("user.dir")+"/searchSpeed.txt");
	f1.createNewFile();
	printStream = new PrintStream(f1);
}
}
