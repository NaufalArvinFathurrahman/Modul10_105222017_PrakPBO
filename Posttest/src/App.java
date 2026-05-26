import java.util.*;

// Soal 1
class Film {
    String judul;
    int harga;

    public Film(String judul, int harga) {
        this.judul = judul;
        this.harga = harga;
    }
}

// Soal 3
class Pesanan {
    String namaPemesan;
    String judulFilm;
    String nomorKursi;
    int harga;

    public Pesanan(String namaPemesan, String judulFilm, String nomorKursi, int harga) {
        this.namaPemesan = namaPemesan;
        this.judulFilm = judulFilm;
        this.nomorKursi = nomorKursi;
        this.harga = harga;
    }

    @Override
    public String toString() {
        return "Pemesan: " + namaPemesan + " | Film: " + judulFilm + " | Kursi: " + nomorKursi + " | Total Harga: Rp" + harga;
    }
}

public class App {
    public static void main(String[] args) {
        
        // Soal 1
        Map<String, Film> jadwalBioskop = new HashMap<>();
        jadwalBioskop.put("F01", new Film("Avengers: Endgame", 50000));
        jadwalBioskop.put("F02", new Film("Joker", 45000));
        jadwalBioskop.put("F03", new Film("Inception", 40000));

        // Soal 2
        Set<String> kursiTerpesan = new HashSet<>();

        // Soal 3
        List<Pesanan> riwayatTransaksi = new ArrayList<>();

        System.out.println("=== SISTEM PEMESANAN TIKET BIOSKOP ===");

        prosesPesanan("Andi", "F01", "A1", jadwalBioskop, kursiTerpesan, riwayatTransaksi);
        prosesPesanan("Budi", "F01", "A2", jadwalBioskop, kursiTerpesan, riwayatTransaksi);
        prosesPesanan("Citra", "F02", "B1", jadwalBioskop, kursiTerpesan, riwayatTransaksi);
        
        prosesPesanan("Dani", "F01", "A1", jadwalBioskop, kursiTerpesan, riwayatTransaksi);
        prosesPesanan("Eko", "F99", "C5", jadwalBioskop, kursiTerpesan, riwayatTransaksi);

        System.out.println("\n=== RIWAYAT TRANSAKSI (BERURUTAN) ===");
        int urutan = 1;
        for (Pesanan p : riwayatTransaksi) {
            System.out.println(urutan + ". " + p);
            urutan++;
        }
    }

    public static void prosesPesanan(String nama, String kodeFilm, String kursi, Map<String, Film> jadwal, Set<String> setKursi, List<Pesanan> riwayat) {
        System.out.println("\nMemproses Pesanan: " + nama + " (Film: " + kodeFilm + ", Kursi: " + kursi + ")");
        
        if (!jadwal.containsKey(kodeFilm)) {
            System.out.println("-> [GAGAL] Kode film '" + kodeFilm + "' tidak ditemukan di jadwal hari ini.");
            return;
        }

        String kodeKunciKursi = kodeFilm + "-" + kursi;

        if (setKursi.add(kodeKunciKursi)) {
            Film filmPilihan = jadwal.get(kodeFilm);
            Pesanan pesananBaru = new Pesanan(nama, filmPilihan.judul, kursi, filmPilihan.harga);
            riwayat.add(pesananBaru);
            System.out.println("-> [BERHASIL] Tiket tercetak untuk " + nama + ".");
        } else {
            System.out.println("-> [GAGAL] Kursi '" + kursi + "' sudah tidak tersedia (Sudah dipesan orang lain).");
        }
    }
}
