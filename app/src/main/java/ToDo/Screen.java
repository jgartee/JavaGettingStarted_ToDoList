package ToDo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Screen {
    private final String header;
    private final HashMap<String, MenuItem> menuItems;
    private final boolean clearScreen = false;

    public Screen(String header){
        this.header = header;
        menuItems = new HashMap<>();
    }

    public Screen(String header, HashMap<String, MenuItem> menuItems, boolean clearScreen) {
        this.header = header;
        this.menuItems = menuItems;
    }

    public void displayMenu() {
        clearConsole();
        String centeredHeader = centerHeader();
        consoleWrite(centeredHeader);

        String newLine = System.getProperty("line.separator");
        consoleWrite(newLine);
        consoleWrite(newLine);

        for(Map.Entry<String, MenuItem> item: menuItems.entrySet())
            consoleWrite(item.getValue().getLinePrompt());
    }

    private String centerHeader() {
        String valueToCenter = new String(this.header);
        int padCount = calculatePadAmount(valueToCenter);
        String pad = "";
        for(int i = 0 ; i < padCount ; i++){
            pad = pad.concat(" ");
        }

        String paddedHeader = pad.concat(valueToCenter);
        return paddedHeader;
    }

    private int calculatePadAmount(String valueToCenter) {
        return (80 - valueToCenter.length() / 2) / 2;
    }

    protected void consoleWrite(String outPut) {
        System.out.println(outPut);
    }

    //    static public Stack<Screen> screenStack = new Stack<>();
//    private final String header;
//    private final Map<String, MenuItem> menuItems;
//    private boolean clearBeforeDisplay;
//
//    private String newLine = System.getProperty("line.separator");
//
//    public Screen(String header, Map<String, MenuItem> menuItems, boolean clearBeforeDisplay) {
//
//        this.header = header;
//        this.menuItems = menuItems;
//        this.clearBeforeDisplay = clearBeforeDisplay;
//    }
//
//    public void display() {
//        if (clearBeforeDisplay)
//            clearConsole();
//
//        String menu = this.header
//                .concat(newLine)
//                .concat(newLine);
//        String menuBody = "";
//
//        for (Map.Entry<String, MenuItem> item : menuItems.entrySet()) {
//            String num = item.getKey();
//            String prompt = item.getValue().getLinePrompt();
//            menuBody
//                    .concat(num)
//                    .concat(".\t")
//                    .concat(item.getValue().getLinePrompt());
//
//        }
//
//        menu.concat(menuBody)
//                .concat(newLine)
//                .concat(newLine)
//                .concat(newLine);
//
//        System.out.println(menu);
//    }
//
    public Screen selectOption() {
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        Screen nextScreen = null;

        if (menuItems.containsKey(option)) {
            nextScreen = menuItems.get(option).getNextScreen();
        }

        return nextScreen;
    }

    public int clearConsole() {
        int rc = 0;

        try {
            if (System.getProperty("os.name").contains("Windows")) {
                consoleWrite("\\033[H\\033[2J");
            } else {
                consoleWrite("\033\143");
            }
        } catch (Exception ex) {
            rc = 1;
        }

        return rc;
    }
}
