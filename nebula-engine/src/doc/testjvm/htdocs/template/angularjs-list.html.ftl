<h1>${type.name} List</h1>
   
<div>
<input type="text" ng-model="query" class="search-query" placeholder="Search">
<a href="#/d/${type.name}/!new">Add New</a>
</div>
<!--Body content-->

      <table class="table">
      	<thead>
      		<tr>
			  <th>No.</th>
			<#list type.fields as field><#t>
			  <th>${field.name}</th>
			</#list>
      		</tr>
      	</thead>
      	<tbody>
	        <tr ng-repeat="data in datalist | filter:query | orderBy:orderProp">
			  <td>{{$index+1}}</td>
	          
			<#list type.fields as field><#t>
				<#if field.importance == "Key">
					<td><a href="#/d/${type.name}/{{data.${field.name}}}">{{data.${field.name}}}</a></td>
				<#else>
			  		<td>{{data.${field.name}}}</td>
  				</#if>
  
			</#list>
				
	        </tr>
      	</tbody>
      </table>