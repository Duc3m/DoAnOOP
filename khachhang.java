
public class khachhang extends mortals {
    private static int countId;
    private String id;

    public khachhang() {
        super();
    }

    public khachhang(long cccd, String name, int ngay, int thang, int nam, String phonenumber,
            char gender) {
        super(cccd, name, phonenumber, gender, ngay, thang, nam);
        countId++;
        id = "kh" + countId;

    }

    public khachhang(String id, long cccd, String name, int ngay, int thang, int nam,
            String phonenumber,
            char gender) {
        super(cccd, name, phonenumber, gender, ngay, thang, nam);
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public static void setCountId(int countId) {
        khachhang.countId = countId;
    }

    @Override
    public String toString() {
        return String.format("%-5s| %-15s| %-15s| %-10s| %-11s| %-10s\n", id, cccd, name,
                getBirthdayNEW(), phonenumber, gender);
    }
}
