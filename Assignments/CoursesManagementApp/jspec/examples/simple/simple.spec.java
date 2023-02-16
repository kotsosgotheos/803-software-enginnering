import jspec.*;
import java.util.ArrayList;

class SimpleTestClass {
    int i;
    double d;
    String s;

    void debug_msg() {
        System.out.println("This is called before all tests");
    }
    
    void teardown_msg() {
        System.out.println("This is called after all tests");
    }

    void initializer() {
        this.i = 1;
        this.d = 2.0;
        this.s = "str";
    }

    void destructor() {
        this.i = -1;
        this.d = 0;
        this.s = null;
    }
}

class cDataLib extends SpecModule {
    SimpleTestClass s = null;

    public void spec_code() {
        describe("jspec functions", () -> {
            before(() -> {
                this.s = new SimpleTestClass();
                s.debug_msg();
            });

            before_each(() -> {
                s.initializer();
            });

            after_each(() -> {
                s.destructor();
            });

            it("succeeds `assert_that`", () -> {
                assert_that(1).is(1);
            });
            it("fails `assert_that`", () -> {
                assert_that(1).isnot(1);
            });

            it("succeeds testing an int", () -> {
                assert_that(1).equals_to(1);
            });
            it("fails testing an int", () -> {
                assert_that(2).equals_to(3);
            });

            xit("skips that test", () -> {
                assert_that(42).is("the meaning of life");
            });

            it("succeeds testing does_not_equal_to", () -> {
                assert_that(42).does_not_equal_to(41);
            });
            it("fails testing does_not_equal_to", () -> {
                assert_that(42).does_not_equal_to(42);
            });

            it("succeeds is_null", () -> {
                assert_that(null).is_null();
            });
            it("fails is_null", () -> {
                assert_that(42).is_null();
            });

            it("succeeds isnot_null", () -> {
                assert_that(42).isnot_null();
            });
            it("fails isnot_null", () -> {
                assert_that(null).isnot_null();
            });

            it("succeeds assert_true", () -> {
                assert_that(true).is_true();
            });
            it("fails assert_true", () -> {
                assert_that(false).is_true();
            });

            it("just fails", () -> {
                fail("This is the fail message");
            });

            after(() -> {
                s.teardown_msg();
            });
        });
    }
}

class second extends SpecModule {
    public void spec_code() {
        describe("DESC 1", () -> {
            it("before on desc 1", () -> {
                assert_that(42).isnot(69);
            });

            describe("DESC 2", () -> {
                describe("DESC 3", () -> {
                    it("does on 3", () -> {
                        assert_that(3).is(3);
                    });
                });
                it("does on 2", () -> {
                    assert_that(2).is(2);
                });
            });
            it("does on 1", () -> {
                assert_that(1).is(1);
            });
        });

        describe("Arraylist Assertions", () -> {
            ArrayList<Integer> a = new ArrayList<Integer>();
            a.add(1);a.add(2);a.add(3);a.add(4);a.add(5);
            ArrayList<Integer> b = new ArrayList<Integer>();
            b.add(7);b.add(7);b.add(7);b.add(7);b.add(7);
            ArrayList<Integer> c = new ArrayList<Integer>();
            c.add(1);c.add(2);c.add(3);c.add(4);

            ArrayList<Double> aa = new ArrayList<Double>();
            aa.add(1.1);aa.add(2.2);aa.add(3.3);aa.add(4.4);aa.add(5.5);
            ArrayList<Double> bb = new ArrayList<Double>();
            bb.add(7.7);bb.add(7.7);bb.add(7.7);bb.add(7.7);bb.add(7.7);
            ArrayList<Double> cc = new ArrayList<Double>();
            cc.add(1.1);cc.add(2.2);cc.add(3.3);cc.add(4.4);

            ArrayList<String> aaa = new ArrayList<String>();
            aaa.add("a");aaa.add("b");aaa.add("c");aaa.add("d");aaa.add("e");
            ArrayList<String> bbb = new ArrayList<String>();
            bbb.add("g");bbb.add("g");bbb.add("g");bbb.add("g");bbb.add("g");
            ArrayList<String> ccc = new ArrayList<String>();
            ccc.add("a");ccc.add("b");ccc.add("c");ccc.add("d");

            it("succeeds testing an int arraylist", () -> {
                ArrayList<Integer> my_arr = new ArrayList<Integer>();
                my_arr.add(1);my_arr.add(2);my_arr.add(3);my_arr.add(4);my_arr.add(5);
                assert_that(my_arr).equals_to(a);
            });
            it("fails tersting an int array`", () -> {
                assert_that(a).equals_to(b);
                assert_that(b).equals_to(c);
            });

            it("succeeds testing a double arraylist", () -> {
                ArrayList<Double> my_arr2 = new ArrayList<Double>();
                my_arr2.add(1.1);my_arr2.add(2.2);my_arr2.add(3.3);my_arr2.add(4.4);my_arr2.add(5.5);
                assert_that(my_arr2).equals_to(aa);
            });
            it("fails testing a double arraylist", () -> {
                assert_that(aa).equals_to(bb);
                assert_that(bb).equals_to(cc);
            });

            it("succeeds testing a double arraylist", () -> {
                ArrayList<String> my_arr3 = new ArrayList<String>();
                my_arr3.add("a");my_arr3.add("b");my_arr3.add("c");my_arr3.add("d");my_arr3.add("e");
                assert_that(my_arr3).equals_to(aaa);
            });
            it("fails testing a double arraylist", () -> {
                assert_that(aaa).equals_to(bbb);
                assert_that(bbb).equals_to(ccc);
            });
        });
    }
}
