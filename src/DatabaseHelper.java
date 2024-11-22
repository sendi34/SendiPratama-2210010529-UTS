import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DatabaseHelper {

    // Metode untuk koneksi ke database SQLite
    public static Connection koneksidb() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:E:\\NETBEANS PROJECT\\SendiPratama-2210010529-UTS\\obat.db"); // Perbaiki URL koneksi sesuai kebutuhan
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in connecting to the database: " + e.getMessage());
            return null;
        }
    }

    // Menambahkan data obat ke database menggunakan objek Medicine
    public static void addMedicine(Medicine medicine) throws SQLException {
        addMedicine(
            medicine.getKode(),
            medicine.getNama(),
            medicine.getKategori(),
            medicine.getJenis(),
            medicine.getMerek(),
            medicine.getHargaBeli(),
            medicine.getHargaJual(),
            medicine.getJumlah(),
            medicine.getTanggalMasuk(),
            medicine.getExpired()
        );
    }

    // Menambahkan data obat ke database menggunakan parameter eksplisit
    public static void addMedicine(String kode, String nama, String kategori, String jenis, String merek, double hargaBeli, double hargaJual, int jumlah, String tanggalMasuk, String expired) throws SQLException {
        String sql = "INSERT INTO data_obat (kode_obat, nama_obat, kategori_obat, jenis_obat, merek, harga_beli, harga_jual, jumlah, tanggal_masuk, expired) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = koneksidb(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, kode);
            pstmt.setString(2, nama);
            pstmt.setString(3, kategori);
            pstmt.setString(4, jenis);
            pstmt.setString(5, merek);
            pstmt.setDouble(6, hargaBeli);
            pstmt.setDouble(7, hargaJual);
            pstmt.setInt(8, jumlah);
            pstmt.setString(9, tanggalMasuk);
            pstmt.setString(10, expired);
            pstmt.executeUpdate();
        }
    }

    // Mendapatkan semua data obat dari database
    public static List<Medicine> getAllMedicines() throws SQLException {
        List<Medicine> medicines = new ArrayList<>();
        String sql = "SELECT * FROM data_obat";
        try (Connection conn = koneksidb(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Medicine medicine = new Medicine(
                    rs.getString("kode_obat"),
                    rs.getString("nama_obat"),
                    rs.getString("kategori_obat"),
                    rs.getString("jenis_obat"),
                    rs.getString("merek"),
                    rs.getDouble("harga_beli"),
                    rs.getDouble("harga_jual"),
                    rs.getInt("jumlah"),
                    rs.getString("tanggal_masuk"),
                    rs.getString("expired")
                );
                medicines.add(medicine);
            }
        }
        return medicines;
    }

    // Mengupdate data obat menggunakan objek Medicine
    public static void updateMedicine(Medicine medicine) throws SQLException {
        updateMedicine(
            medicine.getKode(),
            medicine.getNama(),
            medicine.getKategori(),
            medicine.getJenis(),
            medicine.getMerek(),
            medicine.getHargaBeli(),
            medicine.getHargaJual(),
            medicine.getJumlah(),
            medicine.getTanggalMasuk(),
            medicine.getExpired()
        );
    }

    // Mengupdate data obat menggunakan parameter eksplisit
    public static void updateMedicine(String kode, String nama, String kategori, String jenis, String merek, double hargaBeli, double hargaJual, int jumlah, String tanggalMasuk, String expired) throws SQLException {
        String sql = "UPDATE data_obat SET nama_obat = ?, kategori_obat = ?, jenis_obat = ?, merek = ?, harga_beli = ?, harga_jual = ?, jumlah = ?, tanggal_masuk = ?, expired = ? WHERE kode_obat = ?";
        try (Connection conn = koneksidb(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nama);
            pstmt.setString(2, kategori);
            pstmt.setString(3, jenis);
            pstmt.setString(4, merek);
            pstmt.setDouble(5, hargaBeli);
            pstmt.setDouble(6, hargaJual);
            pstmt.setInt(7, jumlah);
            pstmt.setString(8, tanggalMasuk);
            pstmt.setString(9, expired);
            pstmt.setString(10, kode);
            pstmt.executeUpdate();
        }
    }

    // Menghapus data obat berdasarkan kode
    public static void deleteMedicine(String kode) throws SQLException {
        String sql = "DELETE FROM data_obat WHERE kode_obat = ?";
        try (Connection conn = koneksidb(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, kode);
            pstmt.executeUpdate();
        }
    }

    // Mencari obat berdasarkan nama atau kode
    public static List<Medicine> searchMedicine(String keyword) throws SQLException {
        List<Medicine> medicines = new ArrayList<>();
        String sql = "SELECT * FROM data_obat WHERE nama_obat LIKE ? OR kode_obat LIKE ?";
        try (Connection conn = koneksidb(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Medicine medicine = new Medicine(
                    rs.getString("kode_obat"),
                    rs.getString("nama_obat"),
                    rs.getString("kategori_obat"),
                    rs.getString("jenis_obat"),
                    rs.getString("merek"),
                    rs.getDouble("harga_beli"),
                    rs.getDouble("harga_jual"),
                    rs.getInt("jumlah"),
                    rs.getString("tanggal_masuk"),
                    rs.getString("expired")
                );
                medicines.add(medicine);
            }
        }
        return medicines;
    }
}
