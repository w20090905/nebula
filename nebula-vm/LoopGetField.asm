; T t
.class LoopGetField
.field name

.def main: args=0, locals=8
; n = 100000000
	iconst r1, 10000
; i = 0
	iconst r2, 0
; get 1 into a register
    iconst r4, 1
    iconst r5, 0	; j=0
	
	struct r6, @T    ; T t = new T()
	iconst r7, 717
	fstore r6, @T.age, r7 ; t.age = 1
	
; while i<n:
start:
	ilt r3, r2, r1
	brf r3, done
;         i = i + 1
	iadd r2, r4,  r2
	
	fload r8, r6, @T.age ; 
	;iadd r7, r4,  r2
	
	br r0,start
done:
; print "looped "+n+" times."
	sconst r4, "done"
	print r8
	halt