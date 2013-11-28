package nebula.ui;

import java.util.ArrayList;
import java.util.List;

import nebula.lang.Field;
import nebula.lang.Type;

public class Layouter {

	// final private LoadingCache<Type, String> cachedRenderes;
	// final private Map<String, String> definedRenderes;

	public Layouter() {
		// definedRenderes = new HashMap<String, String>();
		// cachedRenderes = CacheBuilder.newBuilder().build(new
		// CacheLoader<Type, String>() {
		// public String load(Type type) throws Exception {
		// if (definedRenderes.containsKey(type.getName())) {
		// return type.getName();
		// }
		// Object formatType = type.getAttrs().delegate().get(Type.FormatType);
		// if (formatType != null &&
		// definedRenderes.containsKey(String.valueOf(formatType))) {
		// return String.valueOf(formatType);
		// }
		//
		// if (type.getSuperType() != null) {
		// return this.load(type.getSuperType());
		// }
		// return null;
		// }
		// });

	}

	// Master
	public PageView layoutMasterEdit(Type type) {
		PageView pageView = new PageView();

		// Basic Info Tab
		PageTab defaultTab = new PageTab();
		pageView.defaultTab = defaultTab;
		defaultTab.name = type.getName();
		defaultTab.description = type.getDisplayName();
		defaultTab.help = type.getDisplayName();

		List<PageField> pfs = new ArrayList<PageField>();

		// try {
		// int colSpan = 2;
		// int cntLeading = 0;
		// int cnt= 0;
		for (Field field : type.getFields()) {
			if (field.isArray()) {

				switch (field.getRefer()) {
				case ByVal:
					PageField pf = new PageField();
					pf.internalName = field.getName();
					pf.name = field.getDisplayName();
					pf.description = field.getDisplayName();
					pf.help = field.getDisplayName();

					pf.mandatory = field.isRequired();

					if (field.getAttrs().containsKey(Field.Heading)) {
						pf.heading = true;
					}
					if (field.getAttrs().containsKey(Field.SingleLine)) {
						pf.sameLine = false;
						pf.heading = true;
					}

					// Object formatType = field.getAttrs().
					// .delegate().get(Type.FormatType);
					// if (formatType != null &&
					// definedRenderes.containsKey(String.valueOf(formatType)))
					// {
					// pf.render = cachedRenderes.get(field.getType());
					// }
					// if (pf.render == null) {
					// pf.render = cachedRenderes.get(field.getType());
					// }

					pfs.add(pf);
					break;
				case Inline:

					break;

				default:
					break;
				}
				/*
				 * [#list type.fields as of][#t] [#if !of.array] [#switch
				 * of.refer]
				 * 
				 * [#case "Inline"] <!-- inline object --> <fieldset>
				 * <legend>${of.name}</legend> [#list of.type.fields as
				 * in1f][#t] [#if !in1f.array]<!-- --> [#switch in1f.refer]
				 * [#case "ByVal"] <!-- Type B1--> [@nc.controls field=in1f
				 * for="${of.name}${in1f.name}" label="${in1f.displayName}"]
				 * [@nc.inputBox field=in1f id="${of.name}${in1f.name}"
				 * ngModel="data.${of.name}.${in1f.name}"
				 * placeholder="${of.name} ${in1f.name}" required=!(of.ignorable
				 * || in1f.ignorable)/] [/@nc.controls] [#break] [#case
				 * "Inline"] <!-- Type B2--> [#list in1f.type.fields as
				 * in2f][#t] [#if !in2f.array && in2f.refer == "ByVal"] <!--
				 * Type <!-- C1 -->
				 * 
				 * [@nc.controls field=in2f
				 * for="${of.name}${in1f.name}${in2f.name}"
				 * label="${in1f.displayName}${in2f.displayName}"] [@nc.inputBox
				 * field=in2f id="${of.name}${in1f.name}${in2f.name}"
				 * ngModel="data.${of.name}.${in1f.name}${in2f.name}"
				 * placeholder="${of.name} ${in1f.name} ${in2f.name}"
				 * required=!(of.ignorable || in1f.ignorable ||
				 * in2f.ignorable)/] [/@nc.controls] [/#if] [/#list] [#break]
				 * [#case "ByRef"] <!-- Type B3 --> [#case "Cascade"] <!-- Type
				 * B4 -->
				 * 
				 * [@nc.controls field=in1f for="${of.name}${in1f.name}"
				 * label="${in1f.displayName}"] [@nc.popupBox field=in1f
				 * pField=in1f id="${of.name}${in1f.name}"
				 * ngModel="data.${of.name}.${in1f.name}"
				 * placeholder="${of.name} ${in1f.name}" readonly=true
				 * required=!(of.ignorable || in1f.ignorable)/] [/@nc.controls]
				 * [#break] [/#switch] [#else] [#switch in1f.refer] [#case
				 * "ByVal"] <!-- Type B5 -->
				 * 
				 * [@nc.controls field=in1f for="${of.name}${in1f.name}"
				 * label="${of.displayName} ${in1f.displayName}"] [@nc.inputBox
				 * field=in1f id="${of.name}${in1f.name}"
				 * ngModel="data.${of.name}.${in1f.name}"
				 * placeholder="${of.name}${in1f.name}" required=!(of.ignorable
				 * || in1f.ignorable) /] <!-- ngList --> [/@nc.controls]
				 * [#break] [#case "Inline"] <!-- Type B6 -->
				 * 
				 * <table class=
				 * "table table-hover table-striped table-bordered table-condensed"
				 * > <caption>${of.name}
				 * [{{data['${of.name}'].length}}]</caption> <thead> <tr> <th
				 * width="2em">#</th> [#list in1f.type.fields as in2f][#t] [#if
				 * !in2f.array && in2f.refer == "ByVal"] <!-- Type -->
				 * 
				 * <th>${in2f.name}</th>
				 * 
				 * [/#if] [/#list] </tr> </thead>
				 * 
				 * <tbody> <tr x-ng-repeat=
				 * "inlineData in data.${of.name}${in1f.name} | filter:query | orderBy:orderProp"
				 * > <td>#</td> [#list in1f.type.fields as in2f][#t] [#if
				 * !in2f.array && in2f.refer == "ByVal"] <!-- Type -->
				 * 
				 * <td>{{inlineData.${in2f.name}}}</td>
				 * 
				 * [/#if] [/#list] </tr> </tbody> </table>
				 * 
				 * [#break] [#case "ByRef"] <!-- Type B7 --> [#case "Cascade"]
				 * <!-- Type B8 --> throw new UnsupportedOperationException(
				 * "Refer Object cannot has array,must user inline array");
				 * [/#switch] [/#if] [/#list] </fieldset> [#break] [#case
				 * "ByRef"] <!-- Type A3 --> [#case "Cascade"] <!-- Type A4 -->
				 * [@nc.controls field=of for="${of.name}"
				 * label="${of.displayName}"] [@nc.popupBox field=of pField=of
				 * id="${of.name}" ngModel="data.${of.name}"
				 * placeholder="${of.name}" key=(of.key) readonly=true
				 * required=!(of.ignorable)/] [/@nc.controls] [#break]
				 * [/#switch] [#else] <!-- 数组不可以是Key --> [#switch of.refer]
				 * [#case "ByVal"] <!-- Basic Type Field --> <!-- Type A5 -->
				 * [@nc.controls field=of for="${of.name}"
				 * label="${of.displayName}"] [@nc.inputBox field=of
				 * id="${of.name}" ngModel="data.${of.name}"
				 * placeholder="${of.name}" required=!(of.ignorable)
				 * ex="x-ng-list"/] <!-- ngList --> [/@nc.controls] [#break]
				 * [#case "Inline"] <!-- inline object --> <!-- Type A6 -->
				 * <table class=
				 * "table table-hover table-striped table-bordered table-condensed"
				 * > <caption>${of.name}
				 * [{{data['${of.name}'].length}}]</caption> <thead> <tr> <th
				 * width="2em">#</th>
				 * 
				 * [#list of.type.fields as in1f][#t] [#if !in1f.array] [#switch
				 * in1f.refer] [#case "ByVal"] <!-- Type E1 -->
				 * 
				 * <th>${in1f.name}</th>
				 * 
				 * [#break] [#case "Inline"]<!-- Type E2 --> [#list
				 * in1f.type.fields as in2f][#t] [#if !in2f.array && in2f.refer
				 * == "ByVal"]
				 * 
				 * <th>${in1f.displayName} ${in2f.displayName}</th>
				 * 
				 * [/#if] [/#list] [#break] [#case "ByRef"]<!-- Type E3-->
				 * [#case "Cascade"]<!-- Type E4--> [#list in1f.type.fields as
				 * in2f][#t] [#if !in2f.array && in2f.refer == "ByVal" &&
				 * (in2f.key || in2f.core)]
				 * 
				 * <th>${in1f.displayName} ${in2f.displayName}</th>
				 * 
				 * [/#if] [/#list] [#break] [/#switch] [/#if] [/#list]
				 * <th>Actions</th> </tr> </thead> <tbody> <tr x-ng-repeat=
				 * "inlineData in data.${of.name} | filter:query | orderBy:orderProp"
				 * class="dataline-{{inlineData['_STS']}}">
				 * <td>{{$index+1}}</td>
				 * 
				 * [#list of.type.fields as in1f][#t] [#if !in1f.array] [#switch
				 * in1f.refer] [#case "ByVal"] <!-- Type E1 -->
				 * 
				 * <td>{{inlineData.${in1f.name}}}</td>
				 * 
				 * [#break] [#case "Inline"]<!-- Type E2 --> [#list
				 * in1f.type.fields as in2f][#t] [#if !in2f.array && in2f.refer
				 * == "ByVal"]
				 * 
				 * <td>{{inlineData.${in1f.name}${in2f.name}}}</td>
				 * 
				 * [/#if] [/#list] [#break] [#case "ByRef"]<!-- Type E3-->
				 * [#case "Cascade"]<!-- Type E4--> [#list in1f.type.fields as
				 * in2f][#t] [#if !in2f.array && in2f.refer == "ByVal" &&
				 * (in2f.key || in2f.core)]
				 * 
				 * <td>{{inlineData.${in1f.name}${in2f.name}}}</td>
				 * 
				 * [/#if] [/#list] [#break] [/#switch] [/#if] [/#list] <td><a
				 * x-ng-click="inlineData['_STS']='D';$event.preventDefault();">
				 * <i class="icon-minus-sign"> </i> </a></td> </tr>
				 */
			}
		}
		// } catch (ExecutionException e) {
		// throw new RuntimeException(e);
		// }

		return pageView;
	}

}
