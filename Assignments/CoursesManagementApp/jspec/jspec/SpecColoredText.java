package jspec;

public class SpecColoredText {
    String current_text;

    SpecColoredText(String current_text) {
        this.current_text = current_text;
    }

    String red() { return "\033[1;31m" + current_text + "\033[0m"; }
    String green() { return "\033[38;5;78m" + current_text + "\033[0m"; }
    String yellow() { return "\033[38;5;11m" + current_text + "\033[0m"; }
    String gray() { return "\033[38;5;244m" + current_text + "\033[0m"; }
    String cyan() { return "\033[1;36m" + current_text + "\033[0m"; }
    String magenta() { return "\033[38;5;207m" + current_text + "\033[0m"; }
    String module_color() { return "\033[48;5;89m\033[38;5;11m" + current_text + "\033[0m"; }
}
