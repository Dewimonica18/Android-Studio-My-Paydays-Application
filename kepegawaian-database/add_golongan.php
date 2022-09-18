<?php
	if ($_SERVER['REQUEST_METHOD']=='POST') {
		//Mendapatkan Nilai Variable
		$nama_golongan = $_POST['nama_golongan'];
		$gajih_pokok = $_POST['gajih_pokok'];
		$tunjangan_makan = $_POST['tunjangan_makan'];
		$tunjangan_keluarga = $_POST['tunjangan_keluarga'];
		$tunjangan_jabatan = $_POST['tunjangan_jabatan'];

		//Pembuatan Syntax SQL
		$sql = "INSERT INTO tb_golongan (nama_golongan,gajih_pokok,tunjangan_makan,tunjangan_keluarga,tunjangan_jabatan) VALUES ('$nama_golongan','$gajih_pokok','$tunjangan_makan','$tunjangan_keluarga','$tunjangan_jabatan')";

		//Import File Koneksi Database
		require_once('koneksi.php');

		//Eksekusi Query databse
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Menambahkan Golongan';
		}else{
			echo 'Gagal Menambahkan Golongan';
		}

		mysqli_close($con);
	}
?>