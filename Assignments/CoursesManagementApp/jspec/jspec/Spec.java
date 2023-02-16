package jspec;

public class Spec {
    SpecModule modules[] = null;
    SpecData data = null;
    
    public Spec(SpecModule modules[]) {
        this.modules = modules;
        this.data = new SpecData();
    }

    public void run_spec_suite(String type) {
        System.out.println("\033[38;5;95m/######## ########/");
        System.out.println("\033[38;5;95m/##### \033[38;5;89mj\033[38;5;90ms\033[38;5;91mp\033[38;5;92me\033[38;5;93mc\033[0m \033[38;5;95m#####/");
        System.out.println("/######## ########/\033[0m");
        System.out.println("");
        
        for(SpecModule m : modules)
            this.data.add(m.run(type));
        
        data.display();
    }
}
