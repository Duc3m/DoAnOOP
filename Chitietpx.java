import java.util.Arrays;

/**
 * InnerChitietpx
 */
class dssanpham {
    String idsanpham;
    int soluong;

    dssanpham(String id, int soluong) {
        this.idsanpham = id;
        this.soluong = soluong;
    }
}

public class Chitietpx {
    private static int countId;
    private String id;
    private dssanpham[] danhsachsanpham;
    private long tongGia;
    private int tongSL;
    // private String idphieuxuat;

    public Chitietpx() {
        countId++;
        this.id = "ctpx" + countId;
        this.danhsachsanpham = new dssanpham[0];
    }

    // public Chitietpx(String idphieuxuat) {
    // countId++;
    // id = "ctpx" + countId;
    // this.idphieuxuat = idphieuxuat;

    // }

    public Chitietpx(String lssanpham, DanhSachSanPham lSachSanPham) {
        countId++;
        this.id = "ctpx" + countId;
        String[] stringdssanpham = lssanpham.split("\\s");
        for (String i : stringdssanpham) {
            String[] sanpham = i.split(",");
            themSanPham(sanpham[0], Integer.parseInt(sanpham[1]));
        }
        getThongTinTongGiaVoiTongSoLuong(lSachSanPham);
    }

    public Chitietpx(String id, String lssanpham, DanhSachSanPham lSachSanPham) {
        this.id = id;
        String[] stringdssanpham = lssanpham.split("\\s");
        for (String i : stringdssanpham) {
            String[] sanpham = i.split(",");
            themSanPham(sanpham[0], Integer.parseInt(sanpham[1]));
        }
        getThongTinTongGiaVoiTongSoLuong(lSachSanPham);
    }

    public Chitietpx(String id, String lssanpham) {
        this.id = id;
        String[] stringdssanpham = lssanpham.split("\\s");
        for (String i : stringdssanpham) {
            String[] sanpham = i.split(",");
            themSanPham(sanpham[0], Integer.parseInt(sanpham[1]));
        }
    }

    public void getThongTinTongGiaVoiTongSoLuong(DanhSachSanPham lSanPham) {
        for (int i = 0; i < danhsachsanpham.length; i++) {
            int vt = lSanPham.timSanPhamTheoId(danhsachsanpham[i].idsanpham);
            if (vt == -1)
                continue;
            Sanpham curr = lSanPham.getSanphamtheovt(vt);
            int gia = curr.getGia();
            tongGia += (long) gia * danhsachsanpham[i].soluong;
            tongSL += danhsachsanpham[i].soluong;
        }
    }

    public String hienThongTinHoaDon(DanhSachSanPham lssanpham) {
        String result = String.format("%-60s %-12s %-12s\n", "Ten san pham", "SL", "Gia (vnd)");
        result += "-----------------------------------------------------------------------------------------\n";
        for (int i = 0; i < danhsachsanpham.length; i++) {
            int vt = lssanpham.timSanPhamTheoId(danhsachsanpham[i].idsanpham);
            if (vt == -1)
                continue;
            Sanpham curr = lssanpham.getSanphamtheovt(vt);
            String ten = curr.getTen();
            int gia = curr.getGia();
            tongGia += (long) gia * danhsachsanpham[i].soluong;
            String currLine = String.format("%-60s %-12d %-,12d\n", ten, danhsachsanpham[i].soluong, gia);
            result += currLine;
            tongSL += danhsachsanpham[i].soluong;
        }
        result += "-----------------------------------------------------------------------------------------\n";
        String summarize = String.format("%-60s %-12d %-,12d\n", "Total", tongSL, tongGia);
        result += summarize;
        return result;
    }

    public int timSanPhamTheoId(String id) {
        for (int i = 0; i < danhsachsanpham.length; i++) {
            if (danhsachsanpham[i].idsanpham.equals(id))
                return i;
        }
        return -1;
    }

    public void suaSoLuong(int vt, int soluong) {
        if (soluong == 0) {

            return;
        }
        danhsachsanpham[vt].soluong = soluong;
    }

    public void xoaSanPham(String id) {
        for (int i = 0; i < danhsachsanpham.length; i++) {
            if (danhsachsanpham[i].idsanpham.equals(id)) {
                for (int j = i; j < danhsachsanpham.length - 1; j++)
                    danhsachsanpham[j] = danhsachsanpham[j + 1];
                break;
            }
        }
        danhsachsanpham = Arrays.copyOf(danhsachsanpham, danhsachsanpham.length - 1);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDanhsachsanpham() {
        String result = "";
        for (int i = 0; i < danhsachsanpham.length; i++)
            result += danhsachsanpham[i].idsanpham + "," + danhsachsanpham[i].soluong + " ";
        return result;
    }

    public void themSanPham(String id) {
        int soluong = 1;
        for (dssanpham i : danhsachsanpham) {
            if (i.idsanpham.equals(id)) {
                i.soluong += soluong;
                return;
            }
        }
        danhsachsanpham = Arrays.copyOf(danhsachsanpham, (danhsachsanpham.length + 1));
        danhsachsanpham[danhsachsanpham.length - 1] = new dssanpham(id, soluong);
        // danhsachsanpham[danhsachsanpham.length - 1].idsanpham = id;
        // danhsachsanpham[danhsachsanpham.length - 1].soluong = soluong;
    }

    public void themSanPham(String id, int soluong) {
        if (danhsachsanpham == null) {
            danhsachsanpham = new dssanpham[0];
        }
        for (dssanpham i : danhsachsanpham) {
            if (i.idsanpham.equals(id)) {
                i.soluong += soluong;
                return;
            }
        }
        danhsachsanpham = Arrays.copyOf(danhsachsanpham, (danhsachsanpham.length + 1));
        danhsachsanpham[danhsachsanpham.length - 1] = new dssanpham(id, soluong);
        // danhsachsanpham[danhsachsanpham.length - 1].idsanpham = id;
        // danhsachsanpham[danhsachsanpham.length - 1].soluong = soluong;
    }

    public void setDanhsachsanpham(dssanpham[] danhsachsanpham) {
        this.danhsachsanpham = danhsachsanpham;
    }

    public static void setcountId(int countId) {
        Chitietpx.countId = countId;
    }

    public static int getCountId() {
        return countId;
    }

    public void muaSanPham(DanhSachSanPham lssanpham) {
        for (int i = 0; i < danhsachsanpham.length; i++) {
            lssanpham.muaSanPham(danhsachsanpham[i].idsanpham, danhsachsanpham[i].soluong);
        }
    }

    public void setTongGia(long tongGia) {
        this.tongGia = tongGia;
    }

    public long getTongGia() {
        return tongGia;
    }

    public void setTongSL(int tongSL) {
        this.tongSL = tongSL;
    }

    public int getTongSL() {
        return tongSL;
    }

    @Override
    public String toString() {
        return id + "-" + getDanhsachsanpham();
    }
}
