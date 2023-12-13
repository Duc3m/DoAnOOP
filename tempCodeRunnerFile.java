public int readSl() {
    //     int sl = 0;
    //     Pattern header = Pattern.compile(null);
    //     Pattern body = Pattern.compile(null);
    //     Matcher findmatch;
    //     try {
    //         BufferedReader fs = new BufferedReader(new FileReader("nhacungcap.txt"));
    //         String currline = fs.readLine();
    //         findmatch = header.matcher((currline));
    //         if (!findmatch.find()) {
    //             fs.close();
    //             return -1;
    //         }
    //         while (currline != null) {
    //             currline = fs.readLine();
    //             if (currline == null)
    //                 break;
    //             findmatch = body.matcher(currline);

    //             if (!findmatch.find()) {
    //                 System.out.println(sl);
    //                 fs.close();
    //                 return -1;
    //             }
    //             sl++;
    //         }
    //         fs.close();
    //     } catch (Exception e) {
    //         return -1;
    //     }
    //     return sl;
    // }

    // public void writeToFile() {
    //     try {
    //         FileWriter file = new FileWriter("nhacungcap.txt");
    //         file.write(
    //                 String.format("\n%-10s| %-30s| %-15s| %-15s\n", "Id", "Ten", "Diachi", "Sodt"));
    //         for (nhacungcap i : dsncc) {
    //             file.write(i.toString());
    //         }
    //         file.close();
    //     } catch (Exception e) {
    //         System.err.println(e);
    //     }
    // }

    // public void readFile() {
    //     this.soluong = readSl();
    //     if (soluong == -1) {
    //         System.out.println("Khong tim thay file hoac file bi loi! Bat dau khoi tao danh sach co san.");
    //         dsncc = new nhacungcap[4];
    //         dsncc[0] = new nhacungcap("APPLE", "TPHCM", "0987654321");
    //         dsncc[0] = new nhacungcap("XIAOMI", "TPHCM", "0987653421");
    //         dsncc[0] = new nhacungcap("SAMSUM", "TPHCM", "0978654321");
    //         dsncc[0] = new nhacungcap("VIVO", "TPHCM", "0987654312");
    //         soluong = 4;
    //         writeToFile();
    //         return;
    //     }
    //     try {
    //         dsncc = new nhacungcap[soluong];
    //         Scanner sc = new Scanner(new File("nhacungcap.txt"));
    //         sc.nextLine();
    //         for (int i = 0; i < soluong; i++) {
    //             String id = sc.next();
    //             sc.next();
    //             String name = "";
    //             while (sc.hasNext()) {
    //                 String currLine1 = sc.next();
    //                 if (currLine1.equals("|")) {
    //                     break;
    //                 }
    //                 name += currLine1 + " ";
    //             }
    //             String address = "";
    //             while (sc.hasNext()) {
    //                 String currLine2 = sc.next();
    //                 if (currLine2.equals("|")) {
    //                     break;
    //                 }
    //                 address += currLine2 + " ";
    //             }
    //             String phoneNumber = sc.next();
    //         }
    //     } catch (Exception e) {

    //     }
    // }
