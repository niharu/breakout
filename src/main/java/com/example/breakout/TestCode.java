package com.example.breakout;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by ryo on 2018/08/26.
 */
public class TestCode {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>() {
            {
                add("caa"); add("bbb");
            }
        };
        Optional<String> str = list.stream().filter(s -> s.startsWith("c")).findFirst();
        str.ifPresent(System.out::println);
//        System.out.println(str.get().length());
    }
}
