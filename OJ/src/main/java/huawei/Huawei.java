package huawei;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Huawei {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] logs = new String[n];
        for (int i = 0; i < n; i++) {
            logs[i] = sc.nextLine();
        }
        Arrays.sort(logs,(s1, s2) -> compator(s1,s2) );
        for (String log : logs) {
            System.out.println(log);
        }
    }

    public static int compator(final String log1, final String log2) {
//        log1 = Arrays.stream(log1.split("\\.")).map(s -> trim1(s)).collect(Collectors.joining("."));
//        log2 = Arrays.stream(log2.split("\\.")).map(s -> trim1(s)).collect(Collectors.joining("."));
        String[] a = log1.replace(":", ".").split("\\.");
        String[] b = log2.replace(":", ".").split("\\.");
        for (int i = 0; i < a.length; i++) {
            int temp = Integer.parseInt(a[i]) - (Integer.parseInt(b[i]));
            if (temp > 0) return 1;
            if (temp < 0) return -1;
        }
        return 0;
    }

    public static String trim1(String s) {
        if ("0".equals(s)) return s;
        if (!s.startsWith("0")) return s;
        return trim1(s.substring(1));
    }
}
