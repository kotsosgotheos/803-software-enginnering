import jspec.*;

class StackSpec extends SpecModule {
    Stack s = null;

    public void spec_code() {
        before_each(() -> {
            s = new Stack(20);
        });
        
        it("asserts that a new stack is empty", () -> {
            assert_that(s.isEmpty()).is_true();
        });

        it("pushes an element into the stack", () -> {
            s.push(0);
            assert_that(s.isEmpty()).is_false();
        });

        it("pops an empty stack and retuns null", () -> {
            assert_that(s.pop()).is_null();
            assert_that(s.isEmpty()).is_true();
        });

        it("is empty after 1 push and 1 pop", () -> {
            s.push(0);
            s.pop();
            assert_that(s.isEmpty()).is_true();
        });

        it("is not empty after 2 pushes and 1 pop", () -> {
            s.push(0);
            s.push(0);
            s.pop();
            assert_that(s.isEmpty()).is_false();
        });

        it("pops `x` after pushing `x`", () -> {
            s.push(99);
            assert_that(s.pop()).equals_to(99);
            s.push(88);
            assert_that(s.pop()).is(88);
        });

        it("pops `y` then `x` after pushing `x` then `y`", () -> {
            s.push(99);
            s.push(88);
            assert_that(s.pop()).equals_to(88);
            assert_that(s.pop()).equals_to(99);
        });
    }
}
