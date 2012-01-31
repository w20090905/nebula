; T t
.class T
.field name

.def main: args=0, locals=5
; n = 100000000
	iconst r1, 10000
; i = 0
	iconst r2, 0
; get 1 into a register
    iconst r4, 1
; j=0
    iconst r5, 0
; while i<n:
start:
	ilt r3, r2, r1
	brf r3, done
;         i = i + 1
	iadd r2, r4,  r2
;	j=j+1
	iadd r5, r4,  r5
	br start
done:
; print "looped "+n+" times."
	sconst r4, "done"
	print r5
	halt
