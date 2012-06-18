<h1>${type.name} List</h1>
<div class="container-fluid">    
	<div>
      <!--Sidebar content-->

      Search: <input type="text" ng-model="query" class="search-query" placeholder="Search">
      Sort by:
      <select ng-model="orderProp">
        <option value="name">Alphabetical</option>
        <option value="standalone" selected="selected">standalone</option>
      </select>

    </div>

    <div >
      <!--Body content-->

      <table class="table">
      	<thead>
      		<tr>
			<#list type.fields as field><#t>
			  <th>${field.name}</th>
			</#list>
      		</tr>
      	</thead>
      	<tbody>
	        <tr ng-repeat="data in datalist | filter:query | orderBy:orderProp" class="thumbnail">
	          
			<#list type.fields as field><#t>
			  <td>{{data.${field.name}}}</td>
			</#list>
				
	        </tr>
      	</tbody>
      </table>
  </div>
</div>