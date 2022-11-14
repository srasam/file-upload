<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>File Upload Demo</title>
</head>
<body>
	<center>
		<h1>FILE UPLOAD</h1>
		<form action="uploadFile" method="post" enctype="multipart/form-data">
			Select files:
			<input type="file"	name="uploadFile" /><br/><br/> 
				<input type="submit" value="Upload" />
		</form>
	</center>

</body>
</html>