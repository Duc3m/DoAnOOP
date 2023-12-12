import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DanhSachSanPham {
    private Sanpham sanphamList[];
    private int soLoai;

    public DanhSachSanPham() {
        sanphamList = new Sanpham[0];
    }

    public int getSoLoai() {
        return this.soLoai;
    }

    public void setSoLoai(int soLoai) {
        this.soLoai = soLoai;
    }

    public void setSanPhamList(Sanpham tempSanphams[]) {
        this.sanphamList = tempSanphams;
    }

    public void xuatSanPham() {
        System.out.printf("%-10s| %-30s| %-10s| %-10s| %-15s| %-30s\n", "id", "ten", "loai", "gia", "so luong",
                "nhacungcapId");
        for (Sanpham i : sanphamList) {
            System.out.print(i);
        }
    }

    private int readSl() {
        int sl = 0;
        Pattern header = Pattern.compile(
                "id\\s{8}\\| ten\\s{27}\\| loai\\s{6}\\| gia\\s{7}\\| so luong\\s{7}\\| nhacungcapId\\s{3}$");

        Pattern body = Pattern.compile(
                "^(pk|dt)\\d{1,9}\\s{0,8}\\| [a-zA-z0-9-_ ]{0,30}\\| (phukien\\s{3}|dienthoai\\s{1})\\| \\d{4,10}\\s{0,6}\\| \\d{1,9}\\s{0,15}\\| ncc\\d{1,9}\\s{0,11}$");
        Matcher findmatch;
        try {
            BufferedReader fs = new BufferedReader(new FileReader("sanpham.txt"));
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
        this.soLoai = readSl();
        if (soLoai == -1) {
            System.out.println("Khong tim thay file hoac file bi loi, bat dau khoi tao mang co san");
            sanphamList = new Sanpham[4];
            sanphamList[0] = new Dienthoai("Iphone 15 pro max", 34990000, 1000, "ncc1");
            sanphamList[1] = new Dienthoai("Samsumg Galaxy S23 ultra", 31990000, 1000, "ncc2");
            sanphamList[2] = new Phukien("AirPods Pro 2 2023 USB-C", 6190000, 1000, "ncc1");
            sanphamList[3] = new Phukien("Cu sac Samsung Type-C 45W", 1190000, 1000, "ncc2");
            soLoai = 4;
            writeToFile();
            return;
        }
        try {
            sanphamList = new Sanpham[soLoai];
            Scanner sc = new Scanner(new File("sanpham.txt"));
            sc.nextLine(); // bỏ qua heading
            for (int i = 0; i < soLoai; i++) {
                String id = sc.next();
                sc.next(); // bỏ qua "|"
                String name = "";
                while (sc.hasNext()) {
                    String currLine = sc.next();
                    if (currLine.equals("|")) {
                        break;
                    }
                    name += currLine + " ";
                }
                String loai = sc.next();
                sc.next(); // bỏ qua "|"
                String gia = sc.next();
                sc.next(); // bỏ qua "|"
                String soluong = sc.next();
                sc.next(); // bỏ qua "|"
                String nhacungcap = sc.next();
                if (loai.equals("dienthoai")) {
                    sanphamList[i] = new Dienthoai(id, name, Integer.parseInt(gia), Integer.parseInt(soluong),
                            nhacungcap);
                    int currCountId = Integer.parseInt(sanphamList[i].getId().replace("dt", ""));
                    if (Dienthoai.getcountId() < currCountId)
                        Dienthoai.setcountId(currCountId);
                }
                if (loai.equals("phukien")) {
                    sanphamList[i] = new Phukien(id, name, Integer.parseInt(gia), Integer.parseInt(soluong),
                            nhacungcap);
                    int currCountId = Integer.parseInt(sanphamList[i].getId().replace("pk", ""));
                    if (Phukien.getcountId() < currCountId)
                        Phukien.setcountId(currCountId);
                }
            }
        } catch (Exception e) {
        }
    }

    public void writeToFile() {
        try {
            FileWriter file = new FileWriter("sanpham.txt");
            file.write(
                    String.format("%-10s| %-30s| %-10s| %-10s| %-15s| %-15s\n", "id", "ten", "loai", "gia",
                            "so luong",
                            "nhacungcapId"));
            for (Sanpham i : sanphamList) {
                file.write(i.toString());
            }
            file.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    // public void input() {
    // Scanner sc = new Scanner(System.in);
    // System.out.println("nhap so Loai san pham: ");
    // soLoai = Integer.parseInt(sc.nextLine());
    // sanphamList = new Sanpham[soLoai];
    // for (int i = 0; i < soLoai; i++) {
    // System.out.println("san pham thu " + (i + 1));
    // System.out.print("nhap ten: ");
    // String name = sc.nextLine();
    // // System.out.print("nhap loai: ");
    // // String loai = sc.nextLine();
    // System.out.print("nhap gia: ");
    // int gia = Integer.parseInt(sc.nextLine());
    // System.out.println("nhap so luong: ");
    // int soLuong = Integer.parseInt(sc.nextLine());
    // System.out.println("nhap id nha cung cap: ");
    // String nhacungcapId = sc.nextLine();
    // System.out.println();
    // sanphamList[i] = new Dienthoai(name, gia, soLuong, nhacungcapId);
    // }
    // sc.close();
    // }

    public void themSanPham(Sanpham sp) {
        sanphamList = Arrays.copyOf(sanphamList, soLoai + 1);
        sanphamList[soLoai++] = sp;
    }

    public void themSanPhamMoi(Scanner sc) {
        System.out.println();
        System.out.print("Nhap ten san pham moi: ");
        String name = sc.nextLine();

        System.out.print("Nhap gia san pham moi: ");
        int price = checkPattern.checkInt(sc);

        System.out.print("Nhap so luong san pham moi: ");
        int amount = checkPattern.checkInt(sc);

        System.out.print("Nhap nha cung cap san pham moi: ");
        String ncc = sc.nextLine();

        System.out.println("Chon loai: ");
        System.out.println("1. Dien thoai");
        System.out.println("2. Phu kien");
        char typeChoice = sc.nextLine().charAt(0);
        switch (typeChoice) {
            case '1':
                Dienthoai newSp1 = new Dienthoai(name, price, amount, ncc);
                themSanPham(newSp1);
                System.out.println("San pham da duoc them thanh cong");
                break;
            case '2':
                Phukien newSp2 = new Phukien(name, price, amount, ncc);
                themSanPham(newSp2);
                System.out.println("San pham da duoc them thanh cong");
                break;
            default:
                break;
        }
    }

    public void xoaSanPham(String id) {
        for (int i = 0; i < soLoai; i++)
            if (sanphamList[i].getId().equals(id))
                for (int j = i; i < sanphamList.length - 1; j++)
                    sanphamList[i] = sanphamList[j];
        soLoai--;
    }

    public int timSanPhamTheoId(String id) {
        // System.out.println(sanphamList[0]);
        for (int i = 0; i < soLoai; i++) {
            if (sanphamList[i].getId().equals(id))
                return i;
        }
        return -1;
    }

    public Sanpham[] timSanPhamTheoTen(String ten) {
        Sanpham result[] = null;
        int index = 0;
        for (Sanpham i : sanphamList) {
            if (i.getTen().equals(ten)) {
                result = Arrays.copyOf(result, index++);
            }
        }
        return result;
    }

    public void suaSanPham(Scanner sc) {
        System.out.println();
        System.out.print("Nhap id ban muon sua: ");
        String id = sc.next();
        sc.nextLine();

        int vitri = timSanPhamTheoId(id);

        if (vitri == -1) {
            System.out.println("Khong tim thay san pham");
            return;
        }
        System.out.printf("\n%-10s| %-30s| %-10s| %-10s| %-15s| %-30s\n", "id", "ten", "loai", "gia", "so luong",
                "nhacungcapId");
        System.out.println(sanphamList[vitri]);

        char option;
        do {
            System.out.println();
            System.out.println("========== Sua thong tin san pham ==========");
            System.out.println("1. Ten");
            System.out.println("2. Gia");
            System.out.println("3. So luong");
            System.out.println("4. Nha cung cap");
            System.out.println("Nhap x de tro lai");
            System.out.print("Chon thong tin ban muon sua: ");
            option = sc.nextLine().charAt(0);

            switch (option) {
                case '1':
                    System.out.println();
                    System.out.print("Nhap ten moi: ");
                    String newName = sc.nextLine();
                    sanphamList[vitri].setTen(newName);
                    System.out.println("Sua ten san pham thanh cong");
                    break;
                case '2':
                    System.out.println();
                    System.out.print("Nhap gia moi: ");
                    int newPrice = checkPattern.checkInt(sc);
                    sanphamList[vitri].setGia(newPrice);
                    System.out.println("Sua gia san pham thanh cong");
                    break;
                case '3':
                    System.out.println();
                    System.out.print("Nhap so luong moi: ");
                    int newAmount = checkPattern.checkInt(sc);
                    sanphamList[vitri].setGia(newAmount);
                    System.out.println("Sua so luong san pham thanh cong");
                    break;
                case '4':
                    System.out.println();
                    System.out.print("Nhap nha cung cap moi: ");
                    String newNcc = sc.nextLine();
                    sanphamList[vitri].setNhacungcap(newNcc);
                    System.out.println("Sua nha cung cap san pham thanh cong");
                    break;
                default:
                    break;
            }
        } while (option != 'x');
        writeToFile();
    }

    // public String menuDonHang(Scanner sc, String currPx) {
    // String option;
    // Phieunhap PN;
    // Chitietpn ctpn;
    // do {
    // System.out.println();
    // System.out.println("========== Menu Don Hang ==========");
    // if (currPx.equals(null)) {
    // System.out.println("tao 1 don hang moi");
    // PN = new Phieunhap();
    // currPx = PN.getId();
    // }
    // System.out.println("x. de thoat");
    // option = sc.nextLine();
    // } while (!option.equals("x"));
    // return currPx;
    // }

    public void mainMenu(Scanner sc, account curruser) {
        String option;
        // String currPX = null;
        do {
            System.out.println();
            System.out.println("========== Menu San Pham ==========");
            System.out.println("1. Hien danh sach san pham hien tai");
            System.out.println("2. Thao tac voi don hang");
            if (curruser.getType().equals("QL")) {
                System.out.println("3. Them san pham moi");
                System.out.println("4. Xoa san pham");
                System.out.println("5. Sua thong tin san pham");
            }
            System.out.println("Nhap x de tro lai");
            System.out.print("Chon thao tac ban muon lam: ");
            option = sc.nextLine();

            switch (option) {
                case "1":
                    xuatSanPham();
                    break;
                case "2":
                    // currPX = menuDonHang(sc, currPX);
                    break;
                case "3":
                    if (!curruser.themSanPham()) {
                        System.out.println("khong hop le vui long nhap lai");
                        break;
                    }
                    themSanPhamMoi(sc);
                    break;
                case "4":
                    if (!curruser.xoaSanPham()) {
                        System.out.println("khong hop le vui long nhap lai");
                        break;
                    }
                    break;
                case "5":
                    if (!curruser.suaSanPham()) {
                        System.out.println("khong hop le vui long nhap lai");
                        break;
                    }
                    suaSanPham(sc);
                    break;
                default:
                    System.out.println("khong hop le vui long nhap lai");
                    break;
            }
        } while (!option.equals("x"));
    }

    // public static void main(String[] args) {
    // DanhSachSanPham ls = new DanhSachSanPham();
    // ls.readFile();
    // ls.xuatSanPham();
    // }
}
