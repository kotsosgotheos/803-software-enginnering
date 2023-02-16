/**
 * Name: Eirini Thoma,            AM: 4292
 * Name: Athanasios Papapostolou, AM: 4147
 */

/**
 * @class: Stack
 * @desc: Implementation of a pushdown stack
 * @param <T> Generic type of the stack
 */
public class Stack<T> {
    private T[] S;
    private int N;

    /**
     * @Constructor
     */
    @SuppressWarnings("unchecked")
    Stack(int maxN) {
        S = (T[]) new Object[maxN];
        N = 0;
    }

    /**
     * @func: isEmpty
     * @desc: Checks if the the stack is empty
     * @return A boolean
     */
    boolean isEmpty() {
        return N == 0;
    }

    /**
     * @func: push
     * @desc: Implements a bury operation on the stack structure
     * @param item The item to push on the stack
     */
    void push(T item) {
        S[N++] = item;
    }

    /**
     * @func: pop
     * @desc: Implements an unbury operation on the stack structure
     * @return The item we popped off
     */
    T pop() {
        if(N > 0) {
            T type = S[--N];
            S[N] = null; /* Free the memory of the position we popped */
            return type;
        }
        return null;
    }
}
