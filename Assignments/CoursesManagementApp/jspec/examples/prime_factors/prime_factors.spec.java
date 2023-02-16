import jspec.*;
import java.util.ArrayList;

class PrimeFactors extends SpecModule {
    private FactorsClass o = null;

    public void spec_code() {
        describe("Find prime factors of integer", () -> {
            before_each(() -> {
                this.o = new FactorsClass();
            });
            
            xit("finds the factors of 1", () -> {
                ArrayList<Integer> f1 = this.o.factors_of(1);
                assert_that(f1.get(0)).equals_to(1);
            });

            xit("finds the factors of 2", () -> {
                ArrayList<Integer> f2 = this.o.factors_of(2);
                assert_that(f2.get(0)).equals_to(2);
            });

            xit("finds the factors of 3", () -> {
                ArrayList<Integer> f3 = this.o.factors_of(3);
                assert_that(f3.get(0)).equals_to(3);
            });

            it("finds the factors of 4", () -> {
                ArrayList<Integer> f4 = this.o.factors_of(4);
                
                assert_that(f4.get(0)).is(2);
                assert_that(f4.get(1)).is(2);
            });

            it("find test factor of 2*2*3*3*5*7*11*11*13", () -> {
                ArrayList<Integer> f = this.o.factors_of(2*2*3*3*5*7*11*11*13);
                assert_that(f.get(0)).equals_to(2);
                assert_that(f.get(1)).equals_to(2);
                assert_that(f.get(2)).equals_to(3);
                assert_that(f.get(3)).equals_to(3);
                assert_that(f.get(4)).equals_to(5);
                assert_that(f.get(5)).equals_to(7);
                assert_that(f.get(6)).equals_to(11);
                assert_that(f.get(7)).equals_to(11);
                assert_that(f.get(8)).equals_to(13);
            });
        });
    }
}
