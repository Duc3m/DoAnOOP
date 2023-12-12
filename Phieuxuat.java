
import java.util.Date;

public class Phieuxuat {
    private static int countId;
    private String id;
    private String idkhachhang;
    private String chitietsanpham; // tham chiếu tới chi tiết phiếu xuất
    private Date ngayban;
    private String idnguoiban;

    public Phieuxuat() {
        countId++;
        this.id = "px" + countId;
        this.idkhachhang = "";
        this.chitietsanpham = "";
        this.ngayban = new Date();
        this.ngayban.setTime(this.ngayban.getYear() + 1900);
        this.idnguoiban = "";
    }

    public Phieuxuat(String idkhachhang, String chitietsanpham, int ngay, int thang, int nam, String idnguoiban) {
        countId++;
        id = "px" + countId;
        this.idkhachhang = idkhachhang;
        this.chitietsanpham = chitietsanpham;
        this.ngayban = new Date(nam, (thang - 1), ngay);
        this.idnguoiban = idnguoiban;
    }

    public Phieuxuat(String id, String idkhachhang, String chitietsanpham, int ngay, int thang, int nam,
            String idnguoiban) {
        this.id = id;
        this.idkhachhang = idkhachhang;
        this.chitietsanpham = chitietsanpham;
        this.ngayban = new Date(nam, (thang - 1), ngay);
        this.idnguoiban = idnguoiban;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdkhachhang() {
        return this.idkhachhang;
    }

    public void setIdkhachhang(String idkhachhang) {
        this.idkhachhang = idkhachhang;
    }

    public String getChitietsanpham() {
        return this.chitietsanpham;
    }

    public void setChitietsanpham(String chitietsanpham) {
        this.chitietsanpham = chitietsanpham;
    }

    public Date getNgayban() {
        return this.ngayban;
    }

    public void setNgayban(Date ngayban) {
        this.ngayban = ngayban;
    }

    public String getIdnguoiban() {
        return this.idnguoiban;
    }

    public void setIdnguoiban(String idnguoiban) {
        this.idnguoiban = idnguoiban;
    }

    public static void setcountId(int countid) {
        Phieuxuat.countId = countid;
    }

    public static int getcountId() {
        return countId;
    }

    public String getNgayBan() {
        return ngayban.getDate() + "/" + (ngayban.getMonth() + 1) + "/" + ngayban.getYear();
    }

    public String toString() {
        return String.format("%-15s| %-15s| %-15s| %-15s| %-15s", id, idkhachhang, chitietsanpham, getNgayBan(),
                idnguoiban);
    }
}