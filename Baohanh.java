import java.util.Scanner;
import java.util.Date;

public class Baohanh {
    public static int countId;
    private String id;
    private String spbaohanh;
    private Date ngaybatdau;
    private int thoigian;
    private String idnguoimua; 

    public Baohanh(){
        countId++;
        this.id = "bh" + countId;
        this.spbaohanh = "";
        this.thoigian = 0;
        this.idnguoimua = "";
        ngaybatdau.setYear(ngaybatdau.getYear() + 1900);
    }

    public Baohanh(String spbaohanh, int thoigian, String idnguoimua, int ngay, int thang, int nam){
        countId++;
        this.id = "bh" + countId;
        this.spbaohanh = spbaohanh;
        this.thoigian = thoigian;
        ngaybatdau = new Date(ngay, thang, nam);
    }

    public Baohanh(String id, String spbaohanh, int thoigian, String idnguoimua, int ngay, int thang, int nam){
        this.id = id;
        this.spbaohanh = spbaohanh;
        this.thoigian = thoigian;
        ngaybatdau = new Date(ngay, thang, nam);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpbaohanh() {
        return this.spbaohanh;
    }

    public void setSpbaohanh(String spbaohanh) {
        this.spbaohanh = spbaohanh;
    }

    public Date getNgaybatdau() {
        return this.ngaybatdau;
    }

    public void setNgaybatdau(Date ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public int getThoigian() {
        return this.thoigian;
    }

    public void setThoigian(int thoigian) {
        this.thoigian = thoigian;
    }

    public String getIdnguoimua() {
        return this.idnguoimua;
    }

    public void setIdnguoimua(String idnguoimua) {
        this.idnguoimua = idnguoimua;
    }

    // public void input(Scanner sc){
    //     System.out.printf("Nhap id bao hanh: ");
    //     id = sc.nextLine();
    //     System.out.printf("\nNhap vao san pham can bao hanh ");
    //     spbaohanh = sc.nextLine();
    //     System.out.printf("\nNhap vao id nguoi mua hang ");
    //     idnguoimua = sc.nextLine();
        
    //     System.out.print("\nNhap vao thoi gian bao hanh ");
    

    // }
}