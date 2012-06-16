<h1>Type</h1>

<table class="list"> 
	<thead>	
		<tr>
			<th> Name </th>
			<th> DisplayName </th>
			<th> Master </th>
			<th> Standalone </th>
		</tr>
	</thead>
	
	
	<tbody>
		<#list data  as item><#t>
		<tr>
			<td align="left"> <a href="/d/Type/${item.name}">${item.name}</a></td>
			<td> </td>
			<td> </td>
			<td> ${item.standalone}</td>
		</tr>
		</#list>	
	</tbody>
</table>