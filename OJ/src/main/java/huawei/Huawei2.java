package huawei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Huawei2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String res = sc.nextLine();
        String apply = sc.nextLine();

        String [] tempres = res.split(",");
        List<Integer> reslist = new ArrayList<>();
        for (String s : tempres) {
            String [] a = s.split(":");
            int t = Integer.parseInt(a[1]);
            for (int i = 0; i < t; i++) {
                reslist.add(Integer.parseInt(a[0]));
            }
        }
        reslist.sort(Integer::compare);
        List<Integer> applys = Arrays.stream(apply.split(",")).map(s -> {
            if ("".equals(s)) {
                return "0";
            }
            return s;
        }).map(Integer::parseInt).collect(Collectors.toList());

        boolean[] allocated = new boolean[reslist.size()];
        boolean[] ans = new boolean[applys.size()];
        for (int i = 0; i < applys.size(); i++) {
            for (int j = 0; j < reslist.size(); j++) {
                if (reslist.get(j) >= applys.get(i) && !allocated[j]) {
                    ans[i] = true;
                    allocated[j] = true;
                    break;
                }
            }
        }
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i]);
            if (i != ans.length - 1) {
                System.out.print(",");
            }
        }
    }
}
