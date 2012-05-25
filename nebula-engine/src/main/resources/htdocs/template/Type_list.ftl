<h1>noc.lang.reflect.Type</h1>
		
<a style="float:right;" href="/noc/noc/lang/reflect/Type/noc.lang.reflect.Type">Type</a>

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
		<#list data?sort_by("name") as item><#t>
		<tr>
			<td align="left"> <a href="${item.name}">${item.name}</a></td>
			<td> </td>
			<td> </a></td>
			<td> <#if item.standalone>True<#else>false</#if></td>
		</tr>
		</#list>	
	</tbody>
</table>

<br/>

</body></html>
