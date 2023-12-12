public class nhacungcap {
    private static int countId;
    private String id;
    private String tennhacungcap;
    private String diachi;
    private int sodt;

    public nhacungcap(String tennhacungcap, String diachi,int sodt) {
        countId++;
        id = "ncc" + countId;
        this.tennhacungcap = tennhacungcap;
        this.diachi = diachi;
        this.sodt=sodt;
    }

    public nhacungcap(String id, String tennhacungcap, String diachi,int sodt) {
        this.tennhacungcap = tennhacungcap;
        this.diachi = diachi;
        this.id = id;
        this.sodt=sodt;
    }

    public String getId() {
        return this.id;
    }

    public String getTennhacungcap() {
        return this.tennhacungcap;
    }

    public void setTennhacungcap(String tennhacungcap) {
        this.tennhacungcap = tennhacungcap;
    }

    public String getDiachi() {
        return this.diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getSodt() {
        return this.sodt;
    }

    public void setSodt(int sodt) {
        this.sodt=sodt;
    }

    public String toString() {
        return String.format("\n%-10s| %-30s| %-15s| %-15d\n", id, tennhacungcap, diachi,sodt);
    }

}