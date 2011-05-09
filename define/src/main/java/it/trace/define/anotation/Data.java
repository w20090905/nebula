package it.trace.define.anotation;

public @interface Data {
    @interface Length {
        int value();
    }

    @interface Required {
    }

    @interface Range {
        String[] value();
    }

    @interface DefaultValue {
        String value();
    }

    @interface ValueList {
        String[] value();
    }

    @interface BasicInfo {
    }

    @interface DetailInfo {
    }

    @interface DescriptionInfo {
    }

    @interface ExtendsInfo {
    }

}
