.class T
.field name
.field age
.field length

.def main: args=0, locals=3
	struct r1, @T    ; hold t in r1
	iconst r2, 1
	fstore r1, @T.age, r2 ; field 0
; t.y = "foo"
	sconst r3, "wangdsf sfdsf"
	fstore r1, @T.name, r3 ; field 1
; print t.x
	fload r3, r1, @T.name ;#3
	print r3
	halt
	
.def sayHello: args=1,locals=3
; 
	sconst r1, "wangshilian"
	print r1
	ret r1,1
	
