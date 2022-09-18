<?php
	
	//Mendapatkan Nilai Dari Variable ID Pegawai yang ingin ditampilkan
	$id_golongan = $_GET['id_golongan'];

	//Importing database
	require_once('koneksi.php');

	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
	$sql = "SELECT * FROM tb_golongan WHERE id_golongan=$id_golongan";

	//Mendapatkan Hasil
	$r = mysqli_query($con,$sql);

	//Memasukkan Hasil Kedalam Array
	$result = array();
	$row = mysqli_fetch_array($r);
	array_push($result, array(
		"id_golongan"=>$row['id_golongan'],
		"nama_golongan"=>$row['nama_golongan'],
		"gajih_pokok"=>$row['gajih_pokok'],
		"tunjangan_makan"=>$row['tunjangan_makan'],
		"tunjangan_keluarga"=>$row['tunjangan_keluarga'],
		"tunjangan_jabatan"=>$row['tunjangan_jabatan']
	));

	//Menampilkan dalam format JSON
	echo json_encode(array('result'=>$result));

	mysqli_close($con);
?>