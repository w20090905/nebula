<h1>${type.name}&nbsp;</h1>
<form ng-submit="data.$save()">
	<#list type.fields as field><#t>
	<div class="field" ng-class="{error: myForm.name.$invalid}">
		<label>${field.name}</label><input type="text" ngModel="data.${field.name}"/>
	</div>
	</#list>	
	<div class="action">
		<input class="btn-primary" type="submit" value="save">
	</div>
</form>