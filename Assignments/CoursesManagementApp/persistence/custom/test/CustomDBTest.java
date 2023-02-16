package persistence.custom.test;

import jspec.*;
import persistence.custom.src.CustomDB;

import app.src.entities.Identifiable;

import java.util.ArrayList;

class IdString extends Identifiable {
    public String str;

    public IdString(String id, String str) {
        this.id = id;
        this.str = str;
    }

    @Override
    public String toString() {
        return this.str;
    }

    @Override
    public boolean is(Identifiable other) {
        return this.str.equals(((IdString)other).str);
    }
}

class IdInteger extends Identifiable {
    public int i;

    public IdInteger(String id, int i) {
        this.id = id;
        this.i = i;
    }

    @Override
    public String toString() {
        return String.valueOf(this.i);
    }

    @Override
    public boolean is(Identifiable other) {
        /* TODO Most likely illegal and/or brakes Liskov's */
        return this.i == ((IdInteger)other).i;
    }
}

public class CustomDBTest extends SpecModule {
    public void spec_code() {
        describe("Basic CustomDB function", () -> {
            it("creates a DB", () -> {
                CustomDB db = new CustomDB("testdb_1");
                assert_that(db).isnot(null);
            });

            it("adds a String to the DB", () -> {
                CustomDB db = new CustomDB("testdb_2");
                String id = "entry1";
                String item = "Lesson";
                Identifiable idstr = new IdString(id, item);

                db.save(idstr);
                assert_that(db.get_by_id(id).toString()).equals_to(item);
            });
            
            it("adds 3 Integers to the DB", () -> {
                CustomDB db = new CustomDB("testdb_3");
                db.save(new IdInteger("3rd", 39));
                db.save(new IdInteger("1st", 4));
                db.save(new IdInteger("2nd", 1));

                int first = Integer.parseInt(db.get_by_id("1st").toString());
                int third = Integer.parseInt(db.get_by_id("3rd").toString());
                int second = Integer.parseInt(db.get_by_id("2nd").toString());
                assert_that(first+third-second).equals_to(42);
            });
            
            it("gets all items", () -> {
                CustomDB db = new CustomDB("testdb_4");
                String id1 = "entry1";
                String id2 = "entry2";
                String id3 = "entry3";
                String item1 = "Lesson";
                String item2 = "Less3on";
                String item3 = "Lesson4";
                Identifiable s1 = new IdString(id1, item1);
                Identifiable s2 = new IdString(id2, item2);
                Identifiable s3 = new IdString(id3, item3);
                db.save(s1);
                db.save(s2);
                db.save(s3);

                ArrayList<Identifiable> items = db.get_all_items();
                assert_that(items.get(0).equals(s1)).is(true);
                assert_that(items.get(2).equals(s3)).is(true);
                assert_that(items.get(1).equals(s2)).is(true);
            });

            it("updates the new item", () -> {
                CustomDB db = new CustomDB("testdb_5");
                String id1 = "Lecture1";
                String item1 = "Digital Design I";

                db.save(new IdString(id1, item1));
                db.update(id1, new IdString(id1, "Operating Systems"));

                assert_that(((IdString)db.get_by_id(id1)).str).equals_to("Operating Systems");
            });

            it("deletes an item by id", () -> {
                CustomDB db = new CustomDB("testdb_6");
                String id1 = "Lecture1";
                String item1 = "Digital Design I";
                String id2 = "Lecture2";
                String item2 = "Digital Design II";

                db.save(new IdString(id1, item1));
                db.save(new IdString(id2, item2));
                db.delete(id1);

                assert_that(db.get_all_items().size()).is(1);
                assert_that(((IdString)db.get_by_id(id2)).str).equals_to("Digital Design II");
            });
        });
    }
}
