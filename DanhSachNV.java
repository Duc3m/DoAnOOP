import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
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
        System.out.printf("\n%-5s| %-13s| %-20s| %-11s| %-11s| %-10s| %-13s| %-9s| %-15s| %-10s| %-20s\n",
                "id",
                "CCCD",
                "Ten",
                "Ngay sinh", "SDT", "Gioi tinh", "Luong co ban", "HSL/HSTN", "So gio tang ca", "Chuc vu",
                "Ngay bat dau lam viec");
    }

    public void deleteNewNV(Scanner sc) {
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
        System.out.println("Ban co chan chac muon xoa nhan vien nay? (y/n)");
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
                    String.format("%-5s| %-13s| %-20s| %-11s| %-11s| %-10s| %-13s| %-9s| %-15s| %-10s| %-20s\n", "id",
                            "CCCD",
                            "Ten",
                            "Ngay sinh", "SDT", "Gioi tinh", "Luong co ban", "HSL/HSTN", "So gio tang ca", "Chuc vu",
                            "Ngay bat dau lam viec"));
            for (nhanvien i : dsnv) {
                file.write(i.toString());
            }
            file.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private int readSL() {
        int sl = 0;
        Pattern header = Pattern.compile(
                "^id\\s{3}\\| Ten\\s{12}\\| Ngay sinh \\| SDT\\s{8}\\| Gioi tinh \\| Luong co ban   \\| He so luong    \\| Chuc vu   \\| Ngay bat dau\\s{8}$");
        Pattern body = Pattern.compile(
                "^nv\\d{1,3}\\s{0,2}\\| [a-zA-z ]{1,16}\\s{0,16}\\| \\d{1,2}\\/\\d{1,2}\\/\\d{4}\\s{0,2}\\| 0\\d{9} \\| (F|M)\\s{9}\\| \\d{4,10}\\s{5,11}\\| \\d+.?\\d+\\s{0,12}\\| (nhan vien |quan ly   )\\| \\d{1,2}\\/\\d{1,2}\\/\\d{4}\\s{0,12}$");
        Matcher findmatch;
        try {
            BufferedReader fs = new BufferedReader(new FileReader("nhanvien.txt"));
            String currLine = fs.readLine();
            findmatch = header.matcher(currLine);
            if (!findmatch.find()) {
                fs.close();
                return -1;
            }
            while (currLine != null) {
                currLine = fs.readLine();
                if (currLine == null)
                    break;
                findmatch = body.matcher(currLine);
                if (!findmatch.find()) {
                    fs.close();
                    return -1;
                }
                sl++;
            }
            fs.close();
        } catch (Exception e) {
            sl = -1;
        }
        return sl;
    }

    public void readFile() {
        soluong = readSL();
        if (soluong == -1) {
            System.out.println("khong tim thay file nhanvien hoac file bi loi, khoi tao mang co san");
            dsnv = new nhanvien[2];
            dsnv[1] = new quanly(123123123321L, "quan ly vd",
                    "0902522842", 'F', 20, 10, 2004, 9000000, 1.0, "quan ly", "a1", 2.0);

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

    // System.out.print("Nhap dia chi cua nhan vien: ");
    // String newAddress = sc.nextLine();

    // System.out.print("Nhap so dien thoai cua nhan vien: ");
    // String newSDT = sc.nextLine();

    // System.out.print("Nhap gioi tinh cua nhan vien (M/F): ");
    // char newGender = sc.nextLine().charAt(0);

    // dsnv[i] = new nvbanhang(newCccd, newName, newAddress, newSDT, newGender,
    // newDay, newMonth, newYear, 9000000,
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

        System.out.print("Nhap so gio tang cua nhan vien: ");
        double newSGTC = Double.parseDouble(sc.nextLine());

        nhanvien newNV = new nvbanhang(newCccd, newName, newSDT, newGender, newBirthDay, newBirthMonth,
                newBirthYear,
                9000000,
                1.0, "nhan vien", "", newSGTC);
        addNV(newNV);
        System.out.println("Them nhan vien moi thanh cong!");
    }

    public void mainMenu(Scanner sc) {
        String option;
        do {
            System.out.println();
            System.out.println("========== Menu San Pham ==========");
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
                    deleteNewNV(sc);
                    break;
                case "4":
                    EditNV(sc);
                    break;
                default:
                    break;
            }
        } while (!option.equals("x"));
    }

    // public static void main(String[] args) {
    // DanhSachNV list = new DanhSachNV();
    // Scanner sc = new Scanner(System.in);
    // nhanvien nv1 = new nvbanhang(1231231233, "dead 1",
    // "0902522842", 'F', 20, 10, 2004, 9000000, 1.0, "nhan vien", "", 2.0);
    // nhanvien nv2 = new nvbanhang(1123812233, "dead 2", "0123122842", 'M', 12, 04,
    // 1997, 9000000, 1.0, "nhan vien",
    // "", 2.0);
    // list.addNV(nv1);
    // list.addNV(nv2);
    // // list.input();
    // list.writeToFile();
    // list.mainMenu(sc);
    // }
}
