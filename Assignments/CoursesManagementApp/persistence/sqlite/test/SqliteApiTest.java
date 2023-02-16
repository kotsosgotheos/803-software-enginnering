package persistence.sqlite.test;

import jspec.*;

import app.src.entities.Identifiable;
import persistence.sqlite.src.SqliteApi;

import java.io.File;
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
    public String i;

    public IdInteger(String id, String i) {
        this.id = id;
        this.i = i;
    }

    @Override
    public String toString() {
        return this.i;
    }

    @Override
    public boolean is(Identifiable other) {
        return this.i == ((IdInteger)other).i;
    }
}

public class SqliteApiTest extends SpecModule {
    public void spec_code() {
        describe("Sqlite API", () -> {
            it("creates an Sqlite API object", () -> {
                SqliteApi api = new SqliteApi("users");
                assert_that(api).isnot(null);
            });

            it("performs a select * from table query", () -> {
                SqliteApi api = new SqliteApi("users");
                ArrayList<Identifiable> all_items = api.get_all_items();

                assert_that(all_items.size()).is(0);
            });

            it("adds a String to the DB", () -> {
                SqliteApi api = new SqliteApi("stringtable");
                String id = "entry1";
                String item = "Lesson";
                Identifiable idstr = new IdString(id, item);

                api.save(idstr);
                assert_that(api.get_by_id(id).toString()).equals_to(item);
            });
            
            it("adds 3 Integers to the DB", () -> {
                SqliteApi api = new SqliteApi("integertable");
                api.save(new IdInteger("3rd", "39"));
                api.save(new IdInteger("1st", "4"));
                api.save(new IdInteger("2nd", "1"));

                int first = Integer.parseInt(api.get_by_id("1st").toString());
                int third = Integer.parseInt(api.get_by_id("3rd").toString());
                int second = Integer.parseInt(api.get_by_id("2nd").toString());
                assert_that(first+third-second).equals_to(42);
            });
            
            it("gets all items", () -> {
                SqliteApi api = new SqliteApi("lessontable");
                String id1 = "entry1";
                String id2 = "entry2";
                String id3 = "entry3";
                String item1 = "Lesson";
                String item2 = "Less3on";
                String item3 = "Lesson4";
                Identifiable s1 = new IdString(id1, item1);
                Identifiable s2 = new IdString(id2, item2);
                Identifiable s3 = new IdString(id3, item3);
                api.save(s1);
                api.save(s2);
                api.save(s3);

                ArrayList<Identifiable> items = api.get_all_items();
                assert_that(items.get(0).equals(s1)).is(true);
                assert_that(items.get(2).equals(s3)).is(true);
                assert_that(items.get(1).equals(s2)).is(true);
            });

            it("updates the new item", () -> {
                SqliteApi api = new SqliteApi("updatetable");
                String id1 = "Lecture1";
                String item1 = "Digital Design I";

                api.save(new IdString(id1, item1));
                api.update(id1, new IdString(id1, "Operating Systems"));

                assert_that(((IdString)api.get_by_id(id1)).str).equals_to("Operating Systems");
            });

            it("deletes an item by id", () -> {
                SqliteApi api = new SqliteApi("deletetable");
                String id1 = "Lecture1";
                String item1 = "Digital Design I";
                String id2 = "Lecture2";
                String item2 = "Digital Design II";

                api.save(new IdString(id1, item1));
                api.save(new IdString(id2, item2));
                api.delete(id1);

                assert_that(api.get_all_items().size()).is(1);
                assert_that(((IdString)api.get_all_items().get(0)).str).equals_to("Digital Design II");
            });

            after(() -> {
                new File("persistence/sqlite/src/Database.db").delete();
            });
        });
    }
}
