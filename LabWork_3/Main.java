package LabWork_3;

import java.util.ArrayList;

public class Main {
    static Split_Str pf = new Split_Str();
    static ArrayList<String> rs = new ArrayList<>();


    public static void main(String[] args) {
        pf.setString("Hel lo world, I'am ma ny subdivision ask vo lts quattro");    // Передаваемая строка
        pf.setLenght(17);    // Длина разделенной строки
        pf.needCutWord(false);  // true - если длина строки меньше длины самого большого слова: слово разрезается на части,
        // false - длина строки увеличивается до длины самого большого слова
        pf.separateStr();   // Запуск разделения строки
        rs = pf.getArrListStr();    // Получение результата
        System.out.println(rs);
        System.out.println();
        for(int i = 0;i < rs.size(); i++) {
            System.out.println(rs.get(i));
        }

        System.out.println("_-----------------------------");
        pf.setString("fdsffg gdfsgsdf gdf fddgs fgdsg");    // Передаваемая строка
        pf.setLenght(3);    // Длина разделенной строки
        pf.needCutWord(true);  // true - если длина строки меньше длины самого большого слова: слово разрезается на части,
        // false - длина строки увеличивается до длины самого большого слова
        pf.separateStr();   // Запуск разделения строки
        rs = pf.getArrListStr();    // Получение результата
        System.out.println(rs);
        System.out.println();
        for(int i = 0;i < rs.size(); i++) {
            System.out.println(rs.get(i));
        }
    }
}
