import java.util.Arrays;
import java.util.Scanner;

public class dsBaohanh {
    private Baohanh[] lsbaBaohanhs;
    private int soluong;

    public dsBaohanh()  {
        lsbaBaohanhs = new Baohanh[0];
    }

    public void addBH(Baohanh bh) {
        lsbaBaohanhs = Arrays.copyOf(lsbaBaohanhs, soluong+1);
        lsbaBaohanhs[soluong++] = bh; 
    }

    public void deleteBH( String id){
        for (int i = 0; i< lsbaBaohanhs.length; i++){
            if (lsbaBaohanhs[i].getId().equals(id)) {
                for ( int j = i; j < lsbaBaohanhs.length - 1; j++) 
                    lsbaBaohanhs[j] = lsbaBaohanhs[j + 1];
                break;
            }
        }
        lsbaBaohanhs = Arrays.copyOf(lsbaBaohanhs, soluong-1);
        soluong--;
    }

    public int searchId(String id) {
        for (int i = 0; i < lsbaBaohanhs.length; i++) {
            if (lsbaBaohanhs[i].getId().equals(id))
                return i;
        }
        return -1;
    }

    public void xuatBH(){
        System.out.printf("%-15s| %-15s| %-15s| %-15s| %-15s\n", "id", "Spbaohanh", "ngaybatdau", "thoigian",
        "idnguoimua");
        for (Baohanh i : lsbaBaohanhs) {
            System.out.print(i);
        }
    }
    public static void main(String[] args) {
    dsBaohanh ls = new dsBaohanh();
    Baohanh a = new Baohanh("123","Iphone 16", 12, 12, 5, 2023, "kh1");
    Baohanh b = new Baohanh("124","Airpod 4", 24, 13, 5, 2023, "kh2");
    ls.addBH(a);
    ls.addBH(b);
    ls.xuatBH();
    // ls.writeToFile();
    }
}   
