<h1>${data.name}</h1>

<ul class="data">
	<li>
		<span class="label">Name</span>
		<span class="value">${data.name}</span>
	</li>
	<li>
		<span class="label">Parent Type</span>
		<span class="value">${data.superType.name}</span>
	</li>
	<li>
		<span class="label">standalone</span>
		<span class="value">${data.standalone}</span>
	</li>
	
	<table class="list"> 
		<thead>	
			<tr>
				<th style="width:10em;"> Name </th>
				<th> Type </th>
				<th> Master </th>
				<th> Standalone </th>
			</tr>
		</thead>
			
		<tbody>
			<#list data.fields as item><#t>
			<tr>
				<td align="left">${item.name}</td>
				<td>${item.type.name} </td>
				<td> </a></td>
				<td> </td>
			</tr>
			</#list>	
		</tbody>
	</table>
</ul>

