<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="../scripts/jquery-3.3.1.min.js"></script>
<script type="text/javascript">

function addPerson(){
	alert('adding');
	var postData = {};
	postData.name = $("#txtName").val();
	$.post("./newtolist",postData,function(rs,data){
		$("#divMain").html(rs);
	});
}

function bindList(){

	$.post("../person?action=getall",{},function(rs,data){
		$("#divMain").html(rs);
	});
	
}

	$(function(){
		bindList();
		$("#btnAddNew").click(function(){
			addPerson();
		});
	})


</script>

</head>
<body>

<table border="1" cellpadding="10" cellspacing="0">  
            <tr>  
                <th>姓名 </th>  
                <th>Id </th>  
                  
            </tr>  
            <tr>
            <td><input type=text id=txtName name=txtName ></td><td><input type="button" id=btnAddNew name=btnAddNew value="添加"></td>
            </tr>
</table>

<div id="divMain" >
</div>
</body>
</html>