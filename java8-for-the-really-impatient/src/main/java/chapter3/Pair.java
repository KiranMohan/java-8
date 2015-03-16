/**
 * file       : Pair.java
 * author     : "Kiran Mohan"
 * created on : Mar 16, 2015
 */
package chapter3;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

class Pair<T> {
    T first;
    T second;
    
    public Pair(T first, T second) {
        super();
        this.first = first;
        this.second = second;
    }
    
    public <R> R map(BiFunction<T, T, R> function) {
        return function.apply(first, second);
    }
    
    public <R> Pair<R> flatMap(Function<T, R> function) {
        return new Pair<>(function.apply(first), function.apply(second));
    }


    @Override
    public String toString() {
        return "Pair [first=" + first + ", second=" + second + "]";
    }
    
}