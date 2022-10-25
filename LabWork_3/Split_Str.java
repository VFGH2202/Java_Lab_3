package LabWork_3;

import java.util.*;

public class Split_Str {
    private String original_str = "";
    private int split_lenght = -1;
    private Integer maxLenghtWord = 0;
    private ArrayList<String> result = new ArrayList<>();
    private ArrayList<String> rtrn = new ArrayList<>();
    boolean wordBiggestLineSize = false, cut_word = false;


    public void setString(String str_input) {
        result.clear();
        rtrn.clear();
        original_str = str_input;
        if (split_lenght != -1) {
            checkMaxLenght(original_str);
        }
    }
    public void setLenght(int len) {
        split_lenght = len;
        if (original_str != "") {
            checkMaxLenght(original_str);
        }
    }
    public void needCutWord(boolean par) {
        cut_word = par;
    }
    public void separateStr() {
        if (cut_word) {
            String[] origWords = original_str.split(" ");
            ArrayList<String> cutted = new ArrayList<>();
            for (int i = 0;i < origWords.length; i++) {
                if (origWords[i].length() > split_lenght) {
                    String el = "";
                    char[] wrd = origWords[i].toCharArray();
                    int itr = 1;
                    for (int j = 0;j < origWords[i].length(); j++) {
                        if (j < split_lenght * itr) {
                            el = el.concat(String.valueOf(wrd[j]));
                        }
                        else {
                            cutted.add(el);
                            el = String.valueOf(wrd[j]);
                            itr++;
                        }
                    }
                    cutted.add(el);
                }
                else {
                    cutted.add(origWords[i]);
                }
            }

            String[] words = cutted.toArray(new String[0]);
            String line = "";
            int count = 0;
            for (int i = 0; i < words.length; i++) {
                if (count + words[i].length() <= split_lenght) {
                    count += words[i].length() + 1;
                    line += words[i] + " ";
                }
                else {
                    result.add(line.substring(0, line.length() - 1));
                    line = "";
                    line += words[i] + " ";
                    count = words[i].length() + 1;
                }
                if (i == (words.length - 1)) {
                    result.add(line.substring(0, line.length() - 1));
                }

            }
        }
        else {
            if (split_lenght < maxLenghtWord) split_lenght = maxLenghtWord;

            String[] words = original_str.split(" ");
            System.out.println(split_lenght);

            String line = "";
            int count = 0;

            for (int i = 0; i < words.length; i++) {
                if (count + words[i].length() <= split_lenght) {
                    count += words[i].length() + 1;
                    line += words[i] + " ";
                }
                else {
                    result.add(line.substring(0, line.length() - 1));
                    line = "";
                    line += words[i] + " ";
                    count = words[i].length() + 1;
                }
                if (i == (words.length - 1)) {
                    result.add(line.substring(0, line.length() - 1));
                }

            }
        }
//        System.out.println(result);
        gapsDestribution();
    }
    public String[][] getArrListStr() {

        String[][] out = new String[rtrn.size()][split_lenght];
        for (int i = 0; i < rtrn.size(); i++) {
            for (int j = 0;j < split_lenght; j++) {
                char[] line = rtrn.get(i).toCharArray();
                out[i][j] = String.valueOf(line[j]);
            }
        }
        original_str = "";
        split_lenght = -1;
        maxLenghtWord = 0;
        wordBiggestLineSize = false;
        cut_word = false;
        return out;

    }


    private void checkMaxLenght(String str) {
        String[] sp = str.split(" ");
        for (int i = 0; i < sp.length; i++) {
            maxLenghtWord = Math.max(maxLenghtWord, sp[i].length());
        }
        if (maxLenghtWord > split_lenght) wordBiggestLineSize = true;
    }
    private void gapsDestribution() {
//        System.out.println("           ");
        for (int i = 0; i < result.size(); i++) {
            char[] item = result.get(i).toCharArray();  // Берем одну из итоговых строк бер распределения пробелов

            int gaps_count = 0; // Кол-во недостающих пробелов
            ArrayList<Integer> gapsSeparate = new ArrayList<>();    // Распределение кол-ва пробелов по позициям

            for (int j = 0; j < item.length; j++) {
                if (item[j] == ' ') {
                    gaps_count++;
                    gapsSeparate.add(1);
                }
                if (j == item.length - 1 && gapsSeparate.isEmpty()) {
                    gapsSeparate.add(0);
                }
            }   //Подсчет кол-ва пробелов с их позицией

            gaps_count = (split_lenght - item.length);  // Кол-во недостающих пробелов

            int sepUnits = 0;   // Перебор индексов gapsSeparate
            while (gaps_count > 0) {
                gapsSeparate.set(sepUnits, (gapsSeparate.get(sepUnits) + 1));
                gaps_count--;
                if (sepUnits == gapsSeparate.size()-1) sepUnits = 0;
                else sepUnits++;
            }

            String endLine = "";
            int getSepInsex = 0;
            for (int j = 0; j < item.length; j++) {
                if (item[j] != ' ') {
                    endLine = endLine.concat(String.valueOf(item[j]));
                }
                else {
                    endLine = endLine.concat(" ".repeat(gapsSeparate.get(getSepInsex)));
                    getSepInsex++;
                }
            }   // Финальния строка
            if (endLine.length() != split_lenght) {
                endLine = endLine.concat(" ".repeat(gapsSeparate.get(getSepInsex)));
            }
//            System.out.println(endLine);
            rtrn.add(endLine);
        }
    }
}
