import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dsphieuxuat {
    private Phieuxuat[] lspx;
    private int soluong;

    public dsphieuxuat() {
        lspx = new Phieuxuat[0];
    }

    public void themPhieuXuat(Phieuxuat px) {
        lspx = Arrays.copyOf(lspx, soluong + 1);
        lspx[soluong++] = px;
    }

    public void xoaPhieuXuat(String id) {
        for (int i = 0; i < lspx.length; i++) {
            if (lspx[i].getId().equals(id)) {
                for (int j = i; j < lspx.length - 1; j++)
                    lspx[j] = lspx[j + 1];
                break;
            }
        }
        lspx = Arrays.copyOf(lspx, soluong - 1);
        soluong--;
    }

    public int searchId(String id) {
        for (int i = 0; i < lspx.length; i++) {
            if (lspx[i].getId().equals(id))
                return i;
        }
        return -1;
    }

    public void xuatPhieuXuat() {
        System.out.printf("%-15s| %-15s| %-15s| %-15s| %-15s\n", "id", "idkhachang", "idChiTietPx", "ngay ban",
                "idnguoiban");
        for (Phieuxuat i : lspx) {
            System.out.println(i);
        }
    }

    private int readSL() {
        int sl = 0;
        Pattern header = Pattern
                .compile("id\\s{13}\\| idkhachang\\s{5}\\| idChiTietPx\\s{4}\\| ngay ban\\s{7}\\| idnguoiban\\s{5}$");
        Pattern body = Pattern.compile(
                "^px\\d{1,9}\\s{0,12}\\| kh\\d{1,9}\\s{0,12}\\| ctpx\\d{1,9}\\s{0,10}\\| \\d{1,2}\\/\\d{1,2}\\/\\d{4}\\s{5,7}\\| nv\\d{1,9}\\s{0,12}$");
        Matcher findmatch;
        try {
            BufferedReader fs = new BufferedReader(new FileReader("phieuxuat.txt"));
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
            System.out.println("khong tim thay file phieu nhap hoac bi loi bat dau tao mang co san");
            lspx = new Phieuxuat[2];
            lspx[0] = new Phieuxuat("kh1", "ctpx1", 12, 5, 2023, "nv1");
            lspx[1] = new Phieuxuat("kh1", "ctpx2", 10, 12, 2019, "nv1");
            soluong = 2;
            writeToFile();
            return;
        }
        try {
            lspx = new Phieuxuat[soluong];
            Scanner sc = new Scanner(new File("phieuxuat.txt"));
            sc.nextLine(); // bỏ qua header
            for (int i = 0; i < soluong; i++) {
                String id = sc.next();
                sc.next(); // bỎ qua |
                String idkhachang = sc.next();
                sc.next();// bỎ qua |
                String idChitietPx = sc.next();
                sc.next();
                String[] ngayban = new String[3];
                ngayban = sc.next().split("\\/");
                sc.next();
                String idnguoiban = sc.next();
                lspx[i] = new Phieuxuat(id, idkhachang, idChitietPx, Integer.parseInt(ngayban[0]),
                        Integer.parseInt(ngayban[1]), Integer.parseInt(ngayban[2]), idnguoiban);
            }
            Phieuxuat.setcountId(Integer.parseInt(lspx[lspx.length - 1].getId().replace("px", "")));
        } catch (Exception e) {
        }
    }

    public void writeToFile() {
        try {
            FileWriter fs = new FileWriter("phieuxuat.txt");
            fs.write(String.format("%-15s| %-15s| %-15s| %-15s| %-15s\n", "id", "idkhachang", "idChiTietPx", "ngay ban",
                    "idnguoiban"));
            for (Phieuxuat i : lspx) {
                fs.write(i.toString() + "\n");
            }
            fs.close();
        } catch (Exception e) {
        }
    }

    public void themSanPhamVaoHoaDon(Scanner sc, DanhSachSanPham lSanPham, Chitietpx ctpx) {
        System.out.print("Nhap id san pham ban muon them: ");
        String id = sc.nextLine();
        int vt = lSanPham.timSanPhamTheoId(id);
        if (vt == -1) {
            System.out.println("Khong tim thay san pham");
            return;
        }
        Sanpham tempSanpham = lSanPham.getSanphamtheovt(vt);
        int sl;
        do {
            System.out.printf("Nhap so luong san pham: ");
            sl = checkPattern.checkInt(sc);
            if (sl > tempSanpham.getSoLuong())
                System.out.println("khong du hang ton kho vui long nhap lai");
        } while (sl > tempSanpham.getSoLuong());
        if (sl == 0)
            return;
        ctpx.themSanPham(id, sl);
        System.out.println("Them thanh cong");
    }

    public void suaSLSanPham(Scanner sc, Chitietpx chitietpx) {
        System.out.print("ID ban muon sua: ");
        String id = sc.nextLine();
        int vt = chitietpx.timSanPhamTheoId(id);
        if (vt == -1) {
            System.out.println("khong co san pham trong hoa don");
            return;
        }
        System.out.print("nhap so luong muon thay doi: ");
        int sl = checkPattern.checkInt(sc);
        chitietpx.suaSoLuong(vt, sl);
    }

    public void xoaSanPhamKhoiHoaDon(Scanner sc, Chitietpx chitietpx) {
        System.out.print("ID ban muon sua: ");
        String id = sc.nextLine();
        int vt = chitietpx.timSanPhamTheoId(id);
        if (vt == -1) {
            System.out.println("khong co san pham trong hoa don");
            return;
        }
        chitietpx.xoaSanPham(id);
        System.out.println("Xoa thanh cong");
    }

    public void hienthongtinsanpham(Scanner sc, Phieuxuat px, Chitietpx ctpx, DanhSachSanPham lSanPham) {
        System.out.println("Nguoi mua: " + px.getIdkhachhang());
        System.out.println("Ngay mua hang: " + px.getNgayBan());
        System.out.println(ctpx.hienThongTinHoaDon(lSanPham));
        System.out.println("Bam nut bat ky de thoat....");
        sc.nextLine();
    }

    public void hienThongTinHoaDonBH(Scanner sc, Phieuxuat px, Chitietpx ctpx, DanhSachSanPham lSanPham) {
        System.out.println("Nguoi mua: " + px.getIdkhachhang());
        System.out.println("Ngay mua hang: " + px.getNgayBan());
        System.out.println(ctpx.hienThongTinHoaDonBH(lSanPham));
        System.out.println("Bam nut bat ky de thoat....");
        sc.nextLine();
    }

    public void inHoaDon(Phieuxuat px, Chitietpx ctpx, DanhSachSanPham lSanPham) {
        try {
            FileWriter fs = new FileWriter("hoadon.txt");
            fs.write(String.format("%-46s %s\n", " ", "Hoa Don"));
            fs.write("Ma Hoa Don: " + px.getId() + '\n');
            fs.write("Nguoi mua: " + px.getIdkhachhang() + '\n');
            fs.write("Ngay mua hang: " + px.getNgayBan() + '\n');
            fs.write(ctpx.hienThongTinHoaDon(lSanPham));
            fs.close();
        } catch (Exception e) {
        }
    }

    public Phieuxuat timtheovt(int vt) {
        Phieuxuat result = lspx[vt];
        return result;
    }

    public void menupx(Scanner sc, DanhSachSanPham lSanPham, dschitietpx lschitietpx, dsBaohanh lDsBaohanh) {
        String option;
        Phieuxuat currPx = new Phieuxuat();
        Chitietpx currCTPX = new Chitietpx();
        currPx.setChitietsanpham(currCTPX.getId());
        do {
            System.out.println();
            System.out.println("========== Menu Don Hang ==========");
            System.out.println("1. Hien thong tin don hang hien tai");
            System.out.println("2. Them san pham vao don hang");
            System.out.println("3. Xoa san pham khoi don hang");
            System.out.println("4. Thay doi so luong cua san pham");
            System.out.println("5. Hien danh sach san pham");
            System.out.println("6. Hien danh sach khach hang");
            System.out.println("7. Chon khach hang mua san pham");
            System.out.println("8. Them khach hang moi");
            System.out.println("n. huy don hang");
            System.out.println("y. xac nhan don hang");
            System.out.print("Nhap lua chon cua ban: ");
            option = sc.nextLine();
            switch (option) {
                case "1":
                    hienthongtinsanpham(sc, currPx, currCTPX, lSanPham);
                    break;
                case "2":
                    themSanPhamVaoHoaDon(sc, lSanPham, currCTPX);
                    break;
                case "3":
                    xoaSanPhamKhoiHoaDon(sc, currCTPX);
                    break;
                case "4":
                    suaSLSanPham(sc, currCTPX);
                    break;
                case "5":
                    lSanPham.xuatSanPham(sc);
                    break;
                case "6":
                    System.out.println("Hien danh sach khach hang");
                    break;
                case "7":
                    System.out.println("chon khach hang mua san pham(id)");
                    break;
                case "8":
                    System.out.println("them khach hang moi");
                    break;
                case "n":
                    currCTPX = null;
                    currPx = null;
                    Chitietpx.setcountId((Chitietpx.getCountId() - 1));
                    Phieuxuat.setcountId((Phieuxuat.getcountId() - 1));
                    System.out.println("Huy don hang thanh cong");
                    break;
                case "y":
                    if (currCTPX.getDanhsachsanpham().equals("")) {
                        System.out.println("Hoa don khong the bo trong san pham");
                        option = "skip";
                        break;
                    }
                    themPhieuXuat(currPx);
                    lschitietpx.themCtPx(currCTPX);
                    writeToFile();
                    lschitietpx.writeToFile();
                    currCTPX.muaSanPham(lSanPham);
                    lSanPham.writeToFile();
                    System.out.print("Nhap thoi gian bao hanh: ");
                    int tgbh = checkPattern.checkInt(sc);
                    lDsBaohanh.addBH(new Baohanh(currPx.getId(), tgbh));
                    lDsBaohanh.writeToFile();
                    System.out.println("Don hang thanh cong bat dau in hoa don");
                    inHoaDon(currPx, currCTPX, lSanPham);
                    break;
                default:
                    System.out.println("khong hop le vui long nhap lai");
                    break;
            }
        } while (!option.equals("y") && !option.equals("n"));

    }

    // public static void main(String[] args) {
    // dsphieuxuat ls = new dsphieuxuat();
    // // Phieuxuat a = new Phieuxuat("kh1", "ctpx1", 12, 5, 2023, "nv1");
    // // Phieuxuat b = new Phieuxuat("kh1", "ctpx2", 10, 12, 2019, "nv1");
    // // ls.themPhieuXuat(a);
    // // ls.themPhieuXuat(b);
    // ls.readFile();
    // ls.xuatPhieuXuat();
    // ls.writeToFile();
    // }
}
