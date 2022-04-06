import java.util.*;

class Hiragana {
    private final static Map<String, String> hiragana;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    static {
        hiragana = new HashMap<String, String>();
        hiragana.put("a", "あ");
        hiragana.put("i", "い");
        hiragana.put("u", "う");
        hiragana.put("e", "え");
        hiragana.put("o", "お");

        hiragana.put("ka", "か");
        hiragana.put("ki", "き");
        hiragana.put("ku", "く");
        hiragana.put("ke", "け");
        hiragana.put("ko", "こ");

        hiragana.put("sa", "さ");
        hiragana.put("shi", "し");
        hiragana.put("su", "す");
        hiragana.put("se", "せ");
        hiragana.put("so", "そ");

        hiragana.put("ta", "た");
        hiragana.put("chi", "ち");
        hiragana.put("tsu", "つ");
        hiragana.put("te", "て");
        hiragana.put("to", "と");

        hiragana.put("na", "な");
        hiragana.put("ni", "に");
        hiragana.put("nu", "ぬ");
        hiragana.put("ne", "ね");
        hiragana.put("no", "の");

        hiragana.put("ha", "は");
        hiragana.put("hi", "ひ");
        hiragana.put("fu", "ふ");
        hiragana.put("he", "へ");
        hiragana.put("ho", "ほ");

        hiragana.put("ma", "ま");
        hiragana.put("mi", "み");
        hiragana.put("mu", "む");
        hiragana.put("me", "め");
        hiragana.put("mo", "も");

        hiragana.put("ya", "や");
        hiragana.put("yu", "ゆ");
        hiragana.put("yo", "よ");

        hiragana.put("ra", "ら");
        hiragana.put("ri", "り");
        hiragana.put("ru", "る");
        hiragana.put("re", "れ");
        hiragana.put("ro", "ろ");

        hiragana.put("wa", "わ");
        hiragana.put("wo", "を");
        hiragana.put("n", "ん");

        hiragana.put("ga", "が");
        hiragana.put("gi", "ぎ");
        hiragana.put("gu", "ぐ");
        hiragana.put("ge", "げ");
        hiragana.put("go", "ご");

        hiragana.put("za", "ざ");
        hiragana.put("ji", "じ");
        hiragana.put("zu", "ず");
        hiragana.put("ze", "ぜ");
        hiragana.put("zo", "ぞ");

        hiragana.put("da", "だ");
        hiragana.put("ji", "ぢ");
        hiragana.put("zu", "づ");
        hiragana.put("de", "で");
        hiragana.put("do", "ど");

        hiragana.put("ba", "ば");
        hiragana.put("bi", "び");
        hiragana.put("bu", "ぶ");
        hiragana.put("be", "べ");
        hiragana.put("bo", "ぼ");

        hiragana.put("pa", "ぱ");
        hiragana.put("pi", "ぴ");
        hiragana.put("pu", "ぷ");
        hiragana.put("pe", "ぺ");
        hiragana.put("po", "ぽ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exit;
        String select;
        do {
            System.out.println("Nhấn 1 để chọn dịch từ " + ANSI_RED + "a " + ANSI_RESET + "sang " + ANSI_RED + "あ" + ANSI_RESET + "   |  2:Ngược lại");
            select = scanner.nextLine();
            if (select.equals("1") || select.equals("2")) {
                do {
                    List<String> valuesList = new ArrayList<>(hiragana.values());
                    List<String> keyList = new ArrayList<>(hiragana.keySet());
                    int randomIndex = new Random().nextInt(valuesList.size());
                    String randomValue = valuesList.get(randomIndex);
                    String randomKey = keyList.get(randomIndex);
                    if (select.equals("2")) {
                        System.out.println(ANSI_RED + randomValue + ANSI_RESET + "  là gì?");
                        System.out.print("Đáp án của bạn là:");
                        String result = scanner.nextLine();
                        if (result.equals(randomKey)) {
                            System.out.println(ANSI_BLUE + "Đáp án chính xác!" + ANSI_RESET);
                        } else {
                            System.out.println(ANSI_PURPLE + "Sai rồi! Đáp án chính xác là: " + ANSI_RESET + ANSI_RED + randomKey + ANSI_RESET);
                        }
                    } else {

                        System.out.println("Hãy viết ra giấy từ " + ANSI_RED + randomKey + ANSI_RESET + " nào!");
                        System.out.println("Nhấn Enter để xem kết quả!");
                        scanner.nextLine();
                        System.out.println("Kết quả là:  " + ANSI_RED + randomValue + ANSI_RESET);
                    }
                    System.out.println("Enter để tiếp tục  |  1:để quay lại!");
                    exit = scanner.nextLine();
                } while (!exit.equals("1"));
            }
        } while (!select.equals("0"));
    }
}
