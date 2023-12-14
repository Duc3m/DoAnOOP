import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class dsNhaCungCap {
    private nhacungcap[] dsncc;
    private int soluong;

    public dsNhaCungCap() {
        dsncc = new nhacungcap[0];
    }

    public void setNCC(nhacungcap ncc[]) {
        this.dsncc = ncc;
    }

    public void Header() {
        System.out.printf("\n%-10s| %-30s| %-15s| %-15s\n", "Id", "Ten", "Diachi", "Sodt");
    }

    public void XuatNCC() {
        Header();
        for (nhacungcap i : dsncc) {
            System.out.print(i);
        }
    }

    public int readSl() {
        int sl = 0;
        Pattern header = Pattern.compile("id\\s{6}\\| Ten\\s{17}\\| Dia chi\\s{15}\\| SDT");
        Pattern body = Pattern.compile(
                "ncc\\d{1,5}\\s{0,4}\\| [a-zA-Z ]{1,29}\\s{0,15}\\| [a-zA-Z ]{1,15}\\s{0,17}\\| 0\\d{9}\\s{0,3}");
        Matcher findmatch;
        try {
            BufferedReader fs = new BufferedReader(new FileReader("nhacungcap.txt"));
            String currline = fs.readLine();
            findmatch = header.matcher((currline));
            if (!findmatch.find()) {
                fs.close();
                return -1;
            }
            while (currline != null) {
                currline = fs.readLine();
                if (currline == null)
                    break;
                findmatch = body.matcher(currline);

                if (!findmatch.find()) {
                    System.out.println(sl);
                    fs.close();
                    return -1;
                }
                sl++;
            }
            fs.close();
        } catch (Exception e) {
            return -1;
        }
        return sl;
    }

    public void writeToFile() {
        try {
            FileWriter file = new FileWriter("nhacungcap.txt");
            file.write(
                    String.format("\n%-10s| %-30s| %-15s| %-15s\n", "Id", "Ten", "Diachi",
                            "Sodt"));
            for (nhacungcap i : dsncc) {
                file.write(i.toString());
            }
            file.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void readFile() {
        this.soluong = readSl();
        if (soluong == -1) {
            System.out.println("Khong tim thay file hoac file bi loi! Bat dau khoi tao danh sach co san.");
            dsncc = new nhacungcap[4];
            dsncc[0] = new nhacungcap("APPLE", "TPHCM", "0987654321");
            dsncc[1] = new nhacungcap("XIAOMI", "TPHCM", "0987653421");
            dsncc[2] = new nhacungcap("SAMSUM", "TPHCM", "0978654321");
            dsncc[3] = new nhacungcap("VIVO", "TPHCM", "0987654312");
            soluong = 4;
            writeToFile();
            return;
        }
        try {
            dsncc = new nhacungcap[soluong];
            Scanner sc = new Scanner(new File("nhacungcap.txt"));
            sc.nextLine();
            for (int i = 0; i < soluong; i++) {
                String id = sc.next();
                sc.next();// bo qua "|"
                String name = "";
                while (sc.hasNext()) {
                    String currLine1 = sc.next();
                    if (currLine1.equals("|")) {
                        break;
                    }
                    name += currLine1 + " ";
                }
                String address = "";
                while (sc.hasNext()) {
                    String currLine2 = sc.next();
                    if (currLine2.equals("|")) {
                        break;
                    }
                    address += currLine2 + " ";
                }
                String phoneNumber = sc.next();
                dsncc[i] = new nhacungcap(id, name, address, phoneNumber);
            }
        } catch (Exception e) {

        }
    }

    public void themNCC(nhacungcap ncc) {
        dsncc = Arrays.copyOf(dsncc, soluong + 1);
        dsncc[soluong++] = ncc;
    }

    public void themNCCmoi(Scanner sc) {
        System.out.println();
        System.out.print("Nhap ten nha cung cap moi: ");
        String name = sc.nextLine();

        System.out.print("Nhap dia chi nha cung cap moi: ");
        String address = sc.nextLine();

        System.out.print("Nhap so dien thoai nha cung cap moi: ");
        String number = checkPattern.checkSdt(sc);

        nhacungcap newncc = new nhacungcap(name, address, number);
        themNCC(newncc);
        XuatNCC();
        System.out.println("Da them nha cung cap.");

    }

    public int xoaNCC(String id) {
        int index = -1;
        for (int i = 0; i < dsncc.length; i++) {
            if (dsncc[i].getId().equals(id)) {
                index = i;
                for (int j = i; j < dsncc.length - 1; j++)
                    dsncc[j] = dsncc[j + 1];
            }
        }
        return index;
    }

    public void xoaNCCmoi(Scanner sc) {
        XuatNCC();
        System.out.println();
        System.out.print("Nhap id ban muon xoa: ");
        String id = sc.nextLine();

        int index = xoaNCC(id);
        if (index == -1) {
            System.out.println("Khong tim thay nha cung cap can xoa.");
            return;
        } else {
            for (; index < dsncc.length; index++) {
                if (dsncc[index].getId().equals(id)) {
                    for (int j = index; j < dsncc.length - 1; j++)
                        dsncc[j] = dsncc[j + 1];
                }
            }
            dsncc = Arrays.copyOf(dsncc, soluong - 1);
            soluong--;
            System.out.println();
            XuatNCC();
            System.out.println("Da xoa thanh cong.");
        }
    }

    public int timNCCtheoId(String id) {
        for (int i = 0; i < dsncc.length; i++) {
            if (dsncc[i].getId().equals(id))
                return i;
        }
        return -1;
    }

    public void suaNCC(Scanner sc) {
        XuatNCC();
        System.out.println();
        System.out.print("Nhap id ban muon sua: ");
        String id = sc.nextLine();

        int index = timNCCtheoId(id);
        if (index == -1) {
            System.out.println("Khong tim thay nha cung cap can sua.");
            return;
        }
        Header();
        System.out.println(dsncc[index]);
        char option;
        do {
            System.out.println("========== Sua thong tin Nha Cung Cap ==========");
            System.out.println("1. Ten.");
            System.out.println("2. Dia chi.");
            System.out.println("3. So dien thoai.");
            System.out.println("Nhap x de tro lai.");
            System.out.print("Chon thong tin ban muon sua: ");

            option = sc.nextLine().charAt(0);

            switch (option) {
                case '1':
                    System.out.println();
                    System.out.print("Nhap lai ten nha cung cap moi: ");
                    String newname = sc.nextLine();
                    dsncc[index].setTennhacungcap(newname);
                    Header();
                    System.out.println(dsncc[index]);
                    System.out.println("Da sua thanh cong.");
                    break;
                case '2':
                    System.out.println();
                    System.out.print("Nhap lai dia chi nha cung cap moi: ");
                    String newaddress = sc.nextLine();
                    dsncc[index].setDiachi(newaddress);
                    Header();
                    System.out.println(dsncc[index]);
                    System.out.println("Da sua thanh cong.");
                    break;
                case '3':
                    System.out.println();
                    System.out.print("Nhap lai so dien thoai nha cung cap moi: ");
                    String newnumber = sc.nextLine();
                    dsncc[index].setSodt(newnumber);
                    Header();
                    System.out.println(dsncc[index]);
                    System.out.println("Da sua thanh cong.");
                    break;
                default:
                    break;
            }
        } while (option != 'x');
    }

    public void mainMenu(Scanner sc) {
        char option;
        do {
            System.out.println();
            System.out.println("========== Menu Nha Cung Cap ==========");
            System.out.println("1. Hien danh sach nha cung cap");
            System.out.println("2. Them nha cung cap moi");
            System.out.println("3. Xoa nha cung cap");
            System.out.println("4. Sua thong tin nha cung cap");
            System.out.println("Nhap x de tro lai");
            System.out.print("Chon thao tac ban muon lam: ");
            option = sc.nextLine().charAt(0);
            switch (option) {
                case '1':
                    XuatNCC();
                    break;
                case '2':
                    themNCCmoi(sc);
                    break;
                case '3':
                    xoaNCCmoi(sc);
                    break;
                case '4':
                    suaNCC(sc);
                    break;
                default:
                    break;
            }

        } while (option != 'x');
    }

    public static void main(String[] args) {
        dsNhaCungCap ds = new dsNhaCungCap();
        Scanner sc = new Scanner(System.in);
        ds.readFile();
        ds.mainMenu(sc);
        ds.writeToFile();
    }

}