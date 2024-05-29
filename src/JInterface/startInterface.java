package src.JInterface;

public class startInterface {
    public static formInreface form;
    public static baseInterface bInterface;
    public void interfaceReg() {
        form = new formInreface();
        form.setVisible(true);
        //bInterface = new baseInterface();
        //bInterface.setVisible(true);
    }
    public static void closeForm() {
        if (form != null) {
            form.dispose(); // Закрыть форму
        }
    }
}
