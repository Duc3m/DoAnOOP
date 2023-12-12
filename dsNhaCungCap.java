import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class dsNhaCungCap {
    private nhacungcap[] dsncc;
    private int soluong;

    public dsNhaCungCap() {
        dsncc = new nhacungcap[0];
    }

    public void setNCC(nhacungcap ncc[]) {
        this.dsncc = ncc;
    }

    public void XuatNCC() {
        System.out.printf("\n%-10s| %-30s| %-15s| %-15s\n", "id", "ten", "diachi", "sodt");
        for (nhacungcap i : dsncc) {
            System.out.print(i);
        }
    }

    public void writeToFile() {
        try {
            FileWriter file = new FileWriter("sanpham.txt");
            file.write(
                    String.format("\n%-10s| %-30s| %-15s| %-15s\n", "id", "ten", "diachi", "sodt"));
            for (nhacungcap i : dsncc) {
                file.write(i.toString());
            }
            file.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void themNCC(nhacungcap ncc) {
        dsncc = Arrays.copyOf(dsncc, soluong + 1);
        dsncc[soluong++] = ncc;
    }

    public void themNCCmoi(Scanner sc) {
        System.out.println();
        System.out.print("Nhap ten nha cung cap moi: ");
        String name = sc.nextLine();

        System.out.print("Nhap dia chi nha cung cap moi: ");
        String address = sc.nextLine();

        System.out.print("Nhap so dien thoai nha cung cap moi: ");
        int number = Integer.parseInt(sc.nextLine());

        nhacungcap newncc = new nhacungcap(name, address, number);
        themNCC(newncc);
        System.out.println("Da them nha cung cap.");

    }

    public int xoaNCC(String id) {
        int index=-1;
        for (int i = 0; i < dsncc.length; i++) {
            if (dsncc[i].getId().equals(id)) {
                index=i;
                for (int j = i; j < dsncc.length - 1; j++)
                    dsncc[j] = dsncc[j + 1];
            }
        }
        return index;
    }

    public void xoaNCCmoi(Scanner sc) {
        System.out.println();
        System.out.print("Nhap id ban muon xoa: ");
        String id = sc.nextLine();

        int index = xoaNCC(id);
        if (index == -1) {
            System.out.println("Khong tim thay nha cung cap can xoa.");
            return;
        } else {
            for (; index < dsncc.length; index++) {
                if (dsncc[index].getId().equals(id)) {
                    for (int j = index; j < dsncc.length - 1; j++)
                        dsncc[j] = dsncc[j + 1];
                }
            }
            dsncc = Arrays.copyOf(dsncc, soluong - 1);
            soluong--;
            System.out.println();
            System.out.println("Da xoa thanh cong.");
        }
    }

    public int timNCCtheoId(String id) {
        for (int i = 0; i < dsncc.length; i++) {
            if (dsncc[i].getId().equals(id))
                return i;
        }
        return -1;
    }

    public void suaNCC(Scanner sc) {
        System.out.println();
        System.out.print("Nhap id ban muon sua: ");
        String id = sc.nextLine();

        int index = timNCCtheoId(id);
        if (index == -1) {
            System.out.println("Khong tim thay nha cung cap can sua.");
            return;
        }

        System.out.printf("\n%-10s| %-30s| %-15s| %-15s\n", "id", "ten", "diachi", "sodt");
        System.out.println(dsncc[index]);
        char option;
        do {
            System.out.println("========== Sua thong tin Nha Cung Cap ==========");
            System.out.println("1. ten");
            System.out.println("2. dia chi");
            System.out.println("3. number");
            System.out.println("Nhap x de tro lai");
            System.out.print("Chon thong tin ban muon sua: ");

            option = sc.nextLine().charAt(0);

            switch (option) {
                case '1':
                    System.out.println();
                    System.out.print("Nhap lai ten nha cung cap moi: ");
                    String newname = sc.nextLine();
                    dsncc[index].setTennhacungcap(newname);
                    System.out.println("Da sua thanh cong.");
                    break;
                case '2':
                    System.out.println();
                    System.out.print("Nhap lai dia chi nha cung cap moi: ");
                    String newaddress = sc.nextLine();
                    dsncc[index].setDiachi(newaddress);
                    System.out.println("Da sua thanh cong.");
                    break;
                case '3':
                    System.out.println();
                    System.out.print("Nhap lai so dien thoai nha cung cap moi: ");
                    int newnumber = Integer.parseInt(sc.nextLine());
                    dsncc[index].setSodt(newnumber);
                    System.out.println("Da sua thanh cong.");
                    break;
                default:
                    break;
            }
        } while (option != 'x');
    }

    public void mainMenu(Scanner sc) {
        char option;
        do {
            System.out.println();
            System.out.println("========== Menu Nha Cung Cap ==========");
            System.out.println("1. Hien danh sach nha cung cap");
            System.out.println("2. Them nha cung cap moi");
            System.out.println("3. Xoa nha cung cap");
            System.out.println("4. Sua thong tin nha cung cap");
            System.out.println("nhap x de tro lai");
            System.out.print("Chon thao tac ban muon lam: ");
            option = sc.nextLine().charAt(0);
            switch (option) {
                case '1':
                    XuatNCC();
                    break;
                case '2':
                    themNCCmoi(sc);
                    break;
                case '3':
                    xoaNCCmoi(sc);
                    break;
                case '4':
                    suaNCC(sc);
                    break;
                default:
                    break;
            }

        } while (option != 'x');
    }

    public static void main(String[] args) {
        dsNhaCungCap ds = new dsNhaCungCap();
        Scanner sc = new Scanner(System.in);
        ds.mainMenu(sc);
    }

}