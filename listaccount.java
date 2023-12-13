import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class listaccount {
    private account lsAccount[];
    int soLuong;

    private int readSL() {
        int n = 0;
        Pattern header = Pattern
                .compile("id\\s{8}\\| userName\\s{22}\\| password\\s{22}\\| type\\s{6}\\| ngaytao\\s{0,8}$",
                        Pattern.CASE_INSENSITIVE);
        Pattern body = Pattern.compile(
                "^a\\d{1,9}\\s{0,8}\\| [a-zA-Z0-9]{0,30}\\s{0,30}\\| [a-zA-Z0-9]{0,30}\\s{0,30}\\| (NV|QL)\\s{8}\\| \\d{1,2}\\/\\d{1,2}\\/\\d{4}\\s{0,7}$",
                Pattern.CASE_INSENSITIVE);
        Matcher findMatch;
        try {
            BufferedReader fs = new BufferedReader(new FileReader("account.txt"));
            String currentLine = fs.readLine();
            findMatch = header.matcher(currentLine);
            if (!findMatch.find()) {
                fs.close();
                return -1;
            }
            while (currentLine != null) {
                currentLine = fs.readLine();
                if (currentLine == null)
                    break;
                findMatch = body.matcher(currentLine);
                if (!findMatch.find()) {
                    fs.close();
                    return -1;
                }
                n++;
            }
            fs.close();
        } catch (Exception e) {
            n = -1;
        }
        return n;
    }

    public void readFile() {
        soLuong = readSL();
        if (soLuong == -1) {
            System.out.println("Khong tim thay file, bat dau khoi tao mang co san");
            lsAccount = new account[2];
            lsAccount[0] = new account("admin", "admin", "QL", 24, 11, 2023);
            lsAccount[1] = new account("nhanviena2", "123456", "NV", 24, 11, 2023);
            soLuong = 2;
            writeToFile();
            return;
        }
        try {
            lsAccount = new account[soLuong];
            Scanner sc = new Scanner(new File("account.txt"));
            sc.nextLine(); // bỏ qua heading
            for (int i = 0; i < soLuong; i++) {
                String id = sc.next();
                sc.next(); // bỏ qua |
                String userName = sc.next();
                sc.next(); // bỏ qua |
                String password = sc.next();
                sc.next(); // bỏ qua |
                String type = sc.next();
                sc.next(); // bỏ qua |
                String ngaytao[] = new String[3];
                ngaytao = sc.next().split("\\/");
                lsAccount[i] = new account(id, userName, password, type, Integer.parseInt(ngaytao[0]),
                        Integer.parseInt(ngaytao[1]), Integer.parseInt(ngaytao[2]));
            }
            account.setcountId(Integer.parseInt(lsAccount[soLuong - 1].getId().replace("a", "")));
        } catch (Exception e) {
        }
    }

    public listaccount() {
        lsAccount = new account[0];
    }

    public void themTaiKhoan(account sp) {
        lsAccount = Arrays.copyOf(lsAccount, soLuong + 1);
        lsAccount[soLuong++] = sp;
        writeToFile();
    }

    public void xoaTaiKhoan(String id) {
        for (int i = 0; i < lsAccount.length; i++) {
            if (lsAccount[i].getId().equals(id)) {
                for (int j = i; j < lsAccount.length - 1; j++)
                    lsAccount[j] = lsAccount[j + 1];
                break;
            }
        }
        lsAccount = Arrays.copyOf(lsAccount, soLuong - 1);
        soLuong--;
    }

    public boolean searchId(String id) {
        for (account i : lsAccount) {
            if (i.getId().equals(id))
                return true;
        }
        return false;
    }

    public void writeToFile() {
        try {
            FileWriter file = new FileWriter("account.txt");
            file.write(
                    String.format("%-10s| %-30s| %-30s| %-10s| %-15s\n", "id", "userName", "password",
                            "type",
                            "ngaytao"));
            for (account i : lsAccount) {
                file.write(i.toString());
            }
            file.close();
        } catch (Exception e) {
        }
    }

    public void hienTatCaAcc() {
        System.out.printf("%-10s| %-30s| %-30s| %-10s| %-15s\n", "id", "userName", "password",
                "type",
                "ngaytao");
        for (account i : lsAccount) {
            System.out.print(i);
        }
    }

    public String searchType(String id) {
        for (account i : lsAccount) {
            if (i.getId().equals(id)) {
                if (i.getType().equals("QL"))
                    return i.getType();
            }
        }
        return "NV";
    }

    public account checkAccount(String userName, String password) {
        for (account i : lsAccount) {
            if (i.CheckInfo(userName, password))
                return i;
        }
        return null;
    }

    // public static void main(String[] args) {
    // listaccount ls = new listaccount();
    // ls.readFile();
    // ls.hienTatCaAcc();
    // }
}
