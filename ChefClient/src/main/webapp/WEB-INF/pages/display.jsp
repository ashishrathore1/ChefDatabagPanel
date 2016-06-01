<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- "<c:url value="/resources/css/jtable.css" />" 
 -->
 <!DOCTYPE html>
<html>
<head>
<title>Key Values</title>

<link rel="stylesheet" href="<c:url value="/resources/mycss/style.css" />" type="text/css" media="all" />
<link rel="stylesheet" href="<c:url value="/resources/mycss/style1.css" />" type="text/css" media="all" />
<link rel="stylesheet" href= "<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" >

<!-- jTable Metro styles. -->
<link href="<c:url value="/resources/css/metro/blue/jtable.css" />"  rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/jquery-ui.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet" type="text/css" />
<!-- jTable script file. -->
<script src="<c:url value="/resources/js/jQuery-1.8.2.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jquery-ui.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jquery.jtable.js" />" type="text/javascript"></script>
<script type="text/javascript">
        $(document).ready(function() {
        		var databag =  $("#databag").val();
        		var item = $("#item").val();
                $('#DataItemContainer').jtable({
                        title : 'Item List', 
                        actions : {
                            listAction: 'Controller?action=list&databag='+databag+'&item='+item,
                            createAction:'Controller?action=create&databag='+databag+'&item='+item,
                            updateAction: 'Controller?action=update&databag='+databag+'&item='+item,
                            deleteAction: 'Controller?action=delete&databag='+databag+'&item='+item,                     
                  		},
                        fields : {
                                key : {
                                        title : 'key',
                                        width : '50%',
                                        key : true,
                                        list : true,
                                        create : true,
                                        edit : true
                                },
                                value : {
                                        title : 'value',
                                        width : '50%',
                                        edit : true
                                },
                                
                        }
                });
                $('#DataItemContainer').jtable('load');
        });
</script>

</head>
<body>
	<form name='downloadCSV' action="downloadCSV" method='POST'>
		<div class="col-sm-12">
			<input type="hidden" name="databag" value="${databag}" >
			<input type="hidden" name="item" value="${item}" >
			<button name="view" type="submit" class="btn btn-primary"
				value="view">Download CSV</button>
		</div>
	</form>
	<div id="nav">
		<ul>
			<li><a href="GOTOHOME">Home</a></li>
			<li><a href="GOTOLOGOUT">Logout</a></li>
		</ul>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div style="text-align: center;">
		<%-- <h4>Databag : <%= session.getAttribute("databag") %> and Item : <%= session.getAttribute("item") %></h4> --%>
		${databag} ${item}
		<input type="hidden" name="databag" value="${databag}" id="databag" >
		<input type="hidden" name="item" value="${item}" id="item" >
		<div id="DataItemContainer"></div>
	</div>
	
	
</body>
</html>