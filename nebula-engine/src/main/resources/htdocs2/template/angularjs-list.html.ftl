[#ftl/]
<article class="module width_full">
	<header>
		<h1 class="tabs_involved">${type.name}</h1>
		<a href="#/d/${type.name}/!new" class="btn ctrl"><i class="icon-plus"></i></a>
		<input type="text" x-ng-model="query" class="input-medium search-query ctrl" placeholder="Filter">
	</header>
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th>#</th>
				[#list type.fields as field][#if !field.array && field.importance != "Unimportant"]
					[#switch field.refer]
					[#case "ByVal"]
				<th>${field.name}</th>
						[#break]
						
					[#case "Inline"]
						[#if field.key || field.core][#list field.type.fields as rF][#if field.key && rF.key]
				<th>${field.name}&nbsp;${rF.name}</th>
						[/#if][/#list][/#if]
						[#break]
						
					[#case "ByRef"]
						[#list field.type.fields as rF]
							[#if rF.key]	
				<th>${field.name}</th>
							[/#if]
						[/#list]
						[#break]
						
					[#case "Cascade"]
						[#list field.type.fields as rF]
							[#if rF.key]	
				<th>${field.name}</th>
							[/#if]
						[/#list]
						[#break]
					[/#switch]
				[/#if][/#list]
			</tr>
		</thead>
		<tbody>
			<tr x-ng-repeat="data in datalist | filter:query | orderBy:orderProp">
			<td>{{$index+1}}</td>
			[#list type.fields as field][#if !field.array]
				[#switch field.refer]
				[#case "ByVal"]
					[#if field.key]
			<td><a href="#/d/${type.name}/{{data.${field.name}}}">{{data["${field.name}"]}}</a></td>
					[#else]	
			<td>{{data["${field.name}"]}}</td>
					[/#if]
					[#break]
				[#case "Inline"]
					[#if field.key || field.core][#list field.type.fields as rF][#if field.key && rF.key]	
						<td>{{ data["${field.name}${rF.name}"] }}</td>
					[/#if][/#list][/#if]
					[#break]
				[#case "ByRef"]
					[#list field.type.fields as rF]
						[#if field.key && rF.key]
						<td>{{ data["${field.name}${rF.name}"] }}</td>
						[#elseif rF.key]
						<td>{{ data["${field.name}${rF.name}"] }}</td>
						[#elseif rF.core]
						<td>{{ data["${field.name}${rF.name}"] }}</td>
						[/#if]
					[/#list]
					[#break]	
				[#case "Cascade"]
					[#list field.type.fields as rF]
						[#if field.key && rF.key]
						<td>{{ data["${field.name}${rF.name}"] }}</td>
						[#elseif rF.key]
						<td>{{ data["${field.name}${rF.name}"] }}</td>
						[#elseif rF.core]
						<td>{{ data["${field.name}${rF.name}"] }}</td>
						[/#if]
					[/#list]
					[#break]
				[/#switch]
			[/#if][/#list]
			</tr>
		</tbody>
	</table>
</article>
