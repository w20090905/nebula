; T t
.class LoopCall
.field name

.def main: args=0, locals=8
; n = 100000000
	iconst r1, 10
; i = 0
	iconst r2, 0
; get 1 into a register
    iconst r3, 1
	
; while i<n:
start:
	ilt r5, r2, r1
	brf r5, done
;         i = i + 1
	iadd r2, r2,  r3
	
	call r6,@LoopCall.add(),r2

	br r0,start
done:
; print "looped "+n+" times."
	print r6
	halt

.def add: args=2, locals=1
	iadd r3,r1,r2
	ret r3,1
