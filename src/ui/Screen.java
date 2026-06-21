package ui;

public abstract class Screen {

    protected String title;

    public Screen(String title) {
        this.title = title;
    }

    public final void show() {

        System.out.println(title);
        System.out.println();

        display();

        System.out.println();
    }

    protected abstract void display();

    protected String fmt(double value) {
        return String.format("%.4f", value);
    }
}