import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class checkPattern {
    public static int checkInt(Scanner sc) {
        int result;
        Pattern intPattern = Pattern.compile("^\\d+$");
        do {
            String temp = sc.nextLine();
            Matcher findMatch = intPattern.matcher(temp);
            if (findMatch.find()) {
                result = Integer.parseInt(temp);
                return result;
            }
            System.out.print("phai la so, vui long nhap lai: ");
        } while (true);
    }

    public static double checkDouble(Scanner sc) {
        Double result;
        Pattern intPattern = Pattern.compile("^[0-9]+\\.?[0-9]*");
        do {
            String temp = sc.nextLine();
            Matcher findMatch = intPattern.matcher(temp);
            if (findMatch.find()) {
                result = Double.parseDouble(temp);
                return result;
            }
            System.out.print("phai la so, vui long nhap lai: ");
        } while (true);
    }

    public static int checkNgayThang(Scanner sc) {
        int result;
        Pattern intPattern = Pattern.compile("^\\d{1,2}$");
        do {
            String temp = sc.nextLine();
            Matcher findMatch = intPattern.matcher(temp);
            if (findMatch.find()) {
                result = Integer.parseInt(temp);
                return result;
            }
            System.out.print("phai co 1-2 so, vui long nhap lai: ");
        } while (true);
    }

    public static int checkNam(Scanner sc) {
        int result;
        Pattern intPattern = Pattern.compile("^\\d{4}$");
        do {
            String temp = sc.nextLine();
            Matcher findMatch = intPattern.matcher(temp);
            if (findMatch.find()) {
                result = Integer.parseInt(temp);
                return result;
            }
            System.out.print("phai co 4 so, vui long nhap lai: ");
        } while (true);
    }

    public static long checkcccd(Scanner sc) {
        long result;
        Pattern intPattern = Pattern.compile("^\\d{12}$");
        do {
            String temp = sc.nextLine();
            Matcher findMatch = intPattern.matcher(temp);
            if (findMatch.find()) {
                result = Long.parseLong(temp);
                return result;
            }
            System.out.print("cccd phai co 12 so, vui long nhap lai: ");
        } while (true);
    }

    public static String checkSdt(Scanner sc) {
        String result;
        Pattern intPattern = Pattern.compile("^0\\d{9}$");
        do {
            String temp = sc.nextLine();
            Matcher findMatch = intPattern.matcher(temp);
            if (findMatch.find()) {
                result = temp;
                return result;
            }
            System.out.print("sdt phai co 10 so bat dau bang so 0, vui long nhap lai: ");
        } while (true);
    }

    public static char checkgt(Scanner sc) {
        String result;
        Pattern intPattern = Pattern.compile("^(M|F|f|m)$");
        do {
            String temp = sc.nextLine();
            Matcher findMatch = intPattern.matcher(temp);
            if (findMatch.find()) {
                result = temp;
                return result.charAt(0);
            }
            System.out.print("gioi tinh chi co M hoac F, vui long nhap lai: ");
        } while (true);
    }

    public static String checkID(Scanner sc, Object obj) {
        String idlist = obj.toString().split("\\|\\s")[0];
        String idstart = "";
        for (int i = 0; i < idlist.length(); i++) {
            if (idlist.charAt(i) > 'a' && idlist.charAt(i) < 'z')
                idstart += idlist.charAt(i);
            else
                break;
        }
        String result;
        Pattern intPattern = Pattern.compile("^" + idstart + "\\d{1,9}$");
        do {
            String temp = sc.nextLine();
            Matcher findMatch = intPattern.matcher(temp);
            if (findMatch.find()) {
                result = temp;
                return result;
            }
            System.out.print("khong phai id, id phai chua " + idstart + " bat dau sau do la so vd: " + idstart
                    + "12, vui long nhap lai: ");
        } while (true);
    }

    public static String checkIdSP(Scanner sc) {
        String result;
        Pattern idPattern = Pattern.compile("^(dt|pk)\\d+$");
        do {
            result = sc.nextLine();
            Matcher findMatch = idPattern.matcher(result);
            if (findMatch.find())
                return result;
            System.out.print("Id san pham phai bat dau bang \"pk\" hoac \"dt\" va ket thuc bang mot so ");
        } while (true);
    }

}
