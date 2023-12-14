import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dschitietpx {
    private Chitietpx[] lsctpx;
    private int soluong;

    public dschitietpx() {
        lsctpx = new Chitietpx[0];
    }

    public void themCtPx(Chitietpx ctpx) {
        lsctpx = Arrays.copyOf(lsctpx, soluong + 1);
        lsctpx[soluong++] = ctpx;
    }

    public void xoaCtPx(String id) {
        for (int i = 0; i < lsctpx.length; i++) {
            if (lsctpx[i].getId().equals(id)) {
                for (int j = i; j < lsctpx.length - 1; j++)
                    lsctpx[j] = lsctpx[j + 1];
                break;
            }
        }
        lsctpx = Arrays.copyOf(lsctpx, soluong - 1);
        soluong--;
    }

    public void xuatPx() {
        System.out.println("id-dssanpham");
        for (Chitietpx i : lsctpx)
            System.out.println(i);
    }

    public void writeToFile() {
        try {
            FileWriter fs = new FileWriter("chitietpx.txt");
            fs.write("id-dssanpham\n");
            for (Chitietpx i : lsctpx)
                fs.write(i.toString() + '\n');
            fs.close();
        } catch (Exception e) {
        }
    }

    private int readSl() {
        int sl = 0;
        String header = "id-dssanpham";
        Pattern body = Pattern.compile("^ctpx\\d{1,9}-((dt|pk)\\d{1,9},\\d{1,9} )+$");
        Matcher findMatch;
        try {
            BufferedReader fs = new BufferedReader(new FileReader("chitietpx.txt"));
            String currLine = fs.readLine();
            if (!currLine.equals(header)) {
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
                sl++;
            }
            fs.close();
        } catch (Exception e) {
            return -1;
        }
        return sl;
    }

    public Chitietpx timtheovt(int vt) {
        Chitietpx result = lsctpx[vt];
        return result;
    }

    public void readFile(DanhSachSanPham lSanPham) {
        soluong = readSl();
        if (soluong == -1) {
            System.out.println("Khong tim thay file phieu xuat, khoi tao mang co san");
            lsctpx = new Chitietpx[2];
            lsctpx[0] = new Chitietpx("dt1,2 dt2,1 ", lSanPham);
            lsctpx[1] = new Chitietpx("pk1,1 ", lSanPham);
            soluong++;
            return;
        }
        try {
            Scanner sc = new Scanner(new File("chitietpx.txt"));
            lsctpx = new Chitietpx[soluong];
            sc.nextLine();
            for (int i = 0; i < soluong; i++) {
                String curr = sc.nextLine();
                String[] currCTPX = curr.split("-");
                lsctpx[i] = new Chitietpx(currCTPX[0], currCTPX[1], lSanPham);
            }
            Chitietpx.setcountId(Integer.parseInt(lsctpx[lsctpx.length - 1].getId().replace("ctpx", "")));
            sc.close();
        } catch (Exception e) {
        }
    }

    public void tongSoLuongDaMuaVaDoanhThu() {
        int tongSoLuong = 0;
        long doanhthu = 0;
        for (Chitietpx i : lsctpx) {
            tongSoLuong += i.getTongSL();
            doanhthu += i.getTongGia();
        }
        System.out.printf("Tong so luong da ban: %,d\n", tongSoLuong);
        System.out.printf("Tong so tien da ban duoc: %,d vnd\n", doanhthu);
    }

    public String tongSanPhamDaBan() {
        String result = "";
        for (Chitietpx i : lsctpx) {
            result += i.getDanhsachsanpham();
        }
        return result;
    }
}
