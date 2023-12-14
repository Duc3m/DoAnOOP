
public class Baohanh {
    public static int countId;
    private String id;
    private String idphieuxuat;
    private int thoigianbaohanh;

    public Baohanh() {
        countId++;
        this.id = "bh" + countId;
        idphieuxuat = "";
        thoigianbaohanh = 0;
    }

    public Baohanh(String idphieuxuat, int thoigianbaohanh) {
        countId++;
        id = "bh" + countId;
        this.idphieuxuat = idphieuxuat;
        this.thoigianbaohanh = thoigianbaohanh;
    }

    public Baohanh(String id, String idphieuxuat, int thoigianbaohanh) {
        this.id = id;
        this.idphieuxuat = idphieuxuat;
        this.thoigianbaohanh = thoigianbaohanh;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPhieuXuat() {
        return idphieuxuat;
    }

    public void setIdPhieuXuat(String id) {
        this.id = id;
    }

    public int getThoiGianBH() {
        return thoigianbaohanh;
    }

    public void setThoiGianBH(int thoigianbaohanh) {
        this.thoigianbaohanh = thoigianbaohanh;
    }

    public static int getCountId() {
        return Baohanh.countId;
    }

    public static void setcountId(int countId) {
        Baohanh.countId = countId;
    }

    @Override
    public String toString() {
        return String.format("%-15s| %-15s| %-15d\n", id, idphieuxuat, thoigianbaohanh);
    }

    // public void input(Scanner sc){
    // System.out.printf("Nhap id bao hanh: ");
    // id = sc.nextLine();
    // System.out.printf("\nNhap vao san pham can bao hanh ");
    // spbaohanh = sc.nextLine();
    // System.out.printf("\nNhap vao id nguoi mua hang ");
    // idnguoimua = sc.nextLine();

    // System.out.print("\nNhap vao thoi gian bao hanh ");

    // }
}
