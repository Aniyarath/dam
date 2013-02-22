<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<script type="text/javascript">

var values=confirm("Work flow already exist.Do You wish to update?");
alert(values);
if(values)
{
	document.location.href = 'updatedynamicWF.obj';
}
else
{
	document.location.href = 'dynamicWF.obj';
}

</script> 
</body>
</html>