public class nhacungcap {
    private static int countId;
    private String id;
    private String tennhacungcap;
    private String diachi;
    private String sodt;

    public nhacungcap(String tennhacungcap, String diachi, String sodt) {
        countId++;
        id = "ncc" + countId;
        this.tennhacungcap = tennhacungcap;
        this.diachi = diachi;
        this.sodt = sodt;
    }

    public nhacungcap(String id, String tennhacungcap, String diachi, String sodt) {
        this.tennhacungcap = tennhacungcap;
        this.diachi = diachi;
        this.id = id;
        this.sodt = sodt;
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

    public String getSodt() {
        return this.sodt;
    }

    public void setSodt(String sodt) {
        this.sodt = sodt;
    }

    public String toString() {
        return String.format("%-10s| %-30s| %-15s| %-15s\n", id, tennhacungcap, diachi, sodt);
    }

}