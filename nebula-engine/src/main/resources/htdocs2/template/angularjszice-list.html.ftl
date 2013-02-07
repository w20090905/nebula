
                    <div class="header"><span ><span class="ico  gray spreadsheet"></span> ${type.name} List </span>
                            <div class="searchBox">
                            <div class="searchAutocomplete"></div>
                            <div class="searchText">
                                <form action="#" method="GET">
								<input type="text" ng-model="query" class="search-query" placeholder="Search">
								</form>
                            </div>
                            </div>
                            <a href="#/d/${type.name}/!new">Add New</a>
						</div><!-- End header -->	
                    <div class="content" >
                    
                       


<!--Body content-->

      <table class="display">
      	<thead>
      		<tr>
			  <th>No.</th>
			<#list type.fields as field><#t><#if !field.array>
				<#switch field.refer>
				<#case "ByVal">
			  <th>${field.name}</th> 
		  			<#break>
				<#case "Inline">
					<#if field.key || field.core><#list field.type.fields as rF><#if field.key && rF.key>	
			  <th>${field.name}&nbsp;${rF.name}</th>					
		  			</#if></#list></#if>
		  			<#break>
				<#case "ByRef">
					<#list field.type.fields as rF><#rt>
						<#if rF.key><#rt>				
			  <th>${field.name}</th>							
	    				</#if>
		  			</#list>
		  			<#break>		
				<#case "Cascade">
					<#list field.type.fields as rF><#rt>
						<#if rF.key><#rt>				
			  <th>${field.name}</th>
	    				</#if>
		  			</#list>
		  			<#break>	
		  		</#switch>
			</#if></#list>
			
			<th width="6em" >Management</th>
      		</tr>
      	</thead>
      	<tbody>
	        <tr ng-repeat="data in datalist | filter:query | orderBy:orderProp">
			  <td>{{$index+1}}</td>
	          
			<#list type.fields as field><#t><#if !field.array>
				<#switch field.refer>
				<#case "ByVal">
					<#if field.key>
			<td><a href="#/d/${type.name}/{{data.${field.name}}}">{{data.${field.name}}}</a></td>
					<#else>		
			<td>{{data.${field.name}}}</td>
		  			</#if>  
		  			<#break>
				<#case "Inline">
					<#if field.key || field.core><#list field.type.fields as rF><#if field.key && rF.key>				
				  		<td>{{data."${field.name}${rF.name}"}}</td>			
		  			</#if></#list></#if>
		  			<#break>
				<#case "ByRef">
					<#list field.type.fields as rF><#rt>
						<#if field.key && rF.key><#rt>			
				  		<td>{{data."${field.name}${rF.name}"}}</td>			
						<#elseif rF.key><#rt>			
				  		<td>{{data."${field.name}${rF.name}"}}</td>			
						<#elseif rF.core>			
				  		<td>{{data."${field.name}${rF.name}"}}</td>			
	    				</#if>
		  			</#list>
		  			<#break>		
				<#case "Cascade">
					<#list field.type.fields as rF><#rt>
						<#if field.key && rF.key><#rt>			
				  		<td>{{data."${field.name}${rF.name}"}}</td>			
						<#elseif rF.key><#rt>			
				  		<td>{{data."${field.name}${rF.name}"}}</td>			
						<#elseif rF.core>			
				  		<td>{{data."${field.name}${rF.name}"}}</td>			
	    				</#if>
		  			</#list>
		  			<#break>	
		  		</#switch>
			</#if></#list>
				
                                    <td >
                                      <span class="tip" >
                                          <a  title="Edit" >
                                              <img src="images/icon/icon_edit.png" >
                                          </a>
                                      </span> 
                                      <span class="tip" >
                                          <a id="1" class="Delete"  name="Band ring" title="Delete"  >
                                              <img src="images/icon/icon_delete.png" >
                                          </a>
                                      </span> 
                                        </td>
	        </tr>
      	</tbody>
      </table>
      </div>