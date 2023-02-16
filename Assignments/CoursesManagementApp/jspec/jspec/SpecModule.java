package jspec;

import java.util.concurrent.TimeUnit;

public abstract class SpecModule {
    String spacing = "";
    SpecData module_data = null;
    SpecBlock before_each_block = null;
    SpecBlock after_each_block = null;
    Object actual = null;
    String type = "all";

    boolean it_state = true;
    String failed_it_result = "";

    String current_file = "";
    int current_line = -1;

    public abstract void spec_code();

    public void before(SpecBlock block) {
        block.execute();
    }

    public void before_each(SpecBlock block) {
        this.before_each_block = block;
    }

    public void after(SpecBlock block) {
        block.execute();
    }

    public void after_each(SpecBlock block) {
        this.after_each_block = block;
    }

    public void describe(String name, SpecBlock desc) {
        this.spacing += "    ";
        
        System.out.println(this.spacing + new SpecColoredText("`" + name + "`").magenta());
        desc.execute();
        
        this.spacing = this.spacing.substring(0, this.spacing.length() - 4);
    }

    public void it(String name, SpecBlock it) {
        this.spacing += "    ";
        if(this.before_each_block != null) this.before_each_block.execute();

        this.it_state = true;
        this.failed_it_result = "";

        long then = System.nanoTime();
        it.execute();
        this.module_data.time_taken.add(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - then));
        this.module_data.it_counter++;

        if(this.it_state) {
            this.module_data.positive_it_counter++;

            if(this.type.equals("all") || this.type.equals("passing"))
                System.out.println(this.spacing + new SpecColoredText("✓").green() + " it " + name);
        }
        else if(this.type.equals("all") || this.type.equals("failing")) {
            System.out.println(this.spacing + new SpecColoredText("✗").red() + " it " + name);
            System.out.println(this.failed_it_result);
        }

        if(this.after_each_block != null) this.after_each_block.execute();
        this.spacing = this.spacing.substring(0, this.spacing.length() - 4);
    }

    public void xit(String name, SpecBlock xit) {
        this.spacing += "    ";
        if(this.before_each_block != null) this.before_each_block.execute();

        this.module_data.xit_counter++;

        if(this.type.equals("all") || this.type.equals("skipped"))
            System.out.println(this.spacing + new SpecColoredText("- xit " + name + " (skipped)").gray());

        if(this.after_each_block != null) this.after_each_block.execute();
        this.spacing = this.spacing.substring(0, this.spacing.length() - 4);
    }

    public void is(Object expected) {
        if(!actual.equals(expected)) {
            this.it_state = false;
            spacing += "    ";
            this.failed_it_result += (spacing + current_file + ":" + current_line + ":" + "\n");
            spacing += "    ";
            this.failed_it_result += (spacing + "|> " + new SpecColoredText("`" + actual.toString() + "`").red() + " should be `" + expected + "`" + "\n");
            this.spacing = this.spacing.substring(0, this.spacing.length() - 8);
        }
    }

    public void isnot(Object expected) {
        if(actual.equals(expected)) {
            this.it_state = false;
            spacing += "    ";
            this.failed_it_result += (spacing + current_file + ":" + current_line + ":" + "\n");
            spacing += "    ";
            this.failed_it_result += (spacing + "|> " + new SpecColoredText("`" + actual.toString() + "`").red() + " should not be `" + expected + "`" + "\n");
            this.spacing = this.spacing.substring(0, this.spacing.length() - 8);
        }
    }

    public void equals_to(Object expected) {
        if(!actual.equals(expected)) {
            this.it_state = false;
            spacing += "    ";
            this.failed_it_result += (spacing + current_file + ":" + current_line + ":" + "\n");
            spacing += "    ";
            this.failed_it_result += (spacing + "|> `" + expected + "` expected but got " + new SpecColoredText("`" + actual.toString() + "`").red() + "\n");
            this.spacing = this.spacing.substring(0, this.spacing.length() - 8);
        }
    }

    public void does_not_equal_to(Object expected) {
        if(actual.equals(expected)) {
            this.it_state = false;
            spacing += "    ";
            this.failed_it_result += (spacing + current_file + ":" + current_line + ":" + "\n");
            spacing += "    ";
            this.failed_it_result += (spacing + "|> `" + expected + "` must be different from " + new SpecColoredText("`" + actual.toString() + "`").red() + "\n");
            this.spacing = this.spacing.substring(0, this.spacing.length() - 8);
        }
    }

    public void is_true() {
        if(!((boolean)actual)) {
            this.it_state = false;
            this.spacing += "    ";
            this.failed_it_result += (spacing + Thread.currentThread().getStackTrace()[2].getClassName() + ".spec.java:" + Thread.currentThread().getStackTrace()[2].getLineNumber() + ":" + "\n");
            this.spacing += "    ";
            this.failed_it_result += (spacing + "|> " + new SpecColoredText("`" + actual + "`").red() + " should be true" + "\n");
            this.spacing = this.spacing.substring(0, this.spacing.length() - 8);
        }
    }

    public void is_false() {
        if((boolean)actual) {
            this.it_state = false;
            this.spacing += "    ";
            this.failed_it_result += (spacing + Thread.currentThread().getStackTrace()[2].getClassName() + ".spec.java:" + Thread.currentThread().getStackTrace()[2].getLineNumber() + ":" + "\n");
            this.spacing += "    ";
            this.failed_it_result += (spacing + "|> " + new SpecColoredText("`" + actual + "`").red() + " should be false" + "\n");
            this.spacing = this.spacing.substring(0, this.spacing.length() - 8);
        }
    }

    public void is_null() {
        if(actual != null) {
            this.it_state = false;
            spacing += "    ";
            this.failed_it_result += (spacing + current_file + ":" + current_line + ":" + "\n");
            spacing += "    ";
            this.failed_it_result += (spacing + "|> " + new SpecColoredText("`" + actual.toString() + "`").red() + " should be null" + "\n");
            this.spacing = this.spacing.substring(0, this.spacing.length() - 8);
        }
    }

    public void isnot_null() {
        if(actual == null) {
            this.it_state = false;
            spacing += "    ";
            this.failed_it_result += (spacing + current_file + ":" + current_line + ":" + "\n");
            spacing += "    ";
            this.failed_it_result += (spacing + "|> " + new SpecColoredText("`" + "null" + "`").red() + " should not be null" + "\n");
            this.spacing = this.spacing.substring(0, this.spacing.length() - 8);
        }
    }

    public SpecModule assert_that(Object actual) {
        this.actual = actual;
        this.current_file = Thread.currentThread().getStackTrace()[2].getClassName() + ".spec.java";
        this.current_line = Thread.currentThread().getStackTrace()[2].getLineNumber();

        return this;
    }

    public void fail(String message) {
        this.it_state = false;
        this.spacing += "    ";
        this.failed_it_result += (spacing + Thread.currentThread().getStackTrace()[2].getClassName() + ".spec.java:" + Thread.currentThread().getStackTrace()[2].getLineNumber() + ":" + "\n");
        this.spacing += "    ";
        this.failed_it_result += (spacing + "|> " + new SpecColoredText(message).red() + "\n");
        this.spacing = this.spacing.substring(0, this.spacing.length() - 8);
    }

    public SpecData run(String type) {
        this.module_data = new SpecData();
        this.type = type;

        System.out.println(new SpecColoredText("Module `" + this.getClass().getSimpleName() + "`").module_color());
        spec_code();
        System.out.println("");
        
        return this.module_data;
    }
}
