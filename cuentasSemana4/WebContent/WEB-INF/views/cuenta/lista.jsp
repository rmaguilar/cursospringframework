<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AJAX calls using Jquery in Servlet</title>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript">
function fueHecho(respose)
{
	alert("Cuenta pagada con éxito");
}

function pagarAhora(id) {
	
	$.get("pagarCuenta?id=" + id, fueHecho);
}

function pagarAhoraPost(id) {
	
	$.post("pagarCuenta", {'id' : id}, 
			function() {
				$("#cuenta_"+id).html("Pago");
			});
}
</script>
</head>
<body>
	<p>Usuario Logado, ${usuarioLogueado.login} <a href="logout">(Logout)</a></p> 
	
	<table style="height: 10px; width: 775px;" border="1">
		<tr>
			<th>Código</th>
			<th>Descripcion</th>
			<th>Valor</th>
			<th>Tipo</th>
			<th>¿Pago?</th>
			<th>Fecha de Pago</th>
			<th>Acciones</th>
		</tr>
		<c:forEach items="${cuentas}" var="cuenta">
		<tr>
			<td>${cuenta.id}</td>
			<td>${cuenta.descripcion}</td>
			<td>${cuenta.valor}</td>
			<td>${cuenta.tipo}</td>
			<td id="tarifa_${cuenta.id}">
				<c:if test="${cuenta.pago eq false}">
					No pago
				</c:if>
				<c:if test="${cuenta.pago eq true }">
					Pago!
				</c:if>
			</td>
			<td>
				<fmt:formatDate value="${cuenta.fechaPago.time}" pattern="dd/MM/yyyy"/>
			</td>
			<td><a href="eliminarCuenta?id=${cuenta.id}">Eliminar</a> &nbsp;
				<a href="muestraCuenta?id=${cuenta.id}">Modificar</a>
				<a href="#" onclick="pagarAhora(${cuenta.id});">Pagar</a>
				<a href="#" onclick="pagarAhoraPost(${cuenta.id});">PagarPost</a>
			</td>
			
			<td id="cuenta_${cuenta.id}">
				<c:if test="${cuenta.pago eq false}">
					No pago
				</c:if>
				<c:if test="${cuenta.pago eq true }">
					Pago!
				</c:if>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>
