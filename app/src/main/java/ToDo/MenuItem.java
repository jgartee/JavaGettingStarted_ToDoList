package ToDo;

public class MenuItem {
    private final String linePrompt;
    private final MenuScreen nextScreen;

    public MenuItem(String linePrompt, MenuScreen nextScreen){

        this.linePrompt = linePrompt;
        this.nextScreen = nextScreen;
    }
    public MenuScreen getNextScreen(){
        return nextScreen;
    }
    public String getLinePrompt(){
        return linePrompt;
    }
}
