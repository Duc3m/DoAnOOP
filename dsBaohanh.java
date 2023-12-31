import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Flow.Subscriber;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dsBaohanh {
    private Baohanh[] lsbaBaohanhs;
    private int soluong;

    public dsBaohanh() {
        lsbaBaohanhs = new Baohanh[0];
    }

    public void addBH(Baohanh bh) {
        lsbaBaohanhs = Arrays.copyOf(lsbaBaohanhs, soluong + 1);
        lsbaBaohanhs[soluong++] = bh;
    }

    public void deleteBH(String id) {
        for (int i = 0; i < lsbaBaohanhs.length; i++) {
            if (lsbaBaohanhs[i].getId().equals(id)) {
                for (int j = i; j < lsbaBaohanhs.length - 1; j++)
                    lsbaBaohanhs[j] = lsbaBaohanhs[j + 1];
                break;
            }
        }
        lsbaBaohanhs = Arrays.copyOf(lsbaBaohanhs, soluong - 1);
        soluong--;
    }

    public int searchId(String id) {
        for (int i = 0; i < lsbaBaohanhs.length; i++) {
            if (lsbaBaohanhs[i].getId().equals(id))
                return i;
        }
        return -1;
    }

    public void xuatBH() {
        System.out.printf("%-15s| %-15s| %-15s\n", "id", "Phieu Xuat", "Thoi Gian BH");
        for (Baohanh i : lsbaBaohanhs) {
            System.out.print(i);
        }
    }

    public void writeToFile() {
        try {
            FileWriter fs = new FileWriter("baohanh.txt");
            fs.write(String.format("%-15s| %-15s| %-15s\n", "id", "Phieu Xuat", "Thoi Gian BH"));
            for (Baohanh i : lsbaBaohanhs)
                fs.write(i.toString());
            fs.close();
        } catch (Exception e) {
        }
    }

    private int readSL() {
        int soLuong = 0;
        Pattern header = Pattern.compile("id\\s{13}\\| Phieu Xuat\\s{5}\\| Thoi Gian BH\\s{3}$");
        Pattern body = Pattern.compile("bh\\d{1,9}\\s{0,12}\\| px\\d{1,9}\\s{12}\\| \\d{1,9}\\s{0,13}$");
        Matcher findMatch;
        try {
            BufferedReader fs = new BufferedReader(new FileReader("baohanh.txt"));
            String currLine = fs.readLine();
            findMatch = header.matcher(currLine);
            if (!findMatch.find()) {
                fs.close();
                return -1;
            }
            while (currLine != null) {
                currLine = fs.readLine();
                if (currLine == null)
                    break;
                findMatch = body.matcher(currLine);
                if (!findMatch.find()) {
                    fs.close();
                    return -1;
                }
                soLuong++;
            }
            fs.close();

        } catch (Exception e) {
            return -1;
        }
        return soLuong;
    }

    public void readFile() {
        soluong = readSL();
        if (soluong == -1) {
            System.out.println("Khong tim thay file bao hanh hoac file bi loi khoi tao mang co san");
            lsbaBaohanhs = new Baohanh[2];
            lsbaBaohanhs[0] = new Baohanh("px1", 12);
            lsbaBaohanhs[1] = new Baohanh("px2", 12);
            soluong = 2;
            writeToFile();
            return;
        }
        try {
            lsbaBaohanhs = new Baohanh[soluong];
            Scanner sc = new Scanner(new File("baohanh.txt"));
            sc.nextLine();
            for (int i = 0; i < soluong; i++) {
                String id = sc.next();
                sc.next();
                String idpx = sc.next();
                sc.next();
                int thoigianbaohanh = Integer.parseInt(sc.next());
                lsbaBaohanhs[i] = new Baohanh(id, idpx, thoigianbaohanh);
            }
            Baohanh.setcountId(Integer.parseInt(lsbaBaohanhs[lsbaBaohanhs.length - 1].getId().replace("bh", "")));
        } catch (Exception e) {
        }
    }

    public void ChiTietBH(Scanner sc, Baohanh bh, Phieuxuat px, Chitietpx ctpx, dsphieuxuat lDsphieuxuat,
            DanhSachSanPham lDanhSachSanPham) {
        System.out.println("Ma bao hanh: " + bh.getId());
        System.out.println("Thoi han bao hanh: " + bh.getThoiGianBH());
        lDsphieuxuat.hienThongTinHoaDonBH(sc, px, ctpx, lDanhSachSanPham);
        ;
    }

    public void menu(Scanner sc, dschitietpx lDschitietpx, dsphieuxuat lDsphieuxuat, DanhSachSanPham lDanhSachSanPham) {
        String option;
        do {
            System.out.println();
            System.out.println("========== Menu Don Hang ==========");
            System.out.println("1. Hien danh sach Bao Hanh hien tai");
            System.out.println("2. Hien thong tin chi tiet bao hanh");
            System.out.println("x. de thoat");
            option = sc.nextLine();
            switch (option) {
                case "1":
                    xuatBH();
                    break;
                case "2":
                    System.out.print("nhap id bao hanh muon xem chi tiet: ");
                    String id = sc.nextLine();
                    int vt = searchId(id);
                    if (vt == -1) {
                        System.out.println("Khong tim thay id");
                        break;
                    }
                    Baohanh bh = lsbaBaohanhs[vt];
                    int vtpx = lDsphieuxuat.searchId(bh.getIdPhieuXuat());
                    if (vtpx == -1) {
                        System.out.println("khong tim thay thong tin");
                        return;
                    }
                    Phieuxuat tempPx = lDsphieuxuat.timtheovt(vtpx);
                    int vtctpx = lDschitietpx.timTheoID(tempPx.getChitietsanpham());
                    if (vtctpx == -1) {
                        System.out.println("khong tim thay thong tin");
                        return;
                    }
                    Chitietpx ctpx = lDschitietpx.timtheovt(vtctpx);
                    ChiTietBH(sc, bh, tempPx, ctpx, lDsphieuxuat, lDanhSachSanPham);
                    break;
                default:
                    break;
            }
        } while (!option.equals("x"));
    }

}
