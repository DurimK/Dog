import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import org.junit.jupiter.api.*;

public class HR4_2_4StaticScannerDemoTest {

	public static final String VERSION = "2024-01-28 11:14";

	private ByteArrayOutputStream systemOut = new ByteArrayOutputStream();

	private InputStream originalSystemIn = System.in;
	private PrintStream originalSystemOut = System.out;

	@BeforeEach
	public void setupSystemOutputStreams() {
		System.setOut(new PrintStream(systemOut));
	}

	@AfterEach
	public void restoreSystemStreams() {
		System.setIn(originalSystemIn);
		System.setOut(originalSystemOut);
	}

	private void setSystemIn(String... userInputLines) {
		System.setIn(createUserInputStream(userInputLines));
	}

	private InputStream createUserInputStream(String... userInputLines) {
		var input = Arrays.stream(userInputLines).collect(Collectors.joining("\n")) + "\n";
		return new ByteArrayInputStream(input.getBytes());
	}

	private String systemOut() {
		return systemOut.toString().replaceAll("\\R", "\n").trim();
	}

	private void executeTest(String expected, String... userInputLines) {
		setSystemIn(userInputLines);
		try {
			HR4_2_4StaticScannerDemo.main(null);
			assertEquals(expected, systemOut());
		} catch (Exception e) {
			fail(systemOut(), e);
		}
	}

	@Test
	void twoTestsThatDoesExactlyTheSameThingYetOnlyOnePassesMaybeItsThis() {
		executeTest("""
				Skriv ett heltal: 11*2=22
				Skriv ett heltal: 222*3=666""", "11", "222");
	}

	@Test
	void twoTestsThatDoesExactlyTheSameThingYetOnlyOnePassesOrMaybeItsThis() {
		executeTest("""
				Skriv ett heltal: 11*2=22
				Skriv ett heltal: 222*3=666""", "11", "222");
	}
}
