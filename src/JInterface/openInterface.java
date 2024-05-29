package src.JInterface;

public class openInterface {
    public static singUpInterface form;
    public static baseInterface bInterface;
    public void interfaceReg() {
        //form = new singUpInterface();
        //form.setVisible(true);
        bInterface = new baseInterface();
        bInterface.setVisible(true);
    }
    public static void closeForm() {
        if (form != null) {
            form.dispose(); // Закрыть форму
        }
    }
}
