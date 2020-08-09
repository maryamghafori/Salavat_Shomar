package ir.ghafouri.salavatshomar.Objects;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.ColorRes;

import ir.ghafouri.salavatshomar.Interfaces.DialogListener;
import ir.ghafouri.salavatshomar.R;

public class AlertDialogObject {

    private int color;

    private String title, message, positive, negative, neutral;

    private boolean positiveDisplay, negativeDisplay, neutralDisplay, dismissAble = true, input = false;

    @SuppressLint("ResourceType")
    private
    int positiveIcon = R.drawable.ic_check,
        negativeIcon = R.drawable.ic_check,
        neutralIcon = R.drawable.ic_check,
        titleIcon = R.drawable.ic_check;

    @ColorRes
    private
    int positiveColor = R.color.color_1,
        negativeColor = R.color.color_6,
        neutralColor  = R.color.color_3,
        titleColor    = R.color.color_4;

    private DialogListener listener;

    public AlertDialogObject setColor(String color) {
        try {
            this.color = Color.parseColor(color);
        }
        catch (Exception e) {this.color = Color.BLACK;}
        return this;
    }

    public int getColor() {
        return this.color;
    }

    public AlertDialogObject setTitle(String title) { this.title = title; return this; }
    public String getTitle() {
        return title;
    }

    public AlertDialogObject setMessage(String message) { this.message = message; return this; }
    public String getMessage() {
        return message;
    }

    public AlertDialogObject setPositive(String positive) { this.positive = positive; return this; }
    public String getPositive() {
        return positive;
    }

    public AlertDialogObject setNegative(String negative) { this.negative = negative; return this; }
    public String getNegative() {
        return negative;
    }

    public AlertDialogObject setNeutral(String neutral) { this.neutral = neutral; return this; }
    public String getNeutral() {
        return neutral;
    }

    public AlertDialogObject setPositiveDisplay(boolean positiveDisplay) { this.positiveDisplay = positiveDisplay;return this; }
    public boolean getPositiveDisplay() {
        return positiveDisplay;
    }

    public AlertDialogObject setNegativeDisplay(boolean negativeDisplay) { this.negativeDisplay = negativeDisplay; return this;}
    public boolean getNegativeDisplay() {
        return negativeDisplay;
    }

    public AlertDialogObject setNeutralDisplay(boolean neutralDisplay) { this.neutralDisplay = neutralDisplay; return this; }
    public boolean getNeutralDisplay() {
        return neutralDisplay;
    }

    public AlertDialogObject setDismissAble(boolean dismissAble) { this.dismissAble = dismissAble; return this; }
    public boolean getDismissAble() {
        return dismissAble;
    }

    public AlertDialogObject setInput(boolean input) { this.input = input; return this; }
    public boolean getInput() {
        return input;
    }

    public AlertDialogObject setPositiveIcon(int positiveIcon) { this.positiveIcon = positiveIcon; return this; }
    public int getPositiveIcon() {
        return positiveIcon;
    }

    public AlertDialogObject setNegativeIcon(int negativeIcon) { this.negativeIcon = negativeIcon; return this; }
    public int getNegativeIcon() {
        return negativeIcon;
    }

    public AlertDialogObject setNeutralIcon(int neutralIcon) { this.neutralIcon = neutralIcon; return this; }
    public int getNeutralIcon() {
        return neutralIcon;
    }

    public AlertDialogObject setTitleIcon(int titleIcon) {this.titleIcon = titleIcon; return this; }
    public int getTitleIcon() {
        return titleIcon;
    }

    public AlertDialogObject setPositiveColor(int positiveColor) { this.positiveColor = positiveColor; return this;}
    public int getPositiveColor() {
        return positiveColor;
    }

    public AlertDialogObject setNegativeColor(int negativeColor) { this.negativeColor = negativeColor; return this;}
    public int getNegativeColor() {
        return negativeColor;
    }

    public AlertDialogObject setNeutralColor(int neutralColor) { this.neutralColor = neutralColor;return this; }
    public int getNeutralColor() {
        return neutralColor;
    }

    public AlertDialogObject setTitleColor(int titleColor) { this.titleColor = titleColor; return this; }
    public int getTitleColor() {
        return titleColor;
    }

    public AlertDialogObject setListener(DialogListener listener){ this.listener = listener; return this; }
    public DialogListener getListener() {
        return this.listener;
    }
}
