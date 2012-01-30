; T t
.class T
.field name
.field age
.field length

.def main: args=0, locals=3
; t = new T()
	struct r1, @T    ; hold t in r1
; t.x = 1
	iconst r2, 1
	fstore r2, r1, @T.age ; field 0
; t.y = "foo"
	sconst r2, "wangdsf sfdsf"
	fstore r2, r1, @T.name ; field 1
; print t.x
	fload r3, r1, @T.name ;#3
	print r3
	halt
