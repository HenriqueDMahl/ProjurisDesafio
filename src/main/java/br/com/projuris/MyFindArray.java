package br.com.projuris;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyFindArray implements FindArray {

    public int findArray(int[] array, int[] subArray){
        boolean flag = false;
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < subArray.length; j++){
                if(i+j < array.length && array[i + j] == subArray[j]){
                    flag = true;
                }else{
                    flag = false;
                    break;
                }
            }
            if(flag){
                return i;
            }
        }
        return -1;
    }

    public int findArrayCheat(int[] array, int[] subArray){
        return Collections.indexOfSubList(Arrays.asList(array), Arrays.asList(subArray));
    }

}
