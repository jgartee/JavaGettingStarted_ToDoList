package ToDo;

public class MenuItem {
    private final String linePrompt;
    private final Screen nextScreen;

    public MenuItem(String linePrompt, Screen nextScreen){

        this.linePrompt = linePrompt;
        this.nextScreen = nextScreen;
    }
    public Screen getNextScreen(){
        return nextScreen;
    }
    public String getLinePrompt(){
        return linePrompt;
    }
}
