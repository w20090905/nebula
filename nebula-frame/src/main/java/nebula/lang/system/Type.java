package nebula.lang.system;

import java.util.ArrayList;
import java.util.List;

public class Type {

    public static final String Master = "Master";
    public static final String Attribute = "Attribute";
    public static final String Underlying = "Underlying";
    public static final String Sequence = "Sequence";
    public static final String Scala = "Scala";
    public static final String Eembedded = "Eembedded";

    @DisplayName("名称")
    String name;
    @DisplayName("显示名称")
    String displayName;

    @DisplayName("独立")
    boolean standalone = true;

    @DisplayName("实体类型")
    String master;

    @DisplayName("定义在")
    Type declaringType = null;

    @DisplayName("字段")
    List<Field> fields;

    // Type declaringType;

    public Type(String name) {
        super();
        this.name = name;

        this.fields = new ArrayList<Field>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public List<Field> getFields() {
        return fields;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public boolean isStandalone() {
        return standalone;
    }

    public void setStandalone(boolean standalone) {
        this.standalone = standalone;
    }

    public Type getDeclaringType() {
        return declaringType;
    }

    public void setDeclaringType(Type declaringType) {
        this.declaringType = declaringType;
    }

    public class Field {

        public static final String Scala = "Scala";
        public static final String Inline = "Inline";
        public static final String Reference = "Reference";
        public static final String Cascade = "Cascade";

        public static final String PrimaryKey = "PrimaryKey";
        public static final String Core = "Core";
        public static final String Important = "Important";
        public static final String Normal = "Normal";
        // importance

        @DisplayName("名称")
        String name;
        @DisplayName("显示名称")
        String displayName;
        @DisplayName("重要性")
        String importance = Normal;

        @DisplayName("类型")
        Type type;
        @DisplayName("数组")
        boolean array = false;

        @DisplayName("引用类型")
        String refer;

        public Field(String name, Type type) {
            super();
            this.name = name;
            this.displayName = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getImportance() {
            return importance;
        }

        public void setImportance(String importance) {
            this.importance = importance;
        }

        public Type getType() {
            return Type.this;
        }

        public boolean isArray() {
            return array;
        }

        public void setArray(boolean array) {
            this.array = array;
        }

        public String getRefer() {
            return refer;
        }

        public void setRefer(String refer) {
            this.refer = refer;
        }

    }
}
