package nebula.data.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.InlineView;

import nebula.lang.Field;
import nebula.lang.Reference;
import nebula.lang.Type;

public class DBSqlBuilder {

	final DbConfiguration config;

	public DBSqlBuilder(final DbConfiguration config, Type type) {
		this.config = config;
	}

	public List<DBTable>  makeMapping(Type type) {

		List<DBTable>  tables = new ArrayList<DBTable>();
		DBTable mainTable = new DBTable(type.getName());
		tables.add(mainTable);

		for (Field of : type.getFields()) {
//			if (of.isTransient()) continue;

			if (!of.isArray()) { // 一般类型，非列表
				switch (of.getRefer()) {
				case ByVal: // 最基本类型// Type A1
					/*************************************************************************************************/

					n("基本", of.getName(), of.getType().getName());
					mainTable.add(of.getName(), of.getName());

					/* ########################################################### */
					break;
				case Inline: // 嵌入，展开全部
					for (Field in1f : of.getType().getFields()) {
//						if (in1f.isTransient()) continue;// Skip when is Transient

						if (!in1f.isArray()) {// 展开全部项
							switch (in1f.getRefer()) {
							case ByVal: // Type B1 嵌入基本类型
								/*************************************************************************************************/

								n("嵌入->基本", of.getName(), of.getType().getName(), in1f.getName(), in1f.getType().getName());
								mainTable.add(ns(of.getName(), in1f.getName()), ns(of.getName(), in1f.getName()));

								/* ########################################################### */
								break;
							case Inline: // Type B2 嵌入的嵌入
								if (in1f.getRefer() == Reference.Inline) {// TODO 共同同一个表
									for (Field in2f : in1f.getType().getFields()) {
										if (!in2f.isArray() && in2f.getRefer() == Reference.ByVal) { // Type	 C1
											/*************************************************************************************************/										
											mainTable.add(ns(of.getName(), in1f.getName(), in2f.getName()), ns(of.getName(), in1f.getName(), in2f.getName()));
											n("嵌入->嵌入->关键字段", of.getName(), of.getType().getName(), in1f.getName(), in1f.getType().getName(), in2f.getName(),
													in2f.getType().getName());

											/* ########################################################### */
										}
									}
								} else { // 单独Table
									for (Field in2f : in1f.getType().getFields()) {
										if (!in2f.isArray() && in2f.getRefer() == Reference.ByVal) { // Type C1
											/*************************************************************************************************/
											mainTable.add(ns(of.getName(), in1f.getName(), in2f.getName()), ns(of.getName(), in1f.getName(), in2f.getName()));
											n("嵌入->嵌入->关键字段", of.getName(), of.getType().getName(), in1f.getName(), in1f.getType().getName(), in2f.getName(),
													in2f.getType().getName());

											/* ########################################################### */
										}
									}
								}
								break;
							case ByRef: // Type B3
							case Cascade: // Type B4
								for (Field in2f : in1f.getType().getFields()) {
									if (!in2f.isArray() && in2f.getRefer() == Reference.ByVal && (in2f.isKey() || in2f.isCore())) {
										/*************************************************************************************************/
										mainTable.add(ns(of.getName(), in1f.getName(), in2f.getName()), ns(of.getName(), in1f.getName(), in2f.getName()));

										n("嵌入->引用->关键字段", of.getName(), of.getType().getName(), in1f.getName(), in1f.getType().getName(), in2f.getName(), in2f
												.getType().getName());

										/* ########################################################### */
									}
								}
								break;
							}
						} else {
							switch (in1f.getRefer()) {
							case ByVal: // Type B5
								/*************************************************************************************************/
								mainTable.add(ns(of.getName(), in1f.getName()), ns(of.getName(), in1f.getName()));
								n("嵌入->基本(列表)", of.getName(), of.getType().getName(), in1f.getName(), in1f.getType().getName());

								/* ########################################################### */
								break;
							case Inline: // Type B6
								if (in1f.getRefer() == Reference.Inline) {// TODO
																			// 共同同一个表
									for (Field in2f : in1f.getType().getFields()) {
										if (!in2f.isArray() && in2f.getRefer() == Reference.ByVal) { // Type C1 D1
											/*************************************************************************************************/
											mainTable.add(ns(of.getName(), in1f.getName(), in2f.getName()), ns(of.getName(), in1f.getName(), in2f.getName()));

											n("嵌入->嵌入对象(列表)->基本", of.getName(), of.getType().getName(), in1f.getName(), in1f.getType().getName(),
													in2f.getName(), in2f.getType().getName());

											/* ########################################################### */
										}
									}
								} else { // 单独Table
									for (Field in2f : in1f.getType().getFields()) {
										if (!in2f.isArray() && in2f.getRefer() == Reference.ByVal) { // Type C1 D1
											/*************************************************************************************************/
											mainTable.add(ns(of.getName(), in1f.getName(), in2f.getName()), ns(of.getName(), in1f.getName(), in2f.getName()));

											n("嵌入->嵌入对象(列表)->基本", of.getName(), of.getType().getName(), in1f.getName(), in1f.getType().getName(),
													in2f.getName(), in2f.getType().getName());

											/* ########################################################### */
										}
									}
								}

								break;
							case ByRef: // Type B7
							case Cascade: // Type B8
								for (Field in2f : in1f.getType().getFields()) {
									if (!in2f.isArray() && in2f.getRefer() == Reference.ByVal && (in2f.isKey() || in2f.isCore())) {
										/*************************************************************************************************/
										mainTable.add(ns(of.getName(), in1f.getName(), in2f.getName()), ns(of.getName(), in1f.getName(), in2f.getName()));

										n("嵌入->引用->关键字段", of.getName(), of.getType().getName(), in1f.getName(), in1f.getType().getName(), in2f.getName(), in2f
												.getType().getName());

										/* ########################################################### */
									}
								}

							}
						}
					}

					break;
				case ByRef: // Type A3
				case Cascade: // Type A4
					for (Field in1f : of.getType().getFields()) {
						if (!in1f.isArray() && in1f.getRefer() == Reference.ByVal && (in1f.isKey() || in1f.isCore())) {
							/*************************************************************************************************/
							mainTable.add(ns(of.getName(), in1f.getName()), ns(of.getName(), in1f.getName()));

							n("引用->关键字段", of.getName(), of.getType().getName(), in1f.getName(), in1f.getType().getName());

							/* ########################################################### */
						}
					}
					break;
				}
			} else {// 数组不可以是Key
				switch (of.getRefer()) {
				case ByVal: // Basic Type Field // Type A5
					/*************************************************************************************************/
					n("列表.基本", of.getName(), of.getType().getName());
					mainTable.add(of.getName(), of.getName());

					break;
				case Inline: // inline object // Type A6

					for (Field in1f : of.getType().getFields()) {
//						if (in1f.isTransient()) continue;// Skip when is Transient

						if (!in1f.isArray()) {
							switch (in1f.getRefer()) {
							case ByVal: // Type E1
								/*************************************************************************************************/
								n("列表-嵌入->基本", of.getName(), of.getType().getName(), in1f.getName(), in1f.getType().getName());
								mainTable.add(ns(of.getName(), in1f.getName()), ns(of.getName(), in1f.getName()));

								/* ########################################################### */

								break;
							case Inline:// Type E2
								for (Field in2f : in1f.getType().getFields()) {
//									if (in2f.isTransient()) continue;// Skip when is Transient

									if (!in2f.isArray() && in2f.getRefer() == Reference.ByVal) {
										/*************************************************************************************************/
										mainTable.add(ns(of.getName(), in1f.getName(), in2f.getName()), ns(of.getName(), in1f.getName(), in2f.getName()));

										n("列表-嵌入->嵌入 ->基本字段", of.getName(), of.getType().getName(), in1f.getName(), in1f.getType().getName(), in2f.getName(),
												in2f.getType().getName());

										/* ########################################################### */

									}
								}
								break;
							case ByRef:// Type E3
							case Cascade:// Type E4
								for (Field in2f : in1f.getType().getFields()) {
									if (!in2f.isArray() && in2f.getRefer() == Reference.ByVal && (in2f.isKey() || in2f.isCore())) {
										/*************************************************************************************************/
										mainTable.add(ns(of.getName(), in1f.getName(), in2f.getName()), ns(of.getName(), in1f.getName(), in2f.getName()));

										n("列表-嵌入->引用->关键字段", of.getName(), of.getType().getName(), in1f.getName(), in1f.getType().getName(), in2f.getName(),
												in2f.getType().getName());
										/* ########################################################### */

									}
								}
								break;
							}
						}
					}

					break;
				case ByRef:
				case Cascade:
					for (Field in1f : of.getType().getFields()) {
						if (!in1f.isArray() && in1f.getRefer() == Reference.ByVal && (in1f.isKey() || in1f.isCore())) {
							/*************************************************************************************************/

							mainTable.add(ns(of.getName(), in1f.getName(), in1f.getName()), ns(of.getName(), in1f.getName()));
							n("列表-引用->关键字段", of.getName(), of.getType().getName(), in1f.getName(), in1f.getType().getName());

							/* ########################################################### */
						}
					}

				}

			}

		}
		return tables;
	}

	private String ns(String... names) {
		StringBuilder sb = new StringBuilder();
		sb.append(names[0]);
		for (int i = 1; i < names.length; i++) {
			sb.append("_");
			sb.append(names[i]);
		}
		return sb.toString();
	}

	private void n(String... argv) {
		StringBuilder sb = new StringBuilder();
		sb.append(argv[0]);
		for (int i = 1; i < argv.length; i += 2) {
			sb.append(" - ");
			sb.append(argv[i]);
			sb.append('(');
			sb.append(argv[i + 1]);
			sb.append(')');
		}

		System.out.println(sb);
	}
}
