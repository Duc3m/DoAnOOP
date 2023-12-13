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

    public Chitietpx(String lssanpham) {
        countId++;
        this.id = "ctpx" + countId;
        String[] stringdssanpham = lssanpham.split("\\s");
        for (String i : stringdssanpham) {
            String[] sanpham = i.split(",");
            themSanPham(sanpham[0], Integer.parseInt(sanpham[1]));
        }
    }

    public Chitietpx(String id, String lssanpham) {
        this.id = id;
        String[] stringdssanpham = lssanpham.split("\\s");
        for (String i : stringdssanpham) {
            String[] sanpham = i.split(",");
            themSanPham(sanpham[0], Integer.parseInt(sanpham[1]));
        }
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

    @Override
    public String toString() {
        return id + "-" + getDanhsachsanpham();
    }
}