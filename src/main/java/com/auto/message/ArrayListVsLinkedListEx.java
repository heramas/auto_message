package com.auto.message;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListVsLinkedListEx {
  public static void work(int value) {
  }
  
  public static double testParallel(List<Integer> list) {
    double start = System.currentTimeMillis();
    list.stream().parallel().forEach(ArrayListVsLinkedListEx::work);
    double end = System.currentTimeMillis();
    double runTime = (end - start)/1000;
    return runTime;
  }
  
  public static void main(String[] args) {
    List<Integer> arrayList = new ArrayList<>();
    List<Integer> linkedList = new LinkedList<>();
    for (int i = 0; i < 10000000; i++) {
      arrayList.add(i);
      linkedList.add(i);
    }
    
    //워밍업
    double arrayListParallel = testParallel(arrayList);
    double linkedListParallel = testParallel(linkedList);
    
    arrayListParallel = testParallel(arrayList);
    linkedListParallel = testParallel(linkedList);
    
    if (arrayListParallel < linkedListParallel) {
      System.out.println("성능 테스트 결과: ArrayList가 더 빠름 ["+arrayListParallel+"]["+linkedListParallel+"]");
    } else {
      System.out.println("성능 테스트 결과: LinkedList가 더 빠름 ["+arrayListParallel+"]["+linkedListParallel+"]");
    }
  }
}

