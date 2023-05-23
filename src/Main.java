import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        boolean isCorrect = false;
        String currentName = "alsu";
        int n = 4;
        String parent1 = generationString(n);
        String parent2 = generationString(n);
        LinkedList<String> names = new LinkedList<>();
        parent1 = parent1 + " " + counter(parent1, currentName, n);
        parent2 = parent2 + " " + counter(parent2, currentName, n);

        names.add(parent1);
        names.add(parent2);

        print(names);
        while (isCorrect == false){
            crossing(names, currentName, n);
            selection(names, isCorrect);
            System.out.println("Новая иттерация");
            print(names);
        }

    }

    private static void print(LinkedList<String> names) {
        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i));
            if (names.get(i).contains("4")){
                System.out.println("Имя подобрано! Номер в листе:" + i+1);
                break;
            }
        }
    }

    private static boolean selection(LinkedList<String> list, boolean isCorrect){
        int have0 = 0;
        int have1 = 0;
        int have2 = 0;
        int have3 = 0;
        int have4 = 0;
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).contains("0")){
                have0++;
            }
            if (list.get(i).contains("1")){
                have1++;
            }
            if (list.get(i).contains("2")){
                have2++;
            }
            if (list.get(i).contains("3")){
                have3++;
            }
            if (list.get(i).contains("4")){
                have4++;
            }
//            int count = (int) list.get(i).charAt(5);
//            switch (count){
//                case 0:
//                    have0++;
//                    break;
//                case 1:
//                    have1++;
//                    break;
//                case 2:
//                    have2++;
//                    break;
//                case 3:
//                    have3++;
//                    break;
//                case 4:
//                    have4++;
//                    System.out.println("Верное имя!");
//                    break;
//                default:
//                    break;
//            }
        }
        if (have4 > 0){
            isCorrect = true;
        }
        if (have3>3){
            for (int i = list.size() - 1; i >= 0; i--){
                if (list.get(i).contains("0") || list.get(i).contains("1") || list.get(i).contains("2")){
                    list.remove(i);
                }
            }
        } else {
            if (have2>3){
                for (int i = list.size() - 1; i >= 0; i--){
                    if (list.get(i).contains("0") || list.get(i).contains("1")){
                        list.remove(i);
                    }
                }
            } else {
                if (have1>2){
                    for (int i = list.size() - 1; i >= 0; i--){
                        if (list.get(i).contains("0")){
                            list.remove(i);
                        }
                    }
                } else {
                    if (have0 > 2){
                        for (int i = list.size() - 1; i >= 4; i--){
                            list.remove(i);
                        }
                        list.remove(1);
                        list.remove(0);
                    }
                }
            }
        }
        return isCorrect;
    }

    private static Integer counter(String name, String currentName, int n) {
        int count = 0;
        for (int i = 0; i < n; i++){
            if (currentName.charAt(i) == name.charAt(i)){
                count++;
            }
        }
        return count;
    }


    private static LinkedList<String> crossing(LinkedList<String> list, String currentName, int n) {
        int size = list.size();
        String child = "";
        for (int i = 0; i < size - 1; i++){
            for (int j = i+1; j < size; j++){
                makingChild(n, list.get(i), list.get(j), currentName, list);
                makingChild(n, list.get(j), list.get(i), currentName, list);
            }
        }
        return list;
    }

    private static void makingChild(int n, String first, String second, String currentName, LinkedList<String> list) {
        String child = "";
        child = mutation(n, first, second);
        child = child + " " + counter(child, currentName, n);
        //System.out.println(child);
        list.add(child);
    }

    public static String mutation(int max, String first, String second)
    {
        String result = "";
        int min = 1;
        max++;
        max -= min;
        int rnd = (int) (Math.random() * ++max) + min;
        switch (rnd){
            case 1:
                result = generationString(1) + second.substring(1, 2) + first.substring(2, 3) + second.substring(3, 4);
                break;
            case 2:
                result = first.substring(0, 1) + generationString(1) + first.substring(2, 3) + second.substring(3, 4);
                break;
            case 3:
                result = first.substring(0, 1) + second.substring(1, 2) + generationString(1) + second.substring(3, 4);
                break;
            case 4:
                result = first.substring(0, 1) + second.substring(1, 2) + first.substring(2, 3) + generationString(1);
                break;
            case 5:
                result = first.substring(0, 1) + second.substring(1, 2) + first.substring(2, 3) + second.substring(3, 4);
                break;
            default:
                System.out.println("Где-то есть ошибка");
                break;
        }
        return result;
    }

    static String generationString(int n)
    {

        // choose a Character random from this String
        String AlphaNumericString = "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

}