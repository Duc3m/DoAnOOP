import java.util.Scanner;
//Dit me thang duc ngu nhu cho

public class Main {
    private static account curruser = null;
    private static listaccount lsacc = new listaccount();
    private static DanhSachSanPham lssanpham = new DanhSachSanPham();
    private static dsphieuxuat lsphieuxuat = new dsphieuxuat();
    private static dschitietpx lschitietpx = new dschitietpx();
    private static dsNhaCungCap lsnhacc = new dsNhaCungCap();
    private static dskhachhang lskh = new dskhachhang();

    private static account defaultMenu(Scanner sc, listaccount ls) {
        System.out.println("Vui long dang nhap");
        System.out.println("-----------------------------------------------------------------");
        System.out.print("nhap ten dang nhap: ");
        String tk = sc.next();
        sc.nextLine();
        System.out.print("nhap mat khau: ");
        String mk = sc.next();
        sc.nextLine();
        return ls.checkAccount(tk, mk);
    }

    private static void loginMenu(Scanner sc) {
        String choice = null;
        do {
            System.out.println("-----------------------------------------------------------------");
            System.out.println("Menu cua " + (curruser.getType().equals("QL") ? "Quan ly" : "Nhan vien"));
            System.out.println("-----------------------------------------------------------------");
            System.out.println("Vui long chon thao tac ban muon lam: ");
            System.out.println("1. Lam viec voi tai khoan");
            System.out.println("2. Lam viec voi nhan vien");
            System.out.println("3. Lam viec voi san pham");
            System.out.println("4. Lam viec voi nha cung cap");
            System.out.println("5. Lam viec voi khach hang");
            System.out.println("6. Tinh luong thang nay se nhan");
            System.out.println("x. De dang xuat");
            System.out.print("Thao tac ban muon thuc hien: ");
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    curruser.menuACC(sc, lsacc);
                    break;
                case "2":
                    System.out.println("menu nhan vien");
                    break;
                case "3":
                    lssanpham.mainMenu(sc, curruser, lsphieuxuat, lschitietpx);
                    break;
                case "4":
                    System.out.println("menu khach hang");
                    break;
                case "5":
                    System.out.println("tinh luong");
                    break;
                case "x":
                    System.out.println("da dang xuat");
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
        lsphieuxuat.readFile();
        lschitietpx.readFile();
        while (isrunning) {
            if (curruser == null) {
                String choice;
                do {
                    if (curruser != null)
                        break;
                    System.out.println("chon viec ban muon lam: ");
                    System.out.println("1. Dang nhap");
                    System.out.println("x. de thoat");
                    choice = sc.nextLine();
                    switch (choice) {
                        case "1":
                            curruser = defaultMenu(sc, lsacc);
                            if (curruser == null) {
                                System.out.println("tai khoan hoac mat khau khong dung vui long nhap lai");
                                continue;
                            }
                            break;
                        case "x":
                            isrunning = false;
                            break;
                        default:
                            System.out.println("Khong hop le vui long nhap lai");
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
