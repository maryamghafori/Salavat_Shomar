package ir.ghafouri.salavatshomar.Interfaces;

public interface DialogListener {

    void onPositiveClick(String input);

    void onNegativeClick(String input);

    void onNeutralClick(String input);

    void onDismissAble(String input);
}
