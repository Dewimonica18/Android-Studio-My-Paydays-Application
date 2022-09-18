<?php

	if($_SERVER['REQUEST_METHOD']=='POST'){
		//Mendapatkan Nilai dari Variable
		$id_golongan = $_POST['id_golongan'];
		$nama_golongan = $_POST['nama_golongan'];
		$gajih_pokok = $_POST['gajih_pokok'];
		$tunjangan_makan = $_POST['tunjangan_makan'];
		$tunjangan_keluarga = $_POST['tunjangan_keluarga'];
		$tunjangan_jabatan = $_POST['tunjangan_jabatan'];

		//import file koneksi database
		require_once('koneksi.php');

		//Membuat SQL Query
		$sql = "UPDATE tb_golongan SET nama_golongan = '$nama_golongan', gajih_pokok = '$gajih_pokok', tunjangan_makan = '$tunjangan_makan', tunjangan_keluarga = '$tunjangan_keluarga', tunjangan_jabatan = '$tunjangan_jabatan' WHERE id_golongan = $id_golongan;";

		//Meng-update Database
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Update Data Golongan';
		}else{
			echo 'Gagal Update Data Golongan';
		}

		mysqli_close($con);
	}
?>