package com.mysiteforme.admin.util;
import java.util.Arrays;
import java.util.Random;

import com.zfh.app.mongo.service.system.impl.AXBInterfaceDemoImpl;

/**
 * 随机打乱数组
 * Created by 燃烧杯 on 2018/5/12.
 */
public class RandomUtil {

    private static Random rand = new Random();

    public static <T> void swap(T[] a, int i, int j){
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static <T> void shuffle(T[] arr) {
        int length = arr.length;
        for ( int i = length; i > 0; i-- ){
            int randInd = rand.nextInt(i);
            swap(arr, randInd, i - 1);
        }
    }

    public static void main(String[] args) {
    	String[] numbers = AXBInterfaceDemoImpl.getInstance().relationNums;
        shuffle(numbers);
        System.out.println(Arrays.toString(numbers));
    }
}