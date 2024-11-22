public class Medicine {
    private String kode;
    private String nama;
    private String kategori;
    private String jenis;
    private String merek;
    private double hargaBeli;
    private double hargaJual;
    private int jumlah;
    private String tanggalMasuk;
    private String expired;

    // Konstruktor
    public Medicine(String kode, String nama, String kategori, String jenis, String merek, 
                    double hargaBeli, double hargaJual, int jumlah, String tanggalMasuk, String expired) {
        this.kode = kode;
        this.nama = nama;
        this.kategori = kategori;
        this.jenis = jenis;
        this.merek = merek;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
        this.jumlah = jumlah;
        this.tanggalMasuk = tanggalMasuk;
        this.expired = expired;
    }

    // Getter dan Setter
    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public String getKategori() { return kategori; }
    public String getJenis() { return jenis; }
    public String getMerek() { return merek; }
    public double getHargaBeli() { return hargaBeli; }
    public double getHargaJual() { return hargaJual; }
    public int getJumlah() { return jumlah; }
    public String getTanggalMasuk() { return tanggalMasuk; }
    public String getExpired() { return expired; }

    public void setKode(String kode) { this.kode = kode; }
    public void setNama(String nama) { this.nama = nama; }
    public void setKategori(String kategori) { this.kategori = kategori; }
    public void setJenis(String jenis) { this.jenis = jenis; }
    public void setMerek(String merek) { this.merek = merek; }
    public void setHargaBeli(double hargaBeli) { this.hargaBeli = hargaBeli; }
    public void setHargaJual(double hargaJual) { this.hargaJual = hargaJual; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }
    public void setTanggalMasuk(String tanggalMasuk) { this.tanggalMasuk = tanggalMasuk; }
    public void setExpired(String expired) { this.expired = expired; }
}
