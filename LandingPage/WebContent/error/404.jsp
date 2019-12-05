<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Favicon-->
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/template/public/img/favicon.png">
    <!-- Bootstrap core CSS-->
    <link href="<%=request.getContextPath()%>/template/admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="<%=request.getContextPath()%>/template/admin/css/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/template/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="<%=request.getContextPath()%>/template/admin/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<%=request.getContextPath()%>/template/admin/css/sb-admin.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/template/error/css/style.css" rel="stylesheet">
	<title>404 Not Found</title>
</head>
<body>
		<div class="contaiter m-5">
			
			<div class="row">
				<div class="text-pr text-fl">
					<a class="text-1" href="<%=request.getContextPath() %>/admin" > Click 
					<span class="off-fl" > H</span>ere </a> to come back admin	
				</div>
				
			</div>
			<div class="row">
				<div class="center ">
					<img alt="" width="75%" height="75%" src="<%=request.getContextPath() %>/template/error/erorr404.jpg" >
				</div>
			</div>
			
		</div>
</body>
</html>