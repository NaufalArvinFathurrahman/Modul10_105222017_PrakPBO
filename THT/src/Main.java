import java.util.*;

// Soal 1
class Barang {
    String idBarang;
    String namaBarang;
    String kategori;
    int stok;

    public Barang(String idBarang, String namaBarang, String kategori, int stok) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.kategori = kategori;
        this.stok = stok;
    }
}

// Soal 2
class SistemGudang {
    private Map<String, Barang> databaseBarang;
    private Set<String> kategoriUnik;
    private List<String> riwayat;

    public SistemGudang() {
        this.databaseBarang = new HashMap<>();
        this.kategoriUnik = new HashSet<>();
        this.riwayat = new ArrayList<>();
    }

    public void tambahBarangBaru(String id, String nama, String kategori, int stok) {
        Barang barangBaru = new Barang(id, nama, kategori, stok);
        databaseBarang.put(id, barangBaru);
        kategoriUnik.add(kategori);
        riwayat.add("Barang Masuk: " + id + " (" + nama + ") didaftarkan sejumlah " + stok + " unit.");
    }

    public void tambahStok(String id, int jumlah) {
        if (databaseBarang.containsKey(id)) {
            Barang b = databaseBarang.get(id);
            b.stok += jumlah; 
            riwayat.add("Stok Masuk: " + id + " ditambah " + jumlah + " unit. (Total Saat Ini: " + b.stok + ")");
        } else {
            riwayat.add("GAGAL (Tambah Stok): Barang dengan ID " + id + " tidak ditemukan di database.");
        }
    }

    public void kurangiStok(String id, int jumlah) {
        if (databaseBarang.containsKey(id)) {
            Barang b = databaseBarang.get(id);
            if (b.stok >= jumlah) {
                b.stok -= jumlah;
                riwayat.add("Stok Keluar: " + id + " ditarik sebanyak " + jumlah + " unit. (Sisa: " + b.stok + ")");
            } else {
                riwayat.add("GAGAL (Kurangi Stok): Stok " + id + " tidak mencukupi! (Diminta: " + jumlah + ", Tersedia: " + b.stok + ")");
            }
        } else {
            riwayat.add("GAGAL (Kurangi Stok): Barang dengan ID " + id + " tidak ditemukan di database.");
        }
    }

    public void cetakLaporan() {
        System.out.println("\n=========================================");
        System.out.println("   LAPORAN SISTEM MANAJEMEN GUDANG");
        System.out.println("=========================================");
        
        System.out.println("\n[1] DAFTAR KATEGORI BARANG UNIK (Dari Set):");
        for (String kat : kategoriUnik) {
            System.out.println(" - " + kat);
        }

        System.out.println("\n[2] DATABASE STOK BARANG (Dari Map):");
        for (Barang b : databaseBarang.values()) {
            System.out.println(" - [" + b.idBarang + "] " + b.namaBarang + " (Kat: " + b.kategori + ") => Sisa Stok: " + b.stok);
        }

        System.out.println("\n[3] LOG RIWAYAT TRANSAKSI (Dari List):");
        int nomor = 1;
        for (String log : riwayat) {
            System.out.println(" " + nomor + ". " + log);
            nomor++;
        }
        System.out.println("=========================================\n");
    }
}

// Soal 3
public class Main {
    public static void main(String[] args) {
        SistemGudang gudang = new SistemGudang();

        gudang.tambahBarangBaru("B01", "Laptop ASUS", "Elektronik", 10);
        gudang.tambahBarangBaru("B02", "Mouse Logitech", "Elektronik", 50);
        gudang.tambahBarangBaru("B03", "Meja Kerja", "Furnitur", 15);

        gudang.tambahStok("B01", 5); 

        gudang.kurangiStok("B02", 20); 

        gudang.kurangiStok("B03", 100); 

        gudang.cetakLaporan();
    }
}
