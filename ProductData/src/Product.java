public class Product {
    private String id;
    private String nama;
    private double harga;
    private String kategori;
    private int stok;   // Atribut tambahan
    private String merk; // Atribut tambahan

    // Constructor diperbarui untuk menerima semua atribut
    public Product(String id, String nama, double harga, int stok, String kategori, String merk) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.kategori = kategori;
        this.merk = merk;
    }

    // --- GETTERS ---
    public String getId() {
        return this.id;
    }

    public String getNama() {
        return this.nama;
    }

    public double getHarga() {
        return this.harga;
    }

    public String getKategori() {
        return this.kategori;
    }

    public int getStok() { // Getter untuk stok
        return this.stok;
    }

    public String getMerk() { // Getter untuk merk
        return this.merk;
    }

    // --- SETTERS ---
    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setStok(int stok) { // Setter untuk stok
        this.stok = stok;
    }

    public void setMerk(String merk) { // Setter untuk merk
        this.merk = merk;
    }
}