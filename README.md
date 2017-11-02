
An android streaming app. It contains bein sports channels and several other channels.

![alt text](http://www.dablaja.com/app/images/phone1.png)

![alt text](http://www.dablaja.com/app/images/phone2.png)

![alt text](http://www.dablaja.com/app/images/phone3.png)


You should just create a database with 2 tables , sponsors and chaine .Populate the 'chaine' table with iptv urls and channels names . Create and upload php files , and change the url in MainActivity.java and BeinEsp1.java

sponsors table:

```
CREATE TABLE IF NOT EXISTS `sponsors` (
  `nom` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `photo` varchar(500) COLLATE latin1_general_ci NOT NULL,
  `link` varchar(500) COLLATE latin1_general_ci NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=3 ;
```


chaine table
```
CREATE TABLE IF NOT EXISTS `chaine` (
  `nom` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `link` varchar(500) COLLATE latin1_general_ci NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=50 ;

```

getlink.php
```

$ch=$_GET['ch'];
$return_arr = array();
$sql0 = "SELECT * FROM chaine where nom='$ch'";
$result0 = mysqli_query($conn,$sql0);
while ($row = mysqli_fetch_array($result0)) {
    
    $s=$row['link'];
echo $s;
}
?>
```




getrows.php
```

$return_arr = array();
$sql0 = "SELECT count(*) FROM sponsors";
$result0 = mysqli_query($conn,$sql0);
 
while ($row = mysqli_fetch_array($result0)) {
    
echo $row[0];
  }

?>
```



getsponsor.php
```

$return_arr = array();


$sql0 = "SELECT * FROM sponsors";
$result0 = mysqli_query($conn,$sql0);
 
while ($row = mysqli_fetch_array($result0)) {
    
   $row_array['nom'] = $row['nom'];

 

    $row_array['photo'] = $row['photo'];
    $row_array['link'] = $row['link'];

    array_push($return_arr,$row_array);
    
}
echo json_encode($return_arr);


?>
```

