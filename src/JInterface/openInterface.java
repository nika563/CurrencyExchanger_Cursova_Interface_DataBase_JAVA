package src.JInterface;

public class openInterface {
    public static singUpInterface form;
    public static cashierInterface cashierInterface;
    public static adminInterface adminFrame;
    public void interfaceReg() {
        form = new singUpInterface();
        form.setVisible(true);
        //cashierInterface = new cashierInterface();
        //cashierInterface.setVisible(true);
        //adminFrame = new adminInterface();
        //adminFrame.setVisible(true);
    }
    public static void closeForm() {
        if (form != null) {
            form.dispose(); // Закрыть форму
        }
    }
}
