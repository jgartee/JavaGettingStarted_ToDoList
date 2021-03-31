package ToDo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuScreenTest {
    ArrayList<String> screenOneOutputLines = new ArrayList<>();
    ArrayList<String> screenTwoOutputLines = new ArrayList<>();

    @Test
    public void shouldClearScreenBeforeWritingHeaderToTheConsole() {
        MenuScreen myScreen = new MenuScreen("MyHeader", new HashMap<String, MenuItem>(), false) {
            @Override
            protected void consoleWrite(String outPut) {
                screenOneOutputLines.add(outPut);
            }
        };
        screenOneOutputLines.clear();

        myScreen.display();

        assertEquals("\033\143", screenOneOutputLines.get(0));
        assertTrue(screenOneOutputLines.get(1).contains("MyHeader"));
    }

    @Test
    public void shouldDisplayHeaderInCenterOfEightyCharacterLine() {
        MenuScreen myScreen = new MenuScreen("MyHeader", new HashMap<String, MenuItem>(), false) {
            @Override
            protected void consoleWrite(String outPut) {
                screenOneOutputLines.add(outPut);
            }
        };
        screenOneOutputLines.clear();

        myScreen.display();

        assertEquals("                                      MyHeader", screenOneOutputLines.get(1));
    }

    @Test
    public void shouldDisplayMenuItems() {
        HashMap<String, MenuItem> menuItems = new HashMap<String, MenuItem>() {{
            put("1", new MenuItem("MenuItemOne", null));
            put("2", new MenuItem("MenuItemTwo", null));
            put("3", new MenuItem("MenuItemThree", null));
        }};
        MenuScreen myScreen = new MenuScreen("MyHeader", menuItems, false) {
            @Override
            protected void consoleWrite(String outPut) {
                screenOneOutputLines.add(outPut);
            }
        };
        screenOneOutputLines.clear();

        myScreen.display();

        assertTrue(screenOneOutputLines.contains("MenuItemOne"));
        assertTrue(screenOneOutputLines.contains("MenuItemTwo"));
        assertTrue(screenOneOutputLines.contains("MenuItemThree"));

    }

    @Test
    public void headerShouldBeFollowedByTwoNewLines() {
        String newLine = System.getProperty("line.separator");
        HashMap<String, MenuItem> menuItems = new HashMap<String, MenuItem>() {{
            put("1", new MenuItem("MenuItemOne", null));
            put("2", new MenuItem("MenuItemTwo", null));
            put("3", new MenuItem("MenuItemThree", null));
        }};
        MenuScreen myScreen = new MenuScreen("MyHeader", menuItems, false) {
            @Override
            protected void consoleWrite(String outPut) {
                screenOneOutputLines.add(outPut);
            }
        };
        screenOneOutputLines.clear();

        myScreen.display();

        assertEquals(newLine, screenOneOutputLines.get(2));
        assertEquals(newLine, screenOneOutputLines.get(3));
    }

    MenuScreen selectedScreen = null;

    @Test

    public void shouldAllowSelectionOfAValidOption() {
        String newLine = System.getProperty("line.separator");
        HashMap<String, MenuItem> screenOneMenuItems = new HashMap<String, MenuItem>() {{
            put("1", new MenuItem("MenuItemOne", null));
            put("2", new MenuItem("MenuItemTwo", null));
            put("3", new MenuItem("MenuItemThree", null));
        }};
        MenuScreen screenOne = new MenuScreen("Screen One Header", screenOneMenuItems, true) {
            @Override
            protected void consoleWrite(String outPut) {
                screenOneOutputLines.add(outPut);
            }

            @Override
            protected String selectOption() {
                return "1";
            }

        };
        screenOneOutputLines.clear();

        screenOne.display();

        assertEquals("1", screenOne.selectOption());
    }

    boolean firstTime = true;

    @Test
    public void shouldWriteErrorMessageIfInvalidOptionSelected() {
        String newLine = System.getProperty("line.separator");
        HashMap<String, MenuItem> screenOneMenuItems = new HashMap<String, MenuItem>() {{
            put("1", new MenuItem("MenuItemOne", null));
            put("2", new MenuItem("MenuItemTwo", null));
            put("3", new MenuItem("MenuItemThree", null));
        }};
        MenuScreen screenOne = new MenuScreen("Screen One Header", screenOneMenuItems, true) {
            @Override
            protected void consoleWrite(String outPut) {
                screenOneOutputLines.add(outPut);
            }

            @Override
            protected String selectOption() {
                if (firstTime) {
                    firstTime = false;
                    return "4";
                }

                return "1";
            }
        };

        screenOneOutputLines.clear();

        screenOne.execute();

        assertTrue(screenOneOutputLines.contains("4 is an invalid selection...try again."));
        assertTrue(screenOneOutputLines.contains("Press enter..."));
    }


    @Test
    public void shouldInvokeSubScreenIfPresent() {
        String newLine = System.getProperty("line.separator");

        MenuScreen screenTwoFromMenuItemOne = setupScreenTwoFromScreenOneMenuItemOne();
        MenuScreen screenTwoFromMenuItemTwo = setupScreenTwoFromScreenOneMenuItemTwo();
        HashMap<String, MenuItem> screenTwoItemsFromMenuItemThree = new HashMap<String, MenuItem>() {{
            put("1", new MenuItem("Return to Previous Screen", null));
        }};
        MenuScreen screenTwoFromMenuItemThree = new MenuScreen("Screen Two From Item Three",
                screenTwoItemsFromMenuItemThree, false) {
            @Override
            protected void consoleWrite(String outPut) {
                screenTwoOutputLines.add(outPut);
            }

            @Override
            protected String selectOption() {
                return "1";
            }
        };
        HashMap<String, MenuItem> screenOneMenuItems = new HashMap<String, MenuItem>() {{
            put("1", new MenuItem("Screen One Menu Item One", screenTwoFromMenuItemOne));
            put("2", new MenuItem("Screen One Menu Item Two", screenTwoFromMenuItemTwo));
            put("3", new MenuItem("Screen One Menu Item Three", screenTwoFromMenuItemThree));
        }};
        MenuScreen screenOne = new MenuScreen("Screen One Header", screenOneMenuItems, true) {
            @Override
            protected void consoleWrite(String outPut) {
                screenOneOutputLines.add(outPut);
            }

            @Override
            protected String selectOption() {
                return "3";
            }
        };

        screenOneOutputLines.clear();
        screenTwoOutputLines.clear();

        screenOne.execute();

        assertTrue(screenTwoOutputLines.contains("Return to Previous Screen"));
    }

    private MenuScreen setupScreenTwoFromScreenOneMenuItemOne() {
        HashMap<String, MenuItem> sceenTwoItemOneMenuItems = new HashMap<String, MenuItem>() {{
            put("1", new MenuItem("Screen Two Menu Item One", null));
            put("2", new MenuItem("Screen Two Menu Item Two", null));
            put("3", new MenuItem("Screen Two Menu Item Three", null));
        }};

        MenuScreen screenTwoFromMenuItemOne = new MenuScreen("Screen Two From Item One", null, false);
        return screenTwoFromMenuItemOne;
    }

    private MenuScreen setupScreenTwoFromScreenOneMenuItemTwo() {
        HashMap<String, MenuItem> sceenTwoItemTwoMenuItems = new HashMap<String, MenuItem>() {{
            put("1", new MenuItem("Screen Two Menu Item One", null));
            put("1", new MenuItem("Screen Two Menu Item Two", null));
            put("1", new MenuItem("Screen Two Menu Item Three", null));
        }};
        MenuScreen screenTwoFromMenuItemTwo = new MenuScreen("Screen Two From Item Two", null, false);
        return screenTwoFromMenuItemTwo;
    }

}