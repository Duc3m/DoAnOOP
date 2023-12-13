public class nvbanhang extends nhanvien {
    public double sogiotangca;

    public nvbanhang() {
        super();
    }

    // public nvbanhang(String id, int luongcb, double hesoluong, String chucvu, int
    // ngay, int thang, int nam,
    // String accid,
    // double sogiotangca) {
    // super(id, luongcb, hesoluong, chucvu, ngay, thang, nam, accid);
    // this.sogiotangca = sogiotangca;
    // }

    public nvbanhang(long cccd, String name, String phonenumber, char gender, int ngay, int thang,
            int nam, int luongcb, double hesoluong, String chucvu, String accid,
            double sogiotangca) {
        super(cccd, name, phonenumber, gender, ngay, thang, nam, 9000000, 1, chucvu, "");
        setChucvu("nhan vien");
        this.sogiotangca = sogiotangca;
    }

    public nvbanhang(long cccd, String name, String phonenumber, char gender, int ngay, int thang,
            int nam, int luongcb, double hesoluong, String chucvu, String accid,
            double sogiotangca, int ngaylv, int thanglv, int namlv) {
        super(cccd, name, phonenumber, gender, ngay, thang, nam, 9000000, 1, chucvu, "", ngaylv, thanglv, namlv);
        this.sogiotangca = sogiotangca;
    }

    public nvbanhang(String id, long cccd, String name, String phonenumber, char gender, int ngay, int thang,
            int nam, int luongcb, double hesoluong, String chucvu, String accid,
            double sogiotangca, int ngaylv, int thanglv, int namlv) {
        super(cccd, name, phonenumber, gender, ngay, thang, nam, 9000000, 1, chucvu, "", ngaylv, thanglv, namlv);
        this.id = id;
        this.sogiotangca = sogiotangca;
    }

    public double getSogiotangca() {
        return this.sogiotangca;
    }

    public void setSogiotangca(double sogiotangca) {
        this.sogiotangca = sogiotangca;
    }

    @Override
    public int tinhluong() {
        return (int) ((sogiotangca * (luongcb / 30 / 24) * 1.5) + luongcb);
    }

    public void shownhanvien() {
        System.out.println("Ten: " + name);
        System.out.println("tuoi: " + getAge());
        System.out.println("Gioi tinh: " + gender);
        // System.out.println();
    }

    @Override
    public String toString() {
        return String.format("%-5s| %-13s| %-20s| %-11s| %-11s| %-10s| %-13s| %-12s| %-10s| %-10s| %-10s\n", id, cccd,
                name,
                getNewBirthday(), phonenumber, gender, luongcb, hesoluong, sogiotangca, chucvu,
                getStartedDate());
    }
}
