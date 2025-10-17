import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductMenu extends JFrame {
    public static void main(String[] args) {
        // buat object window
        ProductMenu menu = new ProductMenu();
        // atur ukuran window
        menu.setSize(700, 600);
        // letakkan window di tengah layar
        menu.setLocationRelativeTo(null);
        // isi window
        menu.setContentPane(menu.mainPanel);
        // ubah warna background
        menu.getContentPane().setBackground(Color.WHITE);
        // tampilkan window
        menu.setVisible(true);
        // agar program ikut berhenti saat window diclose
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua produk
    // private ArrayList<Product> listProduct;
    private Database database;

    private JPanel mainPanel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField hargaField;
    private JTextField stokField;
    private JTextField merkField;
    private JTable productTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox<String> kategoriComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel idLabel;
    private JLabel namaLabel;
    private JLabel hargaLabel;
    private JLabel kategoriLabel;
    private JLabel stokLabel;
    private JLabel merkLabel;

    // constructor
    public ProductMenu() {

        database = new Database();

        // isi tabel produk
        productTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] kategoriData = {"????", "Elektronik", "Makanan", "Minuman", "Pakaian", "Alat Tulis"};
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });
        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: tambahkan konfirmasi sebelum menghapus data
                int response = JOptionPane.showConfirmDialog(
                        null,
                        "Apakah Anda yakin ingin menghapus data ini?",
                        "Konfirmasi Hapus",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if (response == JOptionPane.YES_OPTION) {
                    deleteData();
                }
            }
        });
        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        // saat salah satu baris tabel ditekan
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = productTable.getSelectedRow();

                // simpan value textfield dan combo box
                String curId = productTable.getModel().getValueAt(selectedIndex, 0).toString();
                String curNama = productTable.getModel().getValueAt(selectedIndex, 1).toString();
                String curHarga = productTable.getModel().getValueAt(selectedIndex, 2).toString();
                String curStok = productTable.getModel().getValueAt(selectedIndex, 3).toString();
                String curKategori = productTable.getModel().getValueAt(selectedIndex, 4).toString();
                String curMerk = productTable.getModel().getValueAt(selectedIndex, 5).toString();

                // ubah isi textfield dan combo box
                idField.setText(curId);
                namaField.setText(curNama);
                hargaField.setText(curHarga);
                stokField.setText(curStok);
                kategoriComboBox.setSelectedItem(curKategori);
                merkField.setText(curMerk);

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");

                // tampilkan button delete
                deleteButton.setVisible(true);

            }
        });
    }

    public final DefaultTableModel setTable() {
        // Tentukan kolom tabel
        Object[] cols = {"Kode Produk", "Nama Produk", "Harga", "Stok", "Kategori", "Merk"};

        // Buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel tmp = new DefaultTableModel(null, cols);

        try {
            // Jalankan query untuk ambil semua data produk
            ResultSet resultSet = database.selectQuery("SELECT * FROM product");

            // Isi tabel dengan hasil query
            int i = 0;
            while (resultSet.next()) {
                Object[] row = new Object[6];
                row[0] = resultSet.getString("id");
                row[1] = resultSet.getString("nama");
                row[2] = resultSet.getString("harga");
                row[3] = resultSet.getInt("stok");
                row[4] = resultSet.getString("kategori");
                row[5] = resultSet.getString("merk");
                tmp.addRow(row);
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tmp;
    }


    public void insertData() {
        try {
            if (idField.getText().isEmpty() || namaField.getText().isEmpty() ||
                    hargaField.getText().isEmpty() || stokField.getText().isEmpty() ||
                    merkField.getText().isEmpty() || kategoriComboBox.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null,
                        "Semua kolom wajib diisi!",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // ambil value dari textfield dan combobox
            String id = idField.getText();
            String nama = namaField.getText();
            double harga = Double.parseDouble(hargaField.getText());
            int stok = Integer.parseInt(stokField.getText());
            String kategori = kategoriComboBox.getSelectedItem().toString();
            String merk = merkField.getText();

            //cek validasi id
            ResultSet rs = database.selectQuery("SELECT id FROM product WHERE id = '" + id + "'");
            if (rs.next()) {
                JOptionPane.showMessageDialog(null,
                        "ID produk sudah terdaftar!",
                        "Duplicate Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sqlQuery = "INSERT INTO product (id, nama, harga, stok, kategori, merk) VALUES (" +
                    "'" + id + "', " +
                    "'" + nama + "', " +
                    harga + ", " +
                    stok + ", " +
                    "'" + kategori + "', " +
                    "'" + merk + "');";

            database.insertUpdateDeleteQuery(sqlQuery);

            productTable.setModel(setTable());
            clearForm();

            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Harga dan stok harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal menambahkan data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    public void updateData() {
        try{
            if (idField.getText().isEmpty() || namaField.getText().isEmpty() ||
                    hargaField.getText().isEmpty() || stokField.getText().isEmpty() ||
                    merkField.getText().isEmpty() || kategoriComboBox.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null,
                        "Semua kolom wajib diisi!",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // ambil data dari form
            String id = idField.getText();
            String nama = namaField.getText();
            double harga = Double.parseDouble(hargaField.getText());
            int stok = Integer.parseInt(stokField.getText());
            String kategori = kategoriComboBox.getSelectedItem().toString();
            String merk = merkField.getText();

            String sqlQuery = "UPDATE product SET " +
                    "nama = '" + nama + "', " +
                    "harga = " + harga + ", " +
                    "stok = " + stok + ", " +
                    "kategori = '" + kategori + "', " +
                    "merk = '" + merk + "' " +
                    "WHERE id = '" + id + "';";

            database.insertUpdateDeleteQuery(sqlQuery);
            // update tabel

            productTable.setModel(setTable());

            // bersihkan form
            clearForm();

            // feedback
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Harga atau Stok harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteData() {
        String id = idField.getText();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sqlQuery = "DELETE FROM product WHERE id = '" + id + "'";
        database.insertUpdateDeleteQuery(sqlQuery);

        // update tabel
        productTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus");

    }

    public void clearForm() {
        // kosongkan semua texfield dan combo box
        idField.setText("");
        namaField.setText("");
        hargaField.setText("");
        stokField.setText("");
        merkField.setText("");
        kategoriComboBox.setSelectedIndex(0);

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }

}