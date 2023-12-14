import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class account implements permission {
    private static int countId;
    private String id;
    private String userName;
    private String password;
    private String type;
    private Date ngaytao;
    private static Pattern idPattern;

    public account() {
        countId++;
        id = "a" + countId;
        userName = "nhanvien" + id;
        password = "123456";
        type = "NV";
        ngaytao = new Date();
        this.ngaytao.setYear(this.ngaytao.getYear() + 1900);
        account.idPattern = Pattern.compile("a[0-9]{1,5}");
    }

    public account(String id, String userName, String password, String type, int ngay, int thang, int nam) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.type = type;
        ngaytao = new Date(nam, thang, ngay);
        account.idPattern = Pattern.compile("a[0-9]{1,5}");
    }

    public account(String userName, String password, String type, int ngay, int thang, int nam) {
        countId++;
        id = "a" + countId;
        ngaytao = new Date(nam, thang, ngay);
        this.userName = userName;
        this.password = password;
        this.type = type;
        account.idPattern = Pattern.compile("a[0-9]{1,5}");
    }

    public static void setcountId(int countId) {
        account.countId = countId;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNgaytao() {
        return this.ngaytao.getDate() + "/" + this.ngaytao.getMonth() + "/"
                + this.ngaytao.getYear();
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public boolean CheckInfo(String userName, String password) {
        if (!this.userName.equals(userName) || !this.password.equals(password))
            return false;
        return true;
    }

    // public String xoataikhoan(String userName, String password) {
    // return id;
    // }

    public void showInfo() {
        System.out.println("UserName: " + userName);
        System.out.println("Loai tai khoan: " + (type.equals("QL") ? "quan ly" : "nhan vien"));
        System.out.println("Ngay tao: " + this.ngaytao.getDate() + "/" +
                this.ngaytao.getMonth() + "/"
                + this.ngaytao.getYear());
    }

    public void menuACC(Scanner sc, listaccount ls) {
        String choise = null;
        do {
            System.out.println();
            showInfo();
            System.out.println();
            System.out.println("========== Menu Account ==========");
            System.out.println("1. Neu ban muon doi mat khau");
            if (type.equals("QL")) {
                System.out.println("2. De xoa tai khoan");
                System.out.println("3. De them tai khoan moi");
                System.out.println("4. Hien danh sach tai khoan");
            }
            System.out.println("x. De tro lai");
            System.out.print("Chon thao tac ban muon lam: ");
            choise = sc.nextLine();
            switch (choise) {
                case "1":
                    System.out.print("Nhap mat khau cu: ");
                    String prevPass = sc.nextLine();
                    if (!password.equals(prevPass)) {
                        System.out.println("Khong dung mat khau vui long nhap lai");
                        break;
                    }
                    String newPassword = null;
                    String confirmPassword = null;
                    do {
                        System.out.print("Nhap mat khau moi: ");
                        newPassword = sc.next();
                        sc.nextLine();
                        System.out.print("Nhap lai mat khau moi: ");
                        confirmPassword = sc.next();
                        sc.nextLine();
                        if (!newPassword.equals(confirmPassword))
                            System.out.println("Vui long nhap ca hai giong nhau");
                        if (newPassword.length() >= 30 || newPassword.length() <= 4)
                            System.out.println("Mat khau can chi tu 4-30 ky tu");
                    } while (!newPassword.equals(confirmPassword) || newPassword.length() >= 30
                            || newPassword.length() <= 4);
                    setPassword(newPassword);
                    ls.writeToFile();
                    break;
                case "2":
                    if (!xoataikhoan()) {
                        System.out.println("Khong hop le vui long nhap lai");
                        break;
                    }
                    do {
                        System.out.println("Nhap id Tai khoan ban muon xoa: ");
                        String delAcc = sc.nextLine();
                        Matcher findMach = account.idPattern.matcher(delAcc);
                        if (!findMach.find()) {
                            System.out.println("Khong dung cu phap id (id la a + so) vui long nhap lai");
                            continue;
                        }
                        if (!ls.searchId(delAcc)) {
                            System.out.println("Khong tim thay tai khoan vui long nhap lai");
                            continue;
                        }
                        if (ls.searchType(delAcc).equals("QL")) {
                            System.out.println("Khong the xoa tai khoan quan ly");
                            break;
                        }
                        System.out.println("Ban that su muon xoa tai khoan " + delAcc + " ?");
                        System.out.println("Nhap y de xoa, nhap x de huy");
                        char confirm;
                        do {
                            confirm = sc.nextLine().charAt(0);
                            if (confirm == 'y') {
                                ls.xoaTaiKhoan(delAcc);
                                ls.writeToFile();
                                break;
                            }
                        } while (confirm != 'x');
                        break;
                    } while (true);
                    break;
                case "3":
                    if (!themtaikhoan()) {
                        System.out.println("Khong hop le vui long nhap lai");
                        break;
                    }
                    System.out.println("Khoi tao tai khoan nhan vien moi mac dinh");
                    account newacc = new account();
                    ls.themTaiKhoan(newacc);
                    System.out.println("Thong tin tai khoan moi: ");
                    newacc.showInfo();
                    System.out.println("Password: " + newacc.getPassword());
                    System.out.print("Bam nut bat ky de tiep tuc....");
                    sc.nextLine();
                    System.out.println("-----------------------------------------------------------------");
                    break;
                case "4":
                    if (!hientaikhoan()) {
                        System.out.println("khong hop le vui long nhap lai");
                        break;
                    }
                    ls.hienTatCaAcc();
                    System.out.print("Bam nut bat ky de tiep tuc...");
                    sc.nextLine();
                    System.out.println("-----------------------------------------------------------------");
                    break;
                default:
                    System.out.println("khong hop le vui long nhap lai");
                    break;
            }
        } while (!choise.equals("x"));
    }

    public String toString() {
        return String.format("%-10s| %-30s| %-30s| %-10s| %-15s\n", id, userName, password, type, getNgaytao());
    }

    public boolean xoataikhoan() {
        if (type.equals("QL"))
            return true;
        return false;
    }

    public boolean themtaikhoan() {
        if (type.equals("QL"))
            return true;
        return false;
    }

    public boolean hientaikhoan() {
        if (type.equals("QL"))
            return true;
        return false;
    }

    public boolean xoaSanPham() {
        if (type.equals("QL"))
            return true;
        return false;
    }

    public boolean themSanPham() {
        if (type.equals("QL"))
            return true;
        return false;
    }

    public boolean suaSanPham() {
        if (type.equals("QL"))
            return true;
        return false;
    }

    public boolean themNhanVien() {
        if (type.equals("QL"))
            return true;
        return false;
    }

    public boolean xoaNhanVien() {
        if (type.equals("QL"))
            return true;
        return false;
    }

    public boolean suaNhanvien() {
        if (type.equals("QL"))
            return true;
        return false;
    }
}
