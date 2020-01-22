<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<head>
  <meta charset="ISO-8859-1"/>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
  <title>Currency Converter</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src=https://code.jquery.com/jquery-1.12.4.js></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div id="box" class="card">
	<div class="card-body">
	<h2 class="card-title">Currency Converter</h2>
	<form:form action="currency" name="currencyForm" method="POST" modelAttribute="currencyConverterAttribute">
	<table>
		<tr>
		<td><form:input path="historicalDate" id="datepicker" required="required"/></td>
		<td><form:input path="fromAmount" required="required" onkeypress="return isNumberKey(event)"/></td>
			<td><form:select path="convertCurrency" items="${countryOptions2}"/></td>
			<td><form:select path="baseCurrency" items="${countryOptions}"/></td>
			<td><input type="submit" value="submit"/></td>
		</tr>
		<tr>
		</tr>
	</table>
	</form:form>
	</div>
</div>
</body>
<script type="text/javascript">
$(function() {
    $("#datepicker").datepicker({ dateFormat: 'yy-mm-dd' });
  });
	$.datepicker.setDefaults({
    	changeMonth:true,
    	changeYear:true,
    	yearRange: "-100:+0"
	});
	
	function isNumberKey(evt)
    {
       var charCode = (evt.which) ? evt.which : evt.keyCode;
       if (charCode != 46 && charCode > 31 
         && (charCode < 48 || charCode > 57))
          return false;
       return true;
    }
</script>