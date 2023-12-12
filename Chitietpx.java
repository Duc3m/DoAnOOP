/**
 * InnerChitietpx
 */
// check
// check2
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
    private String idphieuxuat;
    private int soluong;

    public Chitietpx() {
        countId++;
        this.id = "ctpx" + countId;
        this.danhsachsanpham = null;
        this.idphieuxuat = "";
        this.soluong = 0;
    }

    public Chitietpx(String idphieuxuat, int soluong) {
        countId++;
        id = "ctpx" + countId;
        this.idphieuxuat = idphieuxuat;
        this.soluong = soluong;
    }

    public Chitietpx(String id, dssanpham[] danhsachsanpham, String idphieuxuat, int soluong) {
        this.id = id;
        this.danhsachsanpham = danhsachsanpham;
        this.idphieuxuat = idphieuxuat;
        this.soluong = soluong;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public dssanpham[] getDanhsachsanpham() {
        return this.danhsachsanpham;
    }

    public void setDanhsachsanpham(dssanpham[] danhsachsanpham) {
        this.danhsachsanpham = danhsachsanpham;
    }

    public String getIdphieuxuat() {
        return this.idphieuxuat;
    }

    public void setIdphieuxuat(String idphieuxuat) {
        this.idphieuxuat = idphieuxuat;
    }

    public int getSoluong() {
        return this.soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

}