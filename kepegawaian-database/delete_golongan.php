<?php
	
	//Mendapatkan Nilai ID
	$id_golongan = $_GET['id_golongan'];

	//Import File Koneksi Database
	require_once('koneksi.php');

	//Membuat SQL Query
	$sql = "DELETE FROM tb_golongan WHERE id_golongan=$id_golongan;";

	//Menghapus Nilai pada Database
	if(mysqli_query($con,$sql)){
		echo 'Berhasil Menghapus Golongan';
	}else{
		echo 'Gagal Menghapus Golongan';
	}
	mysqli_close($con);
?>