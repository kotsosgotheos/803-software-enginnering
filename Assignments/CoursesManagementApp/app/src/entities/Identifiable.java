package app.src.entities;

import java.io.Serializable;

public abstract class Identifiable implements Serializable {
    public String id;

    public abstract boolean is(Identifiable o);
    public abstract String toString();

    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null)
            return false;
        
        return this.is((Identifiable)o);
    }
}
