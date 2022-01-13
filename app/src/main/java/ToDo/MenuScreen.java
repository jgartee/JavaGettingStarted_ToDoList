package ToDo;

import java.util.*;

public class MenuScreen implements Screen {
    private final String header;
    private final HashMap<String, MenuItem> menuItems;
    private final boolean clearScreen = false;

    public MenuScreen(String header){
        this.header = header;
        menuItems = new HashMap<>();
    }

    public MenuScreen(String header, HashMap<String, MenuItem> menuItems, boolean clearScreen) {
        this.header = header;
        this.menuItems = menuItems;
    }

    @Override
    public void display() {
        clearConsole();
        String centeredHeader = centerHeader();
        consoleWrite(centeredHeader);

        String newLine = System.getProperty("line.separator");
        consoleWrite(newLine);
        consoleWrite(newLine);

        for(Map.Entry<String, MenuItem> item: menuItems.entrySet())
            consoleWrite(item.getValue().getLinePrompt());

        consoleWrite(newLine);
        consoleWrite("Select an Option:  ");
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
    protected String selectOption() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
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

    @Override
    public void execute() {
        String option = "";

        do {
            display();
            option = selectOption();
            if(!menuItems.containsKey(option)){
                consoleWrite(option + " is an invalid selection...try again.");
                consoleWrite("Press enter...");
                selectOption();
            }

        } while(!menuItems.containsKey(option));

        Screen nextScreen = menuItems.get(option).getNextScreen();

        if(nextScreen == null)
            return;

        nextScreen.execute();
    }
}
