
public class IBatisAutoGen {

    public static void main(String[] args) {


        //        // ID不允许更新
        //        // 实体类型校验（必须是ORMPojo）
        //        // 最好能考虑下updatable和deletable
        //        // 需要判断ID是否需要设置（在insert时是否需要设置）
        //
        //        Class<Player> c = Player.class;
        //        Method idMethod = null;
        //
        //        Table t = c.getAnnotation(Table.class);
        //        if (t == null) {
        //            return;
        //        }
        //        String tableName = t.name();
        //
        //        List<Method> columns = new ArrayList<Method>();
        //        for (Method m : c.getMethods()) {
        //            if (m.isAnnotationPresent(Id.class)) {
        //                idMethod = m;
        //            }
        //            if (m.isAnnotationPresent(Column.class)) {
        //                columns.add(m);
        //            }
        //        }
        //
        //
        //        StringBuilder sb = new StringBuilder("");
        //
        //        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>").append("\r\n");
        //        sb.append("<!DOCTYPE sqlMap PUBLIC \"-//ibatis.apache.org//DTD SQL Map 2.0//EN\" \"http://ibatis.apache.org/dtd/sql-map-2.dtd\">").append("\r\n");
        //        sb.append("\r\n");
        //        sb.append("<sqlMap namespace=\"").append(c.getSimpleName()).append("\">").append("\r\n");
        //        sb.append("\r\n");
        //
        //        // 写入resultMap
        //        sb.append("    ").append("<resultMap id=\"selectResult\" class=\"").append(c.getName()).append("\">").append("\r\n");
        //        for (Method m : columns) {
        //            Column column = m.getAnnotation(Column.class);
        //            sb.append("        ").append("<result property=\"").append(fieldName(m.getName())).append("\" column=\"").append(column.name()).append("\" />").append("\r\n");
        //        }
        //        sb.append("    ").append("</resultMap>").append("\r\n");
        //        sb.append("\r\n");
        //
        //        if (idMethod == null) {
        //            throw new RuntimeException();
        //        }
        //        String idName = fieldName(idMethod.getName());
        //
        //        // 写入selectById
        //        sb.append("    ").append("<select id=\"selectById\" parameterClass=\"long\" resultMap=\"selectResult\">").append("\r\n");
        //        sb.append("        ").append("SELECT * FROM ").append(tableName).append(" WHERE ID = #").append(idName).append("#").append("\r\n");
        //        sb.append("    ").append("</select>").append("\r\n");
        //        sb.append("\r\n");
        //
        //        // 写入selectByIds
        //
        //        // 写入selectAll
        //        sb.append("    ").append("<select id=\"selectAll\" parameterClass=\"long\" resultMap=\"selectResult\">").append("\r\n");
        //        sb.append("        ").append("SELECT * FROM ").append(tableName).append("\r\n");
        //        sb.append("    ").append("</select>").append("\r\n");
        //        sb.append("\r\n");
        //
        //        // 写入insert
        //        sb.append("    ").append("<insert id=\"insert\" parameterClass=\"").append(c.getName()).append("\">").append("\r\n");
        //        sb.append("        ").append("INSERT INTO ").append(tableName).append(" (").append("\r\n");
        //        for (int i = 0; i < columns.size(); i++) {
        //            Column column = columns.get(i).getAnnotation(Column.class);
        //            sb.append("            ").append(column.name());
        //            if (i < columns.size() - 1) {
        //                sb.append(",");
        //            }
        //            sb.append("\r\n");
        //        }
        //        sb.append("        ").append(") VALUES (").append("\r\n");
        //        for (int i = 0; i < columns.size(); i++) {
        //            Column column = columns.get(i).getAnnotation(Column.class);
        //            sb.append("            ").append("#").append(fieldName(columns.get(i).getName())).append("#");
        //            if (i < columns.size() - 1) {
        //                sb.append(",");
        //            }
        //            sb.append("\r\n");
        //        }
        //        sb.append("        ").append(");").append("\r\n");
        //        sb.append("        ").append("<selectKey resultClass=\"long\" keyProperty=\"id\">").append("\r\n");
        //        sb.append("            ").append("SELECT @@IDENTITY AS ID").append("\r\n");
        //        sb.append("        ").append("</selectKey>").append("\r\n");
        //        sb.append("    ").append("</insert>").append("\r\n");
        //        sb.append("\r\n");
        //
        //        // 写入update
        //        sb.append("    ").append("<update id=\"update\" parameterClass=\"").append(c.getName()).append("\">").append("\r\n");
        //        sb.append("        ").append("UPDATE").append("\r\n");
        //        sb.append("            ").append(tableName).append("\r\n");
        //        sb.append("        ").append("SET").append("\r\n");
        //        for (int i = 0; i < columns.size(); i++) {
        //            Column column = columns.get(i).getAnnotation(Column.class);
        //            sb.append("            ").append(column.name()).append(" = ").append("#").append(fieldName(columns.get(i).getName())).append("#");
        //            if (i < columns.size() - 1) {
        //                sb.append(",");
        //            }
        //            sb.append("\r\n");
        //        }
        //        sb.append("        ").append("WHERE").append("\r\n");
        //        sb.append("            ").append(idMethod.getAnnotation(Column.class).name()).append(" = #").append(fieldName(idMethod.getName())).append("\r\n");
        //        sb.append("    ").append("</update>").append("\r\n");
        //        sb.append("\r\n");
        //
        //        // 写入delete
        //        sb.append("    ").append("<update id=\"delete\" parameterClass=\"").append(c.getName()).append("\">").append("\r\n");
        //        sb.append("        ").append("UPDATE ").append(tableName).append(" SET IS_DELETED = 'Y' WHERE ").append(idMethod.getAnnotation(Column.class).name()).append(" = #").append(fieldName(idMethod.getName())).append("\r\n");
        //        sb.append("    ").append("</update>").append("\r\n");
        //        sb.append("\r\n");
        //
        //        sb.append("</sqlMap>").append("\r\n");
        //
        //
        //        System.out.println(sb);


    }

}
