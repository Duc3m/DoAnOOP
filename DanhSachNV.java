import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DanhSachNV {
    private nhanvien[] dsnv;
    private int soluong;

    public DanhSachNV() {
        dsnv = new nhanvien[0];
    }

    public void addNV(nhanvien newNV) {
        dsnv = Arrays.copyOf(dsnv, soluong + 1);
        dsnv[soluong++] = newNV;
    }

    public int getNVId(String id) {
        int index = -1;
        for (int i = 0; i < dsnv.length; i++) {
            if (dsnv[i].getId().equals(id))
                index = i;

        }
        return index;
    }

    public void headerPrint() {
        System.out.printf("\n%-5s| %-13s| %-20s| %-11s| %-11s| %-10s| %-13s| %-12s| %-10s| %-10s| %-10s\n",
                "id",
                "CCCD",
                "Ten",
                "Ngay sinh", "SDT", "Gioi tinh", "Luong co ban", "He so luong", "SGTC/HSTN", "Chuc vu",
                "Ngay BDLV");
    }

    public void deleteNV(Scanner sc) {
        if (dsnv.length <= 0) {
            System.out.println("khong ton tai nhan vien de xoa");
            return;
        }
        System.out.print("Nhap ID cua nhan vien can xoa: ");
        String newID = checkPattern.checkID(sc, dsnv[0]);
        int index = getNVId(newID);
        if (index == -1) {
            System.out.println("\nKhong tim thay nhan vien can xoa!");
            return;
        }
        System.out.print("Ban co chan chac muon xoa nhan vien nay? (y/n): ");
        String confirm = sc.nextLine();
        if (confirm.equals("yes") || confirm.equals("Yes") || confirm.equals("Y") || confirm.equals("y")) {
            for (; index < dsnv.length; index++) {
                if (dsnv[index].getId().equals(newID)) {
                    for (int j = index; j < dsnv.length - 1; j++)
                        dsnv[j] = dsnv[j + 1];
                }
            }
            dsnv = Arrays.copyOf(dsnv, soluong - 1);
            soluong--;
            System.out.println("Xoa nhan vien thanh cong!");
        }
    }

    public void EditNV(Scanner sc) {
        if (dsnv.length <= 0) {
            System.out.println("khong ton tai nhan vien de sua");
            return;
        }
        System.out.print("Nhap ID cua nhan vien ban can sua: ");
        String id = checkPattern.checkID(sc, dsnv[0]);

        int index = getNVId(id);

        if (index == -1) {
            System.out.println("\nKhong tim thay nhan vien");
            return;
        }
        headerPrint();
        System.out.println(dsnv[index]);

        String option;
        do {
            System.out.println();
            System.out.println("========== Sua thong tin nhan vien ==========");
            System.out.println("1. Ten");
            System.out.println("2. Ngay sinh");
            // System.out.println("3. Dia chi");
            System.out.println("3. So dien thoai");
            System.out.println("4. Gioi tinh");
            System.out.println("5. Luong co ban");
            System.out.println("6. He so luong");
            System.out.println("Chon x de tro lai");
            System.out.print("Chon thong tin ban muon sua: ");
            option = sc.nextLine();

            switch (option) {
                case "1":
                    System.out.println();
                    System.out.print("Nhap ten moi cua nhan vien: ");
                    String newName = sc.nextLine();
                    dsnv[index].setName(newName);
                    System.out.println("Sua ten nhan vien thanh cong");
                    headerPrint();
                    System.out.println(dsnv[index]);
                    break;
                case "2":
                    System.out.println();
                    System.out.print("Nhap ngay sinh moi cua nhan vien: ");
                    int newDay = checkPattern.checkNgayThang(sc);
                    System.out.print("Nhap thang sinh moi cua nhan vien: ");
                    int newMonth = checkPattern.checkNgayThang(sc);
                    System.out.print("Nhap nam sinh moi cua nhan vien: ");
                    int newYear = checkPattern.checkNam(sc);
                    dsnv[index].setBirthday(newYear, newMonth, newDay);
                    System.out.println("Sua ngay sinh nhan vien thanh cong!");
                    headerPrint();
                    System.out.println(dsnv[index]);
                    break;
                // case "3":
                // System.out.println();
                // System.out.print("Nhap dia chi moi cua nhan vien: ");
                // String newAddress = sc.nextLine();
                // dsnv[index].setAddress(newAddress);
                // System.out.println("Sua so luong san pham thanh cong");
                // System.out.printf("\n%-5s| %-15s| %-10s| %-11s| %-10s| %-15s| %-15s|
                // %-10s| %-20s\n", "id",
                // "Ten",
                // "Ngay sinh","SDT", "Gioi tinh", "Luong co ban", "He so luong",
                // "Chuc vu",
                // "Ngay bat dau lam viec");
                // System.out.println(dsnv[index]);
                // break;
                case "3":
                    System.out.println();
                    System.out.print("Nhap so dien thoai moi cua nhan vien: ");
                    String newSDT = checkPattern.checkSdt(sc);
                    dsnv[index].setPhonenumber(newSDT);
                    System.out.println("Sua so dien thoai cua nhan vien thanh cong");
                    headerPrint();
                    System.out.println(dsnv[index]);
                    break;
                case "4":
                    System.out.println();
                    System.out.println("Nhap gioi tinh cua nhan vien: ");
                    char newGender = checkPattern.checkgt(sc);
                    dsnv[index].setGender(newGender);
                    System.out.println("Sua gioi tinh cua nhan vien thanh cong");
                    headerPrint();
                    System.out.println(dsnv[index]);
                    break;
                case "5":
                    System.out.println();
                    System.out.println("Nhap luong co ban moi cua nhan vien: ");
                    int newLuongcb = checkPattern.checkInt(sc);
                    dsnv[index].setLuongcb(newLuongcb);
                    System.out.println("Sua luong co ban cua nhan vien thanh cong");
                    headerPrint();
                    System.out.println(dsnv[index]);
                    break;
                case "6":
                    System.out.println();
                    System.out.println("Nhap he so luong moi cua nhan vien: ");
                    double newHesoluong = checkPattern.checkDouble(sc);
                    dsnv[index].setHesoluong(newHesoluong);
                    System.out.println("Sua he so luong cua nhan vien thanh cong");
                    headerPrint();
                    System.out.println(dsnv[index]);
                    break;
                // case "7":
                // System.out.println();
                // System.out.println("Nhap chuc vu moi cua nhan vien: ");
                // String newChucvu = sc.nextLine();
                // dsnv[index].setChucvu(newChucvu);
                // System.out.println("Sua chuc vu cua nhan vien thanh cong");
                // System.out.printf("\n%-5s| %-15s| %-10s| %-11s| %-10s| %-15s| %-15s| %-10s|
                // %-20s\n", "id",
                // "Ten",
                // "Ngay sinh", "SDT", "Gioi tinh", "Luong co ban", "He so luong", "Chuc vu",
                // "Ngay bat dau lam viec");
                // System.out.println(dsnv[index]);
                // break;
                default:
                    break;
            }
        } while (!option.equals("x"));
    }

    public void xuatNhanVien() {
        headerPrint();
        for (nhanvien i : dsnv) {
            System.out.print(i);
        }
    }

    public void writeToFile() {
        try {
            FileWriter file = new FileWriter("nhanvien.txt");
            file.write(
                    String.format("%-5s| %-13s| %-20s| %-11s| %-11s| %-10s| %-13s| %-12s| %-10s| %-10s| %-10s\n", "id",
                            "CCCD",
                            "Ten",
                            "Ngay sinh", "SDT", "Gioi tinh", "Luong co ban", "He so luong", "SGTC/HSTN", "Chuc vu",
                            "Ngay BDLV"));
            for (nhanvien i : dsnv) {
                file.write(i.toString());
            }
            file.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    // public void input() {
    // Scanner sc = new Scanner(System.in);
    // System.out.println("nhap so nhan vien can them: ");
    // soluong = Integer.parseInt(sc.nextLine());
    // dsnv = new nhanvien[soluong];
    // for (int i = 0; i < soluong; i++) {
    // System.out.println("====================");
    // System.out.println("Nhan vien thu " + (i + 1));
    // System.out.print("Nhap ten nhan vien: ");
    // String newName = sc.nextLine();

    // System.out.print("Nhap so CCCD/CMND cua nhan vien: ");
    // long newCccd = sc.nextLong();
    // sc.nextLine();

    // System.out.print("Nhap ngay sinh cua nhan vien: ");
    // int newDay = Integer.parseInt(sc.nextLine());
    // int newMonth = Integer.parseInt(sc.nextLine());
    // int newYear = Integer.parseInt(sc.nextLine());

    // System.out.print("Nhap so dien thoai cua nhan vien: ");
    // String newSDT = sc.nextLine();

    // System.out.print("Nhap gioi tinh cua nhan vien (M/F): ");
    // char newGender = sc.nextLine().charAt(0);

    // dsnv[i] = new nvbanhang(newCccd, newName, newSDT, newGender, newDay,
    // newMonth,
    // newYear,
    // 9000000,
    // 1.0, "nhan vien", "", 2.0);
    // }
    // sc.close();
    // }

    public void addNewNV(Scanner sc) {

        System.out.print("Nhap ten nhan vien: ");
        String newName = sc.nextLine();

        System.out.print("Nhap so CCCD/CMND cua nhan vien: ");
        long newCccd = checkPattern.checkcccd(sc);

        System.out.print("Nhap ngay sinh cua nhan vien: ");
        int newBirthDay = checkPattern.checkNgayThang(sc);
        System.out.print("Nhap thang sinh cua nhan vien: ");
        int newBirthMonth = checkPattern.checkNgayThang(sc);
        System.out.print("Nhap nam sinh cua nhan vien: ");
        int newBirthYear = checkPattern.checkNam(sc);

        System.out.print("Nhap so dien thoai cua nhan vien: ");
        String newSDT = checkPattern.checkSdt(sc);

        System.out.print("Nhap gioi tinh cua nhan vien (M/F): ");
        char newGender = checkPattern.checkgt(sc);

        System.out.print("Nhap so gio tang ca (he so trach nhiem) cua nhan vien (quan ly): ");
        double newSGTC = Double.parseDouble(sc.nextLine());

        nhanvien newNV = new nvbanhang(newCccd, newName, newSDT, newGender, newBirthDay, newBirthMonth,
                newBirthYear,
                9000000,
                1.0, "nhan vien", "", newSGTC);
        addNV(newNV);
        System.out.println("Them nhan vien moi thanh cong!");
    }

    private int readSl() {
        int sl = 0;
        Pattern header = Pattern.compile(
                "id\\s{3}\\| CCCD\\s{9}\\| Ten\\s{17}\\| Ngay sinh\\s{2}\\| SDT\\s{8}\\| Gioi tinh \\| Luong co ban\\s{1}\\| He so luong\\s{1}\\| SGTC\\/HSTN\\s{1}\\| Chuc vu\\s{3}\\| Ngay BDLV\\s{1}$");
        Pattern body = Pattern.compile(
                "^nv\\d{1,3}\\s{0,2}\\| \\d{12} \\|[a-zA-Z ]{1,20}\\s{0,20}\\| \\d{1,2}\\/\\d{1,2}\\/\\d{4}\\s{0,3}\\| 0\\d{9} \\| (F|M|f|m)\\s{9}\\| \\d{4,10}\\s{5,11}\\| \\d+.?\\d+\\s{0,12}\\| \\d+.?\\d+\\s{0,9}\\| (nhan vien | quan ly   )\\| \\d{1,2}\\/\\d{1,2}\\/\\d{4}\\s{0,12}$");
        Matcher findmatch;
        try {
            BufferedReader fs = new BufferedReader(new FileReader("nhanvien.txt"));
            String currline = fs.readLine();
            findmatch = header.matcher(currline);
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

    public void readFile() {
        this.soluong = readSl();
        if (soluong == -1) {
            System.out.println("Khong tim thay file hoac file bi loi! Bat dau khoi tao danh sach co san.");
            dsnv = new nhanvien[4];
            dsnv[0] = new nvbanhang(231231232333L, "Tran Dai Van", "0902522842", 'M', 20, 10, 2004, 9000000, 1.0,
                    "nhan vien", "",
                    4.0, 12, 7, 2021);
            dsnv[1] = new nvbanhang(123231233312L, "Nguyen Dai Nghia", "0902512332", 'M', 12, 4, 1999, 9000000, 1.0,
                    "nhan vien", "",
                    1.0, 3, 5, 2022);
            dsnv[2] = new nvbanhang(121233123312L, "Truong Minh Thu", "0123312325", 'F', 5, 9, 1999, 9000000, 1.0,
                    "nhan vien", "",
                    2.0, 17, 1, 2021);
            dsnv[3] = new nvbanhang(231232323312L, "Tran Duc Em", "0123123225", 'M', 28, 2, 2000, 9000000, 1.0,
                    "nhan vien", "",
                    5.0, 2, 12, 2022);
            soluong = 4;
            writeToFile();
            return;
        }
        try {
            dsnv = new nhanvien[soluong];
            Scanner sc = new Scanner(new File("nhanvien.txt"));
            sc.nextLine();
            for (int i = 0; i < soluong; i++) {
                String id = sc.next();
                sc.next(); // bo qua |
                String cccd = sc.next();
                sc.next(); // bo qua |
                String name = "";
                while (sc.hasNext()) {
                    String currLine = sc.next();
                    if (currLine.equals("|")) {
                        break;
                    }
                    name += currLine + " ";
                }
                String ngaysinh[] = new String[3];
                ngaysinh = sc.next().split("\\/");
                sc.next(); // bo qua |
                String sdt = sc.next();
                sc.next(); // bo qua |
                char gender = sc.next().charAt(0);
                sc.next(); // bo qua |
                sc.next(); // bo qua luong co ban
                sc.next(); // bo qua |
                String heSoLuong = sc.next();
                sc.next(); // bo qua |
                String sgtc_hstn = sc.next();
                sc.next(); // bo qua |
                sc.next(); // bo qua nhan
                sc.next(); // bo qua vien
                sc.next(); // bo qua |
                String NgayBDLV[] = new String[3];
                NgayBDLV = sc.next().split("\\/");
                dsnv[i] = new nvbanhang(id, Long.parseLong(cccd), name, sdt, gender, Integer.parseInt(ngaysinh[0]),
                        Integer.parseInt(ngaysinh[1]), Integer.parseInt(ngaysinh[2]), 9000000,
                        Double.parseDouble(heSoLuong), "nhan vien",
                        "", Double.parseDouble(sgtc_hstn), Integer.parseInt(NgayBDLV[0]), Integer.parseInt(NgayBDLV[1]),
                        Integer.parseInt(NgayBDLV[2]));
            }
        } catch (Exception e) {
        }
    }

    public void mainMenu(Scanner sc) {
        String option;
        do {
            System.out.println();
            System.out.println("========== Menu Nhan Vien ==========");
            System.out.println("1. Hien danh sach nhan vien hien tai");
            System.out.println("2. Them nhan vien moi");
            System.out.println("3. Xoa nhan vien");
            System.out.println("4. Sua thong tin nhan vien");
            System.out.println("Nhap x de tro lai");
            System.out.print("Chon thao tac ban muon lam: ");
            option = sc.nextLine();

            switch (option) {
                case "1":
                    xuatNhanVien();
                    break;
                case "2":
                    System.out.print("Nhap so luong nhan vien can them: ");
                    int n = checkPattern.checkInt(sc);
                    for (int i = 0; i < n; i++) {
                        System.out.println("==========================================");
                        System.out.println("Nhan vien thu " + (i + 1));
                        addNewNV(sc);
                    }
                    break;
                case "3":
                    deleteNV(sc);
                    break;
                case "4":
                    EditNV(sc);
                    break;
                default:
                    break;
            }
        } while (!option.equals("x"));
    }

    public static void main(String[] args) {
    DanhSachNV list = new DanhSachNV();
    Scanner sc = new Scanner(System.in);
    // list.readFile();
    list.mainMenu(sc);
    // list.xuatNhanVien();
    // list.writeToFile();
    }
}
