package com.auto.message;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class streamTest {

	public int solution(int[] A) {
	       
		int N = A.length;
		System.out.println("길이 : "+N);
	        Set<Integer> set = new HashSet<>();
	        for (int a : A) {
	            if (a > 0) {
	            	System.out.println("set add : "+a);
	                set.add(a);
	            }
	        }
	        for (int i = 1; i <= N + 1; i++) {
	        	System.out.println("for : "+i);
	            if (!set.contains(i)) {
	            	System.out.println(i);
	                return i;
	            }
	        }
			return N;
	    }
	
	
	public static void main(String[] args) {
		int[] A = {1, 3, 6, 4, 1, 2};
		
		streamTest a = new streamTest();
		int B = a.solution(A);
		System.out.println(B);
		System.out.println("-------------------------");
		
		
		int ar[] = { 0, 10, 2, -10, -20 }; 
        int max = Arrays.stream(ar).max().getAsInt();
        System.err.println("maxvalue "+max);
        
        int val = IntStream.range(1, max).filter(i->!Arrays.stream(ar).anyMatch(x->x==i))
                .findFirst().getAsInt();
        System.out.println(val);
        
        int[] val1 = IntStream.range(1, max).filter(i->!Arrays.stream(ar).anyMatch(x->x==i)).map(p->p).toArray();
        System.out.println("------------------");
        IntStream.of(val1).forEach(System.out::println);
        
        int[] valEven = IntStream.range(1, max).filter(i->Arrays.stream(val1).anyMatch(x->i%2==0)).map(p->p).toArray();
        System.out.println("------------------");
        IntStream.of(valEven).forEach(System.out::println); 
        
        int[] valOdd = IntStream.range(1, max).filter(i->!Arrays.stream(val1).anyMatch(x->i%2==0)).map(p->p).toArray();
        System.out.println("------------------");
        IntStream.of(valOdd).forEach(System.out::println);  
        
        int[] valOdd1 = IntStream.range(1, max).filter(i->Arrays.stream(val1).noneMatch(x->i%2==0)).map(p->p).toArray();
        System.out.println("------------------");
        IntStream.of(valOdd1).forEach(System.out::println);   
        
        int[] valEven1 = IntStream.range(1, max).filter(i->!Arrays.stream(val1).noneMatch(x->i%2==0)).map(p->p).toArray();
        System.out.println("------------------");
        IntStream.of(valEven1).forEach(System.out::println);       
        
        int[] arr = { 1, 3, 6, 4, 1, 2 };
        Set<Integer> arrSet = Arrays.stream(arr).boxed().collect(Collectors.toSet());
        Optional<Integer> found = IntStream.iterate(1, o -> o + 1).boxed()
                .filter(value -> !arrSet.contains(value))
                .findFirst();
        System.out.println("------------------");
        found.ifPresent(System.out::println);
        
        
        
	}
}
