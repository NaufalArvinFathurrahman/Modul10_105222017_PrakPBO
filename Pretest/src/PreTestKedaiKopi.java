import java.util.*;

public class PreTestKedaiKopi {
    public static void main(String[] args) {
        
        List<String> antrean = new ArrayList<>();

        Set<Integer> mejaTerisi = new HashSet<>();

        Map<String, Integer> hargaMenu = new HashMap<>();

        antrean.add("Budi");
        antrean.add("Siti");

        mejaTerisi.add(4);
        mejaTerisi.add(7);
        mejaTerisi.add(4); 

        hargaMenu.put("Kopi", 15000);
        hargaMenu.put("Teh", 10000);

        System.out.println("Daftar Antrean  : " + antrean);
        System.out.println("Meja Terisi     : " + mejaTerisi); 
        System.out.println("Harga Kopi      : Rp " + hargaMenu.get("Kopi"));
    }
}
