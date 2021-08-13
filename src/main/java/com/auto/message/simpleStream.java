package com.auto.message;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;


public class simpleStream {
	
	
	public Stream<String> streamOf(List<String> list) {
		return list == null || list.isEmpty()
				? Stream.empty() : list.stream();
	}

	// 1-1. streamOf 메소드 테스트 | 비어 있는 스트림
	/*
	List<String> list = new ArrayList<String>();
	
	simpleStream a = new simpleStream(); Stream<String> b = a.streamOf(list);
	System.out.println(b.toString());
	 */
	
	// 1-2. Stream.builder() 빌더를 이용하여 스트림에 직접적으로 원하는 값 넣기
	/*
	Stream<Object> builderStream =
	Stream.builder().add("Eric").add("Elena").add("Java").build();
	System.out.println(builderStream));
	 */
	
	// 1-3. Stream.generate() : Supplier<T> 에 해당하는 람다로 값 넣을 수 있음 ( 인자는 없고 리턴값만 있는 함수형 인터페이스 )
	
//	public static<T> Stream<T> Generate(Supplier<T> s) {
//		Stream<String> generatedStream = Stream.generate(() -> "gen").limit(5);
//		return (Stream<T>) generatedStream;
//	}
	
	// 2. Stream.iterate() : 초기값과 해당 값을 다루는 람다를 이용해서 스트림에 들어갈 요소 만듬
//	Stream<Integer> isStream = Stream.iterate(30, n -> n + 2).limit(5);
	
	// 3. 제네릭 사용하지 않고 직접적으로 해당 타입의 스트림 다루기
	// range 와 rangeCosed 차이
	/*
	IntStream istIntStream = IntStream.range(1, 5);			// 1,2,3,4
	LongStream longStream  = LongStream.rangeClosed(1, 5);	// 1,2,3,4,5
	*/
	// 제네릭을 사용하지 않기 때문에 불필요한 오토박싱이 일어나지 않음 그러나 필요할 경우에 boxed 메소드를 이용해서 박싱 할 수 있음
	//Stream<Integer> boxedStream = IntStream.range(1, 5).boxed();
	
	// 랜덤 클래스는 난수를 가지고 세 가지 타입의 스트림 (인트,롱,더블)을 만들어 낼 수 있다.
//	DoubleStream doubleStream = new Random().doubles(3);
	
	
}
