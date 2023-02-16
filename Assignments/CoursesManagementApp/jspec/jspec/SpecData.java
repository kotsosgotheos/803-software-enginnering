package jspec;

import java.util.ArrayList;

public class SpecData {
    int it_counter;
    int xit_counter;
    int positive_it_counter;
    ArrayList<Long> time_taken = null;

    public SpecData() {
        this.it_counter = 0;
        this.xit_counter = 0;
        this.positive_it_counter = 0;
        this.time_taken = new ArrayList<Long>();
    }

    public void display() {
        System.out.println(new SpecColoredText("● " + (it_counter + xit_counter) + " tests").yellow());
        System.out.println(new SpecColoredText("✓ " + positive_it_counter + " passing").green());
        System.out.println(new SpecColoredText("✗ " + (it_counter - positive_it_counter) + " failing").red());
        System.out.println(new SpecColoredText("- " + xit_counter + " skipped").gray());

        long formatted_time = this.time_taken.stream().mapToLong(v -> v).sum();
        if(formatted_time > 1000)
            System.out.println(new SpecColoredText("★ Finished in " + (double)formatted_time/1000 + " seconds").cyan());
        else if(formatted_time > 60000)
            System.out.println(new SpecColoredText("★ Finished in " + (double)formatted_time/60000 + " minutes").cyan());
        else
            System.out.println(new SpecColoredText("★ Finished in " + formatted_time + " ms").cyan());
    }

    public void add(SpecData data) {
        this.it_counter += data.it_counter;
        this.xit_counter += data.xit_counter;
        this.positive_it_counter += data.positive_it_counter;
        this.time_taken.addAll(data.time_taken);
    }
}
