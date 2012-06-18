<h1>{{data.name}}&nbsp;</h1>
<form ng-submit="data.$save()">
	<ul class="data">
		<#list type.fields as field><#t>
		<li><span class="label">${field.name}</span><input type="text" ngModel="data.${field.name}"/></li>
		</#list>				
	</ul>			
	<div>
		<input class="btn-primary" type="submit" value="save">
	</div>
</form>