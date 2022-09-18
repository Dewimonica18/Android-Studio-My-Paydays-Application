package com.mortaries.kepegawaian;

public class konfigurasi {
    public static final String URL_ADD="http://192.168.0.108/pegawai/add.php";
    public static final String URL_GET_ALL="http://192.168.0.108/pegawai/viewAll.php";
    public static final String URL_GET_EMP="http://192.168.0.108/pegawai/view.php?id=";
    public static final String URL_UPDATE_EMP="http://192.168.0.108/pegawai/update.php";
    public static final String URL_DELETE_EMP="http://192.168.0.108/pegawai/delete.php?id=";

    public static final String URL_ADD_GOL="http://192.168.0.108/pegawai/add_golongan.php";
    public static final String URL_GET_ALL_GOL="http://192.168.0.108/pegawai/viewAll_golongan.php";
    public static final String URL_GET_GOL="http://192.168.0.108/pegawai/view_golongan.php?id_golongan=";
    public static final String URL_UPDATE_GOL="http://192.168.0.108/pegawai/update_golongan.php";
    public static final String URL_DELETE_GOL="http://192.168.0.108/pegawai/delete_golongan.php?id_golongan=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAMA = "name";
    public static final String KEY_EMP_POSISI = "desg"; //desg itu variabel untuk posisi
    public static final String KEY_EMP_GAJIH = "salary"; //salary itu variabel untuk gajih

    public static final String KEY_ID_GOLONGAN = "id_golongan";
    public static final String KEY_NAMA_GOLONGAN = "nama_golongan";
    public static final String KEY_GAJIH_POKOK = "gajih_pokok";
    public static final String KEY_TUNJANGAN_MAKAN = "tunjangan_makan";
    public static final String KEY_TUNJANGAN_KELUARGA = "tunjangan_keluarga";
    public static final String KEY_TUNJANGAN_JABATAN = "tunjangan_jabatan";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "name";
    public static final String TAG_POSISI = "desg";
    public static final String TAG_GAJIH = "salary";

    public static final String TAG_ID_GOLONGAN = "id_golongan";
    public static final String TAG_NAMA_GOLONGAN = "nama_golongan";
    public static final String TAG_GAJIH_POKOK = "gajih_pokok";
    public static final String TAG_TUNJANGAN_MAKAN = "tunjangan_makan";
    public static final String TAG_TUNJANGAN_KELUARGA = "tunjangan_keluarga";
    public static final String TAG_TUNJANGAN_JABATAN = "tunjangan_jabatan";

    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String EMP_ID = "emp_id";

    public static final String GOL_ID = "gol_id";

}
