package com.hong.test.generic;

/**
 * Created by cai on 2015/9/9 18:55.
 */
public class GenericMemoryCell<T> {
    private T t;

    void write(T t) {
        this.t = t;
    }

    T read() {
        return t;
    }

    static <T> boolean contains(T[] arr, T x) {
        for (T t : arr) {
            if (x.equals(t))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        GenericMemoryCell<String>[] arr1 = new GenericMemoryCell[10];
//        GenericMemoryCell<Double> cell = new GenericMemoryCell<>();
//        cell.write(4.5);
//        Object[] arr2 = arr1;
//        arr2[0] = cell;
//        String s = arr1[0].read();    // 此处运行时异常，说明泛型在编译时不会检查类型是否匹配

        String[] ss = new String[]{"1", "2", "3"};
        Integer s = 1;
        System.out.println(GenericMemoryCell.contains(ss, s));
    }
}
