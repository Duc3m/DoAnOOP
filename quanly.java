
public class quanly extends nhanvien {
    private double hesotrachnhiem;

    public quanly() {
        super();
    }

    public quanly(String id, long cccd, String name, String phonenumber, char gender, int ngaysinh,
            int thangsinh, int namsinh, int luongcb, double hesoluong, String chucvu, String accid,
            double hesotrachnhiem) {
        super(id, cccd, name, phonenumber, gender, ngaysinh, thangsinh, namsinh, luongcb, hesoluong, chucvu, accid);
        setChucvu("quan ly");
        this.hesotrachnhiem = hesotrachnhiem;
    }

    public quanly(long cccd, String name, String phonenumber, char gender, int ngaysinh,
            int thangsinh, int namsinh, int luongcb, double hesoluong, String chucvu, String accid,
            double hesotrachnhiem) {
        super(cccd, name, phonenumber, gender, ngaysinh, thangsinh, namsinh, luongcb, hesoluong, chucvu, accid);
        setChucvu("quan ly");
        this.hesotrachnhiem = hesotrachnhiem;
    }

    public double getHesotrachnhiem() {
        return this.hesotrachnhiem;
    }

    public void setHesotrachnhiem(double hesotrachnhiem) {
        this.hesotrachnhiem = hesotrachnhiem;
    }

    @Override
    public int tinhluong() {
        return (int) (hesotrachnhiem + hesoluong) * luongcb;
    }

    // public long tinhdoanhthu() {
    // }

    public void shownhanvien() {
    }

    // public void nhaphang() {

    // }

}
