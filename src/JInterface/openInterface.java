package src.JInterface;

public class openInterface {
    public static singUpInterface form;
    public static cashierInterface bInterface;
    public void interfaceReg() {
        //form = new singUpInterface();
        //form.setVisible(true);
        bInterface = new cashierInterface();
        bInterface.setVisible(true);
    }
    public static void closeForm() {
        if (form != null) {
            form.dispose(); // Закрыть форму
        }
    }
}
