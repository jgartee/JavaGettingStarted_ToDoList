package ToDo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ScreenTest {
    ArrayList<String> linesOut = new ArrayList<>();

    @Test
    public void shouldClearScreenBeforeWritingHeaderToTheConsole() {
        Screen myScreen = new Screen("MyHeader", new HashMap<String, MenuItem>(), false) {
            @Override
            protected void consoleWrite(String outPut) {
                linesOut.add(outPut);
            }
        };
        linesOut.clear();

        myScreen.displayMenu();

        assertEquals("\033\143", linesOut.get(0));
        assertTrue(linesOut.get(1).contains("MyHeader"));
    }

    @Test
    public void shouldDisplayHeaderInCenterOfEightyCharacterLine() {
        Screen myScreen = new Screen("MyHeader", new HashMap<String, MenuItem>(), false) {
            @Override
            protected void consoleWrite(String outPut) {
                linesOut.add(outPut);
            }
        };
        linesOut.clear();

        myScreen.displayMenu();

        assertEquals("                                      MyHeader", linesOut.get(1));
    }

    @Test
    public void shouldDisplayMenuItems() {
        HashMap<String, MenuItem> menuItems = new HashMap<String, MenuItem>() {{
            put("1", new MenuItem("MenuItemOne", null));
            put("2", new MenuItem("MenuItemTwo", null));
            put("3", new MenuItem("MenuItemThree", null));
        }};
        Screen myScreen = new Screen("MyHeader", menuItems, false) {
            @Override
            protected void consoleWrite(String outPut) {
                linesOut.add(outPut);
            }
        };
        linesOut.clear();

        myScreen.displayMenu();

        assertTrue(linesOut.contains("MenuItemOne"));
        assertTrue(linesOut.contains("MenuItemTwo"));
        assertTrue(linesOut.contains("MenuItemThree"));

    }

    @Test
    public void headerShouldBeFollowedByTwoNewLines() {
        String newLine = System.getProperty("line.separator");
        HashMap<String, MenuItem> menuItems = new HashMap<String, MenuItem>() {{
            put("1", new MenuItem("MenuItemOne", null));
            put("2", new MenuItem("MenuItemTwo", null));
            put("3", new MenuItem("MenuItemThree", null));
        }};
        Screen myScreen = new Screen("MyHeader", menuItems, false) {
            @Override
            protected void consoleWrite(String outPut) {
                linesOut.add(outPut);
            }
        };
        linesOut.clear();

        myScreen.displayMenu();

        assertEquals(newLine, linesOut.get(2));
        assertEquals(newLine, linesOut.get(3));
    }

    Screen selectedScreen = null;

    @Test

    public void shouldAllowSelectionOfAValidOption() {
        String newLine = System.getProperty("line.separator");
        HashMap<String, MenuItem> screenOneMenuItems = new HashMap<String, MenuItem>() {{
            put("1", new MenuItem("MenuItemOne", null));
            put("2", new MenuItem("MenuItemTwo", null));
            put("3", new MenuItem("MenuItemThree", null));
        }};
        Screen screenOne = new Screen("Screen One Header", screenOneMenuItems, true) {
            @Override
            protected void consoleWrite(String outPut) {
                linesOut.add(outPut);
            }

            @Override
            public String selectOption() {
                return "1";
            }

        };
        linesOut.clear();

        screenOne.displayMenu();

        assertEquals("1", screenOne.selectOption());
    }

    boolean firstTime = true;
    @Test
    public void shouldWriteErrorMessageIfInvalidOptionSelected(){
        String newLine = System.getProperty("line.separator");
        HashMap<String, MenuItem> screenOneMenuItems = new HashMap<String, MenuItem>() {{
            put("1", new MenuItem("MenuItemOne", null));
            put("2", new MenuItem("MenuItemTwo", null));
            put("3", new MenuItem("MenuItemThree", null));
        }};
        Screen screenOne = new Screen("Screen One Header", screenOneMenuItems, true) {
            @Override
            protected void consoleWrite(String outPut) {
                linesOut.add(outPut);
            }

            @Override
            public String selectOption() {
                if(firstTime) {
                    firstTime = false;
                    return "4";
                }

                return "1";
            }
        };
        linesOut.clear();

        screenOne.displayMenu();

        String actual = screenOne.selectOption();
        assertEquals("1", actual);
        assertTrue(linesOut.contains("Invalid Option Selected...try again."));
    }
}