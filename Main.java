import java.util.Scanner;

public class Main {
    private static account curruser = null;
    private static listaccount lsacc = new listaccount();
    private static DanhSachSanPham lssanpham = new DanhSachSanPham();
    private static dsphieuxuat lsphieuxuat = new dsphieuxuat();
    private static dschitietpx lschitietpx = new dschitietpx();
    private static DanhSachNV lsnhanvien = new DanhSachNV();
    private static dsNhaCungCap lsnhacc = new dsNhaCungCap();
    private static dskhachhang lskh = new dskhachhang();

    private static account defaultMenu(Scanner sc, listaccount ls) {
        System.out.println("\n========== Dang nhap ==========");
        System.out.print("Nhap ten dang nhap: ");
        String tk = sc.next();
        sc.nextLine();
        System.out.print("Nhap mat khau: ");
        String mk = sc.next();
        sc.nextLine();
        return ls.checkAccount(tk, mk);
    }

    private static void thongKeMenu(Scanner sc) {
        String choice = null;
        do {
            System.out.println("\n========== Thong Ke ==========");
            lschitietpx.tongSoLuongDaMuaVaDoanhThu();
            System.out.println("1. Thong ke theo san pham");
            System.out.println("x. De thoat");
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    Chitietpx newCtpx = new Chitietpx("", lschitietpx.tongSanPhamDaBan());
                    System.out.println(newCtpx.hienThongTinHoaDon(lssanpham));
                    System.out.println("Nhap nut bat ky de tiep tuc...");
                    sc.nextLine();
                    newCtpx = null;
                    break;

                default:
                    System.out.println("Khong hop le vui long nhap lai");
                    break;
            }
        } while (!choice.equals("x"));
    }

    private static void loginMenu(Scanner sc) {
        String choice = null;
        do {
            System.out.println();
            System.out.println("========== Menu cua " + (curruser.getType().equals("QL") ? "Quan ly " : "Nhan vien ")
                    + "==========");
            System.out.println("1. Lam viec voi tai khoan");
            System.out.println("2. Lam viec voi nhan vien");
            System.out.println("3. Lam viec voi san pham");
            System.out.println("4. Lam viec voi khach hang");
            System.out.println("5. Lam viec voi thong ke");
            System.out.println("x. De dang xuat");
            System.out.print("Thao tac ban muon thuc hien: ");
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    curruser.menuACC(sc, lsacc);
                    break;
                case "2":
                    lsnhanvien.mainMenu(sc, curruser);
                    break;
                case "3":
                    lssanpham.mainMenu(sc, curruser, lsphieuxuat, lschitietpx);
                    break;
                case "4":
                    lskh.mainMenu(sc);
                    break;
                case "5":
                    thongKeMenu(sc);
                    break;
                case "x":
                    System.out.println("Da dang xuat");
                    break;
                default:
                    System.out.println("khong hop le vui long nhap lai");
                    break;
            }
        } while (!choice.equals("x"));
        Main.curruser = null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isrunning = true;
        lsacc.readFile();
        lssanpham.readFile();
        lsnhanvien.readFile();
        lsphieuxuat.readFile();
        lschitietpx.readFile(lssanpham);
        lskh.readFile();
        while (isrunning) {
            if (curruser == null) {
                String choice;
                do {
                    if (curruser != null)
                        break;
                    System.out.println("\n1. Dang nhap");
                    System.out.println("x. De thoat");
                    System.out.print("Chon viec ban muon lam: ");
                    choice = sc.nextLine();
                    switch (choice) {
                        case "1":
                            curruser = defaultMenu(sc, lsacc);
                            if (curruser == null) {
                                System.out.println("Tai khoan hoac mat khau khong dung");
                                continue;
                            }
                            break;
                        case "x":
                            isrunning = false;
                            break;
                        default:
                            System.out.println("\nKhong hop le vui long nhap lai");
                            break;
                    }
                } while (!choice.equals("x"));
                if (!isrunning)
                    break;
            }
            if (curruser != null) {
                loginMenu(sc);
            }
        }
        sc.close();
    }
}
