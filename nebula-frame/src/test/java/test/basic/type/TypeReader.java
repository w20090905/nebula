package test.basic.type;


public class TypeReader {
//
//    private static final Log log = LogFactory.getLog(TypeReader.class);
//
//    final Store<String, Type> types;
//    final CtClass clzScala;
//
//    TypeReader(Store<String, Type> types, CtClass scala) {
//        this.types = types;
//        this.clzScala = scala;
//    }
//
//    Type fillFrom(CtClass clz, Type type) throws ClassNotFoundException, NotFoundException {
//        Object an = null;
//        an = clz.getAnnotation(DisplayName.class);
//        log.debug(type.name);
//        if (an != null) {
//            log.trace("DisplayName Annotation: " + an);
//            log.trace("DisplayName Annotation Value: " + ((DisplayName) an).value());
//            type.displayName = ((DisplayName) an).value();
//        } else {
//            type.displayName = type.getName();
//        }
//
//        log.trace("Start Prase - " + type.name);
//        log.trace("DisplayName: " + type.displayName);
//
//        // type.displayName = (an = clz.getAnnotation(DisplayName.class)) !=
//        // null ? ((DisplayName) an).value() : clz
//        // .getName();
//
//        // // Handle Frame Type
//        // boolean frameType = clz.getAnnotation(FrameType.class) != null;
//
//        type.master = Type.Master;
//
//        if (clz.getFields().length < 1) {
//            if (clz.isPrimitive() || clz.subtypeOf(clzScala)) {
//                type.master = Type.Scala;
//            } else {
//                type.master = Type.Underlying;
//            }
//            type.standalone = false;
//            return type;
//        }
//
//        log.trace("DeclaringClass: " + clz.getDeclaringClass());
//        if (clz.getDeclaringClass() != null) {
//            type.master = Type.Eembedded;
//            type.declaringType = types.readData(clz.getDeclaringClass().getName());
//            type.standalone = false;
//        } else {
//            type.standalone = true;
//        }
//
//        if (clz.getAnnotation(Master.class) != null) {
//            type.master = Type.Master;
//        } else if (clz.getAnnotation(Sequence.class) != null) {
//            type.master = Type.Sequence;
//        } else if (clz.getAnnotation(Attribute.class) != null) {
//            type.master = Type.Attribute;
//        }
//        log.trace("master: " + type.master);
//
//        if (clz.getAnnotation(Dependent.class) != null) {
//            type.standalone = false;
//            type.master = Type.Eembedded;
//        }
//
//        log.trace("standalone: " + type.standalone);
//
//        // Construct type
//        CtField[] cfs = clz.getFields();
//
//        // type = types.get(name);
//
//        log.trace("Fields length: " + cfs.length);
//        List<Field> fs = type.fields;
//        for (int i = 0; i < cfs.length; i++) {
//            if (!Modifier.isStatic(cfs[i].getModifiers())) {
//                fs.add(readTo(type, cfs[i]));
//            }
//        }
//
//        int countKey = 0;
//        for (Field f : fs) {
//            if (f.importance == Field.PrimaryKey)
//                countKey++;
//        }
//        log.trace("Before resign countKey: " + countKey);
//
//        // 如果没有Key的话,按字段名称寻找PrimaryKey中定义的可以默认作为Key的字段
//        if (countKey <= 0 && (an = PrimaryKey.class.getAnnotation(AutoWireByName.class)) != null) {
//            String autoWire = ((AutoWireByName) an).value();
//            for (Field f : fs) {
//                if (f.name.indexOf(autoWire) > 0) {
//                    f.importance = Field.PrimaryKey;
//                    countKey++;
//                    continue;
//                }
//            }
//        }
//
//        log.trace("After resign by AutoWireByName countKey: " + countKey);
//
//        // 如果没有Key的话,并且是独立实体的话,设定第一个字段为Key
//        if (fs.size() > 0 && type.standalone) {
//            if (countKey <= 0) {
//                fs.get(0).importance = Field.PrimaryKey;
//            }
//        }
//
//        return type;
//    }
//
//    protected Field readTo(Type resideType, CtField ctField) throws ClassNotFoundException, NotFoundException {
//        Object an = null;
//
//        // if (ctField.getName().equals("this$0")) {
//        // }
//
//        String name = ctField.getName();
//        log.trace("Start parse: " + name);
//
//        String displayName = (an = ctField.getAnnotation(DisplayName.class)) != null ? ((DisplayName) an).value()
//                : name;
//
//        log.trace("displayName: " + displayName);
//        /* Handle type */
//        CtClass fieldTypeClazz = ctField.getType();
//
//        Type fieldType = null;
//        boolean array = false;
//
//        /* construct field */
//        if (fieldTypeClazz.isArray()) {
//            log.trace("Is Array :" + fieldTypeClazz.getComponentType().getName());
//            array = true;
//            fieldType = types.readData(fieldTypeClazz.getComponentType().getName());
//        } else if (fieldTypeClazz.getName().equals(java.util.List.class.getName())
//                || fieldTypeClazz.getName().equals(noc.lang.List.class.getName())) {
//            log.trace("Is Generic field :" + fieldTypeClazz.getName());
//            // Generic field
//            array = true;
//            fieldType = types.readData(decorateActualTypeArguments(ctField).get(0));
//        } else {
//            log.trace("Normal :" + fieldTypeClazz.getName());
//            fieldType = types.readData(fieldTypeClazz.getName());
//        }
//
//        if (ctField.hasAnnotation(RealType.class)) {
//            fieldType = types.readData(((RealType) ctField.getAnnotation(RealType.class)).value().getName());
//            log.trace("change type to defined RealType :" + fieldType);
//        }
//
//        assert fieldType != null;
//
//        Field field = new Field(name, fieldType);
//        field.array = array;
//        field.displayName = displayName;
//
//        if (fieldType == resideType) {
//            field.refer = Field.Cascade;
//        } else if (fieldType.master == Type.Scala) {
//            field.refer = Field.Scala;
//        } else if (check(ctField, fieldType.name, Inline.class)) {
//            field.refer = Field.Inline;
//        } else {
//            field.refer = Field.Reference;
//        }
//        if (ctField.hasAnnotation(Reference.class))
//            field.refer = Field.Reference;
//
//        if (check(ctField, fieldTypeClazz, Important.class))
//            field.importance = Field.Important;
//        if (check(ctField, fieldTypeClazz, Core.class))
//            field.importance = Field.Core;
//        if (ctField.hasAnnotation(PrimaryKey.class))
//            field.importance = Field.PrimaryKey;
//
//        return field;
//    }
//
//    protected boolean check(CtField ctField, String typeName, Class<? extends Annotation> anClz)
//            throws ClassNotFoundException, NotFoundException {
//        boolean succeed = false;
//        Annotation an = null;
//
//        an = anClz.getAnnotation(AutoWireByName.class);
//        if (an != null) {
//            AutoWireByName au = (AutoWireByName) an;
//            if (au.value().indexOf(ctField.getName() + ";") >= 0) {
//                succeed = true;
//            }
//        }
//
//        an = anClz.getAnnotation(AutoWireByType.class);
//        if (an != null) {
//            AutoWireByType au = (AutoWireByType) an;
//            for (Class<?> c : au.value()) {
//                if (c.getName().equalsIgnoreCase(typeName)) {
//                    succeed = true;
//                }
//            }
//        }
//
//        succeed = ctField.getAnnotation(anClz) != null ? true : succeed;
//
//        return succeed;
//    }
//
//    protected boolean check(CtClass ctClass, String typeName, Class<? extends Annotation> anClz)
//            throws ClassNotFoundException, NotFoundException {
//        boolean succeed = false;
//        Annotation an = null;
//
//        an = anClz.getAnnotation(AutoWireByName.class);
//        if (an != null) {
//            AutoWireByName au = (AutoWireByName) an;
//            if (au.value().indexOf(ctClass.getName() + ";") >= 0) {
//                succeed = true;
//            }
//        }
//
//        an = anClz.getAnnotation(AutoWireByType.class);
//        if (an != null) {
//            AutoWireByType au = (AutoWireByType) an;
//            for (Class<?> c : au.value()) {
//                if (c.getName().equalsIgnoreCase(typeName)) {
//                    succeed = true;
//                }
//            }
//        }
//
//        succeed = ctClass.getAnnotation(anClz) != null ? true : succeed;
//
//        return succeed;
//    }
//
//    protected boolean check(CtField ctField, CtClass ctType, Class<? extends Annotation> anClz)
//            throws ClassNotFoundException, NotFoundException {
//        boolean succeed = false;
//        Annotation an = null;
//
//        an = anClz.getAnnotation(AutoWireByName.class);
//        if (an != null) {
//            AutoWireByName au = (AutoWireByName) an;
//            if (au.value().indexOf(ctField.getName() + ";") >= 0) {
//                succeed = true;
//            }
//        }
//
//        an = anClz.getAnnotation(AutoWireByType.class);
//        if (an != null) {
//            AutoWireByType au = (AutoWireByType) an;
//            for (Class<?> c : au.value()) {
//                if (c.getName().equalsIgnoreCase(ctType.getName())) {
//                    succeed = true;
//                }
//            }
//        }
//
//        succeed = ctType.getAnnotation(anClz) != null ? true : succeed;
//        succeed = ctField.getAnnotation(anClz) != null ? true : succeed;
//
//        return succeed;
//    }
//
//    protected ArrayList<String> decorateActualTypeArguments(CtField v) {
//
//        log.trace("v.getFieldInfo().getName()" + v.getFieldInfo().getName());
//
//        SignatureAttribute s = (SignatureAttribute) v.getFieldInfo().getAttribute(SignatureAttribute.tag);
//        assert s != null;
//
//        String sig = s.getSignature();
//        ArrayList<String> params = new ArrayList<String>();
//
//        int pos = 0;
//
//        assert sig.charAt(pos) == 'L';
//
//        pos++;
//
//        int start = pos;
//        while (sig.charAt(++pos) != '<')
//            ;
//
//        String typename = sig.substring(start, pos).replace('/', '.');
//
//        assert typename.equals(noc.lang.List.class.getName()) || typename.equals(java.util.List.class.getName());
//        pos++;
//
//        do {
//            String pam;
//            if (sig.charAt(pos) != 'L')
//                break;
//
//            pos++;
//
//            start = pos;
//            while (sig.charAt(++pos) != ';')
//                ;
//
//            pam = sig.substring(start, pos).replace('/', '.');
//            params.add(pam);
//            pos++;
//        } while (sig.charAt(pos) != '>');
//
//        return params;
//    }
}
