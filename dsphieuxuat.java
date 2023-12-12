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

    public void menupx(Scanner sc) {
        String option;
        do {
            System.out.println();
            System.out.println("========== Menu Don Hang ==========");
            System.out.println("1.Hien thong tin don hang hien tai");
            System.out.println("2. Them san pham vao don hang");
            System.out.println("3. Xoa san pham khoi don hang");
            System.out.println("4. Thay doi so luong cua san pham");
            System.out.println("5. Hien danh sach san pham");
            System.out.println("6. Hien danh sach khach hang");
            System.out.println("7. Chon khach hang mua san pham");
            System.out.println("8. Them khach hang moi");
            System.out.println("n. huy don hang");
            System.out.println("y. xac nhan don hang");
            option = sc.nextLine();
            switch (option) {
                case "1":
                    System.out.println("hien thong tin");
                    break;
                case "2":
                    System.out.println("them san pham");
                    break;
                case "3":
                    System.out.println("xoa san pham");
                    break;
                case "4":
                    System.out.println("thay doi so luong");
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
                    System.out.println("huy don hang thanh cong");
                    break;
                case "y":
                    System.out.println("don hang thanh cong bat dau in hoa don");
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
