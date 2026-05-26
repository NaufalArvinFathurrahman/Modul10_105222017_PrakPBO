import java.util.*;

// Soal 1
class Buku {
    String isbn;
    String judul;

    public Buku(String isbn, String judul) {
        this.isbn = isbn;
        this.judul = judul;
    }

    @Override
    public String toString() {
        return "Buku{" + "isbn='" + isbn + "', judul='" + judul + "'}";
    }
}

// Soal 2
class Anggota {
    String idAnggota;
    String nama;
    String tipe;

    public Anggota(String idAnggota, String nama, String tipe) {
        this.idAnggota = idAnggota;
        this.nama = nama;
        this.tipe = tipe;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Anggota anggota = (Anggota) obj;
        return Objects.equals(idAnggota, anggota.idAnggota);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnggota);
    }

    @Override
    public String toString() {
        return "Anggota{" + "idAnggota='" + idAnggota + "', nama='" + nama + "', tipe='" + tipe + "'}";
    }
}

public class App {
    
    // Soal 3
    public static void tambahKeAntrean(Deque<String> antrean, Anggota anggota, Buku buku) {
        String data = anggota.idAnggota + "#" + buku.isbn;
        if (anggota.tipe.equalsIgnoreCase("Dosen")) {
            antrean.addFirst(data);
        } else {
            antrean.addLast(data);
        }
    }

    public static void main(String[] args) {
        
        // Soal 1
        Map<String, Buku> katalogBuku = new HashMap<>();
        
        Buku b1 = new Buku("ISBN-001", "Pemrograman Java");
        Buku b2 = new Buku("ISBN-002", "Struktur Data");
        Buku b3 = new Buku("ISBN-003", "Basis Data");

        katalogBuku.put(b1.isbn, b1);
        katalogBuku.put(b2.isbn, b2);
        katalogBuku.put(b3.isbn, b3);

        System.out.println("--- HASIL SOAL 1 ---");
        System.out.println("Cari instan ISBN-002: " + katalogBuku.get("ISBN-002").judul);

        // Soal 2
        Set<Anggota> daftarAnggota = new HashSet<>();

        Anggota a1 = new Anggota("MHS-01", "Andi", "Mahasiswa");
        Anggota a2 = new Anggota("MHS-02", "Budi", "Mahasiswa");
        Anggota a3 = new Anggota("DSN-01", "Dr. Cipto", "Dosen");
        Anggota a4 = new Anggota("MHS-01", "Andi Kloningan", "Mahasiswa");

        daftarAnggota.add(a1);
        daftarAnggota.add(a2);
        daftarAnggota.add(a3);
        boolean statusA4 = daftarAnggota.add(a4);

        System.out.println("\n--- HASIL SOAL 2 ---");
        System.out.println("Total Anggota yang Terdaftar: " + daftarAnggota.size());
        for (Anggota a : daftarAnggota) {
            System.out.println("- " + a);
        }
        System.out.println("Status pendaftaran ID MHS-01 (Kloningan): " + (statusA4 ? "Berhasil" : "DITOLAK"));

        // Soal 3
        Deque<String> antreanPeminjaman = new LinkedList<>();

        tambahKeAntrean(antreanPeminjaman, a1, b1); 
        Buku bX = new Buku("ISBN-999", "Buku Misteri");
        tambahKeAntrean(antreanPeminjaman, a2, bX);
        Anggota a5 = new Anggota("DSN-02", "Prof. Dina", "Dosen");
        tambahKeAntrean(antreanPeminjaman, a5, b2);
        tambahKeAntrean(antreanPeminjaman, a3, b1);

        System.out.println("\n--- HASIL SOAL 3 ---");
        System.out.println("Isi Antrean Peminjaman Awal (Tanpa Dikeluarkan):");
        int urutan = 1;
        for (String item : antreanPeminjaman) {
            System.out.println(urutan + ". " + item);
            urutan++;
        }

        // Soal 4
        System.out.println("\n--- HASIL SOAL 4 ---");
        Set<String> bukuSedangDipinjam = new HashSet<>();

        while (!antreanPeminjaman.isEmpty()) {
            String data = antreanPeminjaman.pollFirst(); 
            String[] parts = data.split("#");
            String idPeminjam = parts[0];
            String isbnBuku = parts[1];

            System.out.println("\n>> Memproses antrean: " + idPeminjam + " ingin meminjam " + isbnBuku);

            boolean isAnggotaTerdaftar = false;
            for (Anggota a : daftarAnggota) {
                if (a.idAnggota.equals(idPeminjam)) {
                    isAnggotaTerdaftar = true;
                    break;
                }
            }

            boolean isBukuTerdaftar = katalogBuku.containsKey(isbnBuku);

            if (!isAnggotaTerdaftar) {
                System.out.println("   [GAGAL] ID Anggota '" + idPeminjam + "' tidak terdaftar di sistem kami.");
            } else if (!isBukuTerdaftar) {
                System.out.println("   [GAGAL] Buku dengan ISBN '" + isbnBuku + "' tidak ditemukan di dalam katalog.");
            } else {
                if (bukuSedangDipinjam.contains(isbnBuku)) {
                    System.out.println("   [GAGAL] Buku '" + katalogBuku.get(isbnBuku).judul + "' (" + isbnBuku + ") sedang dipinjam orang lain.");
                } else {
                    bukuSedangDipinjam.add(isbnBuku);
                    System.out.println("   [BERHASIL] Peminjaman disetujui! '" + idPeminjam + "' meminjam buku '" + katalogBuku.get(isbnBuku).judul + "'.");
                }
            }
        }
    }
}
