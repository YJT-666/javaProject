package com.jjt;

import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * ClassName: utils
 * Package: jjt
 * Description:
 *               常见IO工具类
 *
 * @Author jjt
 * @Create 2023 /12/12 17:25
 * @Version 1.0
 */
public class IoUtils {
    private static Scanner scanner = new Scanner(System.in);
    private static final HashSet<String> MenuSelectionSet = new HashSet<>();  // 保存合法菜单选项字符串

    static {
        MenuSelectionSet.add("1");
        MenuSelectionSet.add("2");
        MenuSelectionSet.add("3");
        MenuSelectionSet.add("4");
        MenuSelectionSet.add("q");
        // System.out.println(MenuSelectionSet);
    }


    static String readKeyBoard(int limit) {
        String line = "";
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            if (line.length() < 1 || line.length() > limit) {
                System.out.print("输入的长度要求范围为 0~" + limit + "，请重新输入:");
                continue;
            } else {
                return line;
            }
        }
        return line;
    }

    /**
     * Read number double.
     *
     * @return the double
     */
    static double readNumber() {
        double number = 0.0;
        String patternString = "[0-9.]+";
        Pattern pattern = Pattern.compile(patternString);

        while (true) {
            String line = readKeyBoard(8);
            if ( !pattern.matcher(line).matches() ) {
                // 不匹配
                System.out.print("请输入正确的数字，可以包含小数点: ");
                continue;
            } else {
                return Double.parseDouble(line);
            }
        }
    }

    static String readString() {
        return readKeyBoard(255);
    }

    static String readMenuSelection() {
        String selection = "";

        for(;;) {
            selection = readKeyBoard(1);
            if (!MenuSelectionSet.contains(selection)) {
                System.out.print("功能菜单选择错误，请重新输入 : ");
                continue;
            } else {
                break;
            }
        }
        return selection;
    }
}
